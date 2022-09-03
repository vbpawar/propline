package com.freelancer.RealEstate.entity;

import javax.persistence.*;

/**
 * This class is used for handle property features.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_property_features")
public class AdditionalFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "selected")
    private boolean selected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
