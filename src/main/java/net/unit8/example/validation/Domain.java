package net.unit8.example.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * @author kawasima
 */
@Target({METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {DomainValidator.class})
public @interface Domain {
    String message() default "Domain mismatch.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String value();

    @Target({METHOD, FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Domain[] value();
    }
}
