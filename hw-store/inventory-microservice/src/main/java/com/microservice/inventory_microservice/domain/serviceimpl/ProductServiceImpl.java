package com.microservice.inventory_microservice.domain.serviceimpl;

import com.microservice.inventory_microservice.domain.dto.*;
import com.microservice.inventory_microservice.domain.map.*;
import com.microservice.inventory_microservice.domain.repository.AccountRepository;
import com.microservice.inventory_microservice.domain.repository.ProductRepository;
import com.microservice.inventory_microservice.domain.service.ProductService;
import com.microservice.inventory_microservice.domain.specs.ProductSpecifications;
import com.microservice.inventory_microservice.persistence.model.*;
import com.microservice.inventory_microservice.source.exception.ExistRegisterException;
import com.microservice.inventory_microservice.source.exception.FailedRegisterException;
import com.microservice.inventory_microservice.source.exception.RegisterNotFoundException;
import com.microservice.inventory_microservice.source.exception.StorageException;
import com.microservice.inventory_microservice.source.utils.ConstData;
import com.microservice.inventory_microservice.web.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MeasurementUnitMapper measurementUnitMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductDefaultMapper productDefaultMapper;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = {FailedRegisterException.class, Exception.class})
    public ProductDefaultDTO addProduct(ProductBodyDTO productBodyDTO, String token) {
        try {
            if (productRepository.isExistedProduct(productBodyDTO.getCode())) {
                throw new ExistRegisterException("Product with of code <"+ productBodyDTO.getCode()+"> already exists");
            }
            String username = jwtTokenProvider.getUsernameJwt(token);

            Account account = accountRepository.getAccountByUsername(username).orElseThrow(
                    () -> new RegisterNotFoundException("Account with username <"+ username+"> does not exist")
            );

            Brand brand = null;
            if (productBodyDTO.getBrandId() != null) {
                brand = productRepository.getBrandById(productBodyDTO.getBrandId()).orElseThrow(
                        () -> new RegisterNotFoundException("Brand <"+ productBodyDTO.getBrandId()+"> does not exist")
                );
            }

            Product product = Product.builder()
                    .code(productBodyDTO.getCode().toUpperCase())
                    .name(productBodyDTO.getName().toUpperCase())
                    .description(productBodyDTO.getDescription())
                    .retailPrice(productBodyDTO.getRetailPrice())
                    .wholesalePrice(productBodyDTO.getWholesalePrice())
                    .previous_price(productBodyDTO.getPrevious_price())
                    .discount(productBodyDTO.getDiscount())
                    .discountType(productBodyDTO.getDiscountType())
                    .amount(productBodyDTO.getAmount())
                    .minAmount(productBodyDTO.getMinAmount())
                    .purchasePrice(productBodyDTO.getPurchasePrice())
                    .delivery(productBodyDTO.getDelivery())
                    .deliveryPrice(productBodyDTO.getDeliveryPrice())
                    .formula(productBodyDTO.getFormula())
                    .active(productBodyDTO.getActive())
                    .creationDate(LocalDateTime.now())
                    .brand(brand)
                    .creator(account)
                    .build();

            product = productRepository.createProduct(product);

            // Create assignment measurement unit
            if (productBodyDTO.getMeasurementUnitId() != null) {
                MeasurementUnit measurementUnit = productRepository.getMeasureUnitById(productBodyDTO.getMeasurementUnitId()).orElseThrow(
                        () -> new RegisterNotFoundException("MeasureUnit <"+ productBodyDTO.getMeasurementUnitId()+"> does not exist")
                );

                AssignmentMeasure assignmentMeasure = AssignmentMeasure.builder()
                        .productId(product.getId())
                        .measurementUnitId(measurementUnit.getId())
                        .price(product.getRetailPrice())
                        .equivalentValue(BigDecimal.valueOf(1.0000))
                        .isBase(true)
                        .build();
                assignmentMeasure = productRepository.createAssignmentMeasure(assignmentMeasure);
                product.setAssignmentMeasureList(List.of(assignmentMeasure));
            }

            // Create o Upload Image
            if (productBodyDTO.getImages() != null && productBodyDTO.getImages().size() > 0) {
                List<String> listPath = productBodyDTO.getImages().stream()
                        .map(image -> fileSystemStorageService.store(image, ConstData.UPLOADED_FOLDER_PRODUCT))
                        .toList();

                Product finalProduct = product;
                List<ProductImage> productImageList = listPath.stream()
                        .map(path -> productRepository.createProductImage(ProductImage.builder()
                                .imagePath(path)
                                .product(finalProduct)
                                .build()
                        ))
                        .toList();

                product.setImages(productImageList);
            }

            return productDefaultMapper.toDto(product);
        } catch (Exception ex) {
            // La anotación @Transactional se encargará de realizar el rollback en caso de excepción.
            throw ex;
        }

    }

    @Override
    public Page<ProductDefaultDTO> getAllProducts(PaginateAndSortDTO paginateAndSortDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, paginateAndSortDTO.getSortField());
        if (paginateAndSortDTO.getSortOrder().equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, paginateAndSortDTO.getSortField());
        }

        Pageable pageable = PageRequest.of(paginateAndSortDTO.getPage(), paginateAndSortDTO.getSize(), sort);
        Specification<Product> specs = Specification.where(null);

        if (paginateAndSortDTO.getSearchValue() != null) {
            specs = specs.or(ProductSpecifications.codeContains(paginateAndSortDTO.getSearchValue().toUpperCase()));
            specs = specs.or(ProductSpecifications.nameContains(paginateAndSortDTO.getSearchValue().toUpperCase()));
            specs = specs.or(ProductSpecifications.brandNameContains(paginateAndSortDTO.getSearchValue().toUpperCase()));
            specs = specs.or(ProductSpecifications.categoryNameContains(paginateAndSortDTO.getSearchValue().toUpperCase()));
        }

        Page<Product> productPage = productRepository.getAllProductPaginateAndSort(specs, pageable);
        List<ProductDefaultDTO> productDefaultDTOs = productPage.getContent().stream()
                .map(productDefaultMapper::toDto)
                .toList();
        return new PageImpl<>(productDefaultDTOs, pageable, productPage.getTotalElements());
    }

    @Override
    public BrandDTO addBrand(BrandBodyDTO brandBodyDTO) {
        if (productRepository.isExistedBrand(brandBodyDTO.getName())) {
            throw new ExistRegisterException("Brand " + brandBodyDTO.getName() + " already exists");
        }

        // Create o Upload Image
        String imgPath = null;

        if (brandBodyDTO.getImage() != null && !brandBodyDTO.getImage().isEmpty()){
            imgPath = fileSystemStorageService.store(brandBodyDTO.getImage(), ConstData.UPLOADED_FOLDER_BRAND);
        }

        Brand brand = Brand.builder()
                .name(brandBodyDTO.getName())
                .abbreviation(brandBodyDTO.getAbbreviation())
                .state(brandBodyDTO.getState())
                .imgPath(imgPath)
                .build();

        brand = productRepository.createBrand(brand);
        return brandMapper.toDto(brand);
    }

    @Override
    public CategoryDTO addCategory(CategoryBodyDTO categoryBodyDTO) {
        if (productRepository.isExistedCategory(categoryBodyDTO.getName())) {
            throw new ExistRegisterException("Category with name " + categoryBodyDTO.getName() + " already exists");
        }

        // Create o Upload Image
        String imgPath = null;

        if (categoryBodyDTO.getImage() != null && !categoryBodyDTO.getImage().isEmpty()){
            imgPath = fileSystemStorageService.store(categoryBodyDTO.getImage(), ConstData.UPLOADED_FOLDER_CATEGORY);
        }

        Category category = Category.builder()
                .name(categoryBodyDTO.getName())
                .description(categoryBodyDTO.getDescription())
                .featured(categoryBodyDTO.getFeatured())
                .state(categoryBodyDTO.getState())
                .imgPath(imgPath)
                .build();

        category = productRepository.createCategory(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public MeasurementUnitDTO addMeasurementUnit(MeasurementUnitBodyDTO measurementUnitBodyDTO) {
        if (productRepository.isExistedMeasurementUnit(measurementUnitBodyDTO.getName())) {
            throw new ExistRegisterException("Measurement unit with name " + measurementUnitBodyDTO.getName() + " already exists");
        }

        MeasurementUnit measurementUnit = MeasurementUnit.builder()
                .name(measurementUnitBodyDTO.getName().toUpperCase())
                .abbreviation(measurementUnitBodyDTO.getAbbreviation().toUpperCase())
                .symbol(measurementUnitBodyDTO.getSymbol())
                .magnitude(measurementUnitBodyDTO.getMagnitude().toUpperCase())
                .build();

        measurementUnit = productRepository.createMeasurementUnit(measurementUnit);
        return measurementUnitMapper.toDto(measurementUnit);
    }

}
