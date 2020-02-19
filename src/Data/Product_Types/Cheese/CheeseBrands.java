package Data.Product_Types.Cheese;

import Data.Product_Types.Brands;

public enum CheeseBrands implements Brands {

    COLANTA, ITALIA;

    public String toBeautiful(){
        switch (this){
            case COLANTA: return "Colanta";
            case ITALIA: return "Italia";
            default: return "Invalid type";
        }
    }
}
