package com.arjun.halfride.HelperClass;

public class HelperClass {

    String name;
    String number;
    String location;
    String organisation;
    String email;
    String userId;
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HelperClass(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public HelperClass(String name, String number, String location, String organisation, String email, String userId) {
        this.name = name;
        this.number = number;
        this.location = location;
        this.organisation = organisation;
        this.email = email;
        this.userId = userId;
    }



    public HelperClass() {
    }
}
