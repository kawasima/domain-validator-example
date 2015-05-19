package net.unit8.example.validation;

/**
 * @author kawasima
 */
public class MyDomainManager implements DomainManager {
    @Override
    public Class<?> getDomainBean() {
        return MyDomain.class;
    }
}
