package org.ecommerce.app.unit;

import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.dto.CheckoutResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class CheckoutServiceTest extends BeforeAllTest {

    public Stream<Arguments> test_calculate_checkout() {

        CheckoutRequestDto request1 = CheckoutRequestDto.builder().productIdList(List.of("001", "002", "001", "004", "003")).build();
        CheckoutRequestDto request2 = CheckoutRequestDto.builder().productIdList(List.of("001", "002")).build();
        return Stream.of(Arguments.of(request1, BigDecimal.valueOf(360)), Arguments.of(request2, BigDecimal.valueOf(180)));
    }


    @ParameterizedTest
    @MethodSource
    void test_calculate_checkout(CheckoutRequestDto requestDto, BigDecimal expectedValue) {

        CheckoutResponseDto checkoutResponseDto = checkoutService.calculatePrice(requestDto);
        Assertions.assertNotNull(checkoutResponseDto);
        Assertions.assertNotNull(checkoutResponseDto.getPrice());
        Assertions.assertEquals(checkoutResponseDto.getPrice().doubleValue(), expectedValue.doubleValue());
    }


}
