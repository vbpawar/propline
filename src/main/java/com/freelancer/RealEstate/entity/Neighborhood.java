package com.freelancer.RealEstate.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * This Class is used for neighborhood details.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_neighborhood")
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "neighborhood_id")
    private Integer neighborhood_id;

    @Column(name = "neighborhood")
    @NotNull
    private String neighborhood;

    @Column(name = "isActive", columnDefinition = "BIT(1) default false")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    private Cities cities;

    public Integer getNeighborhood_id() {
        return neighborhood_id;
    }

    public void setNeighborhood_id(Integer neighborhood_id) {
        this.neighborhood_id = neighborhood_id;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }
}
