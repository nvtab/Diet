package Data.Product_Types;

public enum Products {
    MILK, EGGS, CHEESE, CEREAL, VEGETABLE, FRUIT, CANDY, GRAIN;

    public static String[] getValues(){

        String [] productTypes = new String[8];

        productTypes[0] = "Leche";
        productTypes[1] = "Huevos";
        productTypes[2] = "Quesos";
        productTypes[3] = "Cereales";
        productTypes[4] = "Vegetales";
        productTypes[5] = "Frutas";
        productTypes[6] = "Dulces";
        productTypes[7] = "Granos";

        return productTypes;
    }
}
