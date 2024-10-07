package ie.atu.week4.jpa;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> add(Product product)
    {

        productRepo.save(product);
        return productRepo.findAll();
    }


    public List<Product> updateProduct(long id, Product updatedProduct) {
        Product existingProduct = productRepo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            productRepo.save(existingProduct);
        }

        return productRepo.findAll();
    }
}