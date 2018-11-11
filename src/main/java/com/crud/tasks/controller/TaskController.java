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
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;
    //getTasks
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")//Redone
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasksDb();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    //getTaskById
    @RequestMapping(method = RequestMethod.GET, value = "getTaskById")//Redone
    public TaskDto getTaskById (@RequestParam Long id){
        return taskMapper.mapToTaskDto(service.getTaskByIdDb(id));
    }
    //getTask
    @RequestMapping(method = RequestMethod.GET, value = "getTask")//Redone
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException:: new));
    }
    //deleteTask - DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")//Redone
    public void deleteTask(@RequestParam Long id) {
        service.deleteTask(id);
    }
    //updateTask
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")//Redone
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }
    //createTask
    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)//Redone
    public void createTask(@RequestBody TaskDto taskDto){
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}