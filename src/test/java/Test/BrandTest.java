package Test;

import Domain.Brand;
import Infrastructure.BrandRepositoryImpl;
import Presentation.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
@Transactional
class BrandTest {
    @Autowired
private JdbcTemplate jdbcTemplate;

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
    @Test
    void findById_ShouldReturnCorrectBrand() {
        // Arrange
        BrandRepositoryImpl brandRepository = new BrandRepositoryImpl(jdbcTemplate);

        // Act
        Brand result = brandRepository.findById(33);

        // Assert
        assertEquals(33, result.getId());
        assertEquals("ADYAR", result.getBrandName());
    }

}