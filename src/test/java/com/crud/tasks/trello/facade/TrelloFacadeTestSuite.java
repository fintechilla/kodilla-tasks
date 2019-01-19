package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloMapper;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite{
    @InjectMocks
    TrelloFacade trelloFacade;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;
    @Mock
    TrelloClient trelloClient;
    @Mock
    TrelloMapper trelloMapper;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloAppToken()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test id", "test board",new ArrayList<>());

        URI uri = new URI("http://test.com/members/slawekhenrykwalerys/boards?key=test&token=test&fields=name,id&lists=all");
        when(trelloConfig.getTrelloAppUsername()).thenReturn("slawekhenrykwalerys");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloFacade.fetchTrelloBoards();//trelloFacade.fetchTrelloBoards();//
        System.out.println("Fetched Trello Boards - size: " + fetchedTrelloBoards.size());
        //Then
//        assertEquals(1, fetchedTrelloBoards.size());
//        assertEquals("test id", fetchedTrelloBoards.get(0).getId());
//        assertEquals("test board", fetchedTrelloBoards.get(0).getName());
//        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }
}
