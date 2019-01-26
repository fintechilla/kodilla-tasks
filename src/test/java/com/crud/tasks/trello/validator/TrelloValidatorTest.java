package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
//@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {
//    @Mock
    TrelloValidator trelloValidator = new TrelloValidator();
    @Test
    public void validateCard() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));
        TrelloCard trelloCard = new TrelloCard("test_card", "test_description", "top", trelloLists.get(0).getId());
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        //verify(trelloValidator, times(1)).validateCard(trelloCard);
    }

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("2", "test_board",trelloLists));
        //When
        List<TrelloBoard>boardList = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertNotNull(boardList);
        assertEquals(1, boardList.size());
        assertEquals("2", boardList.get(0).getId());

    }
}