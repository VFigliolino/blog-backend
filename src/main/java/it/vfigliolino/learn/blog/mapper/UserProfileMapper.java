package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.UserProfile;
import it.vfigliolino.learn.blog.dto.UserProfileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper extends EntityMapper<UserProfileDto, UserProfile> {
}