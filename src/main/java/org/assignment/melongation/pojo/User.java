package org.assignment.melongation.pojo;

import org.hibernate.validator.constraints.EAN;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class User {
    private Integer id;
    @Size(max = 10, min = 4)
    private String username;

    @Size(max = 20, min = 6)
    private String password;
    @NotEmpty
    private String image;
    @Email
    private String email;
    @NotEmpty
    private boolean isActive;

    private List<Paper> pageList;

    public User(Integer id) {
        this.id = id;
    }

    public List<Paper> getPageList() {
        return pageList;
    }

    public void setPageList(List<Paper> pageList) {
        this.pageList = pageList;
    }

    public User() {
    }

    public User(Integer id, String username, String password, String image, String email, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.image = image;
        this.email = email;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", pageList=" + pageList +
                '}';
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
