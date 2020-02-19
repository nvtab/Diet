package Data.Product_Types.Milk;

import Data.Product_Types.ProductType;

public enum MilkType implements ProductType {
    WHOLE, LOWFAT, FATFREE, ORGANIC, LACTOSEFREE;

    public String toBeautiful() {
        switch (this) {
            case WHOLE: return "Entera";
            case LOWFAT: return "Baja en grasa";
            case FATFREE: return "Libre de grasa";
            case ORGANIC: return "Organica";
            case LACTOSEFREE: return "Deslactosada";
            default: return "Invalid type";
        }
    }
}
