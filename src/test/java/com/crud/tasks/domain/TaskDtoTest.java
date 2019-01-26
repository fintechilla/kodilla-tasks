package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskDtoTest {

    @Test
    public void getId() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_task_dto", "taskDto");
        //When
        String result = taskDto.getTitle();
        //Then
        assertEquals("test_task_dto", result);
    }

    @Test
    public void getTitle() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_task_dto", "taskDto");
        //When
        long result = taskDto.getId();
        //Then
        assertEquals(1L, result);
    }

    @Test
    public void getContent() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_task_dto", "taskDto");
        //When
        String result = taskDto.getContent();
        //Then
        assertEquals("taskDto", result);
    }
}