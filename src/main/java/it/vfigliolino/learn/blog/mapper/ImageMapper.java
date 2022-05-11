package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.Image;
import it.vfigliolino.learn.blog.dto.ImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<ImageDto, Image> {
}