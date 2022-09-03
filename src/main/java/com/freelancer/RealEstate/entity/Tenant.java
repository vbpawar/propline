package com.freelancer.RealEstate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tab_tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tenant_id;

    private String tenant_name;
    private String tenant_address;
    private String contact_number;
    private String email;
    private Date dob;
    private Date aggrement_start_date;
    private Date getAggrement_end_date;
    private Date created_on;
    private Date updated_on;
    private String adhar_card;
    private String adhar_card_path;
    private String pan_card;
    private String pan_card_path;
    private String profile_photo;
    private String profile_photo_path;

    @Column(name = "status", columnDefinition = "BIT(1) default true")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    Owner owner;

    public Integer getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Integer tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }

    public String getTenant_address() {
        return tenant_address;
    }

    public void setTenant_address(String tenant_address) {
        this.tenant_address = tenant_address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getAggrement_start_date() {
        return aggrement_start_date;
    }

    public void setAggrement_start_date(Date aggrement_start_date) {
        this.aggrement_start_date = aggrement_start_date;
    }

    public Date getGetAggrement_end_date() {
        return getAggrement_end_date;
    }

    public void setGetAggrement_end_date(Date getAggrement_end_date) {
        this.getAggrement_end_date = getAggrement_end_date;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }

    public String getAdhar_card() {
        return adhar_card;
    }

    public void setAdhar_card(String adhar_card) {
        this.adhar_card = adhar_card;
    }

    public String getAdhar_card_path() {
        return adhar_card_path;
    }

    public void setAdhar_card_path(String adhar_card_path) {
        this.adhar_card_path = adhar_card_path;
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

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
