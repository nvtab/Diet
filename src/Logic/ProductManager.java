package Logic;

import Data.*;

import Data.Product_Types.Candy.CandyBrands;
import Data.Product_Types.Candy.CandyType;
import Data.Product_Types.Cereal.CerealBrand;
import Data.Product_Types.Cereal.CerealType;
import Data.Product_Types.Cheese.CheeseBrands;
import Data.Product_Types.Cheese.CheeseType;
import Data.Product_Types.Egg.EggBrands;
import Data.Product_Types.Egg.EggType;
import Data.Product_Types.Fruits.FruitType;
import Data.Product_Types.Grain.GrainType;
import Data.Product_Types.Milk.MilkBrands;
import Data.Product_Types.Milk.MilkType;
import Data.Product_Types.Products;
import Data.Product_Types.Vegetables.VegetableBrands;
import Data.Product_Types.Vegetables.VegetableTypes;

import java.io.*;
import java.util.HashMap;

public class ProductManager implements Serializable {

    public static final long serialVersionUID = 56L;
    public HashMap<Integer, Product> createdProducts = new HashMap<Integer, Product>();

    public static ProductManager productManager = new ProductManager();

    public void saveProducts () {
        try {
            final FileOutputStream fileOut = new FileOutputStream(new File("ProductsData\\Products.ser"));
            final ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            System.out.println("" + createdProducts.size() + " products saved");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to save ProductManager");
        }
    }

