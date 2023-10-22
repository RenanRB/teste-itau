package com.app.consumer.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.app.consumer.infrastructure.adapters.config.BeanConfiguration;

@ExtendWith(MockitoExtension.class)
public class ConfigTest {

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testModelMapperBean() {
        BeanConfiguration config = new BeanConfiguration();

        ModelMapper modelMapper = config.modelMapper();

        assertNotNull(modelMapper);
    }
}