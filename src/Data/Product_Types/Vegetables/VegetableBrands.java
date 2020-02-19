package Data.Product_Types.Vegetables;

import Data.Product_Types.Brands;

public enum VegetableBrands implements Brands {

    DLP;

    public String toBeautiful(){
        switch (this){
            case DLP: return "De la Plaza";
            default: return "Invalid type";
        }
    }
}
