package no.dnb.reskill.andyonlineretailer.configuration;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//lombok rules :)
@Getter
@Setter
@ToString
public class VatLevels {
    private double lowestVAT;
    private double mediumVat ;
    private double ridiculousVAT;
    private double lowestPriceLevel;
    private double mediumPriceLevel;
}
