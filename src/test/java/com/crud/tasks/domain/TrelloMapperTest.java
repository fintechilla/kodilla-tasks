package com.crud.tasks.domain;

import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.facade.TrelloFacadeTestSuite;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrelloMapperTest {
    //@Mock
    TrelloMapper trelloMapper = new TrelloMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloMapperTest.class);

    @Test
    public void mapToBoards() {
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        List<TrelloBoard>trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("11", "test_board", trelloLists));

        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("12", "test_board_dto", trelloListDtos));

        //when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(trelloBoards);
        //When
        List<TrelloBoard>boardsActual = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        assertNotNull(boardsActual);
        assertEquals(1, boardsActual.size());
        assertEquals("12",boardsActual.get(0).getId());
    }
    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        //when(trelloMapper.mapToList(trelloListDtos)).thenReturn(trelloLists);
        //When
        List<TrelloList>trelloListListActual = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertNotNull(trelloListListActual);
        assertEquals(1, trelloListListActual.size());
        assertEquals("1", trelloListListActual.get(0).getId());
    }
    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));
        //When
        List<TrelloListDto>listDtoActual = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertNotNull(listDtoActual);
        assertEquals(1, listDtoActual.size());
        assertEquals("2", listDtoActual.get(0).getId());
        assertEquals("test_list", listDtoActual.get(0).getName());
    }
    @Test
    public void mapToCardDto() {
        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        TrelloCard trelloCard = new TrelloCard("test_card",
                "testing_card", "top", "2");
        //When
        TrelloCardDto cardDtoActual = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertNotNull(cardDtoActual);
        assertEquals("2", cardDtoActual.getIdList());
        assertEquals("test_card", cardDtoActual.getName());
    }

    @Test
    public void mapToCard() {
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card_dto",
                "testing_card_dto", "top", "1");
        //When
        TrelloCard cardActual= trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertNotNull(cardActual);
        assertEquals("1", cardActual.getListId());
        assertEquals("test_card_dto", cardActual.getName());

    }

    @Test
    public void mapToBoardsDto() {
        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        List<TrelloBoard>trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("11", "test_board", trelloLists));
        //When
        List<TrelloBoardDto>boardDtosActual = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertNotNull(boardDtosActual);
        assertEquals(1, boardDtosActual.size());
        assertEquals("11",boardDtosActual.get(0).getId());
        assertEquals("2", boardDtosActual.get(0).getLists().get(0).getId() );
    }
}