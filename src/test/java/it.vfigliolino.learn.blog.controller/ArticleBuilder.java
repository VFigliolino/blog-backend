package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.ArticleDto;

import java.util.Collections;
import java.util.List;

public class ArticleBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ArticleDto getDto() {
        ArticleDto dto = new ArticleDto();
        dto.setId(1L);
        return dto;
    }
}