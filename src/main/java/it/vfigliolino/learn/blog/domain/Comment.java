package it.vfigliolino.learn.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
	@Column(name="text", nullable = false)
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private UserProfile userProfile;
	
	
	

}

