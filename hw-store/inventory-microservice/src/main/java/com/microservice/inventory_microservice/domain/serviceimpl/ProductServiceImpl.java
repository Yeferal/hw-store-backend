package com.microservice.inventory_microservice.domain.serviceimpl;

import com.microservice.inventory_microservice.domain.dto.*;
import com.microservice.inventory_microservice.domain.map.*;
import com.microservice.inventory_microservice.domain.repository.ProductRepository;
import com.microservice.inventory_microservice.domain.service.ProductService;
import com.microservice.inventory_microservice.persistence.model.*;
import com.microservice.inventory_microservice.source.exception.ExistRegisterException;
import com.microservice.inventory_microservice.source.exception.StorageException;
import com.microservice.inventory_microservice.source.utils.ConstData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MeasurementUnitMapper measurementUnitMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductMinimalMapper productMinimalMapper;
    @Autowired
    private ProductDefaultMapper productDefaultMapper;

    @Override
    public ProductDefaultDTO addProduct(ProductBodyDTO productBodyDTO) {
        if (productRepository.isExistedProduct(productBodyDTO.getCode())) {
            throw new ExistRegisterException("Product with of code <"+ productBodyDTO.getCode()+"> already exists");
        }

//        Account account =

        // Create o Upload Image
        System.out.println(ConstData.UPLOADED_FOLDER_PRODUCT);


        Product product = Product.builder()
                .code(productBodyDTO.getCode())
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
//                falta creator and brand
                .build();

        return null;
    }

    @Override
    public BrandDTO addBrand(BrandBodyDTO brandBodyDTO) {
        if (productRepository.isExistedBrand(brandBodyDTO.getName())) {
            throw new ExistRegisterException("Brand " + brandBodyDTO.getName() + " already exists");
        }

        // Create o Upload Image
        String imgPath = null;
        System.out.println(ConstData.UPLOADED_FOLDER_BRAND);

        Brand brand = Brand.builder()
                .name(brandBodyDTO.getName())
                .abbreviation(brandBodyDTO.getAbbreviation())
                .state(brandBodyDTO.getState())
                .imgPath(imgPath)
                .build();

//        brand = productRepository.createBrand(brand);
        return brandMapper.toDto(brand);
    }

    @Override
    public CategoryDTO addCategory(CategoryBodyDTO categoryBodyDTO) {
        if (productRepository.isExistedCategory(categoryBodyDTO.getName())) {
            throw new ExistRegisterException("Category with name " + categoryBodyDTO.getName() + " already exists");
        }

        // Create o Upload Image
        String imgPath = null;

        if (!categoryBodyDTO.getImage().isEmpty()){
            // Generar un UUID
            String uuid = UUID.randomUUID().toString();
            // Obtener la extensión del archivo original
            String originalFilename = categoryBodyDTO.getImage().getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            // Crear el nuevo nombre de archivo usando el UUID
            String newFilename = uuid + extension;
            imgPath = newFilename;

            Path rootLocation = Paths.get(ConstData.UPLOADED_FOLDER_CATEGORY);
            Path destinationFile = rootLocation.resolve(Paths.get(newFilename))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }

            try (InputStream inputStream = categoryBodyDTO.getImage().getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new StorageException("Failed to store file.", e);
            }
        }

        Category category = Category.builder()
                .name(categoryBodyDTO.getName())
                .description(categoryBodyDTO.getDescription())
                .featured(categoryBodyDTO.getFeatured())
                .state(categoryBodyDTO.getState())
                .imgPath(imgPath)
                .build();

//        category = productRepository.createCategory(category);
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
