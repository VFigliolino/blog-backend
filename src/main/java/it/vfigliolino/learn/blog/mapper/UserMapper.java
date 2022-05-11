package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.User;
import it.vfigliolino.learn.blog.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}