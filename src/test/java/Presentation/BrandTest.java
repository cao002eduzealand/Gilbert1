package Presentation;

import Domain.Brand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    void testBrandConstructor_ShouldCreateBrandWithCorrectValues() {
        // Arrange & Act
        Brand brand = new Brand(1, "Nike");

        // Assert
        assertEquals(1, brand.getId());
        assertEquals("Nike", brand.getBrandName());
    }

    @Test
    void testBrandDefaultConstructor_ShouldCreateEmptyBrand() {
        // Arrange & Act
        Brand brand = new Brand();

        // Assert
        assertEquals(0, brand.getId()); // default int value
        assertNull(brand.getBrandName()); // default String value
    }

    @Test
    void testSetId_ShouldUpdateId() {
        // Arrange
        Brand brand = new Brand();

        // Act
        brand.setId(5);

        // Assert
        assertEquals(5, brand.getId());
    }

    @Test
    void testSetBrandName_ShouldUpdateBrandName() {
        // Arrange
        Brand brand = new Brand();

        // Act
        brand.setBrandName("Adidas");

        // Assert
        assertEquals("Adidas", brand.getBrandName());
    }

    @Test
    void testToString_ShouldReturnBrandName() {
        // Arrange
        Brand brand = new Brand(1, "Puma");

        // Act
        String result = brand.toString();

        // Assert
        assertEquals("Puma", result);
    }

    @Test
    void testToString_WithNullBrandName_ShouldReturnNull() {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandName(null);

        // Act
        String result = brand.toString();

        // Assert
        assertNull(result);
    }

    @Test
    void testBrandEquality_SameBrandName_ShouldBeEqual() {
        // Arrange
        Brand brand1 = new Brand(1, "Nike");
        Brand brand2 = new Brand(2, "Nike");

        // Act & Assert
        assertEquals(brand1.getBrandName(), brand2.getBrandName());
        assertEquals(brand1.toString(), brand2.toString());
    }
}