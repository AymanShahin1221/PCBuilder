package com.app.service.api;

import com.app.exception.InvalidApiResponseException;
import com.app.exception.MaxCallsReachedException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EbayApiServiceTest {

    @Autowired
    private EbayApiService ebayApiService;

    private static List<Arguments> provideMockSearchTerms() {
        return List.of(
                Arguments.of("Intel core i9 14900k", "https://i.ebayimg.com/images/g/QQIAAOSwIAdlTLdC/s-l1600.jpg"),
                Arguments.of("AMD Ryzen 9 5900X Desktop Processor", "https://i.ebayimg.com/images/g/u00AAOSwIIRj4LJj/s-l640.jpg"),
                Arguments.of("AMD Ryzen 9 7950X", "https://i.ebayimg.com/images/g/s3oAAOSwaEpj5fNU/s-l960.jpg"),
                Arguments.of("Intel Xeon E5-1660 V3", "https://i.ebayimg.com/images/g/cGsAAOSwIcRhbw4u/s-l640.jpg"),
                Arguments.of("Intel Pentium G840", "https://i.ebayimg.com/images/g/XdwAAOxysE5SbMgt/s-l640.jpg"),
                Arguments.of("Intel Core i9 15900k", null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMockSearchTerms")
    void testGetImgUrl(String mockSearchTerm, String expectedImgURL) throws MaxCallsReachedException, InvalidApiResponseException {
        String actualImgUrl = ebayApiService.getImgUrl(mockSearchTerm);
        assertEquals(expectedImgURL, actualImgUrl);
    }
}
