package it.vfigliolino.learn.blog.dto;

import io.swagger.annotations.ApiModel;
import it.vfigliolino.learn.blog.annotation.CheckEmail;
import it.vfigliolino.learn.blog.domain.UserProfile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel()
public class UserDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    @NotBlank
    private String username;
    @CheckEmail
    @Size(max = 255)
    @NotBlank
    private String mail;
    @Size(max = 255)
    @NotBlank
    private String password;
    private Set<UserProfile> userProfiles;

    public UserDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return this.mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setProfiles(java.util.Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public java.util.Set<UserProfile> getProfiles() {
        return this.userProfiles;
    }
}