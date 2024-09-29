package repository;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@org.springframework.stereotype.Repository
public class ProductDAO {
    public static final String PRODUCT_KEY = "Product";

    @Autowired
    private RedisTemplate redisTemplate;

    public Product save(Product product) {
        redisTemplate.opsForHash().put(PRODUCT_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return redisTemplate.opsForHash().values(PRODUCT_KEY);
    }

    public Product findById(int id) {
        return (Product) redisTemplate.opsForHash().get(PRODUCT_KEY, id);
    }

    public Long deleteById(String id) {
        return redisTemplate.opsForHash().delete(PRODUCT_KEY, id);
    }
}
