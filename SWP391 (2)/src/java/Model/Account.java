/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author kienb
 */
public class Account {
    private int  id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Date birthday;
    private Role roleid;
    private String address;
    private boolean isActive;
    private boolean gender;
    private String image;
    private String phone;
    

    public Account() {
    }

    public Account(int id, String firstname, String lastname, String password, String email, Date birthday, Role roleid, String address, boolean isActive, boolean gender, String image) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.roleid = roleid;
        this.address = address;
        this.isActive = isActive;
        this.gender = gender;
        this.image = image;
    }

    public Account(int id, String firstname, String lastname, String password, String email, Date birthday, Role roleid, String address, boolean active, boolean gender, String image, String phone) {
       this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.roleid = roleid;
        this.address = address;
        this.isActive = isActive;
        this.gender = gender;
        this.image = image;
        this.phone = phone;//To change body of generated methods, choose Tools | Templates.
    }
    public Account(String email, String phone) {
       this.email = email; 
       this.phone = phone;//To change body of generated methods, choose Tools | Templates.
    }

   
  
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
   
    public boolean isIsActive() {
        return isActive;
    }

    public boolean isGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRoleid() {
        return roleid;
    }

    public void setRoleid(Role roleId) {
        this.roleid = roleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", email=" + email + ", birthday=" + birthday + ", roleid=" + roleid + ", address=" + address + ", isActive=" + isActive + ", gender=" + gender + ", image=" + image + '}';
    }

    

    
    
    
}
