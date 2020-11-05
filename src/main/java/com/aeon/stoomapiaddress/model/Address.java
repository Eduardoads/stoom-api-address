package com.aeon.stoomapiaddress.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name ="stoom_Address")
public class Address implements Serializable {

    @Id
    @Column(name="id", nullable=false)
    private UUID id;

    @Column(name="streetName", nullable=false)
    private String streetName;

    @Column(name="number", nullable=false)
    private Integer number;

    @Column(name="complement")
    private String complement;

    @Column(name="neighbourhood", nullable=false)
    private String neighbourhood;

    @Column(name="city", nullable=false)
    private String city;

    @Column(name="state", nullable=false)
    private String state;

    @Column(name="country", nullable=false)
    private String country;

    @Column(name="zipcode", nullable=false)
    private String zipcode;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }
    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }
    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) &&
                Objects.equals(getStreetName(), address.getStreetName()) &&
                Objects.equals(getNumber(), address.getNumber()) &&
                Objects.equals(getComplement(), address.getComplement()) &&
                Objects.equals(getNeighbourhood(), address.getNeighbourhood()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getState(), address.getState()) &&
                Objects.equals(getCountry(), address.getCountry()) &&
                Objects.equals(getZipcode(), address.getZipcode()) &&
                Objects.equals(getLatitude(), address.getLatitude()) &&
                Objects.equals(getLongitude(), address.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getStreetName(),
                getNumber(),
                getComplement(),
                getNeighbourhood(),
                getCity(),
                getState(),
                getCountry(),
                getZipcode(),
                getLatitude(),
                getLongitude());
    }
}
