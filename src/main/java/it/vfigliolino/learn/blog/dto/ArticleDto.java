package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.domain.Comment;
import it.vfigliolino.learn.blog.domain.Image;
import it.vfigliolino.learn.blog.domain.UserProfile;
import it.vfigliolino.learn.blog.domain.Tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel()
public class ArticleDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    @NotBlank
    private String title;
    @Size(max = 255)
    private String subTitle;
    @Size(max = 255)
    private String summary;
    @Size(max = 255)
    @NotBlank
    private String body;
    @Size(max = 255)
    private String linkToPrimareImage;
    @Size(max = 255)
    private String linkToCoverImage;
    private Set<Comment> comments;
    private Set<Image> images;
    private Set<UserProfile> userProfiles;
    private Set<Tag> tags;

    public ArticleDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setLinkToPrimareImage(String linkToPrimareImage) {
        this.linkToPrimareImage = linkToPrimareImage;
    }

    public String getLinkToPrimareImage() {
        return this.linkToPrimareImage;
    }

    public void setLinkToCoverImage(String linkToCoverImage) {
        this.linkToCoverImage = linkToCoverImage;
    }

    public String getLinkToCoverImage() {
        return this.linkToCoverImage;
    }

    public void setComments(java.util.Set<it.vfigliolino.learn.blog.domain.Comment> comments) {
        this.comments = comments;
    }

    public java.util.Set<it.vfigliolino.learn.blog.domain.Comment> getComments() {
        return this.comments;
    }

    public void setImages(java.util.Set<it.vfigliolino.learn.blog.domain.Image> images) {
        this.images = images;
    }

    public java.util.Set<it.vfigliolino.learn.blog.domain.Image> getImages() {
        return this.images;
    }

    public void setProfiles(java.util.Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public java.util.Set<UserProfile> getProfiles() {
        return this.userProfiles;
    }

    public void setTags(java.util.Set<it.vfigliolino.learn.blog.domain.Tag> tags) {
        this.tags = tags;
    }

    public java.util.Set<it.vfigliolino.learn.blog.domain.Tag> getTags() {
        return this.tags;
    }
}