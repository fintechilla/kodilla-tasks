package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    SimpleEmailService simpleEmailService;
    @Mock
    AdminConfig adminConfig;
    @Mock
    TaskRepository taskRepository;
    @Mock
    Mail mail;
    @Mock
    Badges badges;
    @Mock
    AttachmentByType attachment;
    @Mock
    Trello trello;

    @Test
    public void createTrelloCard() {
        Trello trello = new Trello(1, "1");
        AttachmentByType attachment = new AttachmentByType(trello);
        Badges badges = new Badges(2, attachment);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test_list_dto", false));
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card_dto", "test_description_dto", "top","1" );
        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("test_card_dto_created", "test_description_created_card_dto", "https://test.com", badges);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdCardDto);
        //when
        CreatedTrelloCardDto createdTrelloCardDtoActual = trelloClient.createNewCard(trelloCardDto);
        //Then
        assertNotNull(createdTrelloCardDtoActual);
        assertEquals("1", createdTrelloCardDtoActual.getBadges().getAttachment().getTrello().getCard());
        assertEquals(Optional.of(1), Optional.ofNullable(createdTrelloCardDtoActual.getBadges().getAttachment().getTrello().getBoard()));
    }
    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloList>trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "test_list", false));
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test_board_dto", false));
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "test_board", trelloLists));
        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("3", "test_borad_dto", trelloListDtos));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto>listDtosActual = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(listDtosActual);
        assertEquals("1", listDtosActual.get(0).getLists().get(0).getId());
    }


}