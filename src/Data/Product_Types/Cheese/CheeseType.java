package Data.Product_Types.Cheese;

import Data.Product_Types.ProductType;

public enum CheeseType implements ProductType {

    LOWFAT, MOZARELLA, GOAT, PARMESANO, BLUE;

    public String toBeautiful() {
        switch (this) {
            case LOWFAT: return "Bajo en grasa";
            case MOZARELLA: return "Mozarella";
            case GOAT: return "De Cabra";
            case PARMESANO: return "Parmesano";
            case BLUE : return "Azul";
            default: return "Invalid type";
        }
    }
}
