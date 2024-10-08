package ie.atu.week4.jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private List<Product> productList = new ArrayList<>();
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productList;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<List> addProduct(@RequestBody Product product) {
        productList = productService.add(product);
        //productList.add(product);
        return ResponseEntity.ok(productList);
    }

    private Product findProductById(long id) {
        for (Product product : productList) {
            if (product.getProductCode() == id) {
                return product;
            }
        }
        return null;
    }

    // Update Product Method
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<List<Product>> updateProduct(@PathVariable long id, @RequestBody Product updatedProduct) {
        List<Product> productList = productService.updateProduct(id, updatedProduct);

        if (productList != null) {
            return ResponseEntity.ok(productList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
