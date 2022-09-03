package com.freelancer.RealEstate.entity;

import com.freelancer.RealEstate.config.ObjectConversion;
import com.freelancer.RealEstate.config.ObjectConvert;
import com.freelancer.RealEstate.model.Features;
import com.freelancer.RealEstate.model.OwnerDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tab_owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer owner_id;
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

    @Column(name = "is_current_owner", columnDefinition = "BIT(1) default false")
    private boolean is_current_owner;

    @Column(name = "co_owner_details", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<OwnerDto> ownerDto;

    @ManyToOne
    @JoinColumn(name = "property_id")
    Property property;

    public List<OwnerDto> getOwnerDto() {
        return ownerDto;
    }

    public void setOwnerDto(List<OwnerDto> ownerDto) {
        this.ownerDto = ownerDto;
    }

    public boolean isIs_current_owner() {
        return is_current_owner;
    }

    public void setIs_current_owner(boolean is_current_owner) {
        this.is_current_owner = is_current_owner;
    }


    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

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
