package com.freelancer.RealEstate.entity;

import javax.persistence.*;

/**
 * This Class is used for Property Type.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_property_type")
public class PropertyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_type_id")
    private Integer property_type_id;

    @Column(name = "property_name")
    private String property_name;

    @Column(name = "property_desc")
    private String property_desc;

    public Integer getProperty_type_id() {
        return property_type_id;
    }

    public void setProperty_type_id(Integer property_type_id) {
        this.property_type_id = property_type_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getProperty_desc() {
        return property_desc;
    }

    public void setProperty_desc(String property_desc) {
        this.property_desc = property_desc;
    }

    @Override
    public String toString() {
        return "PropertyType{" +
                "property_type_id=" + property_type_id +
                ", property_name='" + property_name + '\'' +
                ", property_desc='" + property_desc + '\'' +
                '}';
    }
}
