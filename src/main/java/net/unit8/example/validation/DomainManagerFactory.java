package net.unit8.example.validation;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutionException;

/**
 * @author kawasima
 */
public class DomainManagerFactory {
    private static final String KEY = "DOMAIN_MANAGER";

    private static final LoadingCache<String,DomainManager> domainManagerCache = CacheBuilder
            .newBuilder()
            .maximumSize(1)
            .build(new CacheLoader<String, DomainManager>() {
                @Override
                public DomainManager load(String key) throws Exception {
                    ServiceLoader<DomainManager> domainManagerLoader = ServiceLoader.load(DomainManager.class);
                    Iterator<DomainManager> iter = domainManagerLoader.iterator();
                    if (!iter.hasNext()) { throw new IllegalStateException(); }
                    return iter.next();
                }
            });
    public static DomainManager create() {
        try {
            return domainManagerCache.get(KEY);
        } catch (ExecutionException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
