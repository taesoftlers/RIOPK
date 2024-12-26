# RIOPK
# Программное средство подбора персонала в условиях дефицита.

## Архитектура

### Нотация С4

Контейнерный уровень архитектуры в нотации С4
![контейнерный уровень](https://github.com/user-attachments/assets/2f26aa90-4e21-46e1-867d-852e94371621)


Компонентный уровень архитектуры в нотации С4
![компонентный уровень](https://github.com/user-attachments/assets/3d152296-9b1c-4838-a3bd-aa97e4f5a15c)

### Структурные диаграммы

#### Диаграмма классов

Диаграмма классов программного средства
![диаграмма классов](https://github.com/user-attachments/assets/ee0f1599-b5fb-4960-bcba-5b194ede6a0d)

#### Диаграмма компонентов

Диаграмма компонентов программного средства
![компоненты](https://github.com/user-attachments/assets/2f13492c-d15f-44d9-a587-40c4be312a2e)

#### Диаграмма развертывания

Диаграмма развертывания программного средства
![развертывание](https://github.com/user-attachments/assets/19c15713-291d-4495-b91e-cb943d514d2f)

### Поведенческие диаграммы

#### Диаграмма вариантов использования

Диаграмма вариантов использования программного средства
![диаграмма ви](https://github.com/user-attachments/assets/fd3a5fbb-f27a-4005-b3ff-20168bea5e7e)

#### Диаграмма деятельности

Диаграмма деятельности программного средства
![деятельность  весь процесс](https://github.com/user-attachments/assets/d96e3167-0721-44d7-a33c-f169d1c66c1b)

#### Диаграмма последовательности

Диаграмма последовательности программного средства
![последовательность](https://github.com/user-attachments/assets/7dbafab8-6bcc-4277-adaa-a7a7d5d4fc9c)

#### Диаграмма состояний (резюме)

Диаграмма состояний (резюме) программного средства
![состояний  резюме](https://github.com/user-attachments/assets/3f890cf0-8268-4dbb-9630-b6bbceb83c92)


### Схема данных

Схема данных программного средства
![физическая модель](https://github.com/user-attachments/assets/053c596d-c4b4-4aee-aba3-f9ad60430e00)


## Пользовательский интерфейс

### Примеры экранов UI

![image](https://github.com/user-attachments/assets/b17c6434-387a-47f8-8210-823f21f48a86)

![image](https://github.com/user-attachments/assets/fa66a032-a5bd-4ad2-931c-f819c659f9a6)

![image](https://github.com/user-attachments/assets/6f5e13b8-1ff7-4498-90de-701beab0a858)

![image](https://github.com/user-attachments/assets/1f9a3a0c-c550-4eef-a284-22e7c77350b9)

![image](https://github.com/user-attachments/assets/033c0085-8a1f-48ea-a9c4-5a0e9b689f87)

![image](https://github.com/user-attachments/assets/1155b5ae-326e-4715-aa3a-f8854e621ebd)

![image](https://github.com/user-attachments/assets/accdf24d-9258-438a-8bb1-72a84561f9a0)

UI Kit для программного средства 
![ui kit](https://github.com/user-attachments/assets/d6e4c41d-c6ef-4dc8-b5ee-086148b92cb2)

### User-flow диаграммы

User-flow диаграмма программного средства (Hr-менеджер)
![user-flow (hr-менеджер)](https://github.com/user-attachments/assets/323eb2c6-7cc2-406d-8807-779e4ece5eae)


User-flow диаграмма программного средства (Руководитель отдела)
![user-flow (руководитель)](https://github.com/user-attachments/assets/21c42c37-4900-4e7d-ac1d-9140f3556893)


User-flow диаграмма программного средства (Технический специалист)
![user-flow (тех  специалист)](https://github.com/user-attachments/assets/e3b177af-f299-46b8-adbc-cfdfe7b8c81d)


## Документация

![image](https://github.com/user-attachments/assets/15b6bf92-3c6a-4666-8f04-d6c9b206a638)

![image](https://github.com/user-attachments/assets/ee946775-dd2c-4a44-b515-b29b470c0ee5)

![image](https://github.com/user-attachments/assets/84ac28a5-8e28-4b61-969c-518ec3131009)

![image](https://github.com/user-attachments/assets/1613c9ab-facb-4281-8149-22baeefc55e2)

## Ссылка на документацию

https://github.com/taesoftlers/RIOPK_HiringCandidates/blob/main/API%20Documentation

## Тестирование

### Unit-тест для проверки создания резюме

    @Test
    void saveResume_ShouldReturnCreatedResume() throws Exception {
        Resume resume = new Resume();
        resume.setResumeID(1L);
        resume.setResumeFile(mock(Blob.class));

        when(resumeService.saveResume(resume)).thenReturn(resume);

        ResponseEntity<Resume> response = resumeController.saveResume(resume);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(resume, response.getBody());
        verify(resumeService, times(1)).saveResume(resume);
    }

![image](https://github.com/user-attachments/assets/e7c66479-bd76-4aa0-8fd6-a71ecf1fd763)

### Интеграционный тест для проверки сохранения резюме

    @Test
    public void testSaveResume() throws Exception {
        Resume resume = new Resume();
        resume.setResumeFile(null); // Example: You can replace null with a Blob for actual testing

        mockMvc.perform(post("/api/v1/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resume)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.resumeID", notNullValue()));
    }

![image](https://github.com/user-attachments/assets/eb7b4d64-b83e-488a-998d-684769b32403)

## Безопасность

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomLogoutHandler logoutHandler;

    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomLogoutHandler logoutHandler) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/login/**", "/register/**", "/refresh_token/**", "/activate/*", "/**")
                                .permitAll()
                                .requestMatchers("/hr_manager/**").hasAuthority("HRManager")
                                .requestMatchers("/director/**").hasAuthority("HRManager")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailsServiceImp)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        e -> e.accessDeniedHandler(
                                        (request, response, accessDeniedException) -> response.setStatus(403)
                                )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(l -> l
                        .logoutUrl("/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                        ))
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

## Развертывание

Dockerfile для сервиса на Java:

### Используем официальный образ OpenJDK
FROM openjdk:11-jre-slim

### Устанавливаем рабочую директорию
WORKDIR /app

### Копируем JAR файл приложения
COPY target/my-java-app.jar app.jar

### Указываем команду для запуска приложения
CMD ["java", "-jar", "app.jar"]
Docker-compose:

version: '3.9'

services:
  app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      - DB_HOST=postgres
      - DB_USER=app_user
      - DB_PASSWORD=app_password
      - DB_NAME=app_db
      - REDIS_HOST=redis
      - NATS_URL=nats:4222
    depends_on:
      - postgres
      - redis
      - nats

  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: app_user
      POSTGRES_PASSWORD: app_password
      POSTGRES_DB: app_db
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  redis:
    image: redis:7
    ports:
      - "6379:6379"

  nats:
    image: nats:2.9
    ports:
      - "4222:4222"
    environment:
      - JS_ENABLE=true # Включение JetStream

volumes:
  postgres_data:
 
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/mydb
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - db

  db:
    image: mysql:8.0
    restart: always
    environment:
      MYSQL_DATABASE: mydb
      MYSQL_ROOT_PASSWORD: password
    ports:
      - "3306:3306"

