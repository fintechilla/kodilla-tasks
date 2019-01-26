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
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

        List<TrelloBoardDto>trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "test_board", trelloLists));

        List<TrelloList>mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard>mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("2", "test",mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(mappedTrelloBoards);
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
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        List<TrelloList>mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test id", "test_board",new ArrayList<>());

        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("2", "test_board_dto", new ArrayList<>()));

        List<TrelloBoard>trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("13", "test_board", mappedTrelloLists ));

        when(trelloMapper.mapToBoards(anyList())).thenReturn(trelloBoardList);
        when(trelloValidator.validateTrelloBoards(anyList())).thenReturn(trelloBoardList);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtoList);
        assertEquals(1, trelloBoardDtoList.size());

        trelloBoardDtoList.forEach(trelloBoardDto -> {
            assertEquals("2", trelloBoardDto.getId());
            assertEquals("test_board_dto", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("test_list", trelloBoardDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });
        });

//        assertEquals(1, fetchedTrelloBoards.size());
//        assertEquals("2", fetchedTrelloBoards.get(0).getId());
//        assertEquals("test_board_dto", fetchedTrelloBoards.get(0).getName());
//        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

}
