package com.innoq.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.innoq.domain.Brand} entity. This class is used
 * in {@link com.innoq.web.rest.BrandResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /brands?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BrandCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private BooleanFilter deleted;

    private BooleanFilter isActive;

    private InstantFilter createdAt;

    private InstantFilter modifiedAt;

    private StringFilter createdById;

    private StringFilter modifiedById;

    private StringFilter nameEnUs;

    private StringFilter nameDeDe;

    private StringFilter code;

    private StringFilter ownerUserId;

    private StringFilter assignedUserId;

    private LongFilter productId;

    public BrandCriteria() {
    }

    public BrandCriteria(BrandCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.deleted = other.deleted == null ? null : other.deleted.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifiedAt = other.modifiedAt == null ? null : other.modifiedAt.copy();
        this.createdById = other.createdById == null ? null : other.createdById.copy();
        this.modifiedById = other.modifiedById == null ? null : other.modifiedById.copy();
        this.nameEnUs = other.nameEnUs == null ? null : other.nameEnUs.copy();
        this.nameDeDe = other.nameDeDe == null ? null : other.nameDeDe.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.ownerUserId = other.ownerUserId == null ? null : other.ownerUserId.copy();
        this.assignedUserId = other.assignedUserId == null ? null : other.assignedUserId.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
    }

    @Override
    public BrandCriteria copy() {
        return new BrandCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BooleanFilter getDeleted() {
        return deleted;
    }

    public void setDeleted(BooleanFilter deleted) {
        this.deleted = deleted;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public InstantFilter getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(InstantFilter modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public StringFilter getCreatedById() {
        return createdById;
    }

    public void setCreatedById(StringFilter createdById) {
        this.createdById = createdById;
    }

    public StringFilter getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(StringFilter modifiedById) {
        this.modifiedById = modifiedById;
    }

    public StringFilter getNameEnUs() {
        return nameEnUs;
    }

    public void setNameEnUs(StringFilter nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    public StringFilter getNameDeDe() {
        return nameDeDe;
    }

    public void setNameDeDe(StringFilter nameDeDe) {
        this.nameDeDe = nameDeDe;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(StringFilter ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public StringFilter getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(StringFilter assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public LongFilter getProductId() {
        return productId;
    }

    public void setProductId(LongFilter productId) {
        this.productId = productId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BrandCriteria that = (BrandCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifiedAt, that.modifiedAt) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(modifiedById, that.modifiedById) &&
            Objects.equals(nameEnUs, that.nameEnUs) &&
            Objects.equals(nameDeDe, that.nameDeDe) &&
            Objects.equals(code, that.code) &&
            Objects.equals(ownerUserId, that.ownerUserId) &&
            Objects.equals(assignedUserId, that.assignedUserId) &&
            Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        deleted,
        isActive,
        createdAt,
        modifiedAt,
        createdById,
        modifiedById,
        nameEnUs,
        nameDeDe,
        code,
        ownerUserId,
        assignedUserId,
        productId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BrandCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (deleted != null ? "deleted=" + deleted + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
                (modifiedAt != null ? "modifiedAt=" + modifiedAt + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
                (nameEnUs != null ? "nameEnUs=" + nameEnUs + ", " : "") +
                (nameDeDe != null ? "nameDeDe=" + nameDeDe + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (ownerUserId != null ? "ownerUserId=" + ownerUserId + ", " : "") +
                (assignedUserId != null ? "assignedUserId=" + assignedUserId + ", " : "") +
                (productId != null ? "productId=" + productId + ", " : "") +
            "}";
    }

}
