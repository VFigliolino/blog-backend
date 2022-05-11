package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.ImageDto;

import java.util.Collections;
import java.util.List;

public class ImageBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ImageDto getDto() {
        ImageDto dto = new ImageDto();
        dto.setId(1L);
        return dto;
    }
}