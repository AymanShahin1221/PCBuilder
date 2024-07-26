package com.app.service.api;

import com.app.exception.InvalidApiResponseException;
import com.app.exception.MaxCallsReachedException;
import com.app.service.util.RequestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@PrepareForTest({RequestUtils.class})
class EbayApiServiceExceptionsTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EbayApiService ebayApiService;

    @Test
    void testGetImgUrlInvalidApiResponse() {
        try (var mockedStatic = Mockito.mockStatic(RequestUtils.class))
        {
            String searchTerm = "intel core 19-14900k";
            String mockJsonResponse = "{\"Errors\":[{\"ErrorClassification\":\"RequestError\",\"ShortMessage\":\"Invalid token.\",\"SeverityCode\":\"Error\",\"LongMessage\":\"Invalid token. Please specify a valid token as HTTP header.\",\"ErrorCode\":\"1.32\"}],\"Version\":\"1157\",\"Build\":\"E1157_CORE_APILW2_19110892_R1\",\"Ack\":\"Failure\",\"Timestamp\":\"2024-07-25T22:12:26.757Z\"}";

            ResponseEntity<String> mockResponse = new ResponseEntity<>(mockJsonResponse, HttpStatus.OK);
            mockedStatic.when(() -> RequestUtils.makeGetRequest(Mockito.anyString(), Mockito.any(HttpHeaders.class), Mockito.any(RestTemplate.class)))
                    .thenReturn(mockResponse);

            assertThrows(InvalidApiResponseException.class, () -> {
                ebayApiService.getImgUrl(searchTerm);
            });
        }
    }

    @Test
    void testGetImgUrlMaxCallsReached() {
        try (var mockedStatic = Mockito.mockStatic(RequestUtils.class))
        {
            String searchTerm = "intel core 19-14900k";
            String mockJsonResponse = "{\"Errors\":[{\"ErrorClassification\":\"RequestError\",\"ShortMessage\":\"IP limit exceeded.\",\"SeverityCode\":\"Error\",\"LongMessage\":\"Invalid token. Please specify a valid token as HTTP header.\",\"ErrorCode\":\"1.32\"}],\"Version\":\"1157\",\"Build\":\"E1157_CORE_APILW2_19110892_R1\",\"Ack\":\"Failure\",\"Timestamp\":\"2024-07-25T22:12:26.757Z\"}";

            ResponseEntity<String> mockResponse = new ResponseEntity<>(mockJsonResponse, HttpStatus.OK);
            mockedStatic.when(() -> RequestUtils.makeGetRequest(Mockito.anyString(), Mockito.any(HttpHeaders.class), Mockito.any(RestTemplate.class)))
                    .thenReturn(mockResponse);

            assertThrows(MaxCallsReachedException.class, () -> {
                ebayApiService.getImgUrl(searchTerm);
            });
        }
    }
}
