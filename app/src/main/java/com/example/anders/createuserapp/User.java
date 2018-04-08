
package com.example.anders.createuserapp;
public class User {

    String firstName;
    String lastName;
    String memberNumber;
    String email;



    public User(){

    }

    public User(String firstName, String lastName, String memberNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
    }

    public User(String email, String firstName, String lastName, String memberNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


