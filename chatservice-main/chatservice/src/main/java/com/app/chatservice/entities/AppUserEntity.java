package com.app.chatservice.entities;


import java.util.Date;

// import org.hibernate.annotations.GenerationTime;
// import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "APPUSER")
public class AppUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "APPUSER_ID")
  private String appuserId;

  @Column(name = "APPUSER_UK_NAME")
  private String appuserUkName;

  @Column(name = "APPUSER_DISPLAY_NAME")
  private String appuserDisplayName;

  @Column(name = "APPUSER_EMAIL")
  private String appuserEmail;

  @Column(name = "APPUSER_PASSWORD")
  private String appuserPassword;

  @Temporal(TemporalType.DATE)
  @Column(name = "APPUSER_REG_DATE", nullable = false)
  private Date appuserRegDate;

  @Column(name = "APPUSER_GEN_PFP")
  private String appuserGenPfp;

  @Column(name = "APPUSER_ABOUT")
  private String appuserAbout;
  
  @PrePersist
  private void onCreate(){
    appuserRegDate = new Date();
  }

  @Override
  public String toString() {
    return "AppUserEntity [appuserId=" + appuserId + ", appuserUkName=" + appuserUkName + ", appuserDisplayName="
        + appuserDisplayName + ", appuserEmail=" + appuserEmail + ", appuserPassword=" + appuserPassword
        + ", appuserRegDate=" + appuserRegDate + ", appuserAbout=" + appuserAbout + ", appuserGenPfp=" + appuserGenPfp
        + "]";
  } 
  public String getAppuserId() {
    return appuserId;
  }
  public void setAppuserId(String appuserId) {
    this.appuserId = appuserId;
  }
  public String getAppuserUkName() {
    return appuserUkName;
  }
  public void setAppuserUkName(String appuserUkName) {
    this.appuserUkName = appuserUkName;
  }
  public String getAppuserDisplayName() {
    return appuserDisplayName;
  }
  public void setAppuserDisplayName(String appuserDisplayName) {
    this.appuserDisplayName = appuserDisplayName;
  }
  public String getAppuserEmail() {
    return appuserEmail;
  }
  public void setAppuserEmail(String appuserEmail) {
    this.appuserEmail = appuserEmail;
  }
  public String getAppuserPassword() {
    return appuserPassword;
  }
  public void setAppuserPassword(String appuserPassword) {
    this.appuserPassword = appuserPassword;
  }
  public Date getAppuserRegDate() {
    return appuserRegDate;
  }
  public String getAppuserGenPfp() {
    return appuserGenPfp;
  }
  public void setAppuserGenPfp(String appuserGenPfp) {
    this.appuserGenPfp = appuserGenPfp;
  }
  public String getAppuserAbout() {
    return appuserAbout;
  }
  public void setAppuserAbout(String appuserAbout) {
    this.appuserAbout = appuserAbout;
  }

  public AppUserEntity(String appuserId, String appuserUkName, String appuserDisplayName, String appuserEmail,
      Date appuserRegDate, String appuserGenPfp, String appuserAbout) {
    this.appuserId = appuserId;
    this.appuserUkName = appuserUkName;
    this.appuserDisplayName = appuserDisplayName;
    this.appuserEmail = appuserEmail;
    this.appuserRegDate = appuserRegDate;
    this.appuserGenPfp = appuserGenPfp;
    this.appuserAbout = appuserAbout;
  }

  public AppUserEntity() {
  }

  

  

}
