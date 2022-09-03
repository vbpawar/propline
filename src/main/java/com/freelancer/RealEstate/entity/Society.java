package com.freelancer.RealEstate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is used to maintain society table.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_society")
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "society_id")
    private Integer society_id;

    @Column(name = "society")
    @NotNull(message = "Society can not be null")
    private String society;

    @Column(name = "location")
    @NotNull(message = "Location can not be null")
    private String location;

    @Column(name = "pin")
    @NotNull(message = "pin can not be null")
    private String pin;

    @ManyToOne
    @JoinColumn(name = "street_id")
    Streets streets;

    public Integer getSociety_id() {
        return society_id;
    }

    public void setSociety_id(Integer society_id) {
        this.society_id = society_id;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Streets getStreets() {
        return streets;
    }

    public void setStreets(Streets streets) {
        this.streets = streets;
    }
}
