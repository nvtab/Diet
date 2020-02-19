package Data;

import Data.Product_Types.Brands;
import Data.Product_Types.ProductType;
import Data.Product_Types.Products;
import Logic.ProductManager;

import java.io.Serializable;

import static Logic.ProductManager.productManager;

public class Product implements Serializable {
    public static final long serialVersionUID = 11451341234L;
    public String name;
    public Products product;
    public ProductType productType;
    public double caloriesPer100g;
    public double price;
    public double weight;
    public FoodGroup foodGroup;
    public Brands brand;


    public Product (String name, Products product, double caloriesPer100g, double price, double weight, ProductType productType, Brands brand, FoodGroup foodGroup){
        this.name = name;
        this.product = product;
        this.caloriesPer100g = caloriesPer100g;
        this.price = price;
        this.weight = weight;
        this.productType = productType;
        this.brand = brand;
        this.foodGroup = foodGroup;
        ProductManager.productManager.createdProducts.put(ProductManager.productManager.createdProducts.size()+1, this);
    }
}
