package com.ms_login.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @Column(name="ID",  columnDefinition="NUMBER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int userid;

    @Column(name="USERNAME", columnDefinition = "VARCHAR2")
    @NotNull
    private String username;

    @Column(name="PASSWORD", columnDefinition = "VARCHAR2")
    @NotNull
    private String password;

    @Column(name="EMAIL", columnDefinition = "VARCHAR2")
    @NotNull
    private String email;

    @Column(name="ROLE", columnDefinition = "NUMBER")
    @NotNull
    private int role;

    @Column(name="REGISTER_DATE", columnDefinition = "DATE")
    @NotNull
    private LocalDate registerdate;

    @Column(name = "UPDATE_DATE", columnDefinition = "DATE")
    @NotNull
    private LocalDate updatedate;

    public User() {
    }

    public User(String username, String password, String email, int role, LocalDate registerdate, LocalDate updatedate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registerdate = registerdate;
        this.updatedate = updatedate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public LocalDate getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(LocalDate registerdate) {
        this.registerdate = registerdate;
    }

    public LocalDate getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDate updatedate) {
        this.updatedate = updatedate;
    }
}
