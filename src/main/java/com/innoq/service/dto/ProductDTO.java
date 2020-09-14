package com.innoq.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.innoq.domain.Product} entity.
 */
public class ProductDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String name;

    private Boolean deleted;

    private Instant createdAt;

    private Instant modifiedAt;

    @Size(max = 255)
    private String sku;

    @NotNull
    private Boolean isActive;

    @Size(max = 255)
    private String type;

    private Double amount;

    @Size(max = 24)
    private String createdById;

    @Size(max = 24)
    private String modifiedById;

    @Size(max = 24)
    private String productFamilyId;

    @Size(max = 255)
    private String nameEnUs;

    @Size(max = 255)
    private String nameDeDe;

    @Size(max = 255)
    private String productStatus;

    private Double price;

    @Size(max = 255)
    private String currency;

    @Size(max = 255)
    private String priceCurrency;

    @Size(max = 24)
    private String taxId;

    @Size(max = 255)
    private String ean;

    @Size(max = 255)
    private String mpn;

    @Size(max = 24)
    private String packagingId;

    private Double uvp;

    /**
     * default={[]}
     */
    @ApiModelProperty(value = "default={[]}")
    @Lob
    private String tag;

    @Size(max = 24)
    private String ownerUserId;

    @Size(max = 24)
    private String assignedUserId;

    private Double finalPrice;

    @Size(max = 255)
    private String finalPriceCurrency;

    @Lob
    private String longDescription;

    @Lob
    private String longDescriptionDeDe;

    @Lob
    private String longDescriptionEnUs;

    @Size(max = 24)
    private String productSerieId;

    @Lob
    private String data;

    @Size(max = 24)
    private String catalogId;

    private Double basePriceAmount;

    private Double packedAmount;

    @Size(max = 24)
    private String imageId;

    @Size(max = 24)
    private String measuringUnitId;

    @Size(max = 255)
    private String deliveryStatus;

    @Size(max = 255)
    private String deliveryStatusDeDe;

    @Size(max = 255)
    private String deliveryStatusEnUs;

    @Lob
    private String supplied;

    @Lob
    private String suppliedDeDe;

    @Lob
    private String suppliedEnUs;


    private Long brandId;
    
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public String getProductFamilyId() {
        return productFamilyId;
    }

    public void setProductFamilyId(String productFamilyId) {
        this.productFamilyId = productFamilyId;
    }

    public String getNameEnUs() {
        return nameEnUs;
    }

    public void setNameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    public String getNameDeDe() {
        return nameDeDe;
    }

    public void setNameDeDe(String nameDeDe) {
        this.nameDeDe = nameDeDe;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(String packagingId) {
        this.packagingId = packagingId;
    }

    public Double getUvp() {
        return uvp;
    }

    public void setUvp(Double uvp) {
        this.uvp = uvp;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getFinalPriceCurrency() {
        return finalPriceCurrency;
    }

    public void setFinalPriceCurrency(String finalPriceCurrency) {
        this.finalPriceCurrency = finalPriceCurrency;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescriptionDeDe() {
        return longDescriptionDeDe;
    }

    public void setLongDescriptionDeDe(String longDescriptionDeDe) {
        this.longDescriptionDeDe = longDescriptionDeDe;
    }

    public String getLongDescriptionEnUs() {
        return longDescriptionEnUs;
    }

    public void setLongDescriptionEnUs(String longDescriptionEnUs) {
        this.longDescriptionEnUs = longDescriptionEnUs;
    }

    public String getProductSerieId() {
        return productSerieId;
    }

    public void setProductSerieId(String productSerieId) {
        this.productSerieId = productSerieId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public Double getBasePriceAmount() {
        return basePriceAmount;
    }

    public void setBasePriceAmount(Double basePriceAmount) {
        this.basePriceAmount = basePriceAmount;
    }

    public Double getPackedAmount() {
        return packedAmount;
    }

    public void setPackedAmount(Double packedAmount) {
        this.packedAmount = packedAmount;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getMeasuringUnitId() {
        return measuringUnitId;
    }

    public void setMeasuringUnitId(String measuringUnitId) {
        this.measuringUnitId = measuringUnitId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatusDeDe() {
        return deliveryStatusDeDe;
    }

    public void setDeliveryStatusDeDe(String deliveryStatusDeDe) {
        this.deliveryStatusDeDe = deliveryStatusDeDe;
    }

    public String getDeliveryStatusEnUs() {
        return deliveryStatusEnUs;
    }

    public void setDeliveryStatusEnUs(String deliveryStatusEnUs) {
        this.deliveryStatusEnUs = deliveryStatusEnUs;
    }

    public String getSupplied() {
        return supplied;
    }

    public void setSupplied(String supplied) {
        this.supplied = supplied;
    }

    public String getSuppliedDeDe() {
        return suppliedDeDe;
    }

    public void setSuppliedDeDe(String suppliedDeDe) {
        this.suppliedDeDe = suppliedDeDe;
    }

    public String getSuppliedEnUs() {
        return suppliedEnUs;
    }

    public void setSuppliedEnUs(String suppliedEnUs) {
        this.suppliedEnUs = suppliedEnUs;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifiedAt='" + getModifiedAt() + "'" +
            ", sku='" + getSku() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", type='" + getType() + "'" +
            ", amount=" + getAmount() +
            ", createdById='" + getCreatedById() + "'" +
            ", modifiedById='" + getModifiedById() + "'" +
            ", productFamilyId='" + getProductFamilyId() + "'" +
            ", nameEnUs='" + getNameEnUs() + "'" +
            ", nameDeDe='" + getNameDeDe() + "'" +
            ", productStatus='" + getProductStatus() + "'" +
            ", price=" + getPrice() +
            ", currency='" + getCurrency() + "'" +
            ", priceCurrency='" + getPriceCurrency() + "'" +
            ", taxId='" + getTaxId() + "'" +
            ", ean='" + getEan() + "'" +
            ", mpn='" + getMpn() + "'" +
            ", packagingId='" + getPackagingId() + "'" +
            ", uvp=" + getUvp() +
            ", tag='" + getTag() + "'" +
            ", ownerUserId='" + getOwnerUserId() + "'" +
            ", assignedUserId='" + getAssignedUserId() + "'" +
            ", finalPrice=" + getFinalPrice() +
            ", finalPriceCurrency='" + getFinalPriceCurrency() + "'" +
            ", longDescription='" + getLongDescription() + "'" +
            ", longDescriptionDeDe='" + getLongDescriptionDeDe() + "'" +
            ", longDescriptionEnUs='" + getLongDescriptionEnUs() + "'" +
            ", productSerieId='" + getProductSerieId() + "'" +
            ", data='" + getData() + "'" +
            ", catalogId='" + getCatalogId() + "'" +
            ", basePriceAmount=" + getBasePriceAmount() +
            ", packedAmount=" + getPackedAmount() +
            ", imageId='" + getImageId() + "'" +
            ", measuringUnitId='" + getMeasuringUnitId() + "'" +
            ", deliveryStatus='" + getDeliveryStatus() + "'" +
            ", deliveryStatusDeDe='" + getDeliveryStatusDeDe() + "'" +
            ", deliveryStatusEnUs='" + getDeliveryStatusEnUs() + "'" +
            ", supplied='" + getSupplied() + "'" +
            ", suppliedDeDe='" + getSuppliedDeDe() + "'" +
            ", suppliedEnUs='" + getSuppliedEnUs() + "'" +
            ", brandId=" + getBrandId() +
            "}";
    }
}
