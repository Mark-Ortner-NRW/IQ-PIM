package com.innoq.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Size(max = 255)
    @Column(name = "sku", length = 255)
    private String sku;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Size(max = 255)
    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "amount")
    private Double amount;

    @Size(max = 24)
    @Column(name = "created_by_id", length = 24)
    private String createdById;

    @Size(max = 24)
    @Column(name = "modified_by_id", length = 24)
    private String modifiedById;

    @Size(max = 24)
    @Column(name = "product_family_id", length = 24)
    private String productFamilyId;

    @Size(max = 255)
    @Column(name = "name_en_us", length = 255)
    private String nameEnUs;

    @Size(max = 255)
    @Column(name = "name_de_de", length = 255)
    private String nameDeDe;

    @Size(max = 255)
    @Column(name = "product_status", length = 255)
    private String productStatus;

    @Column(name = "price")
    private Double price;

    @Size(max = 255)
    @Column(name = "currency", length = 255)
    private String currency;

    @Size(max = 255)
    @Column(name = "price_currency", length = 255)
    private String priceCurrency;

    @Size(max = 24)
    @Column(name = "tax_id", length = 24)
    private String taxId;

    @Size(max = 255)
    @Column(name = "ean", length = 255)
    private String ean;

    @Size(max = 255)
    @Column(name = "mpn", length = 255)
    private String mpn;

    @Size(max = 24)
    @Column(name = "packaging_id", length = 24)
    private String packagingId;

    @Column(name = "uvp")
    private Double uvp;

    /**
     * default={[]}
     */
    @Lob
    @Column(name = "tag")
    private String tag;

    @Size(max = 24)
    @Column(name = "owner_user_id", length = 24)
    private String ownerUserId;

    @Size(max = 24)
    @Column(name = "assigned_user_id", length = 24)
    private String assignedUserId;

    @Column(name = "final_price")
    private Double finalPrice;

    @Size(max = 255)
    @Column(name = "final_price_currency", length = 255)
    private String finalPriceCurrency;

    @Lob
    @Column(name = "long_description")
    private String longDescription;

    @Lob
    @Column(name = "long_description_de_de")
    private String longDescriptionDeDe;

    @Lob
    @Column(name = "long_description_en_us")
    private String longDescriptionEnUs;

    @Size(max = 24)
    @Column(name = "product_serie_id", length = 24)
    private String productSerieId;

    @Lob
    @Column(name = "data")
    private String data;

    @Size(max = 24)
    @Column(name = "catalog_id", length = 24)
    private String catalogId;

    @Column(name = "base_price_amount")
    private Double basePriceAmount;

    @Column(name = "packed_amount")
    private Double packedAmount;

    @Size(max = 24)
    @Column(name = "image_id", length = 24)
    private String imageId;

    @Size(max = 24)
    @Column(name = "measuring_unit_id", length = 24)
    private String measuringUnitId;

    @Size(max = 255)
    @Column(name = "delivery_status", length = 255)
    private String deliveryStatus;

    @Size(max = 255)
    @Column(name = "delivery_status_de_de", length = 255)
    private String deliveryStatusDeDe;

    @Size(max = 255)
    @Column(name = "delivery_status_en_us", length = 255)
    private String deliveryStatusEnUs;

    @Lob
    @Column(name = "supplied")
    private String supplied;

    @Lob
    @Column(name = "supplied_de_de")
    private String suppliedDeDe;

    @Lob
    @Column(name = "supplied_en_us")
    private String suppliedEnUs;

    @ManyToOne
    @JsonIgnoreProperties(value = "products", allowSetters = true)
    private Brand brand;

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

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Product deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Product createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public Product modifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getSku() {
        return sku;
    }

    public Product sku(String sku) {
        this.sku = sku;
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Product isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getType() {
        return type;
    }

    public Product type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public Product amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreatedById() {
        return createdById;
    }

    public Product createdById(String createdById) {
        this.createdById = createdById;
        return this;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getModifiedById() {
        return modifiedById;
    }

    public Product modifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
        return this;
    }

    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }

    public String getProductFamilyId() {
        return productFamilyId;
    }

    public Product productFamilyId(String productFamilyId) {
        this.productFamilyId = productFamilyId;
        return this;
    }

    public void setProductFamilyId(String productFamilyId) {
        this.productFamilyId = productFamilyId;
    }

    public String getNameEnUs() {
        return nameEnUs;
    }

    public Product nameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
        return this;
    }

    public void setNameEnUs(String nameEnUs) {
        this.nameEnUs = nameEnUs;
    }

    public String getNameDeDe() {
        return nameDeDe;
    }

    public Product nameDeDe(String nameDeDe) {
        this.nameDeDe = nameDeDe;
        return this;
    }

    public void setNameDeDe(String nameDeDe) {
        this.nameDeDe = nameDeDe;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public Product productStatus(String productStatus) {
        this.productStatus = productStatus;
        return this;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Double getPrice() {
        return price;
    }

    public Product price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public Product currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public Product priceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
        return this;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getTaxId() {
        return taxId;
    }

    public Product taxId(String taxId) {
        this.taxId = taxId;
        return this;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getEan() {
        return ean;
    }

    public Product ean(String ean) {
        this.ean = ean;
        return this;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getMpn() {
        return mpn;
    }

    public Product mpn(String mpn) {
        this.mpn = mpn;
        return this;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getPackagingId() {
        return packagingId;
    }

    public Product packagingId(String packagingId) {
        this.packagingId = packagingId;
        return this;
    }

    public void setPackagingId(String packagingId) {
        this.packagingId = packagingId;
    }

    public Double getUvp() {
        return uvp;
    }

    public Product uvp(Double uvp) {
        this.uvp = uvp;
        return this;
    }

    public void setUvp(Double uvp) {
        this.uvp = uvp;
    }

    public String getTag() {
        return tag;
    }

    public Product tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public Product ownerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
        return this;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public Product assignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
        return this;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public Product finalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
        return this;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getFinalPriceCurrency() {
        return finalPriceCurrency;
    }

    public Product finalPriceCurrency(String finalPriceCurrency) {
        this.finalPriceCurrency = finalPriceCurrency;
        return this;
    }

    public void setFinalPriceCurrency(String finalPriceCurrency) {
        this.finalPriceCurrency = finalPriceCurrency;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public Product longDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescriptionDeDe() {
        return longDescriptionDeDe;
    }

    public Product longDescriptionDeDe(String longDescriptionDeDe) {
        this.longDescriptionDeDe = longDescriptionDeDe;
        return this;
    }

    public void setLongDescriptionDeDe(String longDescriptionDeDe) {
        this.longDescriptionDeDe = longDescriptionDeDe;
    }

    public String getLongDescriptionEnUs() {
        return longDescriptionEnUs;
    }

    public Product longDescriptionEnUs(String longDescriptionEnUs) {
        this.longDescriptionEnUs = longDescriptionEnUs;
        return this;
    }

    public void setLongDescriptionEnUs(String longDescriptionEnUs) {
        this.longDescriptionEnUs = longDescriptionEnUs;
    }

    public String getProductSerieId() {
        return productSerieId;
    }

    public Product productSerieId(String productSerieId) {
        this.productSerieId = productSerieId;
        return this;
    }

    public void setProductSerieId(String productSerieId) {
        this.productSerieId = productSerieId;
    }

    public String getData() {
        return data;
    }

    public Product data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public Product catalogId(String catalogId) {
        this.catalogId = catalogId;
        return this;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public Double getBasePriceAmount() {
        return basePriceAmount;
    }

    public Product basePriceAmount(Double basePriceAmount) {
        this.basePriceAmount = basePriceAmount;
        return this;
    }

    public void setBasePriceAmount(Double basePriceAmount) {
        this.basePriceAmount = basePriceAmount;
    }

    public Double getPackedAmount() {
        return packedAmount;
    }

    public Product packedAmount(Double packedAmount) {
        this.packedAmount = packedAmount;
        return this;
    }

    public void setPackedAmount(Double packedAmount) {
        this.packedAmount = packedAmount;
    }

    public String getImageId() {
        return imageId;
    }

    public Product imageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getMeasuringUnitId() {
        return measuringUnitId;
    }

    public Product measuringUnitId(String measuringUnitId) {
        this.measuringUnitId = measuringUnitId;
        return this;
    }

    public void setMeasuringUnitId(String measuringUnitId) {
        this.measuringUnitId = measuringUnitId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public Product deliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatusDeDe() {
        return deliveryStatusDeDe;
    }

    public Product deliveryStatusDeDe(String deliveryStatusDeDe) {
        this.deliveryStatusDeDe = deliveryStatusDeDe;
        return this;
    }

    public void setDeliveryStatusDeDe(String deliveryStatusDeDe) {
        this.deliveryStatusDeDe = deliveryStatusDeDe;
    }

    public String getDeliveryStatusEnUs() {
        return deliveryStatusEnUs;
    }

    public Product deliveryStatusEnUs(String deliveryStatusEnUs) {
        this.deliveryStatusEnUs = deliveryStatusEnUs;
        return this;
    }

    public void setDeliveryStatusEnUs(String deliveryStatusEnUs) {
        this.deliveryStatusEnUs = deliveryStatusEnUs;
    }

    public String getSupplied() {
        return supplied;
    }

    public Product supplied(String supplied) {
        this.supplied = supplied;
        return this;
    }

    public void setSupplied(String supplied) {
        this.supplied = supplied;
    }

    public String getSuppliedDeDe() {
        return suppliedDeDe;
    }

    public Product suppliedDeDe(String suppliedDeDe) {
        this.suppliedDeDe = suppliedDeDe;
        return this;
    }

    public void setSuppliedDeDe(String suppliedDeDe) {
        this.suppliedDeDe = suppliedDeDe;
    }

    public String getSuppliedEnUs() {
        return suppliedEnUs;
    }

    public Product suppliedEnUs(String suppliedEnUs) {
        this.suppliedEnUs = suppliedEnUs;
        return this;
    }

    public void setSuppliedEnUs(String suppliedEnUs) {
        this.suppliedEnUs = suppliedEnUs;
    }

    public Brand getBrand() {
        return brand;
    }

    public Product brand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
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
            "}";
    }
}
