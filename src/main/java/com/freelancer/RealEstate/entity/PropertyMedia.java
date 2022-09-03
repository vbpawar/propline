package com.freelancer.RealEstate.entity;


import javax.persistence.*;

/**
 * This is used for handle media files.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_property_media")
public class PropertyMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Integer media_id;

    @Column(name = "property_id")
    private Integer property_id;

    @Column(name = "file_path")
    private String file_path;

    @Column(name = "doc_type")
    private String doc_type;

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }

    public Integer getMedia_id() {
        return media_id;
    }

    public void setMedia_id(Integer media_id) {
        this.media_id = media_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
