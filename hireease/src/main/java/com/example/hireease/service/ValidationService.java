package com.example.hireease.service;

import com.example.hireease.exception.CommonException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ValidationService {

    private final Validator validator;

    public <T> void validationRequest(T request) {

        if (request != null) {
            Set<ConstraintViolation<T>> result = validator.validate(request);
            if (!result.isEmpty()) {
                String resultValidations = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
                throw CommonException.builder().code(Code.REQUEST_VALIDATION_ERROR).message(resultValidations).httpStatus(HttpStatus.BAD_REQUEST).build();
            }
        }
    }

}
