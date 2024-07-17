package com.app.service.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan("com.app.service.api")
class EbayApiServiceTest {

    @Autowired
    private EbayApiService ebayApiService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}