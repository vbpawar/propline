package com.freelancer.RealEstate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tab_street")
public class Streets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "street_id")
    private Integer street_id;

    @Column(name = "street")
    @NotNull(message = "Street can not be null")
    private String street;

    @ManyToOne
    @JoinColumn(name = "neigborhood_id")
    private Neighborhood neighborhood;

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getStreet_id() {
        return street_id;
    }

    public void setStreet_id(Integer street_id) {
        this.street_id = street_id;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Streets{" +
                "street_id=" + street_id +
                ", street='" + street + '\'' +
                '}';
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
