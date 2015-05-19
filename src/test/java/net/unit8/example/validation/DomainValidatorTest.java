package net.unit8.example.validation;

import org.junit.Test;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author kawasima
 */
public class DomainValidatorTest {
    @Test
    public void test() throws Exception {
        TestBean bean = new TestBean();
        bean.name = "123456";
        bean.beans = new ArrayList<TestBean>();
        TestBean inner1 = new TestBean();
        inner1.name = "hoge";
        inner1.balance = 1000;
        bean.beans.add(inner1);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<TestBean>> violations = validator.validate(bean);
        System.out.println(violations);
        Path path = violations.iterator().next().getPropertyPath();

    }

    /** */
    public static class TestBean {
        @NotNull
        @Domain("name")
        String name;

        @Domain("money")
        int balance;

        @Valid
        List<TestBean> beans;

    }
}
