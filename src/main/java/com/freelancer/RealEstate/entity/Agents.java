package com.freelancer.RealEstate.entity;

import javax.persistence.*;

@Entity
@Table(name = "tab_agents")
public class Agents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agent_id;

    private String agent_name;

    private String agent_desc;

    private String organisation;

    private String email;

    private String contact;

    private String profile;

    @Column(name = "isActive", columnDefinition = "BIT(1) default false")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(Integer agent_id) {
        this.agent_id = agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getAgent_desc() {
        return agent_desc;
    }

    public void setAgent_desc(String agent_desc) {
        this.agent_desc = agent_desc;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
