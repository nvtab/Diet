package Data.Product_Types.Grain;

import Data.Product_Types.ProductType;

public enum GrainType implements ProductType {

    BEAN, LENTIL, PEA;

    public String toBeautiful(){
        switch (this){
            case BEAN: return "Frijol";
            case LENTIL: return "Lenteja";
            case PEA: return "Arveja";
            default: return "Invalid type";
        }
    }

}
