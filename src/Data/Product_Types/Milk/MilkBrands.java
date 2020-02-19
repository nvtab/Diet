package Data.Product_Types.Milk;

import Data.Product_Types.Brands;

public enum MilkBrands implements Brands {
    ALQUERIA, LA_VACA_LOLA;

    public String toBeautiful(){
        switch (this){
            case ALQUERIA: return "Alqueria";
            case LA_VACA_LOLA: return "La Vaca Lola";
            default: return "Invalid type";
        }
    }
}
