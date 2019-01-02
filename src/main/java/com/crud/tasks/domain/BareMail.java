package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BareMail {
    private String mailTo;
    private String subject;
    private String message;
}