    public void loadProducts (){
        try {
            final FileInputStream fileIn = new FileInputStream(new File("ProductsData\\Products.ser"));
            final ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            productManager = (ProductManager) objectIn.readObject();
            System.out.println(productManager.createdProducts.size() + " retrieved products");
        }

        catch (IOException e) {
            System.out.println(productManager.createdProducts.size() + " retrieved products");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Product getProductByName (String name){
        System.out.println(name);
        for (int a = 1; this.createdProducts.size() > a; a++){
            System.out.println(createdProducts.get(a));
            if (this.createdProducts.get(a).name.equals(name)) {
                return createdProducts.get(a);
            }
        }
        return null;
    }


    public void creatingProducts() {
        Product wholeAlqueriaMilk = new Product("Leche Entera", Products.MILK, 66000, 2000, 900, MilkType.WHOLE, MilkBrands.ALQUERIA, FoodGroup.DAIRY);
        Product lowFatAlqueriaMilk = new Product("Leche baja en grasa", Products.MILK, 102, 3000, 900, MilkType.LOWFAT, MilkBrands.ALQUERIA, FoodGroup.DAIRY);
        Product fatFreeAlqueriaMilk = new Product("Leche libre de grasas", Products.MILK, 50, 3500, 900, MilkType.FATFREE, MilkBrands.ALQUERIA, FoodGroup.DAIRY);
        Product organicAlqueriaMilk = new Product("Leche orgánica", Products.MILK, 8000, 3000, 900, MilkType.ORGANIC, MilkBrands.LA_VACA_LOLA, FoodGroup.DAIRY);

        Product goldenEgg = new Product("Huevo dorado", Products.EGGS, 155, 800, 100, EggType.GOLDEN, EggBrands.SUPERIOR, FoodGroup.PROTEIN);
        Product whiteEgg = new Product("Huevo blanco", Products.EGGS, 155, 1000, 100, EggType.WHITE, EggBrands.SUPERIOR, FoodGroup.PROTEIN);
        Product AAAEgg = new Product("Huevo AAA", Products.EGGS, 180, 900, 100, EggType.AAA, EggBrands.SUPERIOR, FoodGroup.PROTEIN);
        Product duckEgg = new Product("Huevo de Pato", Products.EGGS, 100, 1100, 100, EggType.DUCK, EggBrands.LPDLHDH, FoodGroup.PROTEIN);

        Product lowFatCheese = new Product("Queso bajo en grasa", Products.CHEESE, 190, 5000, 500, CheeseType.LOWFAT, CheeseBrands.COLANTA, FoodGroup.DAIRY);
        Product mozarellaCheese = new Product("Queso mozarella", Products.CHEESE, 200, 4000, 500, CheeseType.MOZARELLA, CheeseBrands.ITALIA, FoodGroup.DAIRY);
        Product blueCheese = new Product("Queso azul", Products.CHEESE, 200, 8000, 500, CheeseType.BLUE, CheeseBrands.ITALIA, FoodGroup.DAIRY);
        Product goatCheese = new Product("Queso de Cabra", Products.CHEESE, 300, 5000, 500, CheeseType.GOAT, CheeseBrands.ITALIA, FoodGroup.DAIRY);
        Product parmesanoCheese = new Product("Queso parmesano", Products.CHEESE, 100, 5000, 500, CheeseType.PARMESANO, CheeseBrands.COLANTA, FoodGroup.DAIRY);

        Product chocokrispis = new Product("ChocoKrispis", Products.CEREAL, 400, 10000, 500, CerealType.CHOCOCRISPIS, CerealBrand.KELLOGS, FoodGroup.CARBOHYDRATES);
        Product cornFlakes = new Product("Corn Flakes", Products.CEREAL, 20, 8000, 500, CerealType.CORNFLAKES, CerealBrand.KELLOGS, FoodGroup.CARBOHYDRATES);
        Product trix = new Product("Trix", Products.CEREAL, 400, 10000, 500, CerealType.TRIX, CerealBrand.KELLOGS, FoodGroup.CARBOHYDRATES);

        Product carrot = new Product("Zanahoria", Products.VEGETABLE, 15, 2000, 100, VegetableTypes.CARROT, VegetableBrands.DLP, FoodGroup.VEGETABLE);
        Product lettuce = new Product("Lechuga", Products.VEGETABLE, 15, 2000, 150, VegetableTypes.LETTUCE, VegetableBrands.DLP, FoodGroup.VEGETABLE);
        Product celery = new Product("Apio", Products.VEGETABLE, 0, 700, 50, VegetableTypes.CELERY, VegetableBrands.DLP, FoodGroup.VEGETABLE);
        Product spinach = new Product("Espinaca", Products.VEGETABLE, 15, 600, 50, VegetableTypes.SPINACH, VegetableBrands.DLP, FoodGroup.VEGETABLE);

        Product watermelon = new Product("Sandía", Products.FRUIT, 70, 5000, 1000, FruitType.WATERMELON, VegetableBrands.DLP, FoodGroup.FRUIT);
        Product banana = new Product("Banana", Products.FRUIT, 70, 600, 200, FruitType.BANANA, VegetableBrands.DLP, FoodGroup.FRUIT);
        Product mango = new Product("Mango", Products.FRUIT, 100, 1200, 1000, FruitType.MANGO, VegetableBrands.DLP, FoodGroup.FRUIT);

        Product chocolateKiss = new Product("Beso de Chocolate", Products.CANDY, 200, 200, 4, CandyType.CHOCOLATE, CandyBrands.FERRERO, FoodGroup.CANDY);
        Product bonbonBum = new Product("BonBonBum", Products.CANDY, 200, 200, 10, CandyType.CARAMEL, CandyBrands.COLOMBINA, FoodGroup.CANDY);
        Product buterscotch = new Product("Dulce de Leche", Products.CANDY, 400, 3000, 100, CandyType.BUTTERSCOTCH, CandyBrands.COLOMBINA, FoodGroup.CANDY);

        Product beans = new Product("Frijoles", Products.GRAIN, 100, 2000, 500, GrainType.BEAN, VegetableBrands.DLP, FoodGroup.GRAIN);
        Product lentils = new Product("Lentejas", Products.GRAIN, 100, 2000, 500, GrainType.LENTIL, VegetableBrands.DLP, FoodGroup.GRAIN);
        Product peas = new Product("Arvejas", Products.GRAIN, 100, 2000, 500, GrainType.PEA, VegetableBrands.DLP, FoodGroup.GRAIN);

        saveProducts();
    }




}


