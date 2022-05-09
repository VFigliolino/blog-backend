package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.domain.Article;
import it.vfigliolino.learn.blog.domain.Profile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel()
public class CommentDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    @NotBlank
    private String text;
    private Article article;
    private Profile profile;

    public CommentDto() {
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

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }
}