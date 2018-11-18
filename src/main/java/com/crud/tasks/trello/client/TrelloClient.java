package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient<T> {
//    private Optional<List<TrelloBoardDto>> trelloBoardDtoOp = null;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.app.username}")
    private String trelloAppUsername;

    @Autowired
    private RestTemplate restTemplate;
    private String buildUrl(){
        return trelloApiEndpoint + "/members/" + trelloAppUsername + "/boards";
    }

    public List<TrelloBoardDto> getTrelloBoards(){
        URI url = UriComponentsBuilder.fromHttpUrl(buildUrl())
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

        System.out.println("@@@@@@@@@@@@@Inside getTrelloBoards before getting a response@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("URI: " + url);

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        //Filter for empty elements and Kodilla
        if (boardsResponse != null){
            List<TrelloBoardDto> resultListDto = new ArrayList<>();
            System.out.println("$$$$$$$$$$BoardsResponse before filter: " + boardsResponse );
            System.out.println("$$$$$$$$$$BoardsResponse size before filter: " + boardsResponse.length);
            for (TrelloBoardDto board:boardsResponse){
                if (board.getId() != null && board.getName() != null && board.getName().contains("Kodilla")){
                    resultListDto.add(board);
                }
            }
            System.out.println("$$$$$$$$$$BoardsResponse after filter: " + resultListDto);
            System.out.println("---------- BoardsResponse size after filter: " + resultListDto.size());
//            try {
                return resultListDto;
//            }catch (NullPointerException e){
//                System.out.println("%%%%%%%%%% Empty return! %%%%%%%%%");
//            }
//            if(resultListDto != null) {
//                return resultListDto;//(boardsResponse);
//            }
        }
        return new ArrayList<>();
    }
}
