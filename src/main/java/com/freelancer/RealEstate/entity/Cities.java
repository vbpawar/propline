package com.freelancer.RealEstate.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * This class is used for cities.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_cities")
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer city_id;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "isActive", columnDefinition = "BIT(1) default true")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "city_id=" + city_id +
                ", city='" + city + '\'' +
                '}';
    }
}
