package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    private TrelloFacade trelloFacade;//TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {return trelloFacade.fetchTrelloBoards(); }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);  //Service  Trello
    }

        //List<TrelloBoardDto> trelloBoards =  //Service
//        Date date = new Date();
//        System.out.println("Current time is: " + date.toString());
//        //filter for Kodilla
//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getName() + " " + trelloBoardDto.getId());
//            System.out.println("This board contains lists: ");
//            trelloBoardDto.getLists().forEach(trelloList -> System.out.println(
//                    trelloList.getName() + " - "
//                            + trelloList.getId() + " - "
//                            + trelloList.isClosed()));
//        });
        //return trelloBoards;
}
