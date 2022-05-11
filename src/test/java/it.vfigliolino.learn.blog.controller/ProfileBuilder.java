package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.UserProfileDto;

import java.util.Collections;
import java.util.List;

public class ProfileBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static UserProfileDto getDto() {
        UserProfileDto dto = new UserProfileDto();
        dto.setId(1L);
        return dto;
    }
}