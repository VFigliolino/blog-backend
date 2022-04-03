package it.vfigliolino.learn.blog.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "article")
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "subtitle")
    private String subTitle;
    
    @Column(name = "summary")
    private String summary;
    
    @Column(name = "body", nullable = false)
    private String body;
    
    @Column(name = "link_to_primary_image")
    private String linkToPrimareImage;
    
    @Column(name = "link_to_cover_image")
    private String linkToCoverImage;
    
    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;
    
    @OneToMany(mappedBy = "article")
    private Set<Image> images;
    
    @ManyToMany(mappedBy = "articlesWritten")
    private Set<Profile> profiles;
    
    @ManyToMany(mappedBy = "articlesTagged")
    private Set<Tag> tags;
    
}
