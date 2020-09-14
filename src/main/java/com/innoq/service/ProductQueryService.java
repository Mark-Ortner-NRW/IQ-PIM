package com.innoq.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.innoq.domain.Product;
import com.innoq.domain.*; // for static metamodels
import com.innoq.repository.ProductRepository;
import com.innoq.service.dto.ProductCriteria;
import com.innoq.service.dto.ProductDTO;
import com.innoq.service.mapper.ProductMapper;

/**
 * Service for executing complex queries for {@link Product} entities in the database.
 * The main input is a {@link ProductCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductDTO} or a {@link Page} of {@link ProductDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductQueryService extends QueryService<Product> {

    private final Logger log = LoggerFactory.getLogger(ProductQueryService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductQueryService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Return a {@link List} of {@link ProductDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductDTO> findByCriteria(ProductCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Product> specification = createSpecification(criteria);
        return productMapper.toDto(productRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findByCriteria(ProductCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Product> specification = createSpecification(criteria);
        return productRepository.findAll(specification, page)
            .map(productMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Product> specification = createSpecification(criteria);
        return productRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Product> createSpecification(ProductCriteria criteria) {
        Specification<Product> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Product_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Product_.name));
            }
            if (criteria.getDeleted() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleted(), Product_.deleted));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Product_.createdAt));
            }
            if (criteria.getModifiedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedAt(), Product_.modifiedAt));
            }
            if (criteria.getSku() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSku(), Product_.sku));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Product_.isActive));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Product_.type));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), Product_.amount));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedById(), Product_.createdById));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifiedById(), Product_.modifiedById));
            }
            if (criteria.getProductFamilyId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProductFamilyId(), Product_.productFamilyId));
            }
            if (criteria.getNameEnUs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEnUs(), Product_.nameEnUs));
            }
            if (criteria.getNameDeDe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameDeDe(), Product_.nameDeDe));
            }
            if (criteria.getProductStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProductStatus(), Product_.productStatus));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Product_.price));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurrency(), Product_.currency));
            }
            if (criteria.getPriceCurrency() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPriceCurrency(), Product_.priceCurrency));
            }
            if (criteria.getTaxId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTaxId(), Product_.taxId));
            }
            if (criteria.getEan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEan(), Product_.ean));
            }
            if (criteria.getMpn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMpn(), Product_.mpn));
            }
            if (criteria.getPackagingId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPackagingId(), Product_.packagingId));
            }
            if (criteria.getUvp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUvp(), Product_.uvp));
            }
            if (criteria.getOwnerUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOwnerUserId(), Product_.ownerUserId));
            }
            if (criteria.getAssignedUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignedUserId(), Product_.assignedUserId));
            }
            if (criteria.getFinalPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalPrice(), Product_.finalPrice));
            }
            if (criteria.getFinalPriceCurrency() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFinalPriceCurrency(), Product_.finalPriceCurrency));
            }
            if (criteria.getProductSerieId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProductSerieId(), Product_.productSerieId));
            }
            if (criteria.getCatalogId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCatalogId(), Product_.catalogId));
            }
            if (criteria.getBasePriceAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBasePriceAmount(), Product_.basePriceAmount));
            }
            if (criteria.getPackedAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPackedAmount(), Product_.packedAmount));
            }
            if (criteria.getImageId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageId(), Product_.imageId));
            }
            if (criteria.getMeasuringUnitId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMeasuringUnitId(), Product_.measuringUnitId));
            }
            if (criteria.getDeliveryStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDeliveryStatus(), Product_.deliveryStatus));
            }
            if (criteria.getDeliveryStatusDeDe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDeliveryStatusDeDe(), Product_.deliveryStatusDeDe));
            }
            if (criteria.getDeliveryStatusEnUs() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDeliveryStatusEnUs(), Product_.deliveryStatusEnUs));
            }
            if (criteria.getBrandId() != null) {
                specification = specification.and(buildSpecification(criteria.getBrandId(),
                    root -> root.join(Product_.brand, JoinType.LEFT).get(Brand_.id)));
            }
        }
        return specification;
    }
}
