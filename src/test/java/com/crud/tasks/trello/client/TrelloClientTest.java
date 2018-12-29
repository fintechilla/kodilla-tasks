package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {
    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

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
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
        }

        @Test
        public void shouldReturnEmptyList(){
            when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
            when(trelloConfig.getTrelloAppKey()).thenReturn("test");
            when(trelloConfig.getTrelloAppToken()).thenReturn("test");

            TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
            trelloBoards[0] = null; //new TrelloBoardDto("test id", "test board",new ArrayList<>());

            URI uri = null;
            try {
                uri = new URI("http://test.com/members/slawekhenrykwalerys/boards?key=test&token=test&fields=name,id&lists=all");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            when(trelloConfig.getTrelloAppUsername()).thenReturn("slawekhenrykwalerys");
            when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

            //When
            List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

            //Then
            assertEquals(0, fetchedTrelloBoards.size());
        }
}