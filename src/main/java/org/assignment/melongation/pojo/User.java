package org.assignment.melongation.pojo;

import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String image;
    private String email;
    private Boolean isActive;
    private List<Paper> pageList;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
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
