package com.innoq.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.innoq.domain.Brand} entity.
 */
public class BrandDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String name;

    private Boolean deleted;

    @Lob
    private String description;

    @NotNull
    private Boolean isActive;

    private Instant createdAt;

    private Instant modifiedAt;

    @Size(max = 24)
    private String createdById;

    @Size(max = 24)
    private String modifiedById;

    @Size(max = 255)
    private String nameEnUs;

    @Lob
    private String descriptionEnUs;

    @Size(max = 255)
    private String nameDeDe;

    @Lob
    private String descriptionDeDe;

    @Size(max = 255)
    private String code;

    @Size(max = 24)
    private String ownerUserId;

    @Size(max = 24)
    private String assignedUserId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }

    public String getNameEnUs() {
        return nameEnUs;
    }

    public void setNameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    public String getDescriptionEnUs() {
        return descriptionEnUs;
    }

    public void setDescriptionEnUs(String descriptionEnUs) {
        this.descriptionEnUs = descriptionEnUs;
    }

    public String getNameDeDe() {
        return nameDeDe;
    }

    public void setNameDeDe(String nameDeDe) {
        this.nameDeDe = nameDeDe;
    }

    public String getDescriptionDeDe() {
        return descriptionDeDe;
    }

    public void setDescriptionDeDe(String descriptionDeDe) {
        this.descriptionDeDe = descriptionDeDe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BrandDTO)) {
            return false;
        }

        return id != null && id.equals(((BrandDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BrandDTO{" +
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
