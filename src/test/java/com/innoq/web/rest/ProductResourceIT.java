package com.innoq.web.rest;

import com.innoq.InnoqPimApp;
import com.innoq.domain.Product;
import com.innoq.domain.Brand;
import com.innoq.repository.ProductRepository;
import com.innoq.service.ProductService;
import com.innoq.service.dto.ProductDTO;
import com.innoq.service.mapper.ProductMapper;
import com.innoq.service.dto.ProductCriteria;
import com.innoq.service.ProductQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProductResource} REST controller.
 */
@SpringBootTest(classes = InnoqPimApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SKU = "AAAAAAAAAA";
    private static final String UPDATED_SKU = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;
    private static final Double SMALLER_AMOUNT = 1D - 1D;

    private static final String DEFAULT_CREATED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FAMILY_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FAMILY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN_US = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN_US = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_DE_DE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_DE_DE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_STATUS = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;
    private static final Double SMALLER_PRICE = 1D - 1D;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_ID = "AAAAAAAAAA";
    private static final String UPDATED_TAX_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EAN = "AAAAAAAAAA";
    private static final String UPDATED_EAN = "BBBBBBBBBB";

    private static final String DEFAULT_MPN = "AAAAAAAAAA";
    private static final String UPDATED_MPN = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGING_ID = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGING_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_UVP = 1D;
    private static final Double UPDATED_UVP = 2D;
    private static final Double SMALLER_UVP = 1D - 1D;

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_OWNER_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_USER_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_FINAL_PRICE = 1D;
    private static final Double UPDATED_FINAL_PRICE = 2D;
    private static final Double SMALLER_FINAL_PRICE = 1D - 1D;

    private static final String DEFAULT_FINAL_PRICE_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_FINAL_PRICE_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_LONG_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_DESCRIPTION_DE_DE = "AAAAAAAAAA";
    private static final String UPDATED_LONG_DESCRIPTION_DE_DE = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_DESCRIPTION_EN_US = "AAAAAAAAAA";
    private static final String UPDATED_LONG_DESCRIPTION_EN_US = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_SERIE_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_SERIE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOG_ID = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_BASE_PRICE_AMOUNT = 1D;
    private static final Double UPDATED_BASE_PRICE_AMOUNT = 2D;
    private static final Double SMALLER_BASE_PRICE_AMOUNT = 1D - 1D;

    private static final Double DEFAULT_PACKED_AMOUNT = 1D;
    private static final Double UPDATED_PACKED_AMOUNT = 2D;
    private static final Double SMALLER_PACKED_AMOUNT = 1D - 1D;

    private static final String DEFAULT_IMAGE_ID = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MEASURING_UNIT_ID = "AAAAAAAAAA";
    private static final String UPDATED_MEASURING_UNIT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATUS_DE_DE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS_DE_DE = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_STATUS_EN_US = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_STATUS_EN_US = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIED = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIED = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIED_DE_DE = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIED_DE_DE = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIED_EN_US = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIED_EN_US = "BBBBBBBBBB";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductQueryService productQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductMockMvc;

    private Product product;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .name(DEFAULT_NAME)
            .deleted(DEFAULT_DELETED)
            .createdAt(DEFAULT_CREATED_AT)
            .modifiedAt(DEFAULT_MODIFIED_AT)
            .sku(DEFAULT_SKU)
            .isActive(DEFAULT_IS_ACTIVE)
            .type(DEFAULT_TYPE)
            .amount(DEFAULT_AMOUNT)
            .createdById(DEFAULT_CREATED_BY_ID)
            .modifiedById(DEFAULT_MODIFIED_BY_ID)
            .productFamilyId(DEFAULT_PRODUCT_FAMILY_ID)
            .nameEnUs(DEFAULT_NAME_EN_US)
            .nameDeDe(DEFAULT_NAME_DE_DE)
            .productStatus(DEFAULT_PRODUCT_STATUS)
            .price(DEFAULT_PRICE)
            .currency(DEFAULT_CURRENCY)
            .priceCurrency(DEFAULT_PRICE_CURRENCY)
            .taxId(DEFAULT_TAX_ID)
            .ean(DEFAULT_EAN)
            .mpn(DEFAULT_MPN)
            .packagingId(DEFAULT_PACKAGING_ID)
            .uvp(DEFAULT_UVP)
            .tag(DEFAULT_TAG)
            .ownerUserId(DEFAULT_OWNER_USER_ID)
            .assignedUserId(DEFAULT_ASSIGNED_USER_ID)
            .finalPrice(DEFAULT_FINAL_PRICE)
            .finalPriceCurrency(DEFAULT_FINAL_PRICE_CURRENCY)
            .longDescription(DEFAULT_LONG_DESCRIPTION)
            .longDescriptionDeDe(DEFAULT_LONG_DESCRIPTION_DE_DE)
            .longDescriptionEnUs(DEFAULT_LONG_DESCRIPTION_EN_US)
            .productSerieId(DEFAULT_PRODUCT_SERIE_ID)
            .data(DEFAULT_DATA)
            .catalogId(DEFAULT_CATALOG_ID)
            .basePriceAmount(DEFAULT_BASE_PRICE_AMOUNT)
            .packedAmount(DEFAULT_PACKED_AMOUNT)
            .imageId(DEFAULT_IMAGE_ID)
            .measuringUnitId(DEFAULT_MEASURING_UNIT_ID)
            .deliveryStatus(DEFAULT_DELIVERY_STATUS)
            .deliveryStatusDeDe(DEFAULT_DELIVERY_STATUS_DE_DE)
            .deliveryStatusEnUs(DEFAULT_DELIVERY_STATUS_EN_US)
            .supplied(DEFAULT_SUPPLIED)
            .suppliedDeDe(DEFAULT_SUPPLIED_DE_DE)
            .suppliedEnUs(DEFAULT_SUPPLIED_EN_US);
        return product;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity(EntityManager em) {
        Product product = new Product()
            .name(UPDATED_NAME)
            .deleted(UPDATED_DELETED)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .sku(UPDATED_SKU)
            .isActive(UPDATED_IS_ACTIVE)
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT)
            .createdById(UPDATED_CREATED_BY_ID)
            .modifiedById(UPDATED_MODIFIED_BY_ID)
            .productFamilyId(UPDATED_PRODUCT_FAMILY_ID)
            .nameEnUs(UPDATED_NAME_EN_US)
            .nameDeDe(UPDATED_NAME_DE_DE)
            .productStatus(UPDATED_PRODUCT_STATUS)
            .price(UPDATED_PRICE)
            .currency(UPDATED_CURRENCY)
            .priceCurrency(UPDATED_PRICE_CURRENCY)
            .taxId(UPDATED_TAX_ID)
            .ean(UPDATED_EAN)
            .mpn(UPDATED_MPN)
            .packagingId(UPDATED_PACKAGING_ID)
            .uvp(UPDATED_UVP)
            .tag(UPDATED_TAG)
            .ownerUserId(UPDATED_OWNER_USER_ID)
            .assignedUserId(UPDATED_ASSIGNED_USER_ID)
            .finalPrice(UPDATED_FINAL_PRICE)
            .finalPriceCurrency(UPDATED_FINAL_PRICE_CURRENCY)
            .longDescription(UPDATED_LONG_DESCRIPTION)
            .longDescriptionDeDe(UPDATED_LONG_DESCRIPTION_DE_DE)
            .longDescriptionEnUs(UPDATED_LONG_DESCRIPTION_EN_US)
            .productSerieId(UPDATED_PRODUCT_SERIE_ID)
            .data(UPDATED_DATA)
            .catalogId(UPDATED_CATALOG_ID)
            .basePriceAmount(UPDATED_BASE_PRICE_AMOUNT)
            .packedAmount(UPDATED_PACKED_AMOUNT)
            .imageId(UPDATED_IMAGE_ID)
            .measuringUnitId(UPDATED_MEASURING_UNIT_ID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryStatusDeDe(UPDATED_DELIVERY_STATUS_DE_DE)
            .deliveryStatusEnUs(UPDATED_DELIVERY_STATUS_EN_US)
            .supplied(UPDATED_SUPPLIED)
            .suppliedDeDe(UPDATED_SUPPLIED_DE_DE)
            .suppliedEnUs(UPDATED_SUPPLIED_EN_US);
        return product;
    }

    @BeforeEach
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();
        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);
        restProductMockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testProduct.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testProduct.getModifiedAt()).isEqualTo(DEFAULT_MODIFIED_AT);
        assertThat(testProduct.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(testProduct.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProduct.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProduct.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testProduct.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testProduct.getModifiedById()).isEqualTo(DEFAULT_MODIFIED_BY_ID);
        assertThat(testProduct.getProductFamilyId()).isEqualTo(DEFAULT_PRODUCT_FAMILY_ID);
        assertThat(testProduct.getNameEnUs()).isEqualTo(DEFAULT_NAME_EN_US);
        assertThat(testProduct.getNameDeDe()).isEqualTo(DEFAULT_NAME_DE_DE);
        assertThat(testProduct.getProductStatus()).isEqualTo(DEFAULT_PRODUCT_STATUS);
        assertThat(testProduct.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProduct.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testProduct.getPriceCurrency()).isEqualTo(DEFAULT_PRICE_CURRENCY);
        assertThat(testProduct.getTaxId()).isEqualTo(DEFAULT_TAX_ID);
        assertThat(testProduct.getEan()).isEqualTo(DEFAULT_EAN);
        assertThat(testProduct.getMpn()).isEqualTo(DEFAULT_MPN);
        assertThat(testProduct.getPackagingId()).isEqualTo(DEFAULT_PACKAGING_ID);
        assertThat(testProduct.getUvp()).isEqualTo(DEFAULT_UVP);
        assertThat(testProduct.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testProduct.getOwnerUserId()).isEqualTo(DEFAULT_OWNER_USER_ID);
        assertThat(testProduct.getAssignedUserId()).isEqualTo(DEFAULT_ASSIGNED_USER_ID);
        assertThat(testProduct.getFinalPrice()).isEqualTo(DEFAULT_FINAL_PRICE);
        assertThat(testProduct.getFinalPriceCurrency()).isEqualTo(DEFAULT_FINAL_PRICE_CURRENCY);
        assertThat(testProduct.getLongDescription()).isEqualTo(DEFAULT_LONG_DESCRIPTION);
        assertThat(testProduct.getLongDescriptionDeDe()).isEqualTo(DEFAULT_LONG_DESCRIPTION_DE_DE);
        assertThat(testProduct.getLongDescriptionEnUs()).isEqualTo(DEFAULT_LONG_DESCRIPTION_EN_US);
        assertThat(testProduct.getProductSerieId()).isEqualTo(DEFAULT_PRODUCT_SERIE_ID);
        assertThat(testProduct.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testProduct.getCatalogId()).isEqualTo(DEFAULT_CATALOG_ID);
        assertThat(testProduct.getBasePriceAmount()).isEqualTo(DEFAULT_BASE_PRICE_AMOUNT);
        assertThat(testProduct.getPackedAmount()).isEqualTo(DEFAULT_PACKED_AMOUNT);
        assertThat(testProduct.getImageId()).isEqualTo(DEFAULT_IMAGE_ID);
        assertThat(testProduct.getMeasuringUnitId()).isEqualTo(DEFAULT_MEASURING_UNIT_ID);
        assertThat(testProduct.getDeliveryStatus()).isEqualTo(DEFAULT_DELIVERY_STATUS);
        assertThat(testProduct.getDeliveryStatusDeDe()).isEqualTo(DEFAULT_DELIVERY_STATUS_DE_DE);
        assertThat(testProduct.getDeliveryStatusEnUs()).isEqualTo(DEFAULT_DELIVERY_STATUS_EN_US);
        assertThat(testProduct.getSupplied()).isEqualTo(DEFAULT_SUPPLIED);
        assertThat(testProduct.getSuppliedDeDe()).isEqualTo(DEFAULT_SUPPLIED_DE_DE);
        assertThat(testProduct.getSuppliedEnUs()).isEqualTo(DEFAULT_SUPPLIED_EN_US);
    }

    @Test
    @Transactional
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId(1L);
        ProductDTO productDTO = productMapper.toDto(product);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIsActive(null);

        // Create the Product, which fails.
        ProductDTO productDTO = productMapper.toDto(product);


        restProductMockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].sku").value(hasItem(DEFAULT_SKU)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID)))
            .andExpect(jsonPath("$.[*].modifiedById").value(hasItem(DEFAULT_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].productFamilyId").value(hasItem(DEFAULT_PRODUCT_FAMILY_ID)))
            .andExpect(jsonPath("$.[*].nameEnUs").value(hasItem(DEFAULT_NAME_EN_US)))
            .andExpect(jsonPath("$.[*].nameDeDe").value(hasItem(DEFAULT_NAME_DE_DE)))
            .andExpect(jsonPath("$.[*].productStatus").value(hasItem(DEFAULT_PRODUCT_STATUS)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].priceCurrency").value(hasItem(DEFAULT_PRICE_CURRENCY)))
            .andExpect(jsonPath("$.[*].taxId").value(hasItem(DEFAULT_TAX_ID)))
            .andExpect(jsonPath("$.[*].ean").value(hasItem(DEFAULT_EAN)))
            .andExpect(jsonPath("$.[*].mpn").value(hasItem(DEFAULT_MPN)))
            .andExpect(jsonPath("$.[*].packagingId").value(hasItem(DEFAULT_PACKAGING_ID)))
            .andExpect(jsonPath("$.[*].uvp").value(hasItem(DEFAULT_UVP.doubleValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.toString())))
            .andExpect(jsonPath("$.[*].ownerUserId").value(hasItem(DEFAULT_OWNER_USER_ID)))
            .andExpect(jsonPath("$.[*].assignedUserId").value(hasItem(DEFAULT_ASSIGNED_USER_ID)))
            .andExpect(jsonPath("$.[*].finalPrice").value(hasItem(DEFAULT_FINAL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].finalPriceCurrency").value(hasItem(DEFAULT_FINAL_PRICE_CURRENCY)))
            .andExpect(jsonPath("$.[*].longDescription").value(hasItem(DEFAULT_LONG_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].longDescriptionDeDe").value(hasItem(DEFAULT_LONG_DESCRIPTION_DE_DE.toString())))
            .andExpect(jsonPath("$.[*].longDescriptionEnUs").value(hasItem(DEFAULT_LONG_DESCRIPTION_EN_US.toString())))
            .andExpect(jsonPath("$.[*].productSerieId").value(hasItem(DEFAULT_PRODUCT_SERIE_ID)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].catalogId").value(hasItem(DEFAULT_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].basePriceAmount").value(hasItem(DEFAULT_BASE_PRICE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].packedAmount").value(hasItem(DEFAULT_PACKED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].imageId").value(hasItem(DEFAULT_IMAGE_ID)))
            .andExpect(jsonPath("$.[*].measuringUnitId").value(hasItem(DEFAULT_MEASURING_UNIT_ID)))
            .andExpect(jsonPath("$.[*].deliveryStatus").value(hasItem(DEFAULT_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].deliveryStatusDeDe").value(hasItem(DEFAULT_DELIVERY_STATUS_DE_DE)))
            .andExpect(jsonPath("$.[*].deliveryStatusEnUs").value(hasItem(DEFAULT_DELIVERY_STATUS_EN_US)))
            .andExpect(jsonPath("$.[*].supplied").value(hasItem(DEFAULT_SUPPLIED.toString())))
            .andExpect(jsonPath("$.[*].suppliedDeDe").value(hasItem(DEFAULT_SUPPLIED_DE_DE.toString())))
            .andExpect(jsonPath("$.[*].suppliedEnUs").value(hasItem(DEFAULT_SUPPLIED_EN_US.toString())));
    }
    
    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.modifiedAt").value(DEFAULT_MODIFIED_AT.toString()))
            .andExpect(jsonPath("$.sku").value(DEFAULT_SKU))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID))
            .andExpect(jsonPath("$.modifiedById").value(DEFAULT_MODIFIED_BY_ID))
            .andExpect(jsonPath("$.productFamilyId").value(DEFAULT_PRODUCT_FAMILY_ID))
            .andExpect(jsonPath("$.nameEnUs").value(DEFAULT_NAME_EN_US))
            .andExpect(jsonPath("$.nameDeDe").value(DEFAULT_NAME_DE_DE))
            .andExpect(jsonPath("$.productStatus").value(DEFAULT_PRODUCT_STATUS))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.priceCurrency").value(DEFAULT_PRICE_CURRENCY))
            .andExpect(jsonPath("$.taxId").value(DEFAULT_TAX_ID))
            .andExpect(jsonPath("$.ean").value(DEFAULT_EAN))
            .andExpect(jsonPath("$.mpn").value(DEFAULT_MPN))
            .andExpect(jsonPath("$.packagingId").value(DEFAULT_PACKAGING_ID))
            .andExpect(jsonPath("$.uvp").value(DEFAULT_UVP.doubleValue()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG.toString()))
            .andExpect(jsonPath("$.ownerUserId").value(DEFAULT_OWNER_USER_ID))
            .andExpect(jsonPath("$.assignedUserId").value(DEFAULT_ASSIGNED_USER_ID))
            .andExpect(jsonPath("$.finalPrice").value(DEFAULT_FINAL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.finalPriceCurrency").value(DEFAULT_FINAL_PRICE_CURRENCY))
            .andExpect(jsonPath("$.longDescription").value(DEFAULT_LONG_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.longDescriptionDeDe").value(DEFAULT_LONG_DESCRIPTION_DE_DE.toString()))
            .andExpect(jsonPath("$.longDescriptionEnUs").value(DEFAULT_LONG_DESCRIPTION_EN_US.toString()))
            .andExpect(jsonPath("$.productSerieId").value(DEFAULT_PRODUCT_SERIE_ID))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.catalogId").value(DEFAULT_CATALOG_ID))
            .andExpect(jsonPath("$.basePriceAmount").value(DEFAULT_BASE_PRICE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.packedAmount").value(DEFAULT_PACKED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.imageId").value(DEFAULT_IMAGE_ID))
            .andExpect(jsonPath("$.measuringUnitId").value(DEFAULT_MEASURING_UNIT_ID))
            .andExpect(jsonPath("$.deliveryStatus").value(DEFAULT_DELIVERY_STATUS))
            .andExpect(jsonPath("$.deliveryStatusDeDe").value(DEFAULT_DELIVERY_STATUS_DE_DE))
            .andExpect(jsonPath("$.deliveryStatusEnUs").value(DEFAULT_DELIVERY_STATUS_EN_US))
            .andExpect(jsonPath("$.supplied").value(DEFAULT_SUPPLIED.toString()))
            .andExpect(jsonPath("$.suppliedDeDe").value(DEFAULT_SUPPLIED_DE_DE.toString()))
            .andExpect(jsonPath("$.suppliedEnUs").value(DEFAULT_SUPPLIED_EN_US.toString()));
    }


    @Test
    @Transactional
    public void getProductsByIdFiltering() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        Long id = product.getId();

        defaultProductShouldBeFound("id.equals=" + id);
        defaultProductShouldNotBeFound("id.notEquals=" + id);

        defaultProductShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProductShouldNotBeFound("id.greaterThan=" + id);

        defaultProductShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProductShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllProductsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where name equals to DEFAULT_NAME
        defaultProductShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the productList where name equals to UPDATED_NAME
        defaultProductShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllProductsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where name not equals to DEFAULT_NAME
        defaultProductShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the productList where name not equals to UPDATED_NAME
        defaultProductShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllProductsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where name in DEFAULT_NAME or UPDATED_NAME
        defaultProductShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the productList where name equals to UPDATED_NAME
        defaultProductShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllProductsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where name is not null
        defaultProductShouldBeFound("name.specified=true");

        // Get all the productList where name is null
        defaultProductShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByNameContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where name contains DEFAULT_NAME
        defaultProductShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the productList where name contains UPDATED_NAME
        defaultProductShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllProductsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where name does not contain DEFAULT_NAME
        defaultProductShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the productList where name does not contain UPDATED_NAME
        defaultProductShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllProductsByDeletedIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deleted equals to DEFAULT_DELETED
        defaultProductShouldBeFound("deleted.equals=" + DEFAULT_DELETED);

        // Get all the productList where deleted equals to UPDATED_DELETED
        defaultProductShouldNotBeFound("deleted.equals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllProductsByDeletedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deleted not equals to DEFAULT_DELETED
        defaultProductShouldNotBeFound("deleted.notEquals=" + DEFAULT_DELETED);

        // Get all the productList where deleted not equals to UPDATED_DELETED
        defaultProductShouldBeFound("deleted.notEquals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllProductsByDeletedIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deleted in DEFAULT_DELETED or UPDATED_DELETED
        defaultProductShouldBeFound("deleted.in=" + DEFAULT_DELETED + "," + UPDATED_DELETED);

        // Get all the productList where deleted equals to UPDATED_DELETED
        defaultProductShouldNotBeFound("deleted.in=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllProductsByDeletedIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deleted is not null
        defaultProductShouldBeFound("deleted.specified=true");

        // Get all the productList where deleted is null
        defaultProductShouldNotBeFound("deleted.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdAt equals to DEFAULT_CREATED_AT
        defaultProductShouldBeFound("createdAt.equals=" + DEFAULT_CREATED_AT);

        // Get all the productList where createdAt equals to UPDATED_CREATED_AT
        defaultProductShouldNotBeFound("createdAt.equals=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedAtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdAt not equals to DEFAULT_CREATED_AT
        defaultProductShouldNotBeFound("createdAt.notEquals=" + DEFAULT_CREATED_AT);

        // Get all the productList where createdAt not equals to UPDATED_CREATED_AT
        defaultProductShouldBeFound("createdAt.notEquals=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedAtIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdAt in DEFAULT_CREATED_AT or UPDATED_CREATED_AT
        defaultProductShouldBeFound("createdAt.in=" + DEFAULT_CREATED_AT + "," + UPDATED_CREATED_AT);

        // Get all the productList where createdAt equals to UPDATED_CREATED_AT
        defaultProductShouldNotBeFound("createdAt.in=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdAt is not null
        defaultProductShouldBeFound("createdAt.specified=true");

        // Get all the productList where createdAt is null
        defaultProductShouldNotBeFound("createdAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedAt equals to DEFAULT_MODIFIED_AT
        defaultProductShouldBeFound("modifiedAt.equals=" + DEFAULT_MODIFIED_AT);

        // Get all the productList where modifiedAt equals to UPDATED_MODIFIED_AT
        defaultProductShouldNotBeFound("modifiedAt.equals=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedAtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedAt not equals to DEFAULT_MODIFIED_AT
        defaultProductShouldNotBeFound("modifiedAt.notEquals=" + DEFAULT_MODIFIED_AT);

        // Get all the productList where modifiedAt not equals to UPDATED_MODIFIED_AT
        defaultProductShouldBeFound("modifiedAt.notEquals=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedAtIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedAt in DEFAULT_MODIFIED_AT or UPDATED_MODIFIED_AT
        defaultProductShouldBeFound("modifiedAt.in=" + DEFAULT_MODIFIED_AT + "," + UPDATED_MODIFIED_AT);

        // Get all the productList where modifiedAt equals to UPDATED_MODIFIED_AT
        defaultProductShouldNotBeFound("modifiedAt.in=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedAt is not null
        defaultProductShouldBeFound("modifiedAt.specified=true");

        // Get all the productList where modifiedAt is null
        defaultProductShouldNotBeFound("modifiedAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsBySkuIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where sku equals to DEFAULT_SKU
        defaultProductShouldBeFound("sku.equals=" + DEFAULT_SKU);

        // Get all the productList where sku equals to UPDATED_SKU
        defaultProductShouldNotBeFound("sku.equals=" + UPDATED_SKU);
    }

    @Test
    @Transactional
    public void getAllProductsBySkuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where sku not equals to DEFAULT_SKU
        defaultProductShouldNotBeFound("sku.notEquals=" + DEFAULT_SKU);

        // Get all the productList where sku not equals to UPDATED_SKU
        defaultProductShouldBeFound("sku.notEquals=" + UPDATED_SKU);
    }

    @Test
    @Transactional
    public void getAllProductsBySkuIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where sku in DEFAULT_SKU or UPDATED_SKU
        defaultProductShouldBeFound("sku.in=" + DEFAULT_SKU + "," + UPDATED_SKU);

        // Get all the productList where sku equals to UPDATED_SKU
        defaultProductShouldNotBeFound("sku.in=" + UPDATED_SKU);
    }

    @Test
    @Transactional
    public void getAllProductsBySkuIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where sku is not null
        defaultProductShouldBeFound("sku.specified=true");

        // Get all the productList where sku is null
        defaultProductShouldNotBeFound("sku.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsBySkuContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where sku contains DEFAULT_SKU
        defaultProductShouldBeFound("sku.contains=" + DEFAULT_SKU);

        // Get all the productList where sku contains UPDATED_SKU
        defaultProductShouldNotBeFound("sku.contains=" + UPDATED_SKU);
    }

    @Test
    @Transactional
    public void getAllProductsBySkuNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where sku does not contain DEFAULT_SKU
        defaultProductShouldNotBeFound("sku.doesNotContain=" + DEFAULT_SKU);

        // Get all the productList where sku does not contain UPDATED_SKU
        defaultProductShouldBeFound("sku.doesNotContain=" + UPDATED_SKU);
    }


    @Test
    @Transactional
    public void getAllProductsByIsActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive equals to DEFAULT_IS_ACTIVE
        defaultProductShouldBeFound("isActive.equals=" + DEFAULT_IS_ACTIVE);

        // Get all the productList where isActive equals to UPDATED_IS_ACTIVE
        defaultProductShouldNotBeFound("isActive.equals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllProductsByIsActiveIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive not equals to DEFAULT_IS_ACTIVE
        defaultProductShouldNotBeFound("isActive.notEquals=" + DEFAULT_IS_ACTIVE);

        // Get all the productList where isActive not equals to UPDATED_IS_ACTIVE
        defaultProductShouldBeFound("isActive.notEquals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllProductsByIsActiveIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive in DEFAULT_IS_ACTIVE or UPDATED_IS_ACTIVE
        defaultProductShouldBeFound("isActive.in=" + DEFAULT_IS_ACTIVE + "," + UPDATED_IS_ACTIVE);

        // Get all the productList where isActive equals to UPDATED_IS_ACTIVE
        defaultProductShouldNotBeFound("isActive.in=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllProductsByIsActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive is not null
        defaultProductShouldBeFound("isActive.specified=true");

        // Get all the productList where isActive is null
        defaultProductShouldNotBeFound("isActive.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where type equals to DEFAULT_TYPE
        defaultProductShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the productList where type equals to UPDATED_TYPE
        defaultProductShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllProductsByTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where type not equals to DEFAULT_TYPE
        defaultProductShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

        // Get all the productList where type not equals to UPDATED_TYPE
        defaultProductShouldBeFound("type.notEquals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllProductsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultProductShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the productList where type equals to UPDATED_TYPE
        defaultProductShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllProductsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where type is not null
        defaultProductShouldBeFound("type.specified=true");

        // Get all the productList where type is null
        defaultProductShouldNotBeFound("type.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByTypeContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where type contains DEFAULT_TYPE
        defaultProductShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the productList where type contains UPDATED_TYPE
        defaultProductShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllProductsByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where type does not contain DEFAULT_TYPE
        defaultProductShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the productList where type does not contain UPDATED_TYPE
        defaultProductShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllProductsByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount equals to DEFAULT_AMOUNT
        defaultProductShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the productList where amount equals to UPDATED_AMOUNT
        defaultProductShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount not equals to DEFAULT_AMOUNT
        defaultProductShouldNotBeFound("amount.notEquals=" + DEFAULT_AMOUNT);

        // Get all the productList where amount not equals to UPDATED_AMOUNT
        defaultProductShouldBeFound("amount.notEquals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultProductShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the productList where amount equals to UPDATED_AMOUNT
        defaultProductShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount is not null
        defaultProductShouldBeFound("amount.specified=true");

        // Get all the productList where amount is null
        defaultProductShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount is greater than or equal to DEFAULT_AMOUNT
        defaultProductShouldBeFound("amount.greaterThanOrEqual=" + DEFAULT_AMOUNT);

        // Get all the productList where amount is greater than or equal to UPDATED_AMOUNT
        defaultProductShouldNotBeFound("amount.greaterThanOrEqual=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount is less than or equal to DEFAULT_AMOUNT
        defaultProductShouldBeFound("amount.lessThanOrEqual=" + DEFAULT_AMOUNT);

        // Get all the productList where amount is less than or equal to SMALLER_AMOUNT
        defaultProductShouldNotBeFound("amount.lessThanOrEqual=" + SMALLER_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount is less than DEFAULT_AMOUNT
        defaultProductShouldNotBeFound("amount.lessThan=" + DEFAULT_AMOUNT);

        // Get all the productList where amount is less than UPDATED_AMOUNT
        defaultProductShouldBeFound("amount.lessThan=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByAmountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where amount is greater than DEFAULT_AMOUNT
        defaultProductShouldNotBeFound("amount.greaterThan=" + DEFAULT_AMOUNT);

        // Get all the productList where amount is greater than SMALLER_AMOUNT
        defaultProductShouldBeFound("amount.greaterThan=" + SMALLER_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllProductsByCreatedByIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdById equals to DEFAULT_CREATED_BY_ID
        defaultProductShouldBeFound("createdById.equals=" + DEFAULT_CREATED_BY_ID);

        // Get all the productList where createdById equals to UPDATED_CREATED_BY_ID
        defaultProductShouldNotBeFound("createdById.equals=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedByIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdById not equals to DEFAULT_CREATED_BY_ID
        defaultProductShouldNotBeFound("createdById.notEquals=" + DEFAULT_CREATED_BY_ID);

        // Get all the productList where createdById not equals to UPDATED_CREATED_BY_ID
        defaultProductShouldBeFound("createdById.notEquals=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedByIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdById in DEFAULT_CREATED_BY_ID or UPDATED_CREATED_BY_ID
        defaultProductShouldBeFound("createdById.in=" + DEFAULT_CREATED_BY_ID + "," + UPDATED_CREATED_BY_ID);

        // Get all the productList where createdById equals to UPDATED_CREATED_BY_ID
        defaultProductShouldNotBeFound("createdById.in=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedByIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdById is not null
        defaultProductShouldBeFound("createdById.specified=true");

        // Get all the productList where createdById is null
        defaultProductShouldNotBeFound("createdById.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByCreatedByIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdById contains DEFAULT_CREATED_BY_ID
        defaultProductShouldBeFound("createdById.contains=" + DEFAULT_CREATED_BY_ID);

        // Get all the productList where createdById contains UPDATED_CREATED_BY_ID
        defaultProductShouldNotBeFound("createdById.contains=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCreatedByIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where createdById does not contain DEFAULT_CREATED_BY_ID
        defaultProductShouldNotBeFound("createdById.doesNotContain=" + DEFAULT_CREATED_BY_ID);

        // Get all the productList where createdById does not contain UPDATED_CREATED_BY_ID
        defaultProductShouldBeFound("createdById.doesNotContain=" + UPDATED_CREATED_BY_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByModifiedByIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedById equals to DEFAULT_MODIFIED_BY_ID
        defaultProductShouldBeFound("modifiedById.equals=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the productList where modifiedById equals to UPDATED_MODIFIED_BY_ID
        defaultProductShouldNotBeFound("modifiedById.equals=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedByIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedById not equals to DEFAULT_MODIFIED_BY_ID
        defaultProductShouldNotBeFound("modifiedById.notEquals=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the productList where modifiedById not equals to UPDATED_MODIFIED_BY_ID
        defaultProductShouldBeFound("modifiedById.notEquals=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedByIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedById in DEFAULT_MODIFIED_BY_ID or UPDATED_MODIFIED_BY_ID
        defaultProductShouldBeFound("modifiedById.in=" + DEFAULT_MODIFIED_BY_ID + "," + UPDATED_MODIFIED_BY_ID);

        // Get all the productList where modifiedById equals to UPDATED_MODIFIED_BY_ID
        defaultProductShouldNotBeFound("modifiedById.in=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedByIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedById is not null
        defaultProductShouldBeFound("modifiedById.specified=true");

        // Get all the productList where modifiedById is null
        defaultProductShouldNotBeFound("modifiedById.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByModifiedByIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedById contains DEFAULT_MODIFIED_BY_ID
        defaultProductShouldBeFound("modifiedById.contains=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the productList where modifiedById contains UPDATED_MODIFIED_BY_ID
        defaultProductShouldNotBeFound("modifiedById.contains=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByModifiedByIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where modifiedById does not contain DEFAULT_MODIFIED_BY_ID
        defaultProductShouldNotBeFound("modifiedById.doesNotContain=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the productList where modifiedById does not contain UPDATED_MODIFIED_BY_ID
        defaultProductShouldBeFound("modifiedById.doesNotContain=" + UPDATED_MODIFIED_BY_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByProductFamilyIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productFamilyId equals to DEFAULT_PRODUCT_FAMILY_ID
        defaultProductShouldBeFound("productFamilyId.equals=" + DEFAULT_PRODUCT_FAMILY_ID);

        // Get all the productList where productFamilyId equals to UPDATED_PRODUCT_FAMILY_ID
        defaultProductShouldNotBeFound("productFamilyId.equals=" + UPDATED_PRODUCT_FAMILY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductFamilyIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productFamilyId not equals to DEFAULT_PRODUCT_FAMILY_ID
        defaultProductShouldNotBeFound("productFamilyId.notEquals=" + DEFAULT_PRODUCT_FAMILY_ID);

        // Get all the productList where productFamilyId not equals to UPDATED_PRODUCT_FAMILY_ID
        defaultProductShouldBeFound("productFamilyId.notEquals=" + UPDATED_PRODUCT_FAMILY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductFamilyIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productFamilyId in DEFAULT_PRODUCT_FAMILY_ID or UPDATED_PRODUCT_FAMILY_ID
        defaultProductShouldBeFound("productFamilyId.in=" + DEFAULT_PRODUCT_FAMILY_ID + "," + UPDATED_PRODUCT_FAMILY_ID);

        // Get all the productList where productFamilyId equals to UPDATED_PRODUCT_FAMILY_ID
        defaultProductShouldNotBeFound("productFamilyId.in=" + UPDATED_PRODUCT_FAMILY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductFamilyIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productFamilyId is not null
        defaultProductShouldBeFound("productFamilyId.specified=true");

        // Get all the productList where productFamilyId is null
        defaultProductShouldNotBeFound("productFamilyId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByProductFamilyIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productFamilyId contains DEFAULT_PRODUCT_FAMILY_ID
        defaultProductShouldBeFound("productFamilyId.contains=" + DEFAULT_PRODUCT_FAMILY_ID);

        // Get all the productList where productFamilyId contains UPDATED_PRODUCT_FAMILY_ID
        defaultProductShouldNotBeFound("productFamilyId.contains=" + UPDATED_PRODUCT_FAMILY_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductFamilyIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productFamilyId does not contain DEFAULT_PRODUCT_FAMILY_ID
        defaultProductShouldNotBeFound("productFamilyId.doesNotContain=" + DEFAULT_PRODUCT_FAMILY_ID);

        // Get all the productList where productFamilyId does not contain UPDATED_PRODUCT_FAMILY_ID
        defaultProductShouldBeFound("productFamilyId.doesNotContain=" + UPDATED_PRODUCT_FAMILY_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByNameEnUsIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameEnUs equals to DEFAULT_NAME_EN_US
        defaultProductShouldBeFound("nameEnUs.equals=" + DEFAULT_NAME_EN_US);

        // Get all the productList where nameEnUs equals to UPDATED_NAME_EN_US
        defaultProductShouldNotBeFound("nameEnUs.equals=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByNameEnUsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameEnUs not equals to DEFAULT_NAME_EN_US
        defaultProductShouldNotBeFound("nameEnUs.notEquals=" + DEFAULT_NAME_EN_US);

        // Get all the productList where nameEnUs not equals to UPDATED_NAME_EN_US
        defaultProductShouldBeFound("nameEnUs.notEquals=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByNameEnUsIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameEnUs in DEFAULT_NAME_EN_US or UPDATED_NAME_EN_US
        defaultProductShouldBeFound("nameEnUs.in=" + DEFAULT_NAME_EN_US + "," + UPDATED_NAME_EN_US);

        // Get all the productList where nameEnUs equals to UPDATED_NAME_EN_US
        defaultProductShouldNotBeFound("nameEnUs.in=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByNameEnUsIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameEnUs is not null
        defaultProductShouldBeFound("nameEnUs.specified=true");

        // Get all the productList where nameEnUs is null
        defaultProductShouldNotBeFound("nameEnUs.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByNameEnUsContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameEnUs contains DEFAULT_NAME_EN_US
        defaultProductShouldBeFound("nameEnUs.contains=" + DEFAULT_NAME_EN_US);

        // Get all the productList where nameEnUs contains UPDATED_NAME_EN_US
        defaultProductShouldNotBeFound("nameEnUs.contains=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByNameEnUsNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameEnUs does not contain DEFAULT_NAME_EN_US
        defaultProductShouldNotBeFound("nameEnUs.doesNotContain=" + DEFAULT_NAME_EN_US);

        // Get all the productList where nameEnUs does not contain UPDATED_NAME_EN_US
        defaultProductShouldBeFound("nameEnUs.doesNotContain=" + UPDATED_NAME_EN_US);
    }


    @Test
    @Transactional
    public void getAllProductsByNameDeDeIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameDeDe equals to DEFAULT_NAME_DE_DE
        defaultProductShouldBeFound("nameDeDe.equals=" + DEFAULT_NAME_DE_DE);

        // Get all the productList where nameDeDe equals to UPDATED_NAME_DE_DE
        defaultProductShouldNotBeFound("nameDeDe.equals=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByNameDeDeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameDeDe not equals to DEFAULT_NAME_DE_DE
        defaultProductShouldNotBeFound("nameDeDe.notEquals=" + DEFAULT_NAME_DE_DE);

        // Get all the productList where nameDeDe not equals to UPDATED_NAME_DE_DE
        defaultProductShouldBeFound("nameDeDe.notEquals=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByNameDeDeIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameDeDe in DEFAULT_NAME_DE_DE or UPDATED_NAME_DE_DE
        defaultProductShouldBeFound("nameDeDe.in=" + DEFAULT_NAME_DE_DE + "," + UPDATED_NAME_DE_DE);

        // Get all the productList where nameDeDe equals to UPDATED_NAME_DE_DE
        defaultProductShouldNotBeFound("nameDeDe.in=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByNameDeDeIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameDeDe is not null
        defaultProductShouldBeFound("nameDeDe.specified=true");

        // Get all the productList where nameDeDe is null
        defaultProductShouldNotBeFound("nameDeDe.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByNameDeDeContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameDeDe contains DEFAULT_NAME_DE_DE
        defaultProductShouldBeFound("nameDeDe.contains=" + DEFAULT_NAME_DE_DE);

        // Get all the productList where nameDeDe contains UPDATED_NAME_DE_DE
        defaultProductShouldNotBeFound("nameDeDe.contains=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByNameDeDeNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where nameDeDe does not contain DEFAULT_NAME_DE_DE
        defaultProductShouldNotBeFound("nameDeDe.doesNotContain=" + DEFAULT_NAME_DE_DE);

        // Get all the productList where nameDeDe does not contain UPDATED_NAME_DE_DE
        defaultProductShouldBeFound("nameDeDe.doesNotContain=" + UPDATED_NAME_DE_DE);
    }


    @Test
    @Transactional
    public void getAllProductsByProductStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productStatus equals to DEFAULT_PRODUCT_STATUS
        defaultProductShouldBeFound("productStatus.equals=" + DEFAULT_PRODUCT_STATUS);

        // Get all the productList where productStatus equals to UPDATED_PRODUCT_STATUS
        defaultProductShouldNotBeFound("productStatus.equals=" + UPDATED_PRODUCT_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByProductStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productStatus not equals to DEFAULT_PRODUCT_STATUS
        defaultProductShouldNotBeFound("productStatus.notEquals=" + DEFAULT_PRODUCT_STATUS);

        // Get all the productList where productStatus not equals to UPDATED_PRODUCT_STATUS
        defaultProductShouldBeFound("productStatus.notEquals=" + UPDATED_PRODUCT_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByProductStatusIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productStatus in DEFAULT_PRODUCT_STATUS or UPDATED_PRODUCT_STATUS
        defaultProductShouldBeFound("productStatus.in=" + DEFAULT_PRODUCT_STATUS + "," + UPDATED_PRODUCT_STATUS);

        // Get all the productList where productStatus equals to UPDATED_PRODUCT_STATUS
        defaultProductShouldNotBeFound("productStatus.in=" + UPDATED_PRODUCT_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByProductStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productStatus is not null
        defaultProductShouldBeFound("productStatus.specified=true");

        // Get all the productList where productStatus is null
        defaultProductShouldNotBeFound("productStatus.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByProductStatusContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productStatus contains DEFAULT_PRODUCT_STATUS
        defaultProductShouldBeFound("productStatus.contains=" + DEFAULT_PRODUCT_STATUS);

        // Get all the productList where productStatus contains UPDATED_PRODUCT_STATUS
        defaultProductShouldNotBeFound("productStatus.contains=" + UPDATED_PRODUCT_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByProductStatusNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productStatus does not contain DEFAULT_PRODUCT_STATUS
        defaultProductShouldNotBeFound("productStatus.doesNotContain=" + DEFAULT_PRODUCT_STATUS);

        // Get all the productList where productStatus does not contain UPDATED_PRODUCT_STATUS
        defaultProductShouldBeFound("productStatus.doesNotContain=" + UPDATED_PRODUCT_STATUS);
    }


    @Test
    @Transactional
    public void getAllProductsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price equals to DEFAULT_PRICE
        defaultProductShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the productList where price equals to UPDATED_PRICE
        defaultProductShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price not equals to DEFAULT_PRICE
        defaultProductShouldNotBeFound("price.notEquals=" + DEFAULT_PRICE);

        // Get all the productList where price not equals to UPDATED_PRICE
        defaultProductShouldBeFound("price.notEquals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultProductShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the productList where price equals to UPDATED_PRICE
        defaultProductShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price is not null
        defaultProductShouldBeFound("price.specified=true");

        // Get all the productList where price is null
        defaultProductShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price is greater than or equal to DEFAULT_PRICE
        defaultProductShouldBeFound("price.greaterThanOrEqual=" + DEFAULT_PRICE);

        // Get all the productList where price is greater than or equal to UPDATED_PRICE
        defaultProductShouldNotBeFound("price.greaterThanOrEqual=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price is less than or equal to DEFAULT_PRICE
        defaultProductShouldBeFound("price.lessThanOrEqual=" + DEFAULT_PRICE);

        // Get all the productList where price is less than or equal to SMALLER_PRICE
        defaultProductShouldNotBeFound("price.lessThanOrEqual=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price is less than DEFAULT_PRICE
        defaultProductShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the productList where price is less than UPDATED_PRICE
        defaultProductShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where price is greater than DEFAULT_PRICE
        defaultProductShouldNotBeFound("price.greaterThan=" + DEFAULT_PRICE);

        // Get all the productList where price is greater than SMALLER_PRICE
        defaultProductShouldBeFound("price.greaterThan=" + SMALLER_PRICE);
    }


    @Test
    @Transactional
    public void getAllProductsByCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where currency equals to DEFAULT_CURRENCY
        defaultProductShouldBeFound("currency.equals=" + DEFAULT_CURRENCY);

        // Get all the productList where currency equals to UPDATED_CURRENCY
        defaultProductShouldNotBeFound("currency.equals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByCurrencyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where currency not equals to DEFAULT_CURRENCY
        defaultProductShouldNotBeFound("currency.notEquals=" + DEFAULT_CURRENCY);

        // Get all the productList where currency not equals to UPDATED_CURRENCY
        defaultProductShouldBeFound("currency.notEquals=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where currency in DEFAULT_CURRENCY or UPDATED_CURRENCY
        defaultProductShouldBeFound("currency.in=" + DEFAULT_CURRENCY + "," + UPDATED_CURRENCY);

        // Get all the productList where currency equals to UPDATED_CURRENCY
        defaultProductShouldNotBeFound("currency.in=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where currency is not null
        defaultProductShouldBeFound("currency.specified=true");

        // Get all the productList where currency is null
        defaultProductShouldNotBeFound("currency.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByCurrencyContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where currency contains DEFAULT_CURRENCY
        defaultProductShouldBeFound("currency.contains=" + DEFAULT_CURRENCY);

        // Get all the productList where currency contains UPDATED_CURRENCY
        defaultProductShouldNotBeFound("currency.contains=" + UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByCurrencyNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where currency does not contain DEFAULT_CURRENCY
        defaultProductShouldNotBeFound("currency.doesNotContain=" + DEFAULT_CURRENCY);

        // Get all the productList where currency does not contain UPDATED_CURRENCY
        defaultProductShouldBeFound("currency.doesNotContain=" + UPDATED_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllProductsByPriceCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where priceCurrency equals to DEFAULT_PRICE_CURRENCY
        defaultProductShouldBeFound("priceCurrency.equals=" + DEFAULT_PRICE_CURRENCY);

        // Get all the productList where priceCurrency equals to UPDATED_PRICE_CURRENCY
        defaultProductShouldNotBeFound("priceCurrency.equals=" + UPDATED_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceCurrencyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where priceCurrency not equals to DEFAULT_PRICE_CURRENCY
        defaultProductShouldNotBeFound("priceCurrency.notEquals=" + DEFAULT_PRICE_CURRENCY);

        // Get all the productList where priceCurrency not equals to UPDATED_PRICE_CURRENCY
        defaultProductShouldBeFound("priceCurrency.notEquals=" + UPDATED_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where priceCurrency in DEFAULT_PRICE_CURRENCY or UPDATED_PRICE_CURRENCY
        defaultProductShouldBeFound("priceCurrency.in=" + DEFAULT_PRICE_CURRENCY + "," + UPDATED_PRICE_CURRENCY);

        // Get all the productList where priceCurrency equals to UPDATED_PRICE_CURRENCY
        defaultProductShouldNotBeFound("priceCurrency.in=" + UPDATED_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where priceCurrency is not null
        defaultProductShouldBeFound("priceCurrency.specified=true");

        // Get all the productList where priceCurrency is null
        defaultProductShouldNotBeFound("priceCurrency.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByPriceCurrencyContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where priceCurrency contains DEFAULT_PRICE_CURRENCY
        defaultProductShouldBeFound("priceCurrency.contains=" + DEFAULT_PRICE_CURRENCY);

        // Get all the productList where priceCurrency contains UPDATED_PRICE_CURRENCY
        defaultProductShouldNotBeFound("priceCurrency.contains=" + UPDATED_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByPriceCurrencyNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where priceCurrency does not contain DEFAULT_PRICE_CURRENCY
        defaultProductShouldNotBeFound("priceCurrency.doesNotContain=" + DEFAULT_PRICE_CURRENCY);

        // Get all the productList where priceCurrency does not contain UPDATED_PRICE_CURRENCY
        defaultProductShouldBeFound("priceCurrency.doesNotContain=" + UPDATED_PRICE_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllProductsByTaxIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where taxId equals to DEFAULT_TAX_ID
        defaultProductShouldBeFound("taxId.equals=" + DEFAULT_TAX_ID);

        // Get all the productList where taxId equals to UPDATED_TAX_ID
        defaultProductShouldNotBeFound("taxId.equals=" + UPDATED_TAX_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByTaxIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where taxId not equals to DEFAULT_TAX_ID
        defaultProductShouldNotBeFound("taxId.notEquals=" + DEFAULT_TAX_ID);

        // Get all the productList where taxId not equals to UPDATED_TAX_ID
        defaultProductShouldBeFound("taxId.notEquals=" + UPDATED_TAX_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByTaxIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where taxId in DEFAULT_TAX_ID or UPDATED_TAX_ID
        defaultProductShouldBeFound("taxId.in=" + DEFAULT_TAX_ID + "," + UPDATED_TAX_ID);

        // Get all the productList where taxId equals to UPDATED_TAX_ID
        defaultProductShouldNotBeFound("taxId.in=" + UPDATED_TAX_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByTaxIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where taxId is not null
        defaultProductShouldBeFound("taxId.specified=true");

        // Get all the productList where taxId is null
        defaultProductShouldNotBeFound("taxId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByTaxIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where taxId contains DEFAULT_TAX_ID
        defaultProductShouldBeFound("taxId.contains=" + DEFAULT_TAX_ID);

        // Get all the productList where taxId contains UPDATED_TAX_ID
        defaultProductShouldNotBeFound("taxId.contains=" + UPDATED_TAX_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByTaxIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where taxId does not contain DEFAULT_TAX_ID
        defaultProductShouldNotBeFound("taxId.doesNotContain=" + DEFAULT_TAX_ID);

        // Get all the productList where taxId does not contain UPDATED_TAX_ID
        defaultProductShouldBeFound("taxId.doesNotContain=" + UPDATED_TAX_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByEanIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ean equals to DEFAULT_EAN
        defaultProductShouldBeFound("ean.equals=" + DEFAULT_EAN);

        // Get all the productList where ean equals to UPDATED_EAN
        defaultProductShouldNotBeFound("ean.equals=" + UPDATED_EAN);
    }

    @Test
    @Transactional
    public void getAllProductsByEanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ean not equals to DEFAULT_EAN
        defaultProductShouldNotBeFound("ean.notEquals=" + DEFAULT_EAN);

        // Get all the productList where ean not equals to UPDATED_EAN
        defaultProductShouldBeFound("ean.notEquals=" + UPDATED_EAN);
    }

    @Test
    @Transactional
    public void getAllProductsByEanIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ean in DEFAULT_EAN or UPDATED_EAN
        defaultProductShouldBeFound("ean.in=" + DEFAULT_EAN + "," + UPDATED_EAN);

        // Get all the productList where ean equals to UPDATED_EAN
        defaultProductShouldNotBeFound("ean.in=" + UPDATED_EAN);
    }

    @Test
    @Transactional
    public void getAllProductsByEanIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ean is not null
        defaultProductShouldBeFound("ean.specified=true");

        // Get all the productList where ean is null
        defaultProductShouldNotBeFound("ean.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByEanContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ean contains DEFAULT_EAN
        defaultProductShouldBeFound("ean.contains=" + DEFAULT_EAN);

        // Get all the productList where ean contains UPDATED_EAN
        defaultProductShouldNotBeFound("ean.contains=" + UPDATED_EAN);
    }

    @Test
    @Transactional
    public void getAllProductsByEanNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ean does not contain DEFAULT_EAN
        defaultProductShouldNotBeFound("ean.doesNotContain=" + DEFAULT_EAN);

        // Get all the productList where ean does not contain UPDATED_EAN
        defaultProductShouldBeFound("ean.doesNotContain=" + UPDATED_EAN);
    }


    @Test
    @Transactional
    public void getAllProductsByMpnIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where mpn equals to DEFAULT_MPN
        defaultProductShouldBeFound("mpn.equals=" + DEFAULT_MPN);

        // Get all the productList where mpn equals to UPDATED_MPN
        defaultProductShouldNotBeFound("mpn.equals=" + UPDATED_MPN);
    }

    @Test
    @Transactional
    public void getAllProductsByMpnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where mpn not equals to DEFAULT_MPN
        defaultProductShouldNotBeFound("mpn.notEquals=" + DEFAULT_MPN);

        // Get all the productList where mpn not equals to UPDATED_MPN
        defaultProductShouldBeFound("mpn.notEquals=" + UPDATED_MPN);
    }

    @Test
    @Transactional
    public void getAllProductsByMpnIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where mpn in DEFAULT_MPN or UPDATED_MPN
        defaultProductShouldBeFound("mpn.in=" + DEFAULT_MPN + "," + UPDATED_MPN);

        // Get all the productList where mpn equals to UPDATED_MPN
        defaultProductShouldNotBeFound("mpn.in=" + UPDATED_MPN);
    }

    @Test
    @Transactional
    public void getAllProductsByMpnIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where mpn is not null
        defaultProductShouldBeFound("mpn.specified=true");

        // Get all the productList where mpn is null
        defaultProductShouldNotBeFound("mpn.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByMpnContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where mpn contains DEFAULT_MPN
        defaultProductShouldBeFound("mpn.contains=" + DEFAULT_MPN);

        // Get all the productList where mpn contains UPDATED_MPN
        defaultProductShouldNotBeFound("mpn.contains=" + UPDATED_MPN);
    }

    @Test
    @Transactional
    public void getAllProductsByMpnNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where mpn does not contain DEFAULT_MPN
        defaultProductShouldNotBeFound("mpn.doesNotContain=" + DEFAULT_MPN);

        // Get all the productList where mpn does not contain UPDATED_MPN
        defaultProductShouldBeFound("mpn.doesNotContain=" + UPDATED_MPN);
    }


    @Test
    @Transactional
    public void getAllProductsByPackagingIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packagingId equals to DEFAULT_PACKAGING_ID
        defaultProductShouldBeFound("packagingId.equals=" + DEFAULT_PACKAGING_ID);

        // Get all the productList where packagingId equals to UPDATED_PACKAGING_ID
        defaultProductShouldNotBeFound("packagingId.equals=" + UPDATED_PACKAGING_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByPackagingIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packagingId not equals to DEFAULT_PACKAGING_ID
        defaultProductShouldNotBeFound("packagingId.notEquals=" + DEFAULT_PACKAGING_ID);

        // Get all the productList where packagingId not equals to UPDATED_PACKAGING_ID
        defaultProductShouldBeFound("packagingId.notEquals=" + UPDATED_PACKAGING_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByPackagingIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packagingId in DEFAULT_PACKAGING_ID or UPDATED_PACKAGING_ID
        defaultProductShouldBeFound("packagingId.in=" + DEFAULT_PACKAGING_ID + "," + UPDATED_PACKAGING_ID);

        // Get all the productList where packagingId equals to UPDATED_PACKAGING_ID
        defaultProductShouldNotBeFound("packagingId.in=" + UPDATED_PACKAGING_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByPackagingIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packagingId is not null
        defaultProductShouldBeFound("packagingId.specified=true");

        // Get all the productList where packagingId is null
        defaultProductShouldNotBeFound("packagingId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByPackagingIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packagingId contains DEFAULT_PACKAGING_ID
        defaultProductShouldBeFound("packagingId.contains=" + DEFAULT_PACKAGING_ID);

        // Get all the productList where packagingId contains UPDATED_PACKAGING_ID
        defaultProductShouldNotBeFound("packagingId.contains=" + UPDATED_PACKAGING_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByPackagingIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packagingId does not contain DEFAULT_PACKAGING_ID
        defaultProductShouldNotBeFound("packagingId.doesNotContain=" + DEFAULT_PACKAGING_ID);

        // Get all the productList where packagingId does not contain UPDATED_PACKAGING_ID
        defaultProductShouldBeFound("packagingId.doesNotContain=" + UPDATED_PACKAGING_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByUvpIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp equals to DEFAULT_UVP
        defaultProductShouldBeFound("uvp.equals=" + DEFAULT_UVP);

        // Get all the productList where uvp equals to UPDATED_UVP
        defaultProductShouldNotBeFound("uvp.equals=" + UPDATED_UVP);
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp not equals to DEFAULT_UVP
        defaultProductShouldNotBeFound("uvp.notEquals=" + DEFAULT_UVP);

        // Get all the productList where uvp not equals to UPDATED_UVP
        defaultProductShouldBeFound("uvp.notEquals=" + UPDATED_UVP);
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp in DEFAULT_UVP or UPDATED_UVP
        defaultProductShouldBeFound("uvp.in=" + DEFAULT_UVP + "," + UPDATED_UVP);

        // Get all the productList where uvp equals to UPDATED_UVP
        defaultProductShouldNotBeFound("uvp.in=" + UPDATED_UVP);
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp is not null
        defaultProductShouldBeFound("uvp.specified=true");

        // Get all the productList where uvp is null
        defaultProductShouldNotBeFound("uvp.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp is greater than or equal to DEFAULT_UVP
        defaultProductShouldBeFound("uvp.greaterThanOrEqual=" + DEFAULT_UVP);

        // Get all the productList where uvp is greater than or equal to UPDATED_UVP
        defaultProductShouldNotBeFound("uvp.greaterThanOrEqual=" + UPDATED_UVP);
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp is less than or equal to DEFAULT_UVP
        defaultProductShouldBeFound("uvp.lessThanOrEqual=" + DEFAULT_UVP);

        // Get all the productList where uvp is less than or equal to SMALLER_UVP
        defaultProductShouldNotBeFound("uvp.lessThanOrEqual=" + SMALLER_UVP);
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp is less than DEFAULT_UVP
        defaultProductShouldNotBeFound("uvp.lessThan=" + DEFAULT_UVP);

        // Get all the productList where uvp is less than UPDATED_UVP
        defaultProductShouldBeFound("uvp.lessThan=" + UPDATED_UVP);
    }

    @Test
    @Transactional
    public void getAllProductsByUvpIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where uvp is greater than DEFAULT_UVP
        defaultProductShouldNotBeFound("uvp.greaterThan=" + DEFAULT_UVP);

        // Get all the productList where uvp is greater than SMALLER_UVP
        defaultProductShouldBeFound("uvp.greaterThan=" + SMALLER_UVP);
    }


    @Test
    @Transactional
    public void getAllProductsByOwnerUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ownerUserId equals to DEFAULT_OWNER_USER_ID
        defaultProductShouldBeFound("ownerUserId.equals=" + DEFAULT_OWNER_USER_ID);

        // Get all the productList where ownerUserId equals to UPDATED_OWNER_USER_ID
        defaultProductShouldNotBeFound("ownerUserId.equals=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByOwnerUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ownerUserId not equals to DEFAULT_OWNER_USER_ID
        defaultProductShouldNotBeFound("ownerUserId.notEquals=" + DEFAULT_OWNER_USER_ID);

        // Get all the productList where ownerUserId not equals to UPDATED_OWNER_USER_ID
        defaultProductShouldBeFound("ownerUserId.notEquals=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByOwnerUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ownerUserId in DEFAULT_OWNER_USER_ID or UPDATED_OWNER_USER_ID
        defaultProductShouldBeFound("ownerUserId.in=" + DEFAULT_OWNER_USER_ID + "," + UPDATED_OWNER_USER_ID);

        // Get all the productList where ownerUserId equals to UPDATED_OWNER_USER_ID
        defaultProductShouldNotBeFound("ownerUserId.in=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByOwnerUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ownerUserId is not null
        defaultProductShouldBeFound("ownerUserId.specified=true");

        // Get all the productList where ownerUserId is null
        defaultProductShouldNotBeFound("ownerUserId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByOwnerUserIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ownerUserId contains DEFAULT_OWNER_USER_ID
        defaultProductShouldBeFound("ownerUserId.contains=" + DEFAULT_OWNER_USER_ID);

        // Get all the productList where ownerUserId contains UPDATED_OWNER_USER_ID
        defaultProductShouldNotBeFound("ownerUserId.contains=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByOwnerUserIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where ownerUserId does not contain DEFAULT_OWNER_USER_ID
        defaultProductShouldNotBeFound("ownerUserId.doesNotContain=" + DEFAULT_OWNER_USER_ID);

        // Get all the productList where ownerUserId does not contain UPDATED_OWNER_USER_ID
        defaultProductShouldBeFound("ownerUserId.doesNotContain=" + UPDATED_OWNER_USER_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByAssignedUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where assignedUserId equals to DEFAULT_ASSIGNED_USER_ID
        defaultProductShouldBeFound("assignedUserId.equals=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the productList where assignedUserId equals to UPDATED_ASSIGNED_USER_ID
        defaultProductShouldNotBeFound("assignedUserId.equals=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByAssignedUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where assignedUserId not equals to DEFAULT_ASSIGNED_USER_ID
        defaultProductShouldNotBeFound("assignedUserId.notEquals=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the productList where assignedUserId not equals to UPDATED_ASSIGNED_USER_ID
        defaultProductShouldBeFound("assignedUserId.notEquals=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByAssignedUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where assignedUserId in DEFAULT_ASSIGNED_USER_ID or UPDATED_ASSIGNED_USER_ID
        defaultProductShouldBeFound("assignedUserId.in=" + DEFAULT_ASSIGNED_USER_ID + "," + UPDATED_ASSIGNED_USER_ID);

        // Get all the productList where assignedUserId equals to UPDATED_ASSIGNED_USER_ID
        defaultProductShouldNotBeFound("assignedUserId.in=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByAssignedUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where assignedUserId is not null
        defaultProductShouldBeFound("assignedUserId.specified=true");

        // Get all the productList where assignedUserId is null
        defaultProductShouldNotBeFound("assignedUserId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByAssignedUserIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where assignedUserId contains DEFAULT_ASSIGNED_USER_ID
        defaultProductShouldBeFound("assignedUserId.contains=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the productList where assignedUserId contains UPDATED_ASSIGNED_USER_ID
        defaultProductShouldNotBeFound("assignedUserId.contains=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByAssignedUserIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where assignedUserId does not contain DEFAULT_ASSIGNED_USER_ID
        defaultProductShouldNotBeFound("assignedUserId.doesNotContain=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the productList where assignedUserId does not contain UPDATED_ASSIGNED_USER_ID
        defaultProductShouldBeFound("assignedUserId.doesNotContain=" + UPDATED_ASSIGNED_USER_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice equals to DEFAULT_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.equals=" + DEFAULT_FINAL_PRICE);

        // Get all the productList where finalPrice equals to UPDATED_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.equals=" + UPDATED_FINAL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice not equals to DEFAULT_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.notEquals=" + DEFAULT_FINAL_PRICE);

        // Get all the productList where finalPrice not equals to UPDATED_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.notEquals=" + UPDATED_FINAL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice in DEFAULT_FINAL_PRICE or UPDATED_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.in=" + DEFAULT_FINAL_PRICE + "," + UPDATED_FINAL_PRICE);

        // Get all the productList where finalPrice equals to UPDATED_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.in=" + UPDATED_FINAL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice is not null
        defaultProductShouldBeFound("finalPrice.specified=true");

        // Get all the productList where finalPrice is null
        defaultProductShouldNotBeFound("finalPrice.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice is greater than or equal to DEFAULT_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.greaterThanOrEqual=" + DEFAULT_FINAL_PRICE);

        // Get all the productList where finalPrice is greater than or equal to UPDATED_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.greaterThanOrEqual=" + UPDATED_FINAL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice is less than or equal to DEFAULT_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.lessThanOrEqual=" + DEFAULT_FINAL_PRICE);

        // Get all the productList where finalPrice is less than or equal to SMALLER_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.lessThanOrEqual=" + SMALLER_FINAL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice is less than DEFAULT_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.lessThan=" + DEFAULT_FINAL_PRICE);

        // Get all the productList where finalPrice is less than UPDATED_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.lessThan=" + UPDATED_FINAL_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPrice is greater than DEFAULT_FINAL_PRICE
        defaultProductShouldNotBeFound("finalPrice.greaterThan=" + DEFAULT_FINAL_PRICE);

        // Get all the productList where finalPrice is greater than SMALLER_FINAL_PRICE
        defaultProductShouldBeFound("finalPrice.greaterThan=" + SMALLER_FINAL_PRICE);
    }


    @Test
    @Transactional
    public void getAllProductsByFinalPriceCurrencyIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPriceCurrency equals to DEFAULT_FINAL_PRICE_CURRENCY
        defaultProductShouldBeFound("finalPriceCurrency.equals=" + DEFAULT_FINAL_PRICE_CURRENCY);

        // Get all the productList where finalPriceCurrency equals to UPDATED_FINAL_PRICE_CURRENCY
        defaultProductShouldNotBeFound("finalPriceCurrency.equals=" + UPDATED_FINAL_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceCurrencyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPriceCurrency not equals to DEFAULT_FINAL_PRICE_CURRENCY
        defaultProductShouldNotBeFound("finalPriceCurrency.notEquals=" + DEFAULT_FINAL_PRICE_CURRENCY);

        // Get all the productList where finalPriceCurrency not equals to UPDATED_FINAL_PRICE_CURRENCY
        defaultProductShouldBeFound("finalPriceCurrency.notEquals=" + UPDATED_FINAL_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceCurrencyIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPriceCurrency in DEFAULT_FINAL_PRICE_CURRENCY or UPDATED_FINAL_PRICE_CURRENCY
        defaultProductShouldBeFound("finalPriceCurrency.in=" + DEFAULT_FINAL_PRICE_CURRENCY + "," + UPDATED_FINAL_PRICE_CURRENCY);

        // Get all the productList where finalPriceCurrency equals to UPDATED_FINAL_PRICE_CURRENCY
        defaultProductShouldNotBeFound("finalPriceCurrency.in=" + UPDATED_FINAL_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceCurrencyIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPriceCurrency is not null
        defaultProductShouldBeFound("finalPriceCurrency.specified=true");

        // Get all the productList where finalPriceCurrency is null
        defaultProductShouldNotBeFound("finalPriceCurrency.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByFinalPriceCurrencyContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPriceCurrency contains DEFAULT_FINAL_PRICE_CURRENCY
        defaultProductShouldBeFound("finalPriceCurrency.contains=" + DEFAULT_FINAL_PRICE_CURRENCY);

        // Get all the productList where finalPriceCurrency contains UPDATED_FINAL_PRICE_CURRENCY
        defaultProductShouldNotBeFound("finalPriceCurrency.contains=" + UPDATED_FINAL_PRICE_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllProductsByFinalPriceCurrencyNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where finalPriceCurrency does not contain DEFAULT_FINAL_PRICE_CURRENCY
        defaultProductShouldNotBeFound("finalPriceCurrency.doesNotContain=" + DEFAULT_FINAL_PRICE_CURRENCY);

        // Get all the productList where finalPriceCurrency does not contain UPDATED_FINAL_PRICE_CURRENCY
        defaultProductShouldBeFound("finalPriceCurrency.doesNotContain=" + UPDATED_FINAL_PRICE_CURRENCY);
    }


    @Test
    @Transactional
    public void getAllProductsByProductSerieIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productSerieId equals to DEFAULT_PRODUCT_SERIE_ID
        defaultProductShouldBeFound("productSerieId.equals=" + DEFAULT_PRODUCT_SERIE_ID);

        // Get all the productList where productSerieId equals to UPDATED_PRODUCT_SERIE_ID
        defaultProductShouldNotBeFound("productSerieId.equals=" + UPDATED_PRODUCT_SERIE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductSerieIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productSerieId not equals to DEFAULT_PRODUCT_SERIE_ID
        defaultProductShouldNotBeFound("productSerieId.notEquals=" + DEFAULT_PRODUCT_SERIE_ID);

        // Get all the productList where productSerieId not equals to UPDATED_PRODUCT_SERIE_ID
        defaultProductShouldBeFound("productSerieId.notEquals=" + UPDATED_PRODUCT_SERIE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductSerieIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productSerieId in DEFAULT_PRODUCT_SERIE_ID or UPDATED_PRODUCT_SERIE_ID
        defaultProductShouldBeFound("productSerieId.in=" + DEFAULT_PRODUCT_SERIE_ID + "," + UPDATED_PRODUCT_SERIE_ID);

        // Get all the productList where productSerieId equals to UPDATED_PRODUCT_SERIE_ID
        defaultProductShouldNotBeFound("productSerieId.in=" + UPDATED_PRODUCT_SERIE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductSerieIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productSerieId is not null
        defaultProductShouldBeFound("productSerieId.specified=true");

        // Get all the productList where productSerieId is null
        defaultProductShouldNotBeFound("productSerieId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByProductSerieIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productSerieId contains DEFAULT_PRODUCT_SERIE_ID
        defaultProductShouldBeFound("productSerieId.contains=" + DEFAULT_PRODUCT_SERIE_ID);

        // Get all the productList where productSerieId contains UPDATED_PRODUCT_SERIE_ID
        defaultProductShouldNotBeFound("productSerieId.contains=" + UPDATED_PRODUCT_SERIE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByProductSerieIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productSerieId does not contain DEFAULT_PRODUCT_SERIE_ID
        defaultProductShouldNotBeFound("productSerieId.doesNotContain=" + DEFAULT_PRODUCT_SERIE_ID);

        // Get all the productList where productSerieId does not contain UPDATED_PRODUCT_SERIE_ID
        defaultProductShouldBeFound("productSerieId.doesNotContain=" + UPDATED_PRODUCT_SERIE_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByCatalogIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where catalogId equals to DEFAULT_CATALOG_ID
        defaultProductShouldBeFound("catalogId.equals=" + DEFAULT_CATALOG_ID);

        // Get all the productList where catalogId equals to UPDATED_CATALOG_ID
        defaultProductShouldNotBeFound("catalogId.equals=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCatalogIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where catalogId not equals to DEFAULT_CATALOG_ID
        defaultProductShouldNotBeFound("catalogId.notEquals=" + DEFAULT_CATALOG_ID);

        // Get all the productList where catalogId not equals to UPDATED_CATALOG_ID
        defaultProductShouldBeFound("catalogId.notEquals=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCatalogIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where catalogId in DEFAULT_CATALOG_ID or UPDATED_CATALOG_ID
        defaultProductShouldBeFound("catalogId.in=" + DEFAULT_CATALOG_ID + "," + UPDATED_CATALOG_ID);

        // Get all the productList where catalogId equals to UPDATED_CATALOG_ID
        defaultProductShouldNotBeFound("catalogId.in=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCatalogIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where catalogId is not null
        defaultProductShouldBeFound("catalogId.specified=true");

        // Get all the productList where catalogId is null
        defaultProductShouldNotBeFound("catalogId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByCatalogIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where catalogId contains DEFAULT_CATALOG_ID
        defaultProductShouldBeFound("catalogId.contains=" + DEFAULT_CATALOG_ID);

        // Get all the productList where catalogId contains UPDATED_CATALOG_ID
        defaultProductShouldNotBeFound("catalogId.contains=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByCatalogIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where catalogId does not contain DEFAULT_CATALOG_ID
        defaultProductShouldNotBeFound("catalogId.doesNotContain=" + DEFAULT_CATALOG_ID);

        // Get all the productList where catalogId does not contain UPDATED_CATALOG_ID
        defaultProductShouldBeFound("catalogId.doesNotContain=" + UPDATED_CATALOG_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount equals to DEFAULT_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.equals=" + DEFAULT_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount equals to UPDATED_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.equals=" + UPDATED_BASE_PRICE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount not equals to DEFAULT_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.notEquals=" + DEFAULT_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount not equals to UPDATED_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.notEquals=" + UPDATED_BASE_PRICE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount in DEFAULT_BASE_PRICE_AMOUNT or UPDATED_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.in=" + DEFAULT_BASE_PRICE_AMOUNT + "," + UPDATED_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount equals to UPDATED_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.in=" + UPDATED_BASE_PRICE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount is not null
        defaultProductShouldBeFound("basePriceAmount.specified=true");

        // Get all the productList where basePriceAmount is null
        defaultProductShouldNotBeFound("basePriceAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount is greater than or equal to DEFAULT_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.greaterThanOrEqual=" + DEFAULT_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount is greater than or equal to UPDATED_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.greaterThanOrEqual=" + UPDATED_BASE_PRICE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount is less than or equal to DEFAULT_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.lessThanOrEqual=" + DEFAULT_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount is less than or equal to SMALLER_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.lessThanOrEqual=" + SMALLER_BASE_PRICE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount is less than DEFAULT_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.lessThan=" + DEFAULT_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount is less than UPDATED_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.lessThan=" + UPDATED_BASE_PRICE_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByBasePriceAmountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePriceAmount is greater than DEFAULT_BASE_PRICE_AMOUNT
        defaultProductShouldNotBeFound("basePriceAmount.greaterThan=" + DEFAULT_BASE_PRICE_AMOUNT);

        // Get all the productList where basePriceAmount is greater than SMALLER_BASE_PRICE_AMOUNT
        defaultProductShouldBeFound("basePriceAmount.greaterThan=" + SMALLER_BASE_PRICE_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount equals to DEFAULT_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.equals=" + DEFAULT_PACKED_AMOUNT);

        // Get all the productList where packedAmount equals to UPDATED_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.equals=" + UPDATED_PACKED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount not equals to DEFAULT_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.notEquals=" + DEFAULT_PACKED_AMOUNT);

        // Get all the productList where packedAmount not equals to UPDATED_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.notEquals=" + UPDATED_PACKED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount in DEFAULT_PACKED_AMOUNT or UPDATED_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.in=" + DEFAULT_PACKED_AMOUNT + "," + UPDATED_PACKED_AMOUNT);

        // Get all the productList where packedAmount equals to UPDATED_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.in=" + UPDATED_PACKED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount is not null
        defaultProductShouldBeFound("packedAmount.specified=true");

        // Get all the productList where packedAmount is null
        defaultProductShouldNotBeFound("packedAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount is greater than or equal to DEFAULT_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.greaterThanOrEqual=" + DEFAULT_PACKED_AMOUNT);

        // Get all the productList where packedAmount is greater than or equal to UPDATED_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.greaterThanOrEqual=" + UPDATED_PACKED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount is less than or equal to DEFAULT_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.lessThanOrEqual=" + DEFAULT_PACKED_AMOUNT);

        // Get all the productList where packedAmount is less than or equal to SMALLER_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.lessThanOrEqual=" + SMALLER_PACKED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount is less than DEFAULT_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.lessThan=" + DEFAULT_PACKED_AMOUNT);

        // Get all the productList where packedAmount is less than UPDATED_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.lessThan=" + UPDATED_PACKED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllProductsByPackedAmountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where packedAmount is greater than DEFAULT_PACKED_AMOUNT
        defaultProductShouldNotBeFound("packedAmount.greaterThan=" + DEFAULT_PACKED_AMOUNT);

        // Get all the productList where packedAmount is greater than SMALLER_PACKED_AMOUNT
        defaultProductShouldBeFound("packedAmount.greaterThan=" + SMALLER_PACKED_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllProductsByImageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where imageId equals to DEFAULT_IMAGE_ID
        defaultProductShouldBeFound("imageId.equals=" + DEFAULT_IMAGE_ID);

        // Get all the productList where imageId equals to UPDATED_IMAGE_ID
        defaultProductShouldNotBeFound("imageId.equals=" + UPDATED_IMAGE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByImageIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where imageId not equals to DEFAULT_IMAGE_ID
        defaultProductShouldNotBeFound("imageId.notEquals=" + DEFAULT_IMAGE_ID);

        // Get all the productList where imageId not equals to UPDATED_IMAGE_ID
        defaultProductShouldBeFound("imageId.notEquals=" + UPDATED_IMAGE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByImageIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where imageId in DEFAULT_IMAGE_ID or UPDATED_IMAGE_ID
        defaultProductShouldBeFound("imageId.in=" + DEFAULT_IMAGE_ID + "," + UPDATED_IMAGE_ID);

        // Get all the productList where imageId equals to UPDATED_IMAGE_ID
        defaultProductShouldNotBeFound("imageId.in=" + UPDATED_IMAGE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByImageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where imageId is not null
        defaultProductShouldBeFound("imageId.specified=true");

        // Get all the productList where imageId is null
        defaultProductShouldNotBeFound("imageId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByImageIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where imageId contains DEFAULT_IMAGE_ID
        defaultProductShouldBeFound("imageId.contains=" + DEFAULT_IMAGE_ID);

        // Get all the productList where imageId contains UPDATED_IMAGE_ID
        defaultProductShouldNotBeFound("imageId.contains=" + UPDATED_IMAGE_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByImageIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where imageId does not contain DEFAULT_IMAGE_ID
        defaultProductShouldNotBeFound("imageId.doesNotContain=" + DEFAULT_IMAGE_ID);

        // Get all the productList where imageId does not contain UPDATED_IMAGE_ID
        defaultProductShouldBeFound("imageId.doesNotContain=" + UPDATED_IMAGE_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByMeasuringUnitIdIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where measuringUnitId equals to DEFAULT_MEASURING_UNIT_ID
        defaultProductShouldBeFound("measuringUnitId.equals=" + DEFAULT_MEASURING_UNIT_ID);

        // Get all the productList where measuringUnitId equals to UPDATED_MEASURING_UNIT_ID
        defaultProductShouldNotBeFound("measuringUnitId.equals=" + UPDATED_MEASURING_UNIT_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByMeasuringUnitIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where measuringUnitId not equals to DEFAULT_MEASURING_UNIT_ID
        defaultProductShouldNotBeFound("measuringUnitId.notEquals=" + DEFAULT_MEASURING_UNIT_ID);

        // Get all the productList where measuringUnitId not equals to UPDATED_MEASURING_UNIT_ID
        defaultProductShouldBeFound("measuringUnitId.notEquals=" + UPDATED_MEASURING_UNIT_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByMeasuringUnitIdIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where measuringUnitId in DEFAULT_MEASURING_UNIT_ID or UPDATED_MEASURING_UNIT_ID
        defaultProductShouldBeFound("measuringUnitId.in=" + DEFAULT_MEASURING_UNIT_ID + "," + UPDATED_MEASURING_UNIT_ID);

        // Get all the productList where measuringUnitId equals to UPDATED_MEASURING_UNIT_ID
        defaultProductShouldNotBeFound("measuringUnitId.in=" + UPDATED_MEASURING_UNIT_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByMeasuringUnitIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where measuringUnitId is not null
        defaultProductShouldBeFound("measuringUnitId.specified=true");

        // Get all the productList where measuringUnitId is null
        defaultProductShouldNotBeFound("measuringUnitId.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByMeasuringUnitIdContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where measuringUnitId contains DEFAULT_MEASURING_UNIT_ID
        defaultProductShouldBeFound("measuringUnitId.contains=" + DEFAULT_MEASURING_UNIT_ID);

        // Get all the productList where measuringUnitId contains UPDATED_MEASURING_UNIT_ID
        defaultProductShouldNotBeFound("measuringUnitId.contains=" + UPDATED_MEASURING_UNIT_ID);
    }

    @Test
    @Transactional
    public void getAllProductsByMeasuringUnitIdNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where measuringUnitId does not contain DEFAULT_MEASURING_UNIT_ID
        defaultProductShouldNotBeFound("measuringUnitId.doesNotContain=" + DEFAULT_MEASURING_UNIT_ID);

        // Get all the productList where measuringUnitId does not contain UPDATED_MEASURING_UNIT_ID
        defaultProductShouldBeFound("measuringUnitId.doesNotContain=" + UPDATED_MEASURING_UNIT_ID);
    }


    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatus equals to DEFAULT_DELIVERY_STATUS
        defaultProductShouldBeFound("deliveryStatus.equals=" + DEFAULT_DELIVERY_STATUS);

        // Get all the productList where deliveryStatus equals to UPDATED_DELIVERY_STATUS
        defaultProductShouldNotBeFound("deliveryStatus.equals=" + UPDATED_DELIVERY_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatus not equals to DEFAULT_DELIVERY_STATUS
        defaultProductShouldNotBeFound("deliveryStatus.notEquals=" + DEFAULT_DELIVERY_STATUS);

        // Get all the productList where deliveryStatus not equals to UPDATED_DELIVERY_STATUS
        defaultProductShouldBeFound("deliveryStatus.notEquals=" + UPDATED_DELIVERY_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatus in DEFAULT_DELIVERY_STATUS or UPDATED_DELIVERY_STATUS
        defaultProductShouldBeFound("deliveryStatus.in=" + DEFAULT_DELIVERY_STATUS + "," + UPDATED_DELIVERY_STATUS);

        // Get all the productList where deliveryStatus equals to UPDATED_DELIVERY_STATUS
        defaultProductShouldNotBeFound("deliveryStatus.in=" + UPDATED_DELIVERY_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatus is not null
        defaultProductShouldBeFound("deliveryStatus.specified=true");

        // Get all the productList where deliveryStatus is null
        defaultProductShouldNotBeFound("deliveryStatus.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByDeliveryStatusContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatus contains DEFAULT_DELIVERY_STATUS
        defaultProductShouldBeFound("deliveryStatus.contains=" + DEFAULT_DELIVERY_STATUS);

        // Get all the productList where deliveryStatus contains UPDATED_DELIVERY_STATUS
        defaultProductShouldNotBeFound("deliveryStatus.contains=" + UPDATED_DELIVERY_STATUS);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatus does not contain DEFAULT_DELIVERY_STATUS
        defaultProductShouldNotBeFound("deliveryStatus.doesNotContain=" + DEFAULT_DELIVERY_STATUS);

        // Get all the productList where deliveryStatus does not contain UPDATED_DELIVERY_STATUS
        defaultProductShouldBeFound("deliveryStatus.doesNotContain=" + UPDATED_DELIVERY_STATUS);
    }


    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusDeDeIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusDeDe equals to DEFAULT_DELIVERY_STATUS_DE_DE
        defaultProductShouldBeFound("deliveryStatusDeDe.equals=" + DEFAULT_DELIVERY_STATUS_DE_DE);

        // Get all the productList where deliveryStatusDeDe equals to UPDATED_DELIVERY_STATUS_DE_DE
        defaultProductShouldNotBeFound("deliveryStatusDeDe.equals=" + UPDATED_DELIVERY_STATUS_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusDeDeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusDeDe not equals to DEFAULT_DELIVERY_STATUS_DE_DE
        defaultProductShouldNotBeFound("deliveryStatusDeDe.notEquals=" + DEFAULT_DELIVERY_STATUS_DE_DE);

        // Get all the productList where deliveryStatusDeDe not equals to UPDATED_DELIVERY_STATUS_DE_DE
        defaultProductShouldBeFound("deliveryStatusDeDe.notEquals=" + UPDATED_DELIVERY_STATUS_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusDeDeIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusDeDe in DEFAULT_DELIVERY_STATUS_DE_DE or UPDATED_DELIVERY_STATUS_DE_DE
        defaultProductShouldBeFound("deliveryStatusDeDe.in=" + DEFAULT_DELIVERY_STATUS_DE_DE + "," + UPDATED_DELIVERY_STATUS_DE_DE);

        // Get all the productList where deliveryStatusDeDe equals to UPDATED_DELIVERY_STATUS_DE_DE
        defaultProductShouldNotBeFound("deliveryStatusDeDe.in=" + UPDATED_DELIVERY_STATUS_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusDeDeIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusDeDe is not null
        defaultProductShouldBeFound("deliveryStatusDeDe.specified=true");

        // Get all the productList where deliveryStatusDeDe is null
        defaultProductShouldNotBeFound("deliveryStatusDeDe.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByDeliveryStatusDeDeContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusDeDe contains DEFAULT_DELIVERY_STATUS_DE_DE
        defaultProductShouldBeFound("deliveryStatusDeDe.contains=" + DEFAULT_DELIVERY_STATUS_DE_DE);

        // Get all the productList where deliveryStatusDeDe contains UPDATED_DELIVERY_STATUS_DE_DE
        defaultProductShouldNotBeFound("deliveryStatusDeDe.contains=" + UPDATED_DELIVERY_STATUS_DE_DE);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusDeDeNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusDeDe does not contain DEFAULT_DELIVERY_STATUS_DE_DE
        defaultProductShouldNotBeFound("deliveryStatusDeDe.doesNotContain=" + DEFAULT_DELIVERY_STATUS_DE_DE);

        // Get all the productList where deliveryStatusDeDe does not contain UPDATED_DELIVERY_STATUS_DE_DE
        defaultProductShouldBeFound("deliveryStatusDeDe.doesNotContain=" + UPDATED_DELIVERY_STATUS_DE_DE);
    }


    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusEnUsIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusEnUs equals to DEFAULT_DELIVERY_STATUS_EN_US
        defaultProductShouldBeFound("deliveryStatusEnUs.equals=" + DEFAULT_DELIVERY_STATUS_EN_US);

        // Get all the productList where deliveryStatusEnUs equals to UPDATED_DELIVERY_STATUS_EN_US
        defaultProductShouldNotBeFound("deliveryStatusEnUs.equals=" + UPDATED_DELIVERY_STATUS_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusEnUsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusEnUs not equals to DEFAULT_DELIVERY_STATUS_EN_US
        defaultProductShouldNotBeFound("deliveryStatusEnUs.notEquals=" + DEFAULT_DELIVERY_STATUS_EN_US);

        // Get all the productList where deliveryStatusEnUs not equals to UPDATED_DELIVERY_STATUS_EN_US
        defaultProductShouldBeFound("deliveryStatusEnUs.notEquals=" + UPDATED_DELIVERY_STATUS_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusEnUsIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusEnUs in DEFAULT_DELIVERY_STATUS_EN_US or UPDATED_DELIVERY_STATUS_EN_US
        defaultProductShouldBeFound("deliveryStatusEnUs.in=" + DEFAULT_DELIVERY_STATUS_EN_US + "," + UPDATED_DELIVERY_STATUS_EN_US);

        // Get all the productList where deliveryStatusEnUs equals to UPDATED_DELIVERY_STATUS_EN_US
        defaultProductShouldNotBeFound("deliveryStatusEnUs.in=" + UPDATED_DELIVERY_STATUS_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusEnUsIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusEnUs is not null
        defaultProductShouldBeFound("deliveryStatusEnUs.specified=true");

        // Get all the productList where deliveryStatusEnUs is null
        defaultProductShouldNotBeFound("deliveryStatusEnUs.specified=false");
    }
                @Test
    @Transactional
    public void getAllProductsByDeliveryStatusEnUsContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusEnUs contains DEFAULT_DELIVERY_STATUS_EN_US
        defaultProductShouldBeFound("deliveryStatusEnUs.contains=" + DEFAULT_DELIVERY_STATUS_EN_US);

        // Get all the productList where deliveryStatusEnUs contains UPDATED_DELIVERY_STATUS_EN_US
        defaultProductShouldNotBeFound("deliveryStatusEnUs.contains=" + UPDATED_DELIVERY_STATUS_EN_US);
    }

    @Test
    @Transactional
    public void getAllProductsByDeliveryStatusEnUsNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where deliveryStatusEnUs does not contain DEFAULT_DELIVERY_STATUS_EN_US
        defaultProductShouldNotBeFound("deliveryStatusEnUs.doesNotContain=" + DEFAULT_DELIVERY_STATUS_EN_US);

        // Get all the productList where deliveryStatusEnUs does not contain UPDATED_DELIVERY_STATUS_EN_US
        defaultProductShouldBeFound("deliveryStatusEnUs.doesNotContain=" + UPDATED_DELIVERY_STATUS_EN_US);
    }


    @Test
    @Transactional
    public void getAllProductsByBrandIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        Brand brand = BrandResourceIT.createEntity(em);
        em.persist(brand);
        em.flush();
        product.setBrand(brand);
        productRepository.saveAndFlush(product);
        Long brandId = brand.getId();

        // Get all the productList where brand equals to brandId
        defaultProductShouldBeFound("brandId.equals=" + brandId);

        // Get all the productList where brand equals to brandId + 1
        defaultProductShouldNotBeFound("brandId.equals=" + (brandId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProductShouldBeFound(String filter) throws Exception {
        restProductMockMvc.perform(get("/api/products?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].sku").value(hasItem(DEFAULT_SKU)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID)))
            .andExpect(jsonPath("$.[*].modifiedById").value(hasItem(DEFAULT_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].productFamilyId").value(hasItem(DEFAULT_PRODUCT_FAMILY_ID)))
            .andExpect(jsonPath("$.[*].nameEnUs").value(hasItem(DEFAULT_NAME_EN_US)))
            .andExpect(jsonPath("$.[*].nameDeDe").value(hasItem(DEFAULT_NAME_DE_DE)))
            .andExpect(jsonPath("$.[*].productStatus").value(hasItem(DEFAULT_PRODUCT_STATUS)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].priceCurrency").value(hasItem(DEFAULT_PRICE_CURRENCY)))
            .andExpect(jsonPath("$.[*].taxId").value(hasItem(DEFAULT_TAX_ID)))
            .andExpect(jsonPath("$.[*].ean").value(hasItem(DEFAULT_EAN)))
            .andExpect(jsonPath("$.[*].mpn").value(hasItem(DEFAULT_MPN)))
            .andExpect(jsonPath("$.[*].packagingId").value(hasItem(DEFAULT_PACKAGING_ID)))
            .andExpect(jsonPath("$.[*].uvp").value(hasItem(DEFAULT_UVP.doubleValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.toString())))
            .andExpect(jsonPath("$.[*].ownerUserId").value(hasItem(DEFAULT_OWNER_USER_ID)))
            .andExpect(jsonPath("$.[*].assignedUserId").value(hasItem(DEFAULT_ASSIGNED_USER_ID)))
            .andExpect(jsonPath("$.[*].finalPrice").value(hasItem(DEFAULT_FINAL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].finalPriceCurrency").value(hasItem(DEFAULT_FINAL_PRICE_CURRENCY)))
            .andExpect(jsonPath("$.[*].longDescription").value(hasItem(DEFAULT_LONG_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].longDescriptionDeDe").value(hasItem(DEFAULT_LONG_DESCRIPTION_DE_DE.toString())))
            .andExpect(jsonPath("$.[*].longDescriptionEnUs").value(hasItem(DEFAULT_LONG_DESCRIPTION_EN_US.toString())))
            .andExpect(jsonPath("$.[*].productSerieId").value(hasItem(DEFAULT_PRODUCT_SERIE_ID)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].catalogId").value(hasItem(DEFAULT_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].basePriceAmount").value(hasItem(DEFAULT_BASE_PRICE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].packedAmount").value(hasItem(DEFAULT_PACKED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].imageId").value(hasItem(DEFAULT_IMAGE_ID)))
            .andExpect(jsonPath("$.[*].measuringUnitId").value(hasItem(DEFAULT_MEASURING_UNIT_ID)))
            .andExpect(jsonPath("$.[*].deliveryStatus").value(hasItem(DEFAULT_DELIVERY_STATUS)))
            .andExpect(jsonPath("$.[*].deliveryStatusDeDe").value(hasItem(DEFAULT_DELIVERY_STATUS_DE_DE)))
            .andExpect(jsonPath("$.[*].deliveryStatusEnUs").value(hasItem(DEFAULT_DELIVERY_STATUS_EN_US)))
            .andExpect(jsonPath("$.[*].supplied").value(hasItem(DEFAULT_SUPPLIED.toString())))
            .andExpect(jsonPath("$.[*].suppliedDeDe").value(hasItem(DEFAULT_SUPPLIED_DE_DE.toString())))
            .andExpect(jsonPath("$.[*].suppliedEnUs").value(hasItem(DEFAULT_SUPPLIED_EN_US.toString())));

        // Check, that the count call also returns 1
        restProductMockMvc.perform(get("/api/products/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProductShouldNotBeFound(String filter) throws Exception {
        restProductMockMvc.perform(get("/api/products?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProductMockMvc.perform(get("/api/products/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .name(UPDATED_NAME)
            .deleted(UPDATED_DELETED)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .sku(UPDATED_SKU)
            .isActive(UPDATED_IS_ACTIVE)
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT)
            .createdById(UPDATED_CREATED_BY_ID)
            .modifiedById(UPDATED_MODIFIED_BY_ID)
            .productFamilyId(UPDATED_PRODUCT_FAMILY_ID)
            .nameEnUs(UPDATED_NAME_EN_US)
            .nameDeDe(UPDATED_NAME_DE_DE)
            .productStatus(UPDATED_PRODUCT_STATUS)
            .price(UPDATED_PRICE)
            .currency(UPDATED_CURRENCY)
            .priceCurrency(UPDATED_PRICE_CURRENCY)
            .taxId(UPDATED_TAX_ID)
            .ean(UPDATED_EAN)
            .mpn(UPDATED_MPN)
            .packagingId(UPDATED_PACKAGING_ID)
            .uvp(UPDATED_UVP)
            .tag(UPDATED_TAG)
            .ownerUserId(UPDATED_OWNER_USER_ID)
            .assignedUserId(UPDATED_ASSIGNED_USER_ID)
            .finalPrice(UPDATED_FINAL_PRICE)
            .finalPriceCurrency(UPDATED_FINAL_PRICE_CURRENCY)
            .longDescription(UPDATED_LONG_DESCRIPTION)
            .longDescriptionDeDe(UPDATED_LONG_DESCRIPTION_DE_DE)
            .longDescriptionEnUs(UPDATED_LONG_DESCRIPTION_EN_US)
            .productSerieId(UPDATED_PRODUCT_SERIE_ID)
            .data(UPDATED_DATA)
            .catalogId(UPDATED_CATALOG_ID)
            .basePriceAmount(UPDATED_BASE_PRICE_AMOUNT)
            .packedAmount(UPDATED_PACKED_AMOUNT)
            .imageId(UPDATED_IMAGE_ID)
            .measuringUnitId(UPDATED_MEASURING_UNIT_ID)
            .deliveryStatus(UPDATED_DELIVERY_STATUS)
            .deliveryStatusDeDe(UPDATED_DELIVERY_STATUS_DE_DE)
            .deliveryStatusEnUs(UPDATED_DELIVERY_STATUS_EN_US)
            .supplied(UPDATED_SUPPLIED)
            .suppliedDeDe(UPDATED_SUPPLIED_DE_DE)
            .suppliedEnUs(UPDATED_SUPPLIED_EN_US);
        ProductDTO productDTO = productMapper.toDto(updatedProduct);

        restProductMockMvc.perform(put("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testProduct.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testProduct.getModifiedAt()).isEqualTo(UPDATED_MODIFIED_AT);
        assertThat(testProduct.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testProduct.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProduct.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProduct.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testProduct.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testProduct.getModifiedById()).isEqualTo(UPDATED_MODIFIED_BY_ID);
        assertThat(testProduct.getProductFamilyId()).isEqualTo(UPDATED_PRODUCT_FAMILY_ID);
        assertThat(testProduct.getNameEnUs()).isEqualTo(UPDATED_NAME_EN_US);
        assertThat(testProduct.getNameDeDe()).isEqualTo(UPDATED_NAME_DE_DE);
        assertThat(testProduct.getProductStatus()).isEqualTo(UPDATED_PRODUCT_STATUS);
        assertThat(testProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProduct.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testProduct.getPriceCurrency()).isEqualTo(UPDATED_PRICE_CURRENCY);
        assertThat(testProduct.getTaxId()).isEqualTo(UPDATED_TAX_ID);
        assertThat(testProduct.getEan()).isEqualTo(UPDATED_EAN);
        assertThat(testProduct.getMpn()).isEqualTo(UPDATED_MPN);
        assertThat(testProduct.getPackagingId()).isEqualTo(UPDATED_PACKAGING_ID);
        assertThat(testProduct.getUvp()).isEqualTo(UPDATED_UVP);
        assertThat(testProduct.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testProduct.getOwnerUserId()).isEqualTo(UPDATED_OWNER_USER_ID);
        assertThat(testProduct.getAssignedUserId()).isEqualTo(UPDATED_ASSIGNED_USER_ID);
        assertThat(testProduct.getFinalPrice()).isEqualTo(UPDATED_FINAL_PRICE);
        assertThat(testProduct.getFinalPriceCurrency()).isEqualTo(UPDATED_FINAL_PRICE_CURRENCY);
        assertThat(testProduct.getLongDescription()).isEqualTo(UPDATED_LONG_DESCRIPTION);
        assertThat(testProduct.getLongDescriptionDeDe()).isEqualTo(UPDATED_LONG_DESCRIPTION_DE_DE);
        assertThat(testProduct.getLongDescriptionEnUs()).isEqualTo(UPDATED_LONG_DESCRIPTION_EN_US);
        assertThat(testProduct.getProductSerieId()).isEqualTo(UPDATED_PRODUCT_SERIE_ID);
        assertThat(testProduct.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testProduct.getCatalogId()).isEqualTo(UPDATED_CATALOG_ID);
        assertThat(testProduct.getBasePriceAmount()).isEqualTo(UPDATED_BASE_PRICE_AMOUNT);
        assertThat(testProduct.getPackedAmount()).isEqualTo(UPDATED_PACKED_AMOUNT);
        assertThat(testProduct.getImageId()).isEqualTo(UPDATED_IMAGE_ID);
        assertThat(testProduct.getMeasuringUnitId()).isEqualTo(UPDATED_MEASURING_UNIT_ID);
        assertThat(testProduct.getDeliveryStatus()).isEqualTo(UPDATED_DELIVERY_STATUS);
        assertThat(testProduct.getDeliveryStatusDeDe()).isEqualTo(UPDATED_DELIVERY_STATUS_DE_DE);
        assertThat(testProduct.getDeliveryStatusEnUs()).isEqualTo(UPDATED_DELIVERY_STATUS_EN_US);
        assertThat(testProduct.getSupplied()).isEqualTo(UPDATED_SUPPLIED);
        assertThat(testProduct.getSuppliedDeDe()).isEqualTo(UPDATED_SUPPLIED_DE_DE);
        assertThat(testProduct.getSuppliedEnUs()).isEqualTo(UPDATED_SUPPLIED_EN_US);
    }

    @Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc.perform(put("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Delete the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
