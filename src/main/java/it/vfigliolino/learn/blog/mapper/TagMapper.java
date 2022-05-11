package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.Tag;
import it.vfigliolino.learn.blog.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<TagDto, Tag> {
}