package org.ecommerce.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.dto.CheckoutResponseDto;
import org.ecommerce.app.exception.NotFoundException;
import org.ecommerce.app.persistance.entity.DiscountEntity;
import org.ecommerce.app.persistance.entity.ProductEntity;
import org.ecommerce.app.service.CheckoutService;
import org.ecommerce.app.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Kemal Acar
 */
@Service
@Slf4j
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final ProductService productService;


    public CheckoutResponseDto calculatePrice(CheckoutRequestDto requestDto) {

        List<String> productIdList = requestDto.getProductIdList();

        Map<String, Long> productCountMap = productIdList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<ProductEntity> productEntities = productService.getProductsById(productCountMap.keySet());

        BigDecimal totalPrice = getProductsPrice(productEntities, productCountMap);

        if (!productCountMap.isEmpty()) {
            log.warn(MessageFormat.format("These product IDs cannot be found in the DB . IDs =  {0} ", productCountMap.keySet()));
            throw new NotFoundException("These provided products cannot be found. IDs =  " + productCountMap.keySet());
        }

        log.info(MessageFormat.format("Checkout price = {0} ", totalPrice));

        return CheckoutResponseDto.builder().price(totalPrice).build();
    }

    private BigDecimal getProductsPrice(List<ProductEntity> productEntities, Map<String, Long> productCountMap) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductEntity productEntity : productEntities) {
            totalPrice = totalPrice.add(getProductPrice(productEntity, productCountMap.get(productEntity.getId())));
            productCountMap.remove(productEntity.getId());
        }
        return totalPrice;
    }

    /**
     * @param productEntity
     * @param requestedQuantity
     * @return calculatedPrice
     */
    public BigDecimal getProductPrice(ProductEntity productEntity, long requestedQuantity) {

        if (productEntity.hasDiscount()) {
            DiscountEntity discount = productEntity.getDiscount();
            long division = requestedQuantity / discount.getQuantity();
            long reminder = requestedQuantity % discount.getQuantity();

            BigDecimal discountedPrice = discount.getTotalPrice().multiply(BigDecimal.valueOf(division));
            BigDecimal reminderPrice = productEntity.getUnitPrice().multiply(BigDecimal.valueOf(reminder));

            return discountedPrice.add(reminderPrice);

        } else {
            return productEntity.getUnitPrice().multiply(BigDecimal.valueOf(requestedQuantity));
        }
    }


}

