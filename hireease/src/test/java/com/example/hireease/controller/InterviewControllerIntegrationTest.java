package com.example.hireease.controller;

import com.example.hireease.model.Interview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InterviewControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void saveInterview_ShouldReturnSavedInterview() {
        // Arrange
        Interview interview = new Interview(null, "Technical Interview", new Date(), "Online", "Passed");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Interview> request = new HttpEntity<>(interview, headers);

        // Act
        ResponseEntity<Interview> response = restTemplate.postForEntity("/api/v1/interview", request, Interview.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Technical Interview", response.getBody().getInterviewName());
    }

    @Test
    void getInterview_ShouldReturnInterview_WhenExists() {
        // Arrange
        Interview interview = new Interview(null, "Technical Interview", new Date(), "Online", "Passed");
        Interview savedInterview = restTemplate.postForObject("/api/v1/interview", interview, Interview.class);

        // Act
        ResponseEntity<Interview> response = restTemplate.getForEntity("/api/v1/interview/" + savedInterview.getId(), Interview.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedInterview.getId(), response.getBody().getId());
    }

    @Test
    void getInterview_ShouldReturnNotFound_WhenDoesNotExist() {
        // Act
        ResponseEntity<Interview> response = restTemplate.getForEntity("/api/v1/interview/9999", Interview.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getInterviews_ShouldReturnListOfInterviews() {
        // Arrange
        Interview interview1 = new Interview(null, "Technical Interview", new Date(), "Online", "Passed");
        Interview interview2 = new Interview(null, "HR Interview", new Date(), "Offline", "Pending");
        restTemplate.postForObject("/api/v1/interview", interview1, Interview.class);
        restTemplate.postForObject("/api/v1/interview", interview2, Interview.class);

        // Act
        ResponseEntity<Interview[]> response = restTemplate.getForEntity("/api/v1/interviews", Interview[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 2);
    }

    @Test
    void updateInterview_ShouldReturnUpdatedInterview_WhenExists() {
        // Arrange
        Interview interview = new Interview(null, "Initial Interview", new Date(), "Online", "Pending");
        Interview savedInterview = restTemplate.postForObject("/api/v1/interview", interview, Interview.class);

        Map<String, Object> updates = new HashMap<>();
        updates.put("interviewName", "Updated Interview");
        updates.put("type", "Offline");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(updates, headers);

        // Act
        ResponseEntity<Interview> response = restTemplate.exchange("/api/v1/interview/" + savedInterview.getId(), HttpMethod.PATCH, request, Interview.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Interview", response.getBody().getInterviewName());
        assertEquals("Offline", response.getBody().getType());
    }

    @Test
    void updateInterview_ShouldReturnNotFound_WhenDoesNotExist() {
        // Arrange
        Map<String, Object> updates = new HashMap<>();
        updates.put("interviewName", "Updated Interview");
        updates.put("type", "Offline");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(updates, headers);

        // Act
        ResponseEntity<Interview> response = restTemplate.exchange("/api/v1/interview/9999", HttpMethod.PATCH, request, Interview.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteInterview_ShouldReturnDeletedInterview_WhenExists() {
        // Arrange
        Interview interview = new Interview(null, "Technical Interview", new Date(), "Online", "Passed");
        Interview savedInterview = restTemplate.postForObject("/api/v1/interview", interview, Interview.class);

        // Act
        ResponseEntity<Interview> response = restTemplate.exchange("/api/v1/interview/" + savedInterview.getId(), HttpMethod.DELETE, null, Interview.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedInterview.getId(), response.getBody().getId());
    }

    @Test
    void deleteInterview_ShouldReturnNotFound_WhenDoesNotExist() {
        // Act
        ResponseEntity<Interview> response = restTemplate.exchange("/api/v1/interview/9999", HttpMethod.DELETE, null, Interview.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
