package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.VoteDto;

import java.util.Collections;
import java.util.List;

public class VoteBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static VoteDto getDto() {
        VoteDto dto = new VoteDto();
        dto.setId(1L);
        return dto;
    }
}