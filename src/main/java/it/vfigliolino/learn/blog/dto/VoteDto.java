package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.domain.Article;
import it.vfigliolino.learn.blog.domain.UserProfile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@ApiModel()
public class VoteDto extends AbstractDto<Long> {
    private Long id;
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer vote;
    private Article article;
    private UserProfile userProfile;

    public VoteDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getVote() {
        return this.vote;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getProfile() {
        return this.userProfile;
    }
}