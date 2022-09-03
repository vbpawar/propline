package com.freelancer.RealEstate.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class OwnerDto {

    private String owner_name;
    private String permanent_address;
    private String email;
    private String contact_number;
    private Date dob;
    private String profile;
    private String profile_path;
    private String adhar_title;
    private String adhar_path;
    private String pan_card;
    private String pan_card_path;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getAdhar_title() {
        return adhar_title;
    }

    public void setAdhar_title(String adhar_title) {
        this.adhar_title = adhar_title;
    }

    public String getAdhar_path() {
        return adhar_path;
    }

    public void setAdhar_path(String adhar_path) {
        this.adhar_path = adhar_path;
    }

    public String getPan_card() {
        return pan_card;
    }

    public void setPan_card(String pan_card) {
        this.pan_card = pan_card;
    }

    public String getPan_card_path() {
        return pan_card_path;
    }

    public void setPan_card_path(String pan_card_path) {
        this.pan_card_path = pan_card_path;
    }
}
