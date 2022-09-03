package com.freelancer.RealEstate.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tab_owner_activity")
public class OwnerActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activity_id;

    @Column(nullable = false, updatable = false, name = "created_on")
    @CreationTimestamp
    private Date created_on;

    @Column(nullable = false, updatable = false, name = "updated_at")
    @UpdateTimestamp
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    Owner owner;

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
