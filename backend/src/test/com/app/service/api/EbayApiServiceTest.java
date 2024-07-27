package com.app.service.api;

import com.app.exception.InvalidApiResponseException;
import com.app.exception.MaxCallsReachedException;
import com.app.service.util.RequestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EbayApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EbayApiService ebayApiService;

    private static List<Arguments> provideMockExpectedImageUrls() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        String baseExampleUrl = "http://example.com/";
        for(int i = 0; i < 1000; i++)
            arguments.add(Arguments.of(baseExampleUrl + random.nextInt() + ".jpg"));

        return arguments;
    }

    @ParameterizedTest
    @MethodSource("provideMockExpectedImageUrls")
    void testGetImgUrl(String mockExpectedImageUrl) throws MaxCallsReachedException, InvalidApiResponseException {

        try (var mockedStatic = Mockito.mockStatic(RequestUtils.class))
        {
            String mockJsonResponse =
                    "{\"Product\": [{\"DisplayStockPhotos\": true,\"StockPhotoURL\": \"" + mockExpectedImageUrl + "\"},{\"DisplayStockPhotos\": false,\"StockPhotoURL\": \"http://example.com/image2.jpg\"}]}\n";

            ResponseEntity<String> mockResponse = new ResponseEntity<>(mockJsonResponse, HttpStatus.OK);
            mockedStatic
                    .when(() -> RequestUtils.makeGetRequest(Mockito.anyString(), Mockito.any(HttpHeaders.class), Mockito.any(RestTemplate.class)))
                    .thenReturn(mockResponse);

            String actualImgUrl = ebayApiService.getImgUrl("some_test_search_term");
            assertEquals(mockExpectedImageUrl, actualImgUrl);
        }
    }
}
