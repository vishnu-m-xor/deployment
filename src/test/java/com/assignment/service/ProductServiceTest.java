package com.assignment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.Dao.ProductDao;
import com.assignment.model.Product;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        product1 = new Product("Laptop", "Gaming Laptop", "Electronics", new BigDecimal("1500.00"), 10);
        product1.setId(1L);
        product2 = new Product("Book", "Java Programming", "Education", new BigDecimal("50.00"), 5);
        product2.setId(2L);
        productList = Arrays.asList(product1, product2);
    }

    @Test
    void testInsertProduct() {
        // Arrange
        when(productDao.insertProduct(productList)).thenReturn(productList);

        // Act
        List<Product> result = productService.insertProduct(productList);

        // Assert
        assertEquals(productList, result);
        verify(productDao, times(1)).insertProduct(productList);
    }

    @Test
    void testSetDataById() {
        // Arrange
        when(productDao.setDataById(product1)).thenReturn(product1);

        // Act
        Product result = productService.setDataById(product1);

        // Assert
        assertEquals(product1, result);
        verify(productDao, times(1)).setDataById(product1);
    }

    @Test
    void testGetAllData() {
        // Arrange
        when(productDao.getAllData()).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllData();

        // Assert
        assertEquals(productList, result);
        verify(productDao, times(1)).getAllData();
    }

    @Test
    void testGetDataById() {
        // Arrange
        Long id = 1L;
        when(productDao.getDataById(id)).thenReturn(product1);

        // Act
        Product result = productService.getDataById(id);

        // Assert
        assertEquals(product1, result);
        verify(productDao, times(1)).getDataById(id);
    }

    @Test
    void testDataByCategory() {
        // Arrange
        String category = "Electronics";
        List<Product> electronics = Arrays.asList(product1);
        when(productDao.dataByCategory(category)).thenReturn(electronics);

        // Act
        List<Product> result = productService.dataByCategory(category);

        // Assert
        assertEquals(electronics, result);
        verify(productDao, times(1)).dataByCategory(category);
    }

    @Test
    void testDataByPrice() {
        // Arrange
        int minPrice = 100;
        int maxPrice = 2000;
        when(productDao.dataByPrice(minPrice, maxPrice)).thenReturn(productList);

        // Act
        List<Product> result = productService.dataByPrice(minPrice, maxPrice);

        // Assert
        assertEquals(productList, result);
        verify(productDao, times(1)).dataByPrice(minPrice, maxPrice);
    }

    @Test
    void testDataBySearch() {
        // Arrange
        String name = "Laptop";
        List<Product> searchResults = Arrays.asList(product1);
        when(productDao.dataBySearch(name)).thenReturn(searchResults);

        // Act
        List<Product> result = productService.dataBySearch(name);

        // Assert
        assertEquals(searchResults, result);
        verify(productDao, times(1)).dataBySearch(name);
    }

    @Test
    void testDataByStock() {
        // Arrange
        when(productDao.dataByStock()).thenReturn(productList);

        // Act
        List<Product> result = productService.dataByStock();

        // Assert
        assertEquals(productList, result);
        verify(productDao, times(1)).dataByStock();
    }

    @Test
    void testDataBySorting() {
        // Arrange
        when(productDao.dataBySorting()).thenReturn(productList);

        // Act
        List<Product> result = productService.dataBySorting();

        // Assert
        assertEquals(productList, result);
        verify(productDao, times(1)).dataBySorting();
    }

    @Test
    void testCountByName() {
        // Arrange
        String cname = "Laptop";
        Long count = 1L;
        when(productDao.CountByName(cname)).thenReturn(count);

        // Act
        Long result = productService.CountByName(cname);

        // Assert
        assertEquals(count, result);
        verify(productDao, times(1)).CountByName(cname);
    }

    @Test
    void testDeleteById() {
        // Arrange
        Long id = 1L;
        doNothing().when(productDao).DeleteById(id);

        // Act
        productService.DeleteById(id);

        // Assert
        verify(productDao, times(1)).DeleteById(id);
    }
}