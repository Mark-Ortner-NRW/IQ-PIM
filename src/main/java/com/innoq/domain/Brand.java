package com.innoq.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Brand.
 */
@Entity
@Table(name = "brand")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "deleted")
    private Boolean deleted;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Size(max = 24)
    @Column(name = "created_by_id", length = 24)
    private String createdById;

    @Size(max = 24)
    @Column(name = "modified_by_id", length = 24)
    private String modifiedById;

    @Size(max = 255)
    @Column(name = "name_en_us", length = 255)
    private String nameEnUs;

    @Lob
    @Column(name = "description_en_us")
    private String descriptionEnUs;

    @Size(max = 255)
    @Column(name = "name_de_de", length = 255)
    private String nameDeDe;

    @Lob
    @Column(name = "description_de_de")
    private String descriptionDeDe;

    @Size(max = 255)
    @Column(name = "code", length = 255)
    private String code;

    @Size(max = 24)
    @Column(name = "owner_user_id", length = 24)
    private String ownerUserId;

    @Size(max = 24)
    @Column(name = "assigned_user_id", length = 24)
    private String assignedUserId;

    @OneToMany(mappedBy = "brand")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Brand name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Brand deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public Brand description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Brand isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Brand createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public Brand modifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getCreatedById() {
        return createdById;
    }

    public Brand createdById(String createdById) {
        this.createdById = createdById;
        return this;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getModifiedById() {
        return modifiedById;
    }

    public Brand modifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
        return this;
    }

    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }

    public String getNameEnUs() {
        return nameEnUs;
    }

    public Brand nameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
        return this;
    }

    public void setNameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    public String getDescriptionEnUs() {
        return descriptionEnUs;
    }

    public Brand descriptionEnUs(String descriptionEnUs) {
        this.descriptionEnUs = descriptionEnUs;
        return this;
    }

    public void setDescriptionEnUs(String descriptionEnUs) {
        this.descriptionEnUs = descriptionEnUs;
    }

    public String getNameDeDe() {
        return nameDeDe;
    }

    public Brand nameDeDe(String nameDeDe) {
        this.nameDeDe = nameDeDe;
        return this;
    }

    public void setNameDeDe(String nameDeDe) {
        this.nameDeDe = nameDeDe;
    }

    public String getDescriptionDeDe() {
        return descriptionDeDe;
    }

    public Brand descriptionDeDe(String descriptionDeDe) {
        this.descriptionDeDe = descriptionDeDe;
        return this;
    }

    public void setDescriptionDeDe(String descriptionDeDe) {
        this.descriptionDeDe = descriptionDeDe;
    }

    public String getCode() {
        return code;
    }

    public Brand code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public Brand ownerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
        return this;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public Brand assignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
        return this;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Brand products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Brand addProduct(Product product) {
        this.products.add(product);
        product.setBrand(this);
        return this;
    }

    public Brand removeProduct(Product product) {
        this.products.remove(product);
        product.setBrand(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }
        return id != null && id.equals(((Brand) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Brand{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", description='" + getDescription() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifiedAt='" + getModifiedAt() + "'" +
            ", createdById='" + getCreatedById() + "'" +
            ", modifiedById='" + getModifiedById() + "'" +
            ", nameEnUs='" + getNameEnUs() + "'" +
            ", descriptionEnUs='" + getDescriptionEnUs() + "'" +
            ", nameDeDe='" + getNameDeDe() + "'" +
            ", descriptionDeDe='" + getDescriptionDeDe() + "'" +
            ", code='" + getCode() + "'" +
            ", ownerUserId='" + getOwnerUserId() + "'" +
            ", assignedUserId='" + getAssignedUserId() + "'" +
            "}";
    }
}
