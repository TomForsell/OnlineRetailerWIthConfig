package no.dnb.reskill.andyonlineretailer.bizlayer;

import no.dnb.reskill.andyonlineretailer.configuration.ConfigVAT;
import no.dnb.reskill.andyonlineretailer.configuration.VatLevels;
import no.dnb.reskill.andyonlineretailer.models.Product;
import no.dnb.reskill.andyonlineretailer.datalayer.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository repository;
    private VatLevels vatLevels;
    @Autowired
    public ProductServiceImpl(@Qualifier("productRepositoryMemory") ProductRepository repository, @Qualifier("vatSpecifications") VatLevels vatLevels ){
        this.repository = repository;
        this.vatLevels = vatLevels;
    }


    @Override
    public double calculateTotalValue() {
        return repository.getAllProducts()
                         .stream()
                         .mapToDouble(p -> p.getPrice() * p.getInStock())
                         .sum();
    }
    @Override
    public Collection<Product> getLowStockProducts(long threashhold) {
        return repository.getAllProducts()
                         .stream()
                         .filter(p -> p.getInStock() < threashhold)
                         .collect(Collectors.toList());

    }

    @Override
    public double getAveragePrice() {
        return repository.getAllProducts()
                         .stream()
                         .mapToDouble(p -> p.getPrice() * p.getInStock())
                         .average()
                         .orElse(0.0);
    }

    @Override
    public void adjustPriceByPercent(long id, double byPercent) {
        Product theProduct = repository.getProductById(id);
        if(theProduct == null){
            return;
        } else{
            theProduct.adjustPriceByPersent(byPercent);
            repository.updateProduct(theProduct);
        }

    }
    @Override
    public Collection<Product> calculateIncludedVATPrices() {
        //VatBean myVatBean = myCTX.getBean(VatBean.class);
       // VatLevels myVatLevels = myCTX.getBean(VatLevels.class);
        return repository.getAllProducts()
                .stream()
                .peek(product -> product.adjustPriceByPersent(getVatAccordingToPrice(product.getPrice())))
               .collect(Collectors.toList());
    }

    public double getVatAccordingToPrice(double price){
        if (price < vatLevels.getLowestPriceLevel()){

            return vatLevels.getLowestVAT();
        }
        if (price < vatLevels.getMediumPriceLevel()){
            return vatLevels.getMediumVat();
        }
        else{
            return vatLevels.getRidiculousVAT();
        }
    }

}
