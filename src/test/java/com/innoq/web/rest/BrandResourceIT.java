package com.innoq.web.rest;

import com.innoq.InnoqPimApp;
import com.innoq.domain.Brand;
import com.innoq.domain.Product;
import com.innoq.repository.BrandRepository;
import com.innoq.service.BrandService;
import com.innoq.service.dto.BrandDTO;
import com.innoq.service.mapper.BrandMapper;
import com.innoq.service.dto.BrandCriteria;
import com.innoq.service.BrandQueryService;

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
 * Integration tests for the {@link BrandResource} REST controller.
 */
@SpringBootTest(classes = InnoqPimApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BrandResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN_US = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN_US = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_EN_US = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_EN_US = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_DE_DE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_DE_DE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_DE_DE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_DE_DE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_OWNER_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_USER_ID = "BBBBBBBBBB";

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandQueryService brandQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBrandMockMvc;

    private Brand brand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Brand createEntity(EntityManager em) {
        Brand brand = new Brand()
            .name(DEFAULT_NAME)
            .deleted(DEFAULT_DELETED)
            .description(DEFAULT_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .modifiedAt(DEFAULT_MODIFIED_AT)
            .createdById(DEFAULT_CREATED_BY_ID)
            .modifiedById(DEFAULT_MODIFIED_BY_ID)
            .nameEnUs(DEFAULT_NAME_EN_US)
            .descriptionEnUs(DEFAULT_DESCRIPTION_EN_US)
            .nameDeDe(DEFAULT_NAME_DE_DE)
            .descriptionDeDe(DEFAULT_DESCRIPTION_DE_DE)
            .code(DEFAULT_CODE)
            .ownerUserId(DEFAULT_OWNER_USER_ID)
            .assignedUserId(DEFAULT_ASSIGNED_USER_ID);
        return brand;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Brand createUpdatedEntity(EntityManager em) {
        Brand brand = new Brand()
            .name(UPDATED_NAME)
            .deleted(UPDATED_DELETED)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .createdById(UPDATED_CREATED_BY_ID)
            .modifiedById(UPDATED_MODIFIED_BY_ID)
            .nameEnUs(UPDATED_NAME_EN_US)
            .descriptionEnUs(UPDATED_DESCRIPTION_EN_US)
            .nameDeDe(UPDATED_NAME_DE_DE)
            .descriptionDeDe(UPDATED_DESCRIPTION_DE_DE)
            .code(UPDATED_CODE)
            .ownerUserId(UPDATED_OWNER_USER_ID)
            .assignedUserId(UPDATED_ASSIGNED_USER_ID);
        return brand;
    }

    @BeforeEach
    public void initTest() {
        brand = createEntity(em);
    }

    @Test
    @Transactional
    public void createBrand() throws Exception {
        int databaseSizeBeforeCreate = brandRepository.findAll().size();
        // Create the Brand
        BrandDTO brandDTO = brandMapper.toDto(brand);
        restBrandMockMvc.perform(post("/api/brands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandDTO)))
            .andExpect(status().isCreated());

        // Validate the Brand in the database
        List<Brand> brandList = brandRepository.findAll();
        assertThat(brandList).hasSize(databaseSizeBeforeCreate + 1);
        Brand testBrand = brandList.get(brandList.size() - 1);
        assertThat(testBrand.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBrand.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testBrand.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBrand.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testBrand.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBrand.getModifiedAt()).isEqualTo(DEFAULT_MODIFIED_AT);
        assertThat(testBrand.getCreatedById()).isEqualTo(DEFAULT_CREATED_BY_ID);
        assertThat(testBrand.getModifiedById()).isEqualTo(DEFAULT_MODIFIED_BY_ID);
        assertThat(testBrand.getNameEnUs()).isEqualTo(DEFAULT_NAME_EN_US);
        assertThat(testBrand.getDescriptionEnUs()).isEqualTo(DEFAULT_DESCRIPTION_EN_US);
        assertThat(testBrand.getNameDeDe()).isEqualTo(DEFAULT_NAME_DE_DE);
        assertThat(testBrand.getDescriptionDeDe()).isEqualTo(DEFAULT_DESCRIPTION_DE_DE);
        assertThat(testBrand.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBrand.getOwnerUserId()).isEqualTo(DEFAULT_OWNER_USER_ID);
        assertThat(testBrand.getAssignedUserId()).isEqualTo(DEFAULT_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void createBrandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = brandRepository.findAll().size();

        // Create the Brand with an existing ID
        brand.setId(1L);
        BrandDTO brandDTO = brandMapper.toDto(brand);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBrandMockMvc.perform(post("/api/brands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Brand in the database
        List<Brand> brandList = brandRepository.findAll();
        assertThat(brandList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = brandRepository.findAll().size();
        // set the field null
        brand.setIsActive(null);

        // Create the Brand, which fails.
        BrandDTO brandDTO = brandMapper.toDto(brand);


        restBrandMockMvc.perform(post("/api/brands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandDTO)))
            .andExpect(status().isBadRequest());

        List<Brand> brandList = brandRepository.findAll();
        assertThat(brandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBrands() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList
        restBrandMockMvc.perform(get("/api/brands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(brand.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID)))
            .andExpect(jsonPath("$.[*].modifiedById").value(hasItem(DEFAULT_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].nameEnUs").value(hasItem(DEFAULT_NAME_EN_US)))
            .andExpect(jsonPath("$.[*].descriptionEnUs").value(hasItem(DEFAULT_DESCRIPTION_EN_US.toString())))
            .andExpect(jsonPath("$.[*].nameDeDe").value(hasItem(DEFAULT_NAME_DE_DE)))
            .andExpect(jsonPath("$.[*].descriptionDeDe").value(hasItem(DEFAULT_DESCRIPTION_DE_DE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].ownerUserId").value(hasItem(DEFAULT_OWNER_USER_ID)))
            .andExpect(jsonPath("$.[*].assignedUserId").value(hasItem(DEFAULT_ASSIGNED_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getBrand() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get the brand
        restBrandMockMvc.perform(get("/api/brands/{id}", brand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(brand.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.modifiedAt").value(DEFAULT_MODIFIED_AT.toString()))
            .andExpect(jsonPath("$.createdById").value(DEFAULT_CREATED_BY_ID))
            .andExpect(jsonPath("$.modifiedById").value(DEFAULT_MODIFIED_BY_ID))
            .andExpect(jsonPath("$.nameEnUs").value(DEFAULT_NAME_EN_US))
            .andExpect(jsonPath("$.descriptionEnUs").value(DEFAULT_DESCRIPTION_EN_US.toString()))
            .andExpect(jsonPath("$.nameDeDe").value(DEFAULT_NAME_DE_DE))
            .andExpect(jsonPath("$.descriptionDeDe").value(DEFAULT_DESCRIPTION_DE_DE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.ownerUserId").value(DEFAULT_OWNER_USER_ID))
            .andExpect(jsonPath("$.assignedUserId").value(DEFAULT_ASSIGNED_USER_ID));
    }


    @Test
    @Transactional
    public void getBrandsByIdFiltering() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        Long id = brand.getId();

        defaultBrandShouldBeFound("id.equals=" + id);
        defaultBrandShouldNotBeFound("id.notEquals=" + id);

        defaultBrandShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBrandShouldNotBeFound("id.greaterThan=" + id);

        defaultBrandShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBrandShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllBrandsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where name equals to DEFAULT_NAME
        defaultBrandShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the brandList where name equals to UPDATED_NAME
        defaultBrandShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where name not equals to DEFAULT_NAME
        defaultBrandShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the brandList where name not equals to UPDATED_NAME
        defaultBrandShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where name in DEFAULT_NAME or UPDATED_NAME
        defaultBrandShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the brandList where name equals to UPDATED_NAME
        defaultBrandShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where name is not null
        defaultBrandShouldBeFound("name.specified=true");

        // Get all the brandList where name is null
        defaultBrandShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByNameContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where name contains DEFAULT_NAME
        defaultBrandShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the brandList where name contains UPDATED_NAME
        defaultBrandShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where name does not contain DEFAULT_NAME
        defaultBrandShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the brandList where name does not contain UPDATED_NAME
        defaultBrandShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllBrandsByDeletedIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where deleted equals to DEFAULT_DELETED
        defaultBrandShouldBeFound("deleted.equals=" + DEFAULT_DELETED);

        // Get all the brandList where deleted equals to UPDATED_DELETED
        defaultBrandShouldNotBeFound("deleted.equals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllBrandsByDeletedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where deleted not equals to DEFAULT_DELETED
        defaultBrandShouldNotBeFound("deleted.notEquals=" + DEFAULT_DELETED);

        // Get all the brandList where deleted not equals to UPDATED_DELETED
        defaultBrandShouldBeFound("deleted.notEquals=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllBrandsByDeletedIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where deleted in DEFAULT_DELETED or UPDATED_DELETED
        defaultBrandShouldBeFound("deleted.in=" + DEFAULT_DELETED + "," + UPDATED_DELETED);

        // Get all the brandList where deleted equals to UPDATED_DELETED
        defaultBrandShouldNotBeFound("deleted.in=" + UPDATED_DELETED);
    }

    @Test
    @Transactional
    public void getAllBrandsByDeletedIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where deleted is not null
        defaultBrandShouldBeFound("deleted.specified=true");

        // Get all the brandList where deleted is null
        defaultBrandShouldNotBeFound("deleted.specified=false");
    }

    @Test
    @Transactional
    public void getAllBrandsByIsActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where isActive equals to DEFAULT_IS_ACTIVE
        defaultBrandShouldBeFound("isActive.equals=" + DEFAULT_IS_ACTIVE);

        // Get all the brandList where isActive equals to UPDATED_IS_ACTIVE
        defaultBrandShouldNotBeFound("isActive.equals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllBrandsByIsActiveIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where isActive not equals to DEFAULT_IS_ACTIVE
        defaultBrandShouldNotBeFound("isActive.notEquals=" + DEFAULT_IS_ACTIVE);

        // Get all the brandList where isActive not equals to UPDATED_IS_ACTIVE
        defaultBrandShouldBeFound("isActive.notEquals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllBrandsByIsActiveIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where isActive in DEFAULT_IS_ACTIVE or UPDATED_IS_ACTIVE
        defaultBrandShouldBeFound("isActive.in=" + DEFAULT_IS_ACTIVE + "," + UPDATED_IS_ACTIVE);

        // Get all the brandList where isActive equals to UPDATED_IS_ACTIVE
        defaultBrandShouldNotBeFound("isActive.in=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllBrandsByIsActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where isActive is not null
        defaultBrandShouldBeFound("isActive.specified=true");

        // Get all the brandList where isActive is null
        defaultBrandShouldNotBeFound("isActive.specified=false");
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdAt equals to DEFAULT_CREATED_AT
        defaultBrandShouldBeFound("createdAt.equals=" + DEFAULT_CREATED_AT);

        // Get all the brandList where createdAt equals to UPDATED_CREATED_AT
        defaultBrandShouldNotBeFound("createdAt.equals=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedAtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdAt not equals to DEFAULT_CREATED_AT
        defaultBrandShouldNotBeFound("createdAt.notEquals=" + DEFAULT_CREATED_AT);

        // Get all the brandList where createdAt not equals to UPDATED_CREATED_AT
        defaultBrandShouldBeFound("createdAt.notEquals=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedAtIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdAt in DEFAULT_CREATED_AT or UPDATED_CREATED_AT
        defaultBrandShouldBeFound("createdAt.in=" + DEFAULT_CREATED_AT + "," + UPDATED_CREATED_AT);

        // Get all the brandList where createdAt equals to UPDATED_CREATED_AT
        defaultBrandShouldNotBeFound("createdAt.in=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdAt is not null
        defaultBrandShouldBeFound("createdAt.specified=true");

        // Get all the brandList where createdAt is null
        defaultBrandShouldNotBeFound("createdAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedAt equals to DEFAULT_MODIFIED_AT
        defaultBrandShouldBeFound("modifiedAt.equals=" + DEFAULT_MODIFIED_AT);

        // Get all the brandList where modifiedAt equals to UPDATED_MODIFIED_AT
        defaultBrandShouldNotBeFound("modifiedAt.equals=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedAtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedAt not equals to DEFAULT_MODIFIED_AT
        defaultBrandShouldNotBeFound("modifiedAt.notEquals=" + DEFAULT_MODIFIED_AT);

        // Get all the brandList where modifiedAt not equals to UPDATED_MODIFIED_AT
        defaultBrandShouldBeFound("modifiedAt.notEquals=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedAtIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedAt in DEFAULT_MODIFIED_AT or UPDATED_MODIFIED_AT
        defaultBrandShouldBeFound("modifiedAt.in=" + DEFAULT_MODIFIED_AT + "," + UPDATED_MODIFIED_AT);

        // Get all the brandList where modifiedAt equals to UPDATED_MODIFIED_AT
        defaultBrandShouldNotBeFound("modifiedAt.in=" + UPDATED_MODIFIED_AT);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedAt is not null
        defaultBrandShouldBeFound("modifiedAt.specified=true");

        // Get all the brandList where modifiedAt is null
        defaultBrandShouldNotBeFound("modifiedAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedByIdIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdById equals to DEFAULT_CREATED_BY_ID
        defaultBrandShouldBeFound("createdById.equals=" + DEFAULT_CREATED_BY_ID);

        // Get all the brandList where createdById equals to UPDATED_CREATED_BY_ID
        defaultBrandShouldNotBeFound("createdById.equals=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedByIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdById not equals to DEFAULT_CREATED_BY_ID
        defaultBrandShouldNotBeFound("createdById.notEquals=" + DEFAULT_CREATED_BY_ID);

        // Get all the brandList where createdById not equals to UPDATED_CREATED_BY_ID
        defaultBrandShouldBeFound("createdById.notEquals=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedByIdIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdById in DEFAULT_CREATED_BY_ID or UPDATED_CREATED_BY_ID
        defaultBrandShouldBeFound("createdById.in=" + DEFAULT_CREATED_BY_ID + "," + UPDATED_CREATED_BY_ID);

        // Get all the brandList where createdById equals to UPDATED_CREATED_BY_ID
        defaultBrandShouldNotBeFound("createdById.in=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedByIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdById is not null
        defaultBrandShouldBeFound("createdById.specified=true");

        // Get all the brandList where createdById is null
        defaultBrandShouldNotBeFound("createdById.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByCreatedByIdContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdById contains DEFAULT_CREATED_BY_ID
        defaultBrandShouldBeFound("createdById.contains=" + DEFAULT_CREATED_BY_ID);

        // Get all the brandList where createdById contains UPDATED_CREATED_BY_ID
        defaultBrandShouldNotBeFound("createdById.contains=" + UPDATED_CREATED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByCreatedByIdNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where createdById does not contain DEFAULT_CREATED_BY_ID
        defaultBrandShouldNotBeFound("createdById.doesNotContain=" + DEFAULT_CREATED_BY_ID);

        // Get all the brandList where createdById does not contain UPDATED_CREATED_BY_ID
        defaultBrandShouldBeFound("createdById.doesNotContain=" + UPDATED_CREATED_BY_ID);
    }


    @Test
    @Transactional
    public void getAllBrandsByModifiedByIdIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedById equals to DEFAULT_MODIFIED_BY_ID
        defaultBrandShouldBeFound("modifiedById.equals=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the brandList where modifiedById equals to UPDATED_MODIFIED_BY_ID
        defaultBrandShouldNotBeFound("modifiedById.equals=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedByIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedById not equals to DEFAULT_MODIFIED_BY_ID
        defaultBrandShouldNotBeFound("modifiedById.notEquals=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the brandList where modifiedById not equals to UPDATED_MODIFIED_BY_ID
        defaultBrandShouldBeFound("modifiedById.notEquals=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedByIdIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedById in DEFAULT_MODIFIED_BY_ID or UPDATED_MODIFIED_BY_ID
        defaultBrandShouldBeFound("modifiedById.in=" + DEFAULT_MODIFIED_BY_ID + "," + UPDATED_MODIFIED_BY_ID);

        // Get all the brandList where modifiedById equals to UPDATED_MODIFIED_BY_ID
        defaultBrandShouldNotBeFound("modifiedById.in=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedByIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedById is not null
        defaultBrandShouldBeFound("modifiedById.specified=true");

        // Get all the brandList where modifiedById is null
        defaultBrandShouldNotBeFound("modifiedById.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByModifiedByIdContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedById contains DEFAULT_MODIFIED_BY_ID
        defaultBrandShouldBeFound("modifiedById.contains=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the brandList where modifiedById contains UPDATED_MODIFIED_BY_ID
        defaultBrandShouldNotBeFound("modifiedById.contains=" + UPDATED_MODIFIED_BY_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByModifiedByIdNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where modifiedById does not contain DEFAULT_MODIFIED_BY_ID
        defaultBrandShouldNotBeFound("modifiedById.doesNotContain=" + DEFAULT_MODIFIED_BY_ID);

        // Get all the brandList where modifiedById does not contain UPDATED_MODIFIED_BY_ID
        defaultBrandShouldBeFound("modifiedById.doesNotContain=" + UPDATED_MODIFIED_BY_ID);
    }


    @Test
    @Transactional
    public void getAllBrandsByNameEnUsIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameEnUs equals to DEFAULT_NAME_EN_US
        defaultBrandShouldBeFound("nameEnUs.equals=" + DEFAULT_NAME_EN_US);

        // Get all the brandList where nameEnUs equals to UPDATED_NAME_EN_US
        defaultBrandShouldNotBeFound("nameEnUs.equals=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameEnUsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameEnUs not equals to DEFAULT_NAME_EN_US
        defaultBrandShouldNotBeFound("nameEnUs.notEquals=" + DEFAULT_NAME_EN_US);

        // Get all the brandList where nameEnUs not equals to UPDATED_NAME_EN_US
        defaultBrandShouldBeFound("nameEnUs.notEquals=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameEnUsIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameEnUs in DEFAULT_NAME_EN_US or UPDATED_NAME_EN_US
        defaultBrandShouldBeFound("nameEnUs.in=" + DEFAULT_NAME_EN_US + "," + UPDATED_NAME_EN_US);

        // Get all the brandList where nameEnUs equals to UPDATED_NAME_EN_US
        defaultBrandShouldNotBeFound("nameEnUs.in=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameEnUsIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameEnUs is not null
        defaultBrandShouldBeFound("nameEnUs.specified=true");

        // Get all the brandList where nameEnUs is null
        defaultBrandShouldNotBeFound("nameEnUs.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByNameEnUsContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameEnUs contains DEFAULT_NAME_EN_US
        defaultBrandShouldBeFound("nameEnUs.contains=" + DEFAULT_NAME_EN_US);

        // Get all the brandList where nameEnUs contains UPDATED_NAME_EN_US
        defaultBrandShouldNotBeFound("nameEnUs.contains=" + UPDATED_NAME_EN_US);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameEnUsNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameEnUs does not contain DEFAULT_NAME_EN_US
        defaultBrandShouldNotBeFound("nameEnUs.doesNotContain=" + DEFAULT_NAME_EN_US);

        // Get all the brandList where nameEnUs does not contain UPDATED_NAME_EN_US
        defaultBrandShouldBeFound("nameEnUs.doesNotContain=" + UPDATED_NAME_EN_US);
    }


    @Test
    @Transactional
    public void getAllBrandsByNameDeDeIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameDeDe equals to DEFAULT_NAME_DE_DE
        defaultBrandShouldBeFound("nameDeDe.equals=" + DEFAULT_NAME_DE_DE);

        // Get all the brandList where nameDeDe equals to UPDATED_NAME_DE_DE
        defaultBrandShouldNotBeFound("nameDeDe.equals=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameDeDeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameDeDe not equals to DEFAULT_NAME_DE_DE
        defaultBrandShouldNotBeFound("nameDeDe.notEquals=" + DEFAULT_NAME_DE_DE);

        // Get all the brandList where nameDeDe not equals to UPDATED_NAME_DE_DE
        defaultBrandShouldBeFound("nameDeDe.notEquals=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameDeDeIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameDeDe in DEFAULT_NAME_DE_DE or UPDATED_NAME_DE_DE
        defaultBrandShouldBeFound("nameDeDe.in=" + DEFAULT_NAME_DE_DE + "," + UPDATED_NAME_DE_DE);

        // Get all the brandList where nameDeDe equals to UPDATED_NAME_DE_DE
        defaultBrandShouldNotBeFound("nameDeDe.in=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameDeDeIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameDeDe is not null
        defaultBrandShouldBeFound("nameDeDe.specified=true");

        // Get all the brandList where nameDeDe is null
        defaultBrandShouldNotBeFound("nameDeDe.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByNameDeDeContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameDeDe contains DEFAULT_NAME_DE_DE
        defaultBrandShouldBeFound("nameDeDe.contains=" + DEFAULT_NAME_DE_DE);

        // Get all the brandList where nameDeDe contains UPDATED_NAME_DE_DE
        defaultBrandShouldNotBeFound("nameDeDe.contains=" + UPDATED_NAME_DE_DE);
    }

    @Test
    @Transactional
    public void getAllBrandsByNameDeDeNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where nameDeDe does not contain DEFAULT_NAME_DE_DE
        defaultBrandShouldNotBeFound("nameDeDe.doesNotContain=" + DEFAULT_NAME_DE_DE);

        // Get all the brandList where nameDeDe does not contain UPDATED_NAME_DE_DE
        defaultBrandShouldBeFound("nameDeDe.doesNotContain=" + UPDATED_NAME_DE_DE);
    }


    @Test
    @Transactional
    public void getAllBrandsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where code equals to DEFAULT_CODE
        defaultBrandShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the brandList where code equals to UPDATED_CODE
        defaultBrandShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllBrandsByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where code not equals to DEFAULT_CODE
        defaultBrandShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the brandList where code not equals to UPDATED_CODE
        defaultBrandShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllBrandsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where code in DEFAULT_CODE or UPDATED_CODE
        defaultBrandShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the brandList where code equals to UPDATED_CODE
        defaultBrandShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllBrandsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where code is not null
        defaultBrandShouldBeFound("code.specified=true");

        // Get all the brandList where code is null
        defaultBrandShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByCodeContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where code contains DEFAULT_CODE
        defaultBrandShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the brandList where code contains UPDATED_CODE
        defaultBrandShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllBrandsByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where code does not contain DEFAULT_CODE
        defaultBrandShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the brandList where code does not contain UPDATED_CODE
        defaultBrandShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllBrandsByOwnerUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where ownerUserId equals to DEFAULT_OWNER_USER_ID
        defaultBrandShouldBeFound("ownerUserId.equals=" + DEFAULT_OWNER_USER_ID);

        // Get all the brandList where ownerUserId equals to UPDATED_OWNER_USER_ID
        defaultBrandShouldNotBeFound("ownerUserId.equals=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByOwnerUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where ownerUserId not equals to DEFAULT_OWNER_USER_ID
        defaultBrandShouldNotBeFound("ownerUserId.notEquals=" + DEFAULT_OWNER_USER_ID);

        // Get all the brandList where ownerUserId not equals to UPDATED_OWNER_USER_ID
        defaultBrandShouldBeFound("ownerUserId.notEquals=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByOwnerUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where ownerUserId in DEFAULT_OWNER_USER_ID or UPDATED_OWNER_USER_ID
        defaultBrandShouldBeFound("ownerUserId.in=" + DEFAULT_OWNER_USER_ID + "," + UPDATED_OWNER_USER_ID);

        // Get all the brandList where ownerUserId equals to UPDATED_OWNER_USER_ID
        defaultBrandShouldNotBeFound("ownerUserId.in=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByOwnerUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where ownerUserId is not null
        defaultBrandShouldBeFound("ownerUserId.specified=true");

        // Get all the brandList where ownerUserId is null
        defaultBrandShouldNotBeFound("ownerUserId.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByOwnerUserIdContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where ownerUserId contains DEFAULT_OWNER_USER_ID
        defaultBrandShouldBeFound("ownerUserId.contains=" + DEFAULT_OWNER_USER_ID);

        // Get all the brandList where ownerUserId contains UPDATED_OWNER_USER_ID
        defaultBrandShouldNotBeFound("ownerUserId.contains=" + UPDATED_OWNER_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByOwnerUserIdNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where ownerUserId does not contain DEFAULT_OWNER_USER_ID
        defaultBrandShouldNotBeFound("ownerUserId.doesNotContain=" + DEFAULT_OWNER_USER_ID);

        // Get all the brandList where ownerUserId does not contain UPDATED_OWNER_USER_ID
        defaultBrandShouldBeFound("ownerUserId.doesNotContain=" + UPDATED_OWNER_USER_ID);
    }


    @Test
    @Transactional
    public void getAllBrandsByAssignedUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where assignedUserId equals to DEFAULT_ASSIGNED_USER_ID
        defaultBrandShouldBeFound("assignedUserId.equals=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the brandList where assignedUserId equals to UPDATED_ASSIGNED_USER_ID
        defaultBrandShouldNotBeFound("assignedUserId.equals=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByAssignedUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where assignedUserId not equals to DEFAULT_ASSIGNED_USER_ID
        defaultBrandShouldNotBeFound("assignedUserId.notEquals=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the brandList where assignedUserId not equals to UPDATED_ASSIGNED_USER_ID
        defaultBrandShouldBeFound("assignedUserId.notEquals=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByAssignedUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where assignedUserId in DEFAULT_ASSIGNED_USER_ID or UPDATED_ASSIGNED_USER_ID
        defaultBrandShouldBeFound("assignedUserId.in=" + DEFAULT_ASSIGNED_USER_ID + "," + UPDATED_ASSIGNED_USER_ID);

        // Get all the brandList where assignedUserId equals to UPDATED_ASSIGNED_USER_ID
        defaultBrandShouldNotBeFound("assignedUserId.in=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByAssignedUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where assignedUserId is not null
        defaultBrandShouldBeFound("assignedUserId.specified=true");

        // Get all the brandList where assignedUserId is null
        defaultBrandShouldNotBeFound("assignedUserId.specified=false");
    }
                @Test
    @Transactional
    public void getAllBrandsByAssignedUserIdContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where assignedUserId contains DEFAULT_ASSIGNED_USER_ID
        defaultBrandShouldBeFound("assignedUserId.contains=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the brandList where assignedUserId contains UPDATED_ASSIGNED_USER_ID
        defaultBrandShouldNotBeFound("assignedUserId.contains=" + UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void getAllBrandsByAssignedUserIdNotContainsSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        // Get all the brandList where assignedUserId does not contain DEFAULT_ASSIGNED_USER_ID
        defaultBrandShouldNotBeFound("assignedUserId.doesNotContain=" + DEFAULT_ASSIGNED_USER_ID);

        // Get all the brandList where assignedUserId does not contain UPDATED_ASSIGNED_USER_ID
        defaultBrandShouldBeFound("assignedUserId.doesNotContain=" + UPDATED_ASSIGNED_USER_ID);
    }


    @Test
    @Transactional
    public void getAllBrandsByProductIsEqualToSomething() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);
        Product product = ProductResourceIT.createEntity(em);
        em.persist(product);
        em.flush();
        brand.addProduct(product);
        brandRepository.saveAndFlush(brand);
        Long productId = product.getId();

        // Get all the brandList where product equals to productId
        defaultBrandShouldBeFound("productId.equals=" + productId);

        // Get all the brandList where product equals to productId + 1
        defaultBrandShouldNotBeFound("productId.equals=" + (productId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBrandShouldBeFound(String filter) throws Exception {
        restBrandMockMvc.perform(get("/api/brands?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(brand.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].modifiedAt").value(hasItem(DEFAULT_MODIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdById").value(hasItem(DEFAULT_CREATED_BY_ID)))
            .andExpect(jsonPath("$.[*].modifiedById").value(hasItem(DEFAULT_MODIFIED_BY_ID)))
            .andExpect(jsonPath("$.[*].nameEnUs").value(hasItem(DEFAULT_NAME_EN_US)))
            .andExpect(jsonPath("$.[*].descriptionEnUs").value(hasItem(DEFAULT_DESCRIPTION_EN_US.toString())))
            .andExpect(jsonPath("$.[*].nameDeDe").value(hasItem(DEFAULT_NAME_DE_DE)))
            .andExpect(jsonPath("$.[*].descriptionDeDe").value(hasItem(DEFAULT_DESCRIPTION_DE_DE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].ownerUserId").value(hasItem(DEFAULT_OWNER_USER_ID)))
            .andExpect(jsonPath("$.[*].assignedUserId").value(hasItem(DEFAULT_ASSIGNED_USER_ID)));

        // Check, that the count call also returns 1
        restBrandMockMvc.perform(get("/api/brands/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBrandShouldNotBeFound(String filter) throws Exception {
        restBrandMockMvc.perform(get("/api/brands?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBrandMockMvc.perform(get("/api/brands/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingBrand() throws Exception {
        // Get the brand
        restBrandMockMvc.perform(get("/api/brands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBrand() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        int databaseSizeBeforeUpdate = brandRepository.findAll().size();

        // Update the brand
        Brand updatedBrand = brandRepository.findById(brand.getId()).get();
        // Disconnect from session so that the updates on updatedBrand are not directly saved in db
        em.detach(updatedBrand);
        updatedBrand
            .name(UPDATED_NAME)
            .deleted(UPDATED_DELETED)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .modifiedAt(UPDATED_MODIFIED_AT)
            .createdById(UPDATED_CREATED_BY_ID)
            .modifiedById(UPDATED_MODIFIED_BY_ID)
            .nameEnUs(UPDATED_NAME_EN_US)
            .descriptionEnUs(UPDATED_DESCRIPTION_EN_US)
            .nameDeDe(UPDATED_NAME_DE_DE)
            .descriptionDeDe(UPDATED_DESCRIPTION_DE_DE)
            .code(UPDATED_CODE)
            .ownerUserId(UPDATED_OWNER_USER_ID)
            .assignedUserId(UPDATED_ASSIGNED_USER_ID);
        BrandDTO brandDTO = brandMapper.toDto(updatedBrand);

        restBrandMockMvc.perform(put("/api/brands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandDTO)))
            .andExpect(status().isOk());

        // Validate the Brand in the database
        List<Brand> brandList = brandRepository.findAll();
        assertThat(brandList).hasSize(databaseSizeBeforeUpdate);
        Brand testBrand = brandList.get(brandList.size() - 1);
        assertThat(testBrand.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBrand.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testBrand.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBrand.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testBrand.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBrand.getModifiedAt()).isEqualTo(UPDATED_MODIFIED_AT);
        assertThat(testBrand.getCreatedById()).isEqualTo(UPDATED_CREATED_BY_ID);
        assertThat(testBrand.getModifiedById()).isEqualTo(UPDATED_MODIFIED_BY_ID);
        assertThat(testBrand.getNameEnUs()).isEqualTo(UPDATED_NAME_EN_US);
        assertThat(testBrand.getDescriptionEnUs()).isEqualTo(UPDATED_DESCRIPTION_EN_US);
        assertThat(testBrand.getNameDeDe()).isEqualTo(UPDATED_NAME_DE_DE);
        assertThat(testBrand.getDescriptionDeDe()).isEqualTo(UPDATED_DESCRIPTION_DE_DE);
        assertThat(testBrand.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBrand.getOwnerUserId()).isEqualTo(UPDATED_OWNER_USER_ID);
        assertThat(testBrand.getAssignedUserId()).isEqualTo(UPDATED_ASSIGNED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingBrand() throws Exception {
        int databaseSizeBeforeUpdate = brandRepository.findAll().size();

        // Create the Brand
        BrandDTO brandDTO = brandMapper.toDto(brand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBrandMockMvc.perform(put("/api/brands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Brand in the database
        List<Brand> brandList = brandRepository.findAll();
        assertThat(brandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBrand() throws Exception {
        // Initialize the database
        brandRepository.saveAndFlush(brand);

        int databaseSizeBeforeDelete = brandRepository.findAll().size();

        // Delete the brand
        restBrandMockMvc.perform(delete("/api/brands/{id}", brand.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Brand> brandList = brandRepository.findAll();
        assertThat(brandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
