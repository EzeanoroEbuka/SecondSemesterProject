package com.africa.semicolon.services;

import com.africa.semicolon.dtos.requests.CreateTaskRequest;
import com.africa.semicolon.dtos.requests.UpdateRequest;
import com.africa.semicolon.dtos.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService.clearAllTask();
    }
    @Test
    public void testForNoTaskAddedYet() {
        assertTrue(taskService.reviewAllTask().getTasks().isEmpty());
    }
    @Test
    public void testThatATaskIsCratedAndSaved() {

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(true);
        request.setDueDate("12/8/2024");
        CreateTaskResponse response = taskService.createNewTask(request);
        assertEquals(response.getTitle(),"PhaseGate project");
        assertTrue(response.isImportant());

    }

    @Test
    public void testThatATaskIsRemindedAtTwoDaysInterval() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(false);
        request.setDueDate("12/8/2024");
        CreateTaskResponse response = taskService.createNewTask(request);
        assertEquals(response.getTitle(),"PhaseGate project");
        ReminderResponse remind = taskService.taskReminder(request.getTitle());
        assertEquals(remind.getMessage(),"Reminder!!!.. Task is not yet completed");
    }

    @Test
    public void testThatATaskCanBeUpdated() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(false);
        request.setDueDate("12/8/2024");
        CreateTaskResponse response = taskService.createNewTask(request);
        assertEquals(response.getTitle(),"PhaseGate project");
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setTitle("LIfe project");
        updateRequest.setDescription("maven project must be completed before I Eat");
        updateRequest.setImportant(true);
        updateRequest.setComplete(true);
        updateRequest.setDueDate("12/8/2024");
        UpdateResponse updateResponse = taskService.updateTask(updateRequest,response.getTitle());
        assertEquals(updateResponse.getMessage(),"Task Updated Successfully");
        assertEquals(updateResponse.getTitle(),updateRequest.getTitle());
        assertEquals(updateResponse.isImportant(),updateRequest.isImportant());
        assertEquals(updateResponse.isCompleted(),updateRequest.isComplete());

    }

    @Test
    public void testThatATaskCanBeDeleted() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(false);
        request.setDueDate("12/8/2024");
        CreateTaskResponse response = taskService.createNewTask(request);
        assertEquals(response.getTitle(),"PhaseGate project");
        DeleteTaskResponse deleteResponse = taskService.deleteTask("PhaseGate project");
        assertEquals(deleteResponse.getTitle(),request.getTitle());
    }

    @Test
    public void testThatATaskCanBeReviewed() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(false);
        request.setDueDate("12/8/2024");
        CreateTaskResponse response = taskService.createNewTask(request);
        assertEquals(response.getTitle(),"PhaseGate project");
        TaskReviewResponse reviewResponse = taskService.reviewTask(request.getTitle());
        assertEquals(reviewResponse.getTitle(),request.getTitle());
    }

    @Test
    public void testThatAllTaskCanBeReviewed() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(false);
        request.setDueDate("12/8/2024");
        taskService.createNewTask(request);
        CreateTaskRequest request2 = new CreateTaskRequest();
        request2.setTitle("Tomorrow Service");
        request2.setDescription("The Service Begins By 7:30am I Must Leave BY 6:00am");
        request2.setImportant(true);
        request2.setDueDate("12/8/2024");
        taskService.createNewTask(request2);
        ReviewAllTaskResponse reviewAll = (taskService.reviewAllTask());
        assertEquals("This Are All Your task", taskService.reviewAllTask().getMessage());
        assertEquals(2,reviewAll.getTasks().size());
    }

    @Test
    public void testThatAllTaskCanBeDeleted() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("PhaseGate project");
        request.setDescription("maven project must be completed before creating a new task");
        request.setImportant(false);
        request.setDueDate("12/8/2024");
        taskService.createNewTask(request);
        CreateTaskRequest request2 = new CreateTaskRequest();
        request2.setTitle("Tomorrow Service");
        request2.setDescription("The Service Begins By 7:30am I Must Leave BY 6:00am");
        request2.setImportant(true);
        request2.setDueDate("12/8/2024");
        taskService.createNewTask(request2);
        DeleteAllTaskResponse deleteAll = taskService.clearAllTask();
        assertEquals("All tasks have been Deleted Successfully", deleteAll.getMessage());
        assertEquals(0,taskService.reviewAllTask().getTasks().size());
    }
}


