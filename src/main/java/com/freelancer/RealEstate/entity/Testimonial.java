package com.freelancer.RealEstate.entity;

import javax.persistence.*;

@Entity
@Table(name = "tab_testimonial")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testimonial_id")
    private Integer testimonial_id;

    @Column(name = "profile")
    private String profile;

    @Lob
    @Column(name = "description",length = 512)
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "designation")
    private String designation;

    public Integer getId() {
        return testimonial_id;
    }

    public void setId(Integer id) {
        this.testimonial_id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
