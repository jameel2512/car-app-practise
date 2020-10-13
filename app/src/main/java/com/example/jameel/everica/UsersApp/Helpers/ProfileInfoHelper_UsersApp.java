package com.example.jameel.everica.UsersApp.Helpers;

public class ProfileInfoHelper_UsersApp {

    public String name,email,contact,password,confirm_password;


    private ProfileInfoHelper_UsersApp()
    {

    }

    public ProfileInfoHelper_UsersApp(String name, String email, String contact, String password,String confirm_password) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password=password;
        this.confirm_password=confirm_password;

    }


    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
