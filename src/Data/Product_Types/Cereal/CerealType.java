package Data.Product_Types.Cereal;


import Data.Product_Types.ProductType;

public enum CerealType implements ProductType {
    CHOCOCRISPIS, CORNFLAKES, TRIX;

    public String toBeautiful(){
        switch (this){
            case CHOCOCRISPIS: return "ChocoCrispis";
            case CORNFLAKES: return "CornFlakes";
            case TRIX: return "Trix";
            default: return "Invalid type";
        }
    }
}
