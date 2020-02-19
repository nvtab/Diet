package Data.Product_Types.Fruits;

import Data.Product_Types.ProductType;

public enum FruitType implements ProductType {
    WATERMELON, BANANA, MANGO;

    public String toBeautiful(){
        switch (this){
            case WATERMELON: return "Sandía";
            case BANANA: return "Banano";
            case MANGO: return "Mango";
            default: return "Invalid type";
        }
    }
}
