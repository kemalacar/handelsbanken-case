package org.ecommerce.app.automation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import org.ecommerce.app.dto.CheckoutRequestDto;
import org.ecommerce.app.unit.BeforeAllTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kemal Acar
 */

@SpringBootTest
public class CalculatePriceTest extends BeforeAllTest  {

    @Inject
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Test
    void test_checkout() {

        try {

            CheckoutRequestDto requestDto = CheckoutRequestDto.builder().productIdList(List.of("001", "002", "001", "004", "003")).build();

            BigDecimal expectedResult  = new BigDecimal(360);

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/checkout")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestDto));

            mockMvc.perform(mockRequest)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", notNullValue()))
                    .andExpect(jsonPath("$.price", is(expectedResult.doubleValue())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

