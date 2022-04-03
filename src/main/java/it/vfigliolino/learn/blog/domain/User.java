package it.vfigliolino.learn.blog.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "user")
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "mail", nullable = false)
	private String mail;
	
	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "user")
	private Set<Profile> profiles;
}
