package com.freelancer.RealEstate.entity;

import com.freelancer.RealEstate.config.ObjectConversion;
import com.freelancer.RealEstate.model.MissionModel;

import javax.persistence.*;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "tab_mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mission_details", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<MissionModel> missionModels;

    @Column(name = "image")
    private String image;

    @Column(name = "isActive", columnDefinition = "BIT(1) default false")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MissionModel> getMissionModels() {
        return missionModels;
    }

    public void setMissionModels(List<MissionModel> missionModels) {
        this.missionModels = missionModels;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
