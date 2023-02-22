package poc.Lmsapplication.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import poc.Lmsapplication.Enum.ResponseStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * User entity of API
 *
 * @author deeksha.singh
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private Long phoneNumber;
    private String emailId;
    private String sex;
    private String hometown;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Dob;
    private int age;
    private String role;

    @Enumerated(EnumType.STRING)
    private ResponseStatus responseStatus;

    public User() {
    }

    public User(Long userId, String username, String password, Long phoneNumber, String emailId, String sex, String hometown, Date dob, int age, String role, ResponseStatus responseStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.sex = sex;
        this.hometown = hometown;
        Dob = dob;
        this.age = age;
        this.role = role;
        this.responseStatus = responseStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailId='" + emailId + '\'' +
                ", sex='" + sex + '\'' +
                ", hometown='" + hometown + '\'' +
                ", Dob=" + Dob +
                ", responseStatus=" + responseStatus +
                '}';
    }
}