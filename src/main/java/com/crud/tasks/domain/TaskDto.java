package com.crud.tasks.domain;

import jdk.nashorn.internal.objects.annotations.Constructor;
//import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;

//    public TaskDto() {
//    }
//
//    public TaskDto(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getContent() {
//        return content;
//    }
}
