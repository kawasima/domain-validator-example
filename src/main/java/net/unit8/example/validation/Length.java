package net.unit8.example.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * @author kawasima
 */
@Documented
@Constraint(validatedBy = {Length.LengthValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
    int min() default 0;
    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    String message() default "{net.unit8.example.validation.Length.message}";
    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Length[] value();
    }

    class LengthValidator implements ConstraintValidator<Length, CharSequence> {
        private int min;
        private int max;

        @Override
        public void initialize(Length parameters) {
            min = parameters.min();
            max = parameters.max();
        }

        @Override
        public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
            if (value == null)
                return true;

            int length = value.length();
            return length >= min && length <= max;
        }
    }
}
