package it.vfigliolino.learn.blog.mapper;

import it.vfigliolino.learn.blog.domain.Article;
import it.vfigliolino.learn.blog.dto.ArticleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article> {
}