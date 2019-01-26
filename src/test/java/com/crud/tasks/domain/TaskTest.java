package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void getId() {
        //Given
        Task task = new Task(1L, "test_task", "task");
        //When
        String result = task.getTitle();
        //Then
        assertEquals("test_task", result);
    }

    @Test
    public void getTitle() {
        //Given
        Task task = new Task(1L, "test_task", "task");
        //When
        String result = task.getContent();
        //Then
        assertEquals("task", result);
    }

    @Test
    public void getContent() {
        //Given
        Task task = new Task(1L, "test_task", "task");
        //When
        long result = task.getId();
        //Then
        assertEquals(1L, result);
    }
}