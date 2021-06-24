package com.github.legionarks.model.property;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "OUTSTANDING")
    private Boolean outstanding;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY")
    private Category category;

    @OneToMany(mappedBy = "property")
    private Set<PropertyAttribute> attributes;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "PROPERTY_ID"), inverseJoinColumns = @JoinColumn(name = "FEATURE_ID"))
    private Set<Feature> features;

    @OneToMany(mappedBy = "property")
    private Set<Media> medias;

    @Column(name = "ADDRESS")
    private String address;

    @Lob
    @Column(name = "SUMMARY")
    private String summary;

    @OneToOne
    @JoinColumn(name = "LOCATION")
    private Location location;

    @Column(name = "ADD_DATE")
    private Date addDate;

    @Column(name = "EDIT_DATE")
    private Date editDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Boolean outstanding) {
        this.outstanding = outstanding;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<PropertyAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<PropertyAttribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Set<Media> getMedias() {
        return medias;
    }

    public void setMedias(Set<Media> medias) {
        this.medias = medias;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

}
