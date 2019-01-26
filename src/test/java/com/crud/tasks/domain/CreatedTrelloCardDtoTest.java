package com.crud.tasks.domain;

import javafx.beans.binding.When;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatedTrelloCardDtoTest {
    //Given
    Trello trello = new Trello(1, "1");
    AttachmentByType attachment = new AttachmentByType(trello);
    Badges badges = new Badges(2, attachment);
    //CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "test_created_card_dto", "http://test.com", badges);

    @Test
    public void getId() {
        //When
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "test_created_card_dto", "http://test.com", badges);
        //Then
        assertEquals("1", cardDto.getId());
    }

    @Test
    public void getName() {
        //When
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "test_created_card_dto", "http://test.com", badges);
        //Then
        assertEquals("test_created_card_dto", cardDto.getName());
    }

    @Test
    public void getShortUrl() {
        //When
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "test_created_card_dto", "http://test.com", badges);
        //Then
        assertEquals("http://test.com", cardDto.getShortUrl());
    }

    @Test
    public void getBadges() {
        //When
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "test_created_card_dto", "http://test.com", badges);
        //Then
        assertEquals( "2", cardDto.getBadges().getVotes().toString());
    }
}