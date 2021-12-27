package com.epam.entity;

import javax.persistence.*;

@Entity
@Table(name = "UserAccount_TBL")
public class Account {
    @Id
    @Column(name = "EMP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "URL")
    private String url;
    @Column(name = "password")
    private String password;
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }


    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    private Group group ;

    public Group getGroup () {
        return group ;
    }

    public void setGroup (Group group) {
        this.group  = group ;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Account() {

    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Account( String userName,String url, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }


}
