package com.homemanagement.demoentity.entity;


import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "SEAS_USER")
public class SeasUser implements Serializable{

    private String id;
    private String userId;
    private String userName;
    private String password;
    private String status;
    private String userPosition;


	@Id
	@GenericGenerator(name = "user-uuid", strategy = "uuid")
	@GeneratedValue(generator = "user-uuid")
	@Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


	@Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


	@Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


	@Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	@Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


	@Column(name = "USER_POSITION")
    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

}
