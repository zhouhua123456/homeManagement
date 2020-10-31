package com.homemanagement.demoentity.entity;


import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "USER_MONEY")
public class UserMoney implements Serializable{

    private String id;
    private String userId;
    private String userName;
    private String totalMoney;


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


	@Column(name = "TOTAL_MONEY")
    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

}
