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

UI Kit для программного средства 
![ui kit](https://github.com/user-attachments/assets/d6e4c41d-c6ef-4dc8-b5ee-086148b92cb2)

### User-flow диаграммы

User-flow диаграмма программного средства (Hr-менеджер)
![user-flow (hr-менеджер)](https://github.com/user-attachments/assets/6b9ebed4-f358-4e4a-b9ec-d99b956174a0)


User-flow диаграмма программного средства (Руководитель отдела)
![user-flow (руководитель)](https://github.com/user-attachments/assets/e51a35dc-ea9a-4168-aede-e873dcc49871)


User-flow диаграмма программного средства (Технический специалист)
![user-flow (техспец)](https://github.com/user-attachments/assets/a0b1e9b6-b7af-4647-9301-e51f6bdf1a51)

## Документация

![image](https://github.com/user-attachments/assets/15b6bf92-3c6a-4666-8f04-d6c9b206a638)

![image](https://github.com/user-attachments/assets/ee946775-dd2c-4a44-b515-b29b470c0ee5)

![image](https://github.com/user-attachments/assets/84ac28a5-8e28-4b61-969c-518ec3131009)

![image](https://github.com/user-attachments/assets/1613c9ab-facb-4281-8149-22baeefc55e2)

## Ссылка на документацию

[http://localhost:8080/swagger-ui/index.html#/](https://github.com/taesoftlers/RIOPK_HiringCandidates/blob/main/API%20Documentation)

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

