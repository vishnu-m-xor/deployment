package com.assignment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.Dao.ProductDao;
import com.assignment.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDao productdao;

    @InjectMocks
    private ProductService productService;

    @Test
    void insertProduct_success() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Laptop", "Electronics", new BigDecimal("50000"), 10),
                new Product(2L, "Phone", "Electrics", new BigDecimal("50000"), 20)
        );

        when(productdao.insertProduct(products)).thenReturn(products);
       
        List<Product> result = productService.insertProduct(products);
        
        
        System.out.println(result);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productdao).insertProduct(products);
    }

    @Test
    void getAllData_success() {
        when(productdao.getAllData()).thenReturn(List.of());

        List<Product> result = productService.getAllData();

        assertNotNull(result);
        verify(productdao).getAllData();
    }

    @Test
    void getDataById_success() {
        Product product = new Product(1L, "Mobile", "Electronics", new BigDecimal("50000"), 5);

        when(productdao.getDataById(1L)).thenReturn(product);

        Product result = productService.getDataById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(productdao).getDataById(1L);
    }
}
