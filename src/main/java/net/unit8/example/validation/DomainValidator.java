package net.unit8.example.validation;

import javax.validation.*;
import java.util.Set;

/**
 * @author kawasima
 */
public class DomainValidator implements ConstraintValidator<Domain, Object> {
    private Class<Object> domainBean;
    private Validator validator;
    private String domainName;

    @Override
    public void initialize(Domain constraintAnnotation) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        DomainManager domainManager = DomainManagerFactory.create();
        domainBean = domainManager.getDomainBean();
        domainName = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        Set<ConstraintViolation<Object>> violations = validator.validateValue(domainBean, domainName, value);
        for (ConstraintViolation<Object> cv : violations) {
            context.buildConstraintViolationWithTemplate(cv.getMessage()).addConstraintViolation();
        }
        return violations.isEmpty();
    }
}
