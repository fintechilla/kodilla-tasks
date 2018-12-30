package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;
    private String buildUrl(){
        return trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloAppUsername() + "/boards";
    }

    //@RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards(){
        URI url = UriComponentsBuilder.fromHttpUrl(buildUrl())
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloAppToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

        LOGGER.info("@@@@@@@@@@@@@Inside getTrelloBoards before getting a response@@@@@@@@@@@@@@@@@@@@@@@@@@");
        LOGGER.info("URI: " + url);
        LOGGER.info("GetTrelloBoards:  - DONE!");
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            LOGGER.info("GetTrelloBoards - SUCCESS!!" + " boardsResponse: " + boardsResponse);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        }catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.info("GetTrelloBoards - FAILED!!");
            return new ArrayList<>();
        }

    }
    //@RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloAppToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getIdList())
                .build().encode().toUri();
        LOGGER.info("CreteTrelloCard - DONE!!");
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
