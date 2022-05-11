package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.domain.Article;

import javax.validation.constraints.Size;

@ApiModel()
public class ImageDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    private String linkToImage;
    private Article article;

    public ImageDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    public String getLinkToImage() {
        return this.linkToImage;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return this.article;
    }
}