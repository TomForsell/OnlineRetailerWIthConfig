package no.dnb.reskill.andyonlineretailer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigVAT {

    //had to give it a name - could not get it to work otherwise
    @Bean (name = "vatSpecifications")
    public VatLevels myVatLevels() {
        VatLevels b = new VatLevels();
        b.setLowestVAT(25.00);
        b.setMediumVat(27.00);
        b.setRidiculousVAT(50.00);
        b.setMediumPriceLevel(10000.00);
        b.setLowestPriceLevel(100.00);
        return b;
    }


}
