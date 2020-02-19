package Data.Product_Types.Vegetables;

import Data.Product_Types.ProductType;

public enum VegetableTypes implements ProductType {

    CARROT, LETTUCE, CELERY, SPINACH;

    public String toBeautiful(){
        switch (this){
            case CARROT: return "Zanahoria";
            case LETTUCE: return "Lechuga";
            case CELERY: return "Apio";
            case SPINACH: return "Espinaca";
            default: return "Invalid type";
        }
    }
}
