package fagpraksisdemo.validators;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class DefaultValidator {

    ValidatorFactory factory;
    Validator validator;

    private static class DefaultValidatorSingleton {
        private static final DefaultValidator INSTANCE = new DefaultValidator();
    }

    private DefaultValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    public static DefaultValidator getInstance() {
        return DefaultValidatorSingleton.INSTANCE;
    }

    public <T> Set<ConstraintViolation<T>> validate(T toValidate) {
        return validator.validate(toValidate);
    }

}
