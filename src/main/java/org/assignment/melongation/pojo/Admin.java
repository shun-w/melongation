package org.assignment.melongation.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * admin
 */
public class Admin {

    private Integer id;

    @Size(max = 10, min = 4)
    private String username;

    @Size(max = 20, min = 6)
    private String password;

    @NotEmpty
    private String image;//头像的url

    @Email
    private String email;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Admin(Integer id, String username, String password, String image, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.image = image;
        this.email = email;
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
