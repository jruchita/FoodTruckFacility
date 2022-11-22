package com.ruchita.learningspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="FOODTRUCKDETAIL")
public class FoodTruckEntity {

    @Id
    @Column(name = "locationid")
    private long objectid;
    @Column(name = "APPLICANT")
    private String applicant;
    @Column(name = "EXPIRATIONDATE")
    private String expirationdate;
    @Column(name = "LOCATIONDESCRIPTION")
    private String locationdescription;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "LOCATION")
    private String location;

    @JsonProperty("objectid")
    public long getObjectId() {
        return objectid;
    }

    public void setObjectId(long objectid) {
        this.objectid = objectid;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    @JsonProperty("expirationdate")
    public String getExpirationDate() {
        return expirationdate;
    }

    public void setExpirationDate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    @JsonProperty("locationdescription")
    public String getLocationDescription() {
        return locationdescription;
    }

    public void setLocationDescription(String locationdescription) {
        this.locationdescription = locationdescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
