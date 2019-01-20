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

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrelloMapperTest {
    @Mock
    TrelloMapper trelloMapper;

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

        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(trelloBoards);
        //When
        List<TrelloBoard>boardsActual = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        assertNotNull(boardsActual);
        assertEquals(1, boardsActual.size());
        assertEquals("11",boardsActual.get(0).getId());
    }
    @Test
    public void mapToBoardsDto() {
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        List<TrelloBoard>trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("11", "test_board", trelloLists));

        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("12", "test_board_dto", trelloListDtos));

        when(trelloMapper.mapToBoardsDto(trelloBoards)).thenReturn(trelloBoardDtos);
        List<TrelloBoardDto>boardDtosActual = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertNotNull(boardDtosActual);
        assertEquals(1, boardDtosActual.size());
        assertEquals("12",boardDtosActual.get(0).getId());
    }
    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        when(trelloMapper.mapToList(trelloListDtos)).thenReturn(trelloLists);
        //When
        List<TrelloList>trelloListListActual = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertNotNull(trelloListListActual);
        assertEquals(1, trelloListListActual.size());
        assertEquals("2", trelloListListActual.get(0).getId());
    }
    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("3", "test_list_dto", false));
        when(trelloMapper.mapToListDto(trelloLists)).thenReturn(trelloListDtos);
        //When
        List<TrelloListDto>listDtoActual = trelloMapper.mapToListDto(trelloLists);
        System.out.println("listDtoActual.get id: " + listDtoActual.get(0).getId());
        //Then
        assertNotNull(listDtoActual);
        assertEquals(1, listDtoActual.size());
        assertEquals("3", listDtoActual.get(0).getId());
        assertEquals("test_list_dto", listDtoActual.get(0).getName());
    }

    @Test
    public void mapToCardDto() {
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        TrelloCard trelloCard = new TrelloCard("test_card", "testing_card", "top", "2");

        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card_dto", "testing_card_dto", "top", "1");

        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        //When
        TrelloCardDto cardDtoActual = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertNotNull(cardDtoActual);
        assertEquals("1", cardDtoActual.getIdList());
        assertEquals("test_card_dto", cardDtoActual.getName());
    }

    @Test
    public void mapToCard() {
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        TrelloCard trelloCard = new TrelloCard("test_card", "testing_card", "top", "2");

        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card_dto", "testing_card_dto", "top", "1");

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        //When
        TrelloCard cardActual= trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertNotNull(cardActual);
        assertEquals("2", cardActual.getListId());
        assertEquals("test_card", cardActual.getName());

    }
}