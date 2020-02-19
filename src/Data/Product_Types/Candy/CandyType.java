package Data.Product_Types.Candy;

import Data.Product_Types.ProductType;

public enum CandyType implements ProductType {
    CHOCOLATE, CARAMEL, BUTTERSCOTCH;

    public String toBeautiful(){
        switch (this){
            case CHOCOLATE: return "Chocolate";
            case CARAMEL: return "Caramelo";
            case BUTTERSCOTCH: return "Dulce de leche";
            default: return "Invalid type";
        }
    }


}
