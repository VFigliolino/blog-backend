package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.UserDto;

import java.util.Collections;
import java.util.List;

public class UserBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static UserDto getDto() {
        UserDto dto = new UserDto();
        dto.setId(1L);
        return dto;
    }
}