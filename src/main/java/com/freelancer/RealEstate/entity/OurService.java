package com.freelancer.RealEstate.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tab_services")
public class OurService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer service_id;

    @Column(name = "icon")
    private String icon;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "msg_desc")
    private String msg_desc;

    @CreationTimestamp
    @Column(name = "created_on")
    private Date created_on;

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return msg_desc;
    }

    public void setDesc(String desc) {
        this.msg_desc = desc;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
}
