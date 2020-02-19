package Data.Product_Types.Egg;

import Data.Product_Types.Brands;

public enum EggBrands implements Brands {
    SUPERIOR, LPDLHDH;

    public String toBeautiful(){
        switch (this){
            case SUPERIOR: return "Superior";
            case LPDLHDH: return "La Pata de los Huevos de Oro";
            default: return "Invalid type";
        }
    }
}
