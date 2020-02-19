package Data;

import java.io.Serializable;
import java.util.ArrayList;

import static Data.Periodicity.*;


public class ProductList implements Serializable {
    public static final long serialVersionUID = 2345452621L;
    public User user;
    public ArrayList<Item> products;
    public String name;
    public Periodicity periodicity;
    public double price;
    public ArrayList<String> recommendations;
    public double totalCalories = 0;

    public ProductList(User user, ArrayList<Item> products, String name, Periodicity periodicity) {
        this.user = user;
        this.products = products;
        this.name = name;
        this.periodicity = periodicity;
        setPrice();
        setTotalCalories();
        this.recommendations = new ArrayList<String>();
        setPyramidalProportions();
        setCaloriesRecommendations();
    }

    public ProductList (User user, String name, Periodicity periodicity){
        this.user = user;
        this.name = name;
        this.products = new ArrayList<Item>();
        this.periodicity = periodicity;
        setPrice();
        setTotalCalories();
        this.recommendations = new ArrayList<String>();
        setRecommendations();
    }


    public void setPrice(){
        for (Item p : this.products){this.price+=p.product.price*p.quantity;}
    }

    public void setTotalCalories (){ for (Item p : this.products) {this.totalCalories+=p.product.caloriesPer100g*p.quantity;} }

    public void setPyramidalProportions (){
        int vegetableCount = 0;
        int fruitCount = 0;
        int grainCount = 0;
        int proteinCount = 0;
        int dairyCount = 0;
        int carbohydratesCount = 0;
        int candyCount = 0;

        for (Item p : this.products){
            switch (p.product.foodGroup){
                case VEGETABLE: vegetableCount+=p.quantity; break;
                case FRUIT: fruitCount+=p.quantity; break;
                case GRAIN: grainCount+=p.quantity; break;
                case PROTEIN: proteinCount+=p.quantity; break;
                case DAIRY: dairyCount+=p.quantity; break;
                case CARBOHYDRATES: carbohydratesCount+=p.quantity; break;
                case CANDY: candyCount+=p.quantity; break;
            }
        }

        int totalSum = vegetableCount+fruitCount+grainCount+proteinCount+dairyCount+carbohydratesCount+candyCount;

        if (totalSum * 0.45 < carbohydratesCount) {this.recommendations.add("No hay suficientes carbohidratos!");}
        if (totalSum * 0.3 < vegetableCount+fruitCount) {this.recommendations.add("No hay suficientes frutas y vegetales!");}
        if (totalSum * 0.1 < dairyCount) {this.recommendations.add("No hay suficientes lacteos!");}
        if (totalSum * 0.1 < proteinCount+grainCount) {this.recommendations.add("No hay suficientes proteinas y granos");}
        if (totalSum * 0.05 < carbohydratesCount) {this.recommendations.add("No hay suficientes dulces");}

        if (totalSum * 0.45 > carbohydratesCount) {this.recommendations.add("Demasiados carbohidratos!");}
        if (totalSum * 0.3 > vegetableCount+fruitCount) {this.recommendations.add("Demasiadas frutas y vegetales!");}
        if (totalSum * 0.1 > dairyCount) {this.recommendations.add("Demasiados lacteos!");}
        if (totalSum * 0.1 > proteinCount+grainCount) {this.recommendations.add("Demasiadas proteinas y granos!");}
        if (totalSum * 0.05 > carbohydratesCount) {this.recommendations.add("Demasiados dulces!");}
    }

    public void setCaloriesRecommendations () {

        if (totalCalories < 50000*user.weight && periodicity == DAILY) { recommendations.add("Tu consumo de calorías es demasiado bajo, perderas peso!");}
        if (totalCalories > 50000*user.weight && periodicity == DAILY) { recommendations.add("Tu consumo de calorias es demasiado alto, ganaras peso!");}

        if (totalCalories < 50000*user.weight*7&&periodicity == WEEKLY) { recommendations.add("Tu consumo de calorias es demasiado bajo, perderas peso!");}
        if (totalCalories > 50000*user.weight*7&&periodicity == WEEKLY) { recommendations.add("Tu consumo de calorias es demasiado alto, ganaras peso!");}

        if (totalCalories < 50000*user.weight*14&&periodicity == BIWEEKLY) { recommendations.add("Tu consumo de calorias es demasiado bajo, perderas peso!");}
        if (totalCalories > 50000*user.weight*14&&periodicity == BIWEEKLY) { recommendations.add("Tu consumo de calorias es demasiado alto, ganaras peso!");}

        if (totalCalories < 50000*user.weight*30&&periodicity == MONTHLY) { recommendations.add("Tu consumo de calorias es demasiado bajo, perderas peso!");}
        if (totalCalories > 50000*user.weight*30&&periodicity == MONTHLY) { recommendations.add("Tu consumo de calorias es demasiado alto, ganaras peso!");}

    }

    public void setRecommendations() {
        price = 0;
        setPrice();
        recommendations.clear();
        setCaloriesRecommendations();
    }
}
