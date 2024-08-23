package com.ezentwix.teamcostco.controller.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.service.search.NaverSearchService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    의존성 추가 

    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
*/

@Controller
public class SearchController {
    
    private final NaverSearchService naverSearchService;
    private final ObjectMapper objectMapper;

    public SearchController(NaverSearchService naverSearchService, ObjectMapper objectMapper) {
        this.naverSearchService = naverSearchService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/search")
    public String search() {
        return "test";
    }

    @GetMapping("/searchinfo")
    public Mono<String> search(
        @RequestParam("query") String query,
        @RequestParam("display") Integer display,
        @RequestParam("start") Integer start,
        Model model) {

        return naverSearchService.search(query, display, start)
            .doOnNext(result -> {
                try {
                    JsonNode jsonNode = objectMapper.readTree(result);
                    List<Map<String, String>> items = parseItems(jsonNode);
                    model.addAttribute("items", items);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            })
            .thenReturn("test"); // Mono<String> 반환
    }

    private List<Map<String, String>> parseItems(JsonNode jsonNode) {
        List<Map<String, String>> items = new ArrayList<>();
        JsonNode itemNodes = jsonNode.get("items");
        if (itemNodes != null) {
            for (JsonNode item : itemNodes) {
                Map<String, String> itemMap = new HashMap<>();
                item.fields().forEachRemaining(entry -> 
                    itemMap.put(entry.getKey(), entry.getValue().asText()));
                items.add(itemMap);
            }
        }
        return items;
    }
}
