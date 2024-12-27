package com.example.hireease.service;

import com.example.hireease.exception.CommonException;
import com.example.hireease.model.AuthenticationResponse;
import com.example.hireease.model.Employee;
import com.example.hireease.model.Token;
import com.example.hireease.model.User;
import com.example.hireease.repository.EmployeeRepository;
import com.example.hireease.repository.TokenRepository;
import com.example.hireease.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService {

    @Value("${application.security.jwt.access-token-expiration}")
    private long accessTokenExpire;

    @Value("${application.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpire;

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    private final ValidationService validationService;

    @Autowired
    private final MailSender mailSender;

    public AuthenticationService(EmployeeRepository employeeRepository,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager,
                                 ValidationService validationService,
                                 MailSender mailSender) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.validationService = validationService;
        this.mailSender = mailSender;
    }

    public AuthenticationResponse register(User request) {
        try {
            validationService.validationRequest(request);
        } catch (CommonException e) {
            return new AuthenticationResponse(e.getMessage());
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null, "User already exist");
        } else if (employeeRepository.findByUsername(request.getUsername()) != null) {
            Employee employee = employeeRepository.findByUsername(request.getUsername());
            employee.setRegistrationDate(new Date(System.currentTimeMillis()));
            employeeRepository.save(employee);

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRoles(employee.getRoles());
            user.setIsActive(false);
            user.setActivationCode(UUID.randomUUID().toString());

            user = userRepository.save(user);

            if (!StringUtils.isEmpty(employee.getEmail())) {
                String message = String.format(
                        "Hello, %s! \n" +
                                "Please, visit next link to activate your account: http://localhost:8080/activate/%s",
                        user.getUsername(),
                        user.getActivationCode()
                );

                mailSender.send(employee.getEmail(), "Activation link", message);
            }

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            saveUserToken(accessToken, refreshToken, user);

            return new AuthenticationResponse(accessToken, refreshToken, "User registration was successful");
        } else {
            return new AuthenticationResponse(null, null, "Employee with this username doesn't exist");
        }
    }

    public AuthenticationResponse authenticate(User request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        Employee employee = employeeRepository.findByUsername(request.getUsername());

        if (!user.getIsActive()) {
            return new AuthenticationResponse("The account is not activated. Please follow the link sent by email");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        employee.setLastLoginDate(new Date(System.currentTimeMillis()));
        employeeRepository.save(employee);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, "User login was successful");

    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getUserId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> t.setLoggedOut(true));

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        token.setCreatedAt(new Date(System.currentTimeMillis()));
        token.setAccessTokenExpiredAt(new Date(System.currentTimeMillis() + accessTokenExpire));
        token.setRefreshTokenExpiredAt(new Date(System.currentTimeMillis() + refreshTokenExpire));
        tokenRepository.save(token);
    }

    public ResponseEntity refreshToken(HttpServletRequest request,
                                       HttpServletResponse response) {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));

        if (jwtService.isValidRefreshToken(token, user)) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken, "New token generated"), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setIsActive(true);
        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }
}
