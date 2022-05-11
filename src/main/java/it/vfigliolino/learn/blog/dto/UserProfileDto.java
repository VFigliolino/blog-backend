package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.domain.Article;
import it.vfigliolino.learn.blog.domain.User;

import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel()
public class UserProfileDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String surname;
    private User user;
    private Set<Article> articlesWritten;

    public UserProfileDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setArticlesWritten(java.util.Set<it.vfigliolino.learn.blog.domain.Article> articlesWritten) {
        this.articlesWritten = articlesWritten;
    }

    public java.util.Set<it.vfigliolino.learn.blog.domain.Article> getArticlesWritten() {
        return this.articlesWritten;
    }
}