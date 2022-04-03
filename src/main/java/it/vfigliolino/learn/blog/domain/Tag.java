package it.vfigliolino.learn.blog.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity(name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@ManyToMany
	@JoinTable(
			name = "article_has_tag", 
			joinColumns =  @JoinColumn(name = "tag_id", referencedColumnName = "id" ),
			inverseJoinColumns = @JoinColumn(name ="article_id", referencedColumnName = "id"))
	private Set<Article> articlesTagged;

}
