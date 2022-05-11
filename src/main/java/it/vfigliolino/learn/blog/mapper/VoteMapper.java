package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.Vote;
import it.vfigliolino.learn.blog.dto.VoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper extends EntityMapper<VoteDto, Vote> {
}