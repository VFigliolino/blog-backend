package it.vfigliolino.learn.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "image")
public class Image {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "link_to_image")
    private String linkToImage;
    
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
