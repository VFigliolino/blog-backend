package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.TagDto;

import java.util.Collections;
import java.util.List;

public class TagBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static TagDto getDto() {
        TagDto dto = new TagDto();
        dto.setId(1L);
        return dto;
    }
}