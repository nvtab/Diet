package Data.Product_Types.Candy;

import Data.Product_Types.Brands;

public enum CandyBrands implements Brands {

    COLOMBINA, FERRERO;

    public String toBeautiful(){
        switch (this){
            case COLOMBINA: return "Colombina";
            case FERRERO: return "Ferrero Rocher";
            default: return "Invalid type";
        }
    }
}
