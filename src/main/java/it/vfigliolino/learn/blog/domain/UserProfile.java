package it.vfigliolino.learn.blog.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "profile")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany
	@JoinTable(
			name = "profile_has_article", 
			joinColumns =  @JoinColumn(name = "profile_id", referencedColumnName = "id" ),
			inverseJoinColumns = @JoinColumn(name ="article_id", referencedColumnName = "id"))
	private Set<Article> articlesWritten;

}
