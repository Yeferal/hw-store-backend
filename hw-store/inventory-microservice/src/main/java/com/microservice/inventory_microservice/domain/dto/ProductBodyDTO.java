package com.microservice.inventory_microservice.domain.dto;

import com.microservice.inventory_microservice.persistence.model.ProductImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Value
public class ProductBodyDTO implements Serializable {
    @NotBlank(message = "El campo code es incorrecto")
    String code;

    @NotBlank(message = "El campo name es incorrecto")
    String name;

    String description;

    @NotNull(message = "El campo retailPrice no puede ser Nulo")
    BigDecimal retailPrice; // Precio al Detal o minorista, o comsumidor

    @NotNull(message = "El campo wholesalePrice no puede ser Nulo")
    BigDecimal wholesalePrice;  // Precio al mayorista

    @NotNull(message = "El campo previous_price no puede ser Nulo")
    BigDecimal previous_price;  // Precio anterior

    BigDecimal discount;    // Descuento

    String discountType;    // Tipo de descuento (% o Fijo)

    @NotNull(message = "El campo amount no puede ser Nulo")
    BigDecimal amount;  // Cantidad existente

    @NotNull(message = "El campo minAmount no puede ser Nulo")
    BigDecimal minAmount; // Cantidad Minima en Stock

    @NotNull(message = "El campo purchasePrice no puede ser Nulo")
    BigDecimal purchasePrice;   // Precio de Compra


    Boolean delivery;   // Si es entregable

    BigDecimal deliveryPrice;   // Precio de entrega

    Boolean formula;    // Tipo de formula

    Boolean active; // Si el producto es activo o no

    List<MultipartFile> images; // Imagenes de presentacion

    Long brandId;   // Id marca del producto

    Long measurementUnitId; // unidade de medida base del producto

}