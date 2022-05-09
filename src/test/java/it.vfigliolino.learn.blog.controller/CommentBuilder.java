package it.vfigliolino.learn.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.vfigliolino.learn.blog.dto.CommentDto;

import java.util.Collections;
import java.util.List;

public class CommentBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static CommentDto getDto() {
        CommentDto dto = new CommentDto();
        dto.setId(1L);
        return dto;
    }
}