package Data.Product_Types.Egg;

import Data.Product_Types.ProductType;

public enum EggType implements ProductType {
    WHITE, GOLDEN, DUCK, AAA;

    public String toBeautiful() {
        switch (this) {
            case WHITE: return "Blanco";
            case GOLDEN: return "Dorado";
            case DUCK: return "De Pato";
            case AAA: return "AAA";
            default: return "Invalid type";
        }
    }
}
