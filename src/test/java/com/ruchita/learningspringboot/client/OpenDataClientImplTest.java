package com.ruchita.learningspringboot.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenDataClientImplTest {

    private OpenDataClientImpl openDataClientImpl;
    @BeforeEach
    void setUp() {
        openDataClientImpl =  new OpenDataClientImpl();
    }
}