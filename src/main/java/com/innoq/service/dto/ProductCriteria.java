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
 * Criteria class for the {@link com.innoq.domain.Product} entity. This class is used
 * in {@link com.innoq.web.rest.ProductResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /products?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProductCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private BooleanFilter deleted;

    private InstantFilter createdAt;

    private InstantFilter modifiedAt;

    private StringFilter sku;

    private BooleanFilter isActive;

    private StringFilter type;

    private DoubleFilter amount;

    private StringFilter createdById;

    private StringFilter modifiedById;

    private StringFilter productFamilyId;

    private StringFilter nameEnUs;

    private StringFilter nameDeDe;

    private StringFilter productStatus;

    private DoubleFilter price;

    private StringFilter currency;

    private StringFilter priceCurrency;

    private StringFilter taxId;

    private StringFilter ean;

    private StringFilter mpn;

    private StringFilter packagingId;

    private DoubleFilter uvp;

    private StringFilter ownerUserId;

    private StringFilter assignedUserId;

    private DoubleFilter finalPrice;

    private StringFilter finalPriceCurrency;

    private StringFilter productSerieId;

    private StringFilter catalogId;

    private DoubleFilter basePriceAmount;

    private DoubleFilter packedAmount;

    private StringFilter imageId;

    private StringFilter measuringUnitId;

    private StringFilter deliveryStatus;

    private StringFilter deliveryStatusDeDe;

    private StringFilter deliveryStatusEnUs;

    private LongFilter brandId;

    public ProductCriteria() {
    }

    public ProductCriteria(ProductCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.deleted = other.deleted == null ? null : other.deleted.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifiedAt = other.modifiedAt == null ? null : other.modifiedAt.copy();
        this.sku = other.sku == null ? null : other.sku.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.createdById = other.createdById == null ? null : other.createdById.copy();
        this.modifiedById = other.modifiedById == null ? null : other.modifiedById.copy();
        this.productFamilyId = other.productFamilyId == null ? null : other.productFamilyId.copy();
        this.nameEnUs = other.nameEnUs == null ? null : other.nameEnUs.copy();
        this.nameDeDe = other.nameDeDe == null ? null : other.nameDeDe.copy();
        this.productStatus = other.productStatus == null ? null : other.productStatus.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.currency = other.currency == null ? null : other.currency.copy();
        this.priceCurrency = other.priceCurrency == null ? null : other.priceCurrency.copy();
        this.taxId = other.taxId == null ? null : other.taxId.copy();
        this.ean = other.ean == null ? null : other.ean.copy();
        this.mpn = other.mpn == null ? null : other.mpn.copy();
        this.packagingId = other.packagingId == null ? null : other.packagingId.copy();
        this.uvp = other.uvp == null ? null : other.uvp.copy();
        this.ownerUserId = other.ownerUserId == null ? null : other.ownerUserId.copy();
        this.assignedUserId = other.assignedUserId == null ? null : other.assignedUserId.copy();
        this.finalPrice = other.finalPrice == null ? null : other.finalPrice.copy();
        this.finalPriceCurrency = other.finalPriceCurrency == null ? null : other.finalPriceCurrency.copy();
        this.productSerieId = other.productSerieId == null ? null : other.productSerieId.copy();
        this.catalogId = other.catalogId == null ? null : other.catalogId.copy();
        this.basePriceAmount = other.basePriceAmount == null ? null : other.basePriceAmount.copy();
        this.packedAmount = other.packedAmount == null ? null : other.packedAmount.copy();
        this.imageId = other.imageId == null ? null : other.imageId.copy();
        this.measuringUnitId = other.measuringUnitId == null ? null : other.measuringUnitId.copy();
        this.deliveryStatus = other.deliveryStatus == null ? null : other.deliveryStatus.copy();
        this.deliveryStatusDeDe = other.deliveryStatusDeDe == null ? null : other.deliveryStatusDeDe.copy();
        this.deliveryStatusEnUs = other.deliveryStatusEnUs == null ? null : other.deliveryStatusEnUs.copy();
        this.brandId = other.brandId == null ? null : other.brandId.copy();
    }

    @Override
    public ProductCriteria copy() {
        return new ProductCriteria(this);
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

    public StringFilter getSku() {
        return sku;
    }

    public void setSku(StringFilter sku) {
        this.sku = sku;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public DoubleFilter getAmount() {
        return amount;
    }

    public void setAmount(DoubleFilter amount) {
        this.amount = amount;
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

    public StringFilter getProductFamilyId() {
        return productFamilyId;
    }

    public void setProductFamilyId(StringFilter productFamilyId) {
        this.productFamilyId = productFamilyId;
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

    public StringFilter getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(StringFilter productStatus) {
        this.productStatus = productStatus;
    }

    public DoubleFilter getPrice() {
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public StringFilter getCurrency() {
        return currency;
    }

    public void setCurrency(StringFilter currency) {
        this.currency = currency;
    }

    public StringFilter getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(StringFilter priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public StringFilter getTaxId() {
        return taxId;
    }

    public void setTaxId(StringFilter taxId) {
        this.taxId = taxId;
    }

    public StringFilter getEan() {
        return ean;
    }

    public void setEan(StringFilter ean) {
        this.ean = ean;
    }

    public StringFilter getMpn() {
        return mpn;
    }

    public void setMpn(StringFilter mpn) {
        this.mpn = mpn;
    }

    public StringFilter getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(StringFilter packagingId) {
        this.packagingId = packagingId;
    }

    public DoubleFilter getUvp() {
        return uvp;
    }

    public void setUvp(DoubleFilter uvp) {
        this.uvp = uvp;
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

    public DoubleFilter getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(DoubleFilter finalPrice) {
        this.finalPrice = finalPrice;
    }

    public StringFilter getFinalPriceCurrency() {
        return finalPriceCurrency;
    }

    public void setFinalPriceCurrency(StringFilter finalPriceCurrency) {
        this.finalPriceCurrency = finalPriceCurrency;
    }

    public StringFilter getProductSerieId() {
        return productSerieId;
    }

    public void setProductSerieId(StringFilter productSerieId) {
        this.productSerieId = productSerieId;
    }

    public StringFilter getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(StringFilter catalogId) {
        this.catalogId = catalogId;
    }

    public DoubleFilter getBasePriceAmount() {
        return basePriceAmount;
    }

    public void setBasePriceAmount(DoubleFilter basePriceAmount) {
        this.basePriceAmount = basePriceAmount;
    }

    public DoubleFilter getPackedAmount() {
        return packedAmount;
    }

    public void setPackedAmount(DoubleFilter packedAmount) {
        this.packedAmount = packedAmount;
    }

    public StringFilter getImageId() {
        return imageId;
    }

    public void setImageId(StringFilter imageId) {
        this.imageId = imageId;
    }

    public StringFilter getMeasuringUnitId() {
        return measuringUnitId;
    }

    public void setMeasuringUnitId(StringFilter measuringUnitId) {
        this.measuringUnitId = measuringUnitId;
    }

    public StringFilter getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(StringFilter deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public StringFilter getDeliveryStatusDeDe() {
        return deliveryStatusDeDe;
    }

    public void setDeliveryStatusDeDe(StringFilter deliveryStatusDeDe) {
        this.deliveryStatusDeDe = deliveryStatusDeDe;
    }

    public StringFilter getDeliveryStatusEnUs() {
        return deliveryStatusEnUs;
    }

    public void setDeliveryStatusEnUs(StringFilter deliveryStatusEnUs) {
        this.deliveryStatusEnUs = deliveryStatusEnUs;
    }

    public LongFilter getBrandId() {
        return brandId;
    }

    public void setBrandId(LongFilter brandId) {
        this.brandId = brandId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductCriteria that = (ProductCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifiedAt, that.modifiedAt) &&
            Objects.equals(sku, that.sku) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(type, that.type) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(modifiedById, that.modifiedById) &&
            Objects.equals(productFamilyId, that.productFamilyId) &&
            Objects.equals(nameEnUs, that.nameEnUs) &&
            Objects.equals(nameDeDe, that.nameDeDe) &&
            Objects.equals(productStatus, that.productStatus) &&
            Objects.equals(price, that.price) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(priceCurrency, that.priceCurrency) &&
            Objects.equals(taxId, that.taxId) &&
            Objects.equals(ean, that.ean) &&
            Objects.equals(mpn, that.mpn) &&
            Objects.equals(packagingId, that.packagingId) &&
            Objects.equals(uvp, that.uvp) &&
            Objects.equals(ownerUserId, that.ownerUserId) &&
            Objects.equals(assignedUserId, that.assignedUserId) &&
            Objects.equals(finalPrice, that.finalPrice) &&
            Objects.equals(finalPriceCurrency, that.finalPriceCurrency) &&
            Objects.equals(productSerieId, that.productSerieId) &&
            Objects.equals(catalogId, that.catalogId) &&
            Objects.equals(basePriceAmount, that.basePriceAmount) &&
            Objects.equals(packedAmount, that.packedAmount) &&
            Objects.equals(imageId, that.imageId) &&
            Objects.equals(measuringUnitId, that.measuringUnitId) &&
            Objects.equals(deliveryStatus, that.deliveryStatus) &&
            Objects.equals(deliveryStatusDeDe, that.deliveryStatusDeDe) &&
            Objects.equals(deliveryStatusEnUs, that.deliveryStatusEnUs) &&
            Objects.equals(brandId, that.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        deleted,
        createdAt,
        modifiedAt,
        sku,
        isActive,
        type,
        amount,
        createdById,
        modifiedById,
        productFamilyId,
        nameEnUs,
        nameDeDe,
        productStatus,
        price,
        currency,
        priceCurrency,
        taxId,
        ean,
        mpn,
        packagingId,
        uvp,
        ownerUserId,
        assignedUserId,
        finalPrice,
        finalPriceCurrency,
        productSerieId,
        catalogId,
        basePriceAmount,
        packedAmount,
        imageId,
        measuringUnitId,
        deliveryStatus,
        deliveryStatusDeDe,
        deliveryStatusEnUs,
        brandId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (deleted != null ? "deleted=" + deleted + ", " : "") +
                (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
                (modifiedAt != null ? "modifiedAt=" + modifiedAt + ", " : "") +
                (sku != null ? "sku=" + sku + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
                (productFamilyId != null ? "productFamilyId=" + productFamilyId + ", " : "") +
                (nameEnUs != null ? "nameEnUs=" + nameEnUs + ", " : "") +
                (nameDeDe != null ? "nameDeDe=" + nameDeDe + ", " : "") +
                (productStatus != null ? "productStatus=" + productStatus + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (priceCurrency != null ? "priceCurrency=" + priceCurrency + ", " : "") +
                (taxId != null ? "taxId=" + taxId + ", " : "") +
                (ean != null ? "ean=" + ean + ", " : "") +
                (mpn != null ? "mpn=" + mpn + ", " : "") +
                (packagingId != null ? "packagingId=" + packagingId + ", " : "") +
                (uvp != null ? "uvp=" + uvp + ", " : "") +
                (ownerUserId != null ? "ownerUserId=" + ownerUserId + ", " : "") +
                (assignedUserId != null ? "assignedUserId=" + assignedUserId + ", " : "") +
                (finalPrice != null ? "finalPrice=" + finalPrice + ", " : "") +
                (finalPriceCurrency != null ? "finalPriceCurrency=" + finalPriceCurrency + ", " : "") +
                (productSerieId != null ? "productSerieId=" + productSerieId + ", " : "") +
                (catalogId != null ? "catalogId=" + catalogId + ", " : "") +
                (basePriceAmount != null ? "basePriceAmount=" + basePriceAmount + ", " : "") +
                (packedAmount != null ? "packedAmount=" + packedAmount + ", " : "") +
                (imageId != null ? "imageId=" + imageId + ", " : "") +
                (measuringUnitId != null ? "measuringUnitId=" + measuringUnitId + ", " : "") +
                (deliveryStatus != null ? "deliveryStatus=" + deliveryStatus + ", " : "") +
                (deliveryStatusDeDe != null ? "deliveryStatusDeDe=" + deliveryStatusDeDe + ", " : "") +
                (deliveryStatusEnUs != null ? "deliveryStatusEnUs=" + deliveryStatusEnUs + ", " : "") +
                (brandId != null ? "brandId=" + brandId + ", " : "") +
            "}";
    }

}
