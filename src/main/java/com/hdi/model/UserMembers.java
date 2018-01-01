package com.hdi.model;


import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员信息
 * @author  王慧东
 * @date 2017/12/27.
 * @version 1.0
 */
@Entity
@Table(name = "t_usermenber")
public class UserMembers implements Serializable,Cloneable{
    /**
     * 主键
     */
    @Id
    @GeneratedValue()
    private Integer id;
    /**
     * 会员号
     */
    @Column(name = "memberCode")
    private String memberCode;
    /**
     * 昵称
     */
    @Column(name = "userName")
    private String userName;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 身份证号
     */
    @Column(name = "idNo")
    private String idNo;
    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;
    /**
     * 手机号码
     */
    @Column(name = "mobile")
    private String mobile;
    /**
     * 真实名字
     */
    @Column(name = "realName")
    private String realName;
    /**
     * 邀请人
     */
    @Column(name = "inviter")
    private String inviter;
    /**
     * 被安置人（左）
     */
    @Column(name = "putPeopleLeft")
    private String putPeopleLeft;
    /**
     * 被安置人（右）
     */
    @Column(name = "putPeopleRight")
    private String putPeopleRight;
    /**
     * 安置人
     */
    @Column(name ="putPeople")
    private String putPeople;
    /**
     * 注册级别
     */
    @Column(name = "registeredLevel")
    private Integer registeredLevel;
    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 安全密码
     */
    @Column(name = "secondPwd")
    private String secondPwd;
    /**
     * 注册时间
     */
    @Column(name = "registereTime")
    private Date registereTime = new Date();
    /**
     * 激活时间
     */
    @Column(name = "activeTime")
    private Date activeTime;
    /**
     * 会员年龄
     */
    @Column(name = "age")
    private Integer age;
    /**
     * 是否激活
     */
    @Column(name = "isActive")
    private boolean isActive;
    /**
     * 国家
     */
    @Column(name = "country")
    private String country;
    /**
     * 邮件
     */
    @Column(name = "email")
    @Email
    private String email;
    /**
     * 银行卡号
     */
    @Column(name = "bankNo")
    private String bankNo;
    /**
     * 银行名称
     */
    @Column(name = "bankName")
    private String bankName;
    /**
     * 区间位置（0：左，1：右）
     */
    @Transient
    private Integer site;
    /**
     * 版本号
     */
    @Version
    @Column(name = "version", nullable = false)
    private int version = 1;

    public UserMembers(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getPutPeopleLeft() {
        return putPeopleLeft;
    }

    public void setPutPeopleLeft(String putPeopleLeft) {
        this.putPeopleLeft = putPeopleLeft;
    }

    public String getPutPeopleRight() {
        return putPeopleRight;
    }

    public void setPutPeopleRight(String putPeopleRight) {
        this.putPeopleRight = putPeopleRight;
    }

    public Integer getRegisteredLevel() {
        return registeredLevel;
    }

    public void setRegisteredLevel(Integer registeredLevel) {
        this.registeredLevel = registeredLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondPwd() {
        return secondPwd;
    }

    public void setSecondPwd(String secondPwd) {
        this.secondPwd = secondPwd;
    }

    public Date getRegistereTime() {
        return registereTime;
    }

    public void setRegistereTime(Date registereTime) {
        this.registereTime = registereTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public String getPutPeople() {
        return putPeople;
    }

    public void setPutPeople(String putPeople) {
        this.putPeople = putPeople;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
