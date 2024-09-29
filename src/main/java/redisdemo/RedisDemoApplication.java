package redisdemo;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import repository.ProductDAO;

import java.util.List;

@SpringBootApplication(scanBasePackages={"config","repository"})
@RestController
@RequestMapping("/api")
public class RedisDemoApplication {


    ProductDAO productDAO = new ProductDAO();

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productDAO.save(product);
    }

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable int id) {
		return productDAO.findById(id);
	}

    @GetMapping("/getAll")
	public List<Product> findAll() {
		return productDAO.findAll();
	}

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

}
