package Validator;

import Model.Category;
import Model.Product;
import javax.validation.ConstraintViolation;
import java.util.Set;
import javax.validation.Validator;

public class ProductValidator {
    public static String validateProduct(Product product) {
        Validator validator = ValidatorUtil.getValidator();
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Product> violation : violations) {
                errorMessage.append(violation.getMessage()).append("<br>");
            }
            return errorMessage.toString();
        }

        return null; // No errors
    }
    
    public static String validateCategory(Category category) {
        Validator validator = ValidatorUtil.getValidator();
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Category> violation : violations) {
                errorMessage.append(violation.getMessage()).append("<br>");
            }
            return errorMessage.toString();
        }

        return null; // No errors
    }
}