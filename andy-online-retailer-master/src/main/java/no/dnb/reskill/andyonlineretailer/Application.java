package no.dnb.reskill.andyonlineretailer;

import no.dnb.reskill.andyonlineretailer.bizlayer.ProductService;
import no.dnb.reskill.andyonlineretailer.datalayer.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        // use interface on the rihgt side, so you can use all the implementations
        ProductService service = ctx.getBean(ProductService.class);
        service.adjustPriceByPercent(1, 10);
        double totalValue = service.calculateTotalValue();
        System.out.println("Total value of the stock: " + totalValue);

        double averagePrice = service.getAveragePrice();
        System.out.println("The average price: " + averagePrice);

        ProductRepository repo = ctx.getBean("productRepositoryMemory", ProductRepository.class);

        repo.getAllProducts()
                .stream()
                .forEach(p -> System.out.println(p));

        service.calculateIncludedVATPrices().stream().forEach(product -> System.out.println("After added VAT: " + product));


    }

}
