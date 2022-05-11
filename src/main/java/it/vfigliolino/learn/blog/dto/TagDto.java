package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.domain.Article;

import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel()
public class TagDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    private String text;
    private Set<Article> articlesTagged;

    public TagDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setArticlesTagged(java.util.Set<it.vfigliolino.learn.blog.domain.Article> articlesTagged) {
        this.articlesTagged = articlesTagged;
    }

    public java.util.Set<it.vfigliolino.learn.blog.domain.Article> getArticlesTagged() {
        return this.articlesTagged;
    }
}