package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    //getTasks
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")//Redone
    public List<TaskDto> getTasks() {
        List<Task> tasks = dbService.getAllTasksDb();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    //getTask
    @RequestMapping(method = RequestMethod.GET, value = "getTask")//Redone
    public TaskDto getTask(@RequestParam Long id) throws TaskNotFoundException {
        Task task = dbService.getTask(id).orElseThrow(TaskNotFoundException::new);
        return taskMapper.mapToTaskDto(task);
    }

    //deleteTask - DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")//Redone
    public void deleteTask(@RequestParam Long taskId) {
        dbService.deleteTask(taskId);
    }

    //updateTask
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")//Redone
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
        return taskDto; //taskMapper.mapToTaskDto(task);
    }
    //createTask
    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)//Redone
    public Task createTask(@RequestBody TaskDto taskDto) {
        Task task = new Task();
        task = taskMapper.mapToTask(taskDto);
        return dbService.saveTask(task);
    }
}