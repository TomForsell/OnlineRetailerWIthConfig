package no.dnb.reskill.andyonlineretailer.bizlayer;

import no.dnb.reskill.andyonlineretailer.models.Product;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

public interface ProductService {
    double calculateTotalValue();
    Collection<Product> getLowStockProducts(long threashhold);
    double getAveragePrice();
    void adjustPriceByPercent(long id, double byPercent);
    Collection<Product> calculateIncludedVATPrices();
    double getVatAccordingToPrice(double price);
}
