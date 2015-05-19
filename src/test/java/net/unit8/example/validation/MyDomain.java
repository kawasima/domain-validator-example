package net.unit8.example.validation;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * @author kawasima
 */
public class MyDomain {
    @Length(max = 5)
    String name;

    @Digits(integer = 3, fraction=0)
    BigDecimal money;
}
