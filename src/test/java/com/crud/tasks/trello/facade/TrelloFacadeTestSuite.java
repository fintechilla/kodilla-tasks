package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite{
    @InjectMocks
    TrelloFacade trelloFacade;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    TrelloMapper trelloMapper;
    @Mock
    TrelloService trelloService;
    @Mock
    TrelloValidator trelloValidator;
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacadeTestSuite.class);
    @Test
    public void shouldFetchEmptyList() throws URISyntaxException {
        //Given
        List<TrelloListDto>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","test_list", false));

        List<TrelloBoardDto>trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList>mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard>mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test",mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        System.out.println("Fetched Trello Boards - size: " + trelloBoardDtos.size());


        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void testTrelloMapper(){
        //Given
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list_dto", false));

        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));

        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("3", "test_board_dto", trelloListDtos));

        List<TrelloBoard>trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("4", "test_board", trelloLists));

        when(trelloMapper.mapToListDto(trelloLists)).thenReturn(trelloListDtos);
        when(trelloMapper.mapToList(trelloListDtos)).thenReturn(trelloLists);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(trelloBoards);
        //When
        List<TrelloListDto>listDtoListActual = trelloMapper.mapToListDto(trelloLists);
        List<TrelloList>trelloListListActual = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertNotNull(listDtoListActual);
        assertEquals(1, listDtoListActual.size());
        assertEquals("test_list_dto", listDtoListActual.get(0).getName());

        assertNotNull(trelloListListActual);
        assertEquals(1, trelloListListActual.size());
        assertEquals("2", trelloListListActual.get(0).getId());

//        assertEquals(1, trelloBoards.size());
//        assertEquals("4", trelloBoards.get(0).getId());

    }

}
