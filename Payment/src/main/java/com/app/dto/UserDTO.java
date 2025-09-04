package com.app.dto;

public class UserDTO {
 private int user_id;
 private String username;
 private String email;
 private String phone_number;
 private String planType;

 // getters and setters
 public int getUser_id() {
     return user_id;
 }
 public void setUser_id(int user_id) {
     this.user_id = user_id;
 }

 public String getUsername() {
     return username;
 }
 public void setUsername(String username) {
     this.username = username;
 }

 public String getEmail() {
     return email;
 }
 public void setEmail(String email) {
     this.email = email;
 }

 public String getPhone_number() {
     return phone_number;
 }
 public void setPhone_number(String phone_number) {
     this.phone_number = phone_number;
 }

 public String getPlanType() {
     return planType;
 }
 public void setPlanType(String planType) {
     this.planType = planType;
 }
}
