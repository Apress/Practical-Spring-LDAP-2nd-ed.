package com.apress.book.ldap.domain;

public class Patron {

    private String uid;
    private String firstName;
    private String lastName;
    private String commonName;
    private String email;

    public Patron() {
    }

    public Patron(String uid, String firstName, String lastName, String commonName, String email) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.commonName = commonName;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Override
    public String toString() {
        return "Patron [uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", commonName="
                + commonName + ", email=" + email + "]";
    }

}