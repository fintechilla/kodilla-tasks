package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
//@RunWith(MockitoJUnitRunner.class)
public class TrelloTest {
//    @InjectMocks
//    Trello trello;
//    @Mock
//    application.properties properties;
    @Test
    public void getBoard() {
        //Given
        Trello trello = new Trello();
        //When
        Integer result = trello.getBoard();
        //Then
        assertEquals(null, result);
    }

    @Test
    public void getCard() {
        //Given
        Trello trello = new Trello();
        //When
        String result = trello.getCard();
        //Then
        assertEquals(null, result);
    }
}