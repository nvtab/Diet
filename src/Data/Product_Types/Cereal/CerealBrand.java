package Data.Product_Types.Cereal;

import Data.Product_Types.Brands;

public enum CerealBrand implements Brands {

    KELLOGS;

    public String toBeautiful(){
        switch (this){
            case KELLOGS: return "Kellogs";
            default: return "Invalid type";
        }
    }
}
