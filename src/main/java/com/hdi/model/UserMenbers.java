package com.hdi.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 会员信息
 * @author  wanghuidong
 * @date 2017/12/27.
 * @version 1.0
 */
@Entity
@Table(name = "t_usermenber",indexes = {@Index(name = "idx_menberCode",columnList = "menberCode",unique=true)})
public class UserMenbers implements Serializable,Cloneable{
    /**
     * 主键
     */
    @Id
    @GeneratedValue()
    private Integer userId;
    /**
     * 会员号
     */
    @Column(name = "menberCode",nullable=false,columnDefinition = "varchar(255) COMMENT '会员号'")
    private String menberCode;
    /**
     * 昵称
     */
    @Column(name = "userName",columnDefinition = "varchar(255) COMMENT '昵称'")
    @NotNull(message="昵称不能为空！")
    private String userName;
    /**
     * 地址
     */
    @Column(name = "address",columnDefinition = "varchar(255) COMMENT '住址'")
    @NotNull(message="地址不能为空！")
    private String address;
    /**
     * 身份证号
     */
    @Column(name = "idNo",columnDefinition = "varchar(255) COMMENT '身份证号码'")
    @NotEmpty(message="身份证号不能为空！")
    private String idNo;
    /**
     * 性别
     */
    @Column(name = "sex",columnDefinition = "varchar(255) COMMENT '性别'")
    private String sex;
    /**
     * 手机号码
     */
    @Column(name = "mobile",columnDefinition = "varchar(255) COMMENT '手机号码'")
    private String mobile;
    /**
     * 真实名字
     */
    @Column(name = "realName",columnDefinition = "varchar(255) COMMENT '真实姓名'")
    private String realName;
    /**
     * 邀请人
     */
    @Column(name = "inviter",columnDefinition = "varchar(255) COMMENT '邀请人'")
    @NotNull(message="邀请人不能为空！")
    private String inviter;
    /**
     * 安置人（左）
     */
    @Column(name = "putPeopleLeft",columnDefinition = "varchar(255) COMMENT '安置人（左）'")
    private String putPeopleLeft;
    /**
     * 安置人（右）
     */
    @Column(name = "putPeopleRight",columnDefinition = "varchar(255) COMMENT '安置人（右）'")
    private String putPeopleRight;
    /**
     * 被安置人
     */
    @Column(name ="putPeople",columnDefinition = "varchar(255) COMMENT '被安置人'")
    private String putPeople;
    /**
     * 注册级别
     */
    @Column(name = "registeredLevel",nullable = false,columnDefinition = "int(11) COMMENT '注册级别'")
    @NotNull(message="注册级别不能为空！")
    private Integer registeredLevel;
    /**
     * 登录密码
     */
    @Column(name = "password",columnDefinition = "varchar(255) COMMENT '登录密码'")
    @NotNull(message="密码不能为空！")
    private String password;
    /**
     * 安全密码
     */
    @Column(name = "secondPwd",columnDefinition = "varchar(255) COMMENT '安全密码'")
    @NotNull(message="安全密码不能为空！")
    private String secondPwd;
    /**
     * 注册时间
     */
    @Column(name = "registereTime",columnDefinition = "datetime COMMENT '注册时间'")
    private Date registereTime = new Date();
    /**
     * 激活时间
     */
    @Column(name = "activeTime",columnDefinition = "datetime COMMENT '激活时间'")
    private Date activeTime;
    /**
     * 会员年龄
     */
    @Column(name = "age",columnDefinition = "int(11) COMMENT '会员年龄'")
    private Integer age;
    /**
     * 是否激活
     */
    @Column(name = "isActive",columnDefinition = "bit(1) COMMENT '是否激活'")
    private boolean isActive=false;
    /**
     * 国家
     */
    @Column(name = "country",columnDefinition = "varchar(255) COMMENT '国家'")
    private String country;
    /**
     * 邮件
     */
    @Column(name = "email",columnDefinition = "varchar(255) COMMENT '邮件'")
    @Email
    private String email;
    /**
     * 银行卡号
     */
    @Column(name = "bankNo",columnDefinition = "varchar(255) COMMENT '银行卡号'")
    private String bankNo;
    /**
     * 开户银行
     */
    @Column(name = "bankName",columnDefinition = "varchar(255) COMMENT '开户银行'")
    private String bankName;
    /**
     * 银行开户名
     */
    @Column(name = "bankAccount",columnDefinition = "varchar(255) COMMENT '银行开户名'")
    private String bankAccount;

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

    @Transient
    private UserMenbers leftUser;

    @Transient
    private UserMenbers rigthUser;

    public UserMenbers(){

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMenberCode() {
        return menberCode;
    }

    public void setMenberCode(String menberCode) {
        this.menberCode = menberCode;
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

    public UserMenbers getLeftUser() {
        return leftUser;
    }

    public void setLeftUser(UserMenbers leftUser) {
        this.leftUser = leftUser;
    }

    public UserMenbers getRigthUser() {
        return rigthUser;
    }

    public void setRigthUser(UserMenbers rigthUser) {
        this.rigthUser = rigthUser;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "UserMenbers{" +
                "userId=" + userId +
                ", menberCode='" + menberCode + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", idNo='" + idNo + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", realName='" + realName + '\'' +
                ", inviter='" + inviter + '\'' +
                ", putPeopleLeft='" + putPeopleLeft + '\'' +
                ", putPeopleRight='" + putPeopleRight + '\'' +
                ", putPeople='" + putPeople + '\'' +
                ", registeredLevel=" + registeredLevel +
                ", password='" + password + '\'' +
                ", secondPwd='" + secondPwd + '\'' +
                ", registereTime=" + registereTime +
                ", activeTime=" + activeTime +
                ", age=" + age +
                ", isActive=" + isActive +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", site=" + site +
                ", version=" + version +
                ", leftUser=" + leftUser +
                ", rigthUser=" + rigthUser +
                '}';
    }
}
