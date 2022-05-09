package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.Comment;
import it.vfigliolino.learn.blog.dto.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {
}