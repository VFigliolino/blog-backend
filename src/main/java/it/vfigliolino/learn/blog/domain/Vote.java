package it.vfigliolino.learn.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity(name = "vote")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
	@Column(name="vote", nullable = false)
	@Max(10)
	@Min(1)
	private Integer vote;
	
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private UserProfile userProfile;

}

