package no.dnb.reskill.andyonlineretailer.datalayer;

import no.dnb.reskill.andyonlineretailer.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class ProductRepositoryMemory implements ProductRepository{

    private Map<Long, Product> catalog = new TreeMap<>();
    private static long nextId =1;


    // pre-populating with the data just to test. Use it normally in Unit-testing
    public ProductRepositoryMemory() {
        insertProduct(new Product("Lamborghini Sian", 2.2e6, 2));
        insertProduct(new Product("Bugatti Divo", 4e6,1));
        insertProduct(new Product("Jaguar I-Pacen", 80000, 4));
        insertProduct(new Product("Mitten", 10, 10));
        insertProduct(new Product("Hat", 100, 11));
        insertProduct(new Product("Fender Jaguar 1965", 3000, 1));
        insertProduct(new Product("Fender Champ 1962", 4000, 1));
    }

    @Override
    public Collection<Product> getAllProducts() {
        return catalog.values();
    }

    @Override
    public Product getProductById(long id) {
        return catalog.get(id);
    }

    @Override
    public Product insertProduct(Product product) {
        if(product.getId() != -1) {
            throw new IllegalArgumentException("Product id must be -1 to be inserted");
        }
        product.setId(nextId++);
        catalog.put(product.getId(),product);
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        long id = product.getId();
        if(!catalog.containsKey(id)){
            return false;
        }else {
            catalog.replace(id, product);
            return true;
        }
    }
    @Override
    public boolean deleteProduct(long id) {
        if(!catalog.containsKey(id)){
            return false;
        } else{
            catalog.remove(id);
            return true;
        }
    }
}
