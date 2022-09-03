package com.freelancer.RealEstate.entity;


import com.freelancer.RealEstate.config.JpaConverterJson;
import com.freelancer.RealEstate.config.ListToJsonConverter;
import com.freelancer.RealEstate.config.ObjectConversion;
import com.freelancer.RealEstate.model.Bedrooms;
import com.freelancer.RealEstate.model.Features;
import com.freelancer.RealEstate.model.OwnerDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This Class is used for handle property entity.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_property")
public class Property implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Integer propertyId;

    @Column(name = "owner_details", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<OwnerDetails> ownerDetails;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @Column(name = "property_for")
    private String property_for;

    @Column(name = "property_type")
    private Integer property_type;

    @Column(name = "property_features", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> property_features;

    @Column(name = "property_area", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> property_area;

    @Column(name = "age_of_property")
    private String age_of_property;

    @Column(name = "rent_lease_details", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> rent_lease_details;

    @Column(name = "ownership_status")
    private String ownership_status;

    @Column(name = "status_of_water", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> status_of_water;

    @Column(name = "price_details", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> price_details;

    @Column(name = "bedroom_list", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<Bedrooms> bedroomsList;

    @Column(name = "is_feature_property")
    private boolean is_feature_property;

    @Column(name = "is_hot_listed")
    private boolean is_hot_listed;

    @Column(name = "transaction_type")
    private String transaction_type;

    @Column(name = "possession_status")
    private String possession_status;

    @Column(name = "property_desc", columnDefinition = "json")
    @Convert(converter = ListToJsonConverter.class)
    private List<String> property_desc;

    @Column(name = "is_property_active")
    private Boolean is_property_active;

    @Column(name = "extra_details", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> extra_details;

    @Column(name = "balcony_list", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<Bedrooms> balcony_list;

    @Column(name = "leaving_room_details", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> leaving_room_details;

    @Column(name = "lobby_details", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> lobby_details;

    @Column(name = "kitchen_details", columnDefinition = "json")
    @Convert(converter = JpaConverterJson.class)
    private Map<String, Object> kitchen_details;

    @Column(name = "overlooking", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<Features> overlooking;


    @Column(name = "amenities", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<Features> amenities;


    @Column(name = "flooring", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<Features> flooring;

    @Column(name = "furnished_items", columnDefinition = "json")
    @Convert(converter = ObjectConversion.class)
    private List<Features> furnished_items;

    @Column(name = "covered_image")
    private String covered_image;

    @Column(name = "property_name")
    private String property_name;

    @OneToMany
    private List<PropertyMedia> propertyMedia;

    @OneToMany
    private List<PropertyMedia> documentList;

    @OneToMany
    private List<PropertyMedia> otherDocs;

    @OneToMany
    private List<PropertyMedia> societyRegCertificate;

    @OneToMany
    private List<PropertyMedia> bankDocs;

    @OneToMany
    private List<PropertyMedia> completionDocs;

    @OneToMany
    private List<PropertyMedia> poa_docs;

    public String getCovered_image() {
        return covered_image;
    }

    public void setCovered_image(String covered_image) {
        this.covered_image = covered_image;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public List<String> getProperty_desc() {
        return property_desc;
    }

    public void setProperty_desc(List<String> property_desc) {
        this.property_desc = property_desc;
    }

    public List<PropertyMedia> getPoa_docs() {
        return poa_docs;
    }

    public void setPoa_docs(List<PropertyMedia> poa_docs) {
        this.poa_docs = poa_docs;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public List<OwnerDetails> getOwnerDetails() {
        return ownerDetails;
    }

    public void setOwnerDetails(List<OwnerDetails> ownerDetails) {
        this.ownerDetails = ownerDetails;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public String getProperty_for() {
        return property_for;
    }

    public void setProperty_for(String property_for) {
        this.property_for = property_for;
    }

    public Integer getProperty_type() {
        return property_type;
    }

    public void setProperty_type(Integer property_type) {
        this.property_type = property_type;
    }

    public Map<String, Object> getProperty_features() {
        return property_features;
    }

    public void setProperty_features(Map<String, Object> property_features) {
        this.property_features = property_features;
    }

    public Map<String, Object> getProperty_area() {
        return property_area;
    }

    public void setProperty_area(Map<String, Object> property_area) {
        this.property_area = property_area;
    }

    public String getAge_of_property() {
        return age_of_property;
    }

    public void setAge_of_property(String age_of_property) {
        this.age_of_property = age_of_property;
    }

    public Map<String, Object> getRent_lease_details() {
        return rent_lease_details;
    }

    public void setRent_lease_details(Map<String, Object> rent_lease_details) {
        this.rent_lease_details = rent_lease_details;
    }

    public String getOwnership_status() {
        return ownership_status;
    }

    public void setOwnership_status(String ownership_status) {
        this.ownership_status = ownership_status;
    }

    public Map<String, Object> getStatus_of_water() {
        return status_of_water;
    }

    public void setStatus_of_water(Map<String, Object> status_of_water) {
        this.status_of_water = status_of_water;
    }

    public Map<String, Object> getPrice_details() {
        return price_details;
    }

    public void setPrice_details(Map<String, Object> price_details) {
        this.price_details = price_details;
    }

    public List<Bedrooms> getBedroomsList() {
        return bedroomsList;
    }

    public void setBedroomsList(List<Bedrooms> bedroomsList) {
        this.bedroomsList = bedroomsList;
    }

    public boolean isIs_feature_property() {
        return is_feature_property;
    }

    public void setIs_feature_property(boolean is_feature_property) {
        this.is_feature_property = is_feature_property;
    }

    public boolean isIs_hot_listed() {
        return is_hot_listed;
    }

    public void setIs_hot_listed(boolean is_hot_listed) {
        this.is_hot_listed = is_hot_listed;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getPossession_status() {
        return possession_status;
    }

    public void setPossession_status(String possession_status) {
        this.possession_status = possession_status;
    }


    public Boolean getIs_property_active() {
        return is_property_active;
    }

    public void setIs_property_active(Boolean is_property_active) {
        this.is_property_active = is_property_active;
    }

    public Map<String, Object> getExtra_details() {
        return extra_details;
    }

    public void setExtra_details(Map<String, Object> extra_details) {
        this.extra_details = extra_details;
    }

    public List<Bedrooms> getBalcony_list() {
        return balcony_list;
    }

    public void setBalcony_list(List<Bedrooms> balcony_list) {
        this.balcony_list = balcony_list;
    }

    public Map<String, Object> getLeaving_room_details() {
        return leaving_room_details;
    }

    public void setLeaving_room_details(Map<String, Object> leaving_room_details) {
        this.leaving_room_details = leaving_room_details;
    }

    public Map<String, Object> getLobby_details() {
        return lobby_details;
    }

    public void setLobby_details(Map<String, Object> lobby_details) {
        this.lobby_details = lobby_details;
    }

    public Map<String, Object> getKitchen_details() {
        return kitchen_details;
    }

    public void setKitchen_details(Map<String, Object> kitchen_details) {
        this.kitchen_details = kitchen_details;
    }

    public List<PropertyMedia> getPropertyMedia() {
        return propertyMedia;
    }

    public void setPropertyMedia(List<PropertyMedia> propertyMedia) {
        this.propertyMedia = propertyMedia;
    }

    public List<PropertyMedia> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<PropertyMedia> documentList) {
        this.documentList = documentList;
    }

    public List<PropertyMedia> getOtherDocs() {
        return otherDocs;
    }

    public void setOtherDocs(List<PropertyMedia> otherDocs) {
        this.otherDocs = otherDocs;
    }

    public List<PropertyMedia> getSocietyRegCertificate() {
        return societyRegCertificate;
    }

    public void setSocietyRegCertificate(List<PropertyMedia> societyRegCertificate) {
        this.societyRegCertificate = societyRegCertificate;
    }

    public List<PropertyMedia> getBankDocs() {
        return bankDocs;
    }

    public void setBankDocs(List<PropertyMedia> bankDocs) {
        this.bankDocs = bankDocs;
    }

    public List<Features> getOverlooking() {
        return overlooking;
    }

    public void setOverlooking(List<Features> overlooking) {
        this.overlooking = overlooking;
    }

    public List<Features> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Features> amenities) {
        this.amenities = amenities;
    }

    public List<Features> getFlooring() {
        return flooring;
    }

    public void setFlooring(List<Features> flooring) {
        this.flooring = flooring;
    }

    public List<Features> getFurnished_items() {
        return furnished_items;
    }

    public void setFurnished_items(List<Features> furnished_items) {
        this.furnished_items = furnished_items;
    }

    public List<PropertyMedia> getCompletionDocs() {
        return completionDocs;
    }

    public void setCompletionDocs(List<PropertyMedia> completionDocs) {
        this.completionDocs = completionDocs;
    }
}
