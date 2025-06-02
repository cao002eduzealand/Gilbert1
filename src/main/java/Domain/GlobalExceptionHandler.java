package Domain;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductOperationException.class)
    public String handleProductOperationException(ProductOperationException ex, Model model) {
        logger.error("Product operation failed: {}", ex.getMessage(), ex);
        model.addAttribute("errorMessage", "Product operation failed: " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ImageUploadException.class)
    public String handleImageUploadException(ImageUploadException ex, Model model) {
        logger.error("Image upload failed: {}", ex.getMessage(), ex);
        model.addAttribute("errorMessage", "Image upload failed: " + ex.getMessage());
        return "error";
    }
    @ExceptionHandler
    public void handleRuntimeException(RuntimeException ex){
        ex.printStackTrace();
    }

}
