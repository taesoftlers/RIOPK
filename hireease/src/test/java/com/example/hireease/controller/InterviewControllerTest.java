package com.example.hireease.controller;

import com.example.hireease.model.Interview;
import com.example.hireease.service.InterviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InterviewControllerTest {

    @InjectMocks
    private InterviewController interviewController;

    @Mock
    private InterviewService interviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveInterview_ShouldReturnSavedInterview() {
        // Arrange
        Interview interview = new Interview(null, "Technical Interview", new Date(), "Online", "Passed");
        when(interviewService.saveInterview(interview)).thenReturn(interview);

        // Act
        ResponseEntity<Interview> response = interviewController.saveInterview(interview);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(interview, response.getBody());
        verify(interviewService, times(1)).saveInterview(interview);
    }

    @Test
    void getInterview_ShouldReturnInterview_WhenExists() {
        // Arrange
        Long id = 1L;
        Interview interview = new Interview(id, "Technical Interview", new Date(), "Online", "Passed");
        when(interviewService.getInterview(id)).thenReturn(interview);

        // Act
        ResponseEntity<Interview> response = interviewController.getInterview(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(interview, response.getBody());
        verify(interviewService, times(1)).getInterview(id);
    }

    @Test
    void getInterview_ShouldReturnNotFound_WhenDoesNotExist() {
        // Arrange
        Long id = 1L;
        when(interviewService.getInterview(id)).thenThrow(new RuntimeException("Not Found"));

        // Act
        ResponseEntity<Interview> response = interviewController.getInterview(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(interviewService, times(1)).getInterview(id);
    }

    @Test
    void getInterviews_ShouldReturnListOfInterviews() {
        // Arrange
        List<Interview> interviews = new ArrayList<>();
        interviews.add(new Interview(1L, "Technical Interview", new Date(), "Online", "Passed"));
        interviews.add(new Interview(2L, "HR Interview", new Date(), "Offline", "Pending"));
        when(interviewService.getInterviews()).thenReturn(interviews);

        // Act
        List<Interview> response = interviewController.getInterviews();

        // Assert
        assertEquals(2, response.size());
        assertEquals(interviews, response);
        verify(interviewService, times(1)).getInterviews();
    }

    @Test
    void updateInterview_ShouldReturnUpdatedInterview_WhenExists() {
        // Arrange
        Long id = 1L;
        Interview interview = new Interview(null, "Updated Interview", new Date(), "Online", "Passed");
        Interview updatedInterview = new Interview(id, "Updated Interview", new Date(), "Online", "Passed");
        when(interviewService.updateInterview(id, interview)).thenReturn(updatedInterview);

        // Act
        ResponseEntity<Interview> response = interviewController.updateInterview(id, interview);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedInterview, response.getBody());
        verify(interviewService, times(1)).updateInterview(id, interview);
    }

    @Test
    void updateInterview_ShouldReturnNotFound_WhenDoesNotExist() {
        // Arrange
        Long id = 1L;
        Interview interview = new Interview(null, "Updated Interview", new Date(), "Online", "Passed");
        when(interviewService.updateInterview(id, interview)).thenThrow(new RuntimeException("Not Found"));

        // Act
        ResponseEntity<Interview> response = interviewController.updateInterview(id, interview);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(interviewService, times(1)).updateInterview(id, interview);
    }

    @Test
    void deleteInterview_ShouldReturnDeletedInterview_WhenExists() {
        // Arrange
        Long id = 1L;
        Interview interview = new Interview(id, "Technical Interview", new Date(), "Online", "Passed");
        when(interviewService.deleteInterview(id)).thenReturn(interview);

        // Act
        ResponseEntity<Interview> response = interviewController.deleteInterview(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(interview, response.getBody());
        verify(interviewService, times(1)).deleteInterview(id);
    }

    @Test
    void deleteInterview_ShouldReturnNotFound_WhenDoesNotExist() {
        // Arrange
        Long id = 1L;
        when(interviewService.deleteInterview(id)).thenThrow(new RuntimeException("Not Found"));

        // Act
        ResponseEntity<Interview> response = interviewController.deleteInterview(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(interviewService, times(1)).deleteInterview(id);
    }
}
