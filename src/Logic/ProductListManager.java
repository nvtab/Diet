package Logic;

import Data.Item;
import Data.ProductList;
import Data.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductListManager implements Serializable {
    public static final long serialVersionUID = 452621L;
    public HashMap<String, ProductList> createdList;
    public ArrayList<String> listsNames;
    public User user;

    public ProductListManager(User user){
        this.createdList = new HashMap<String, ProductList>();
        this.listsNames = new ArrayList<>();
        this.user = user;
    }

    public void createList(ProductList pl){
        if (createdList.get(pl) == null) {
            createdList.put(pl.name, pl);
            listsNames.add(pl.name);
            this.user.saveUser();
        }
        else System.out.println("The name is already used by another list");
    }

    public void deleteList (String listName){
        if(createdList.get(listName) != null) {
            createdList.remove(listName.toLowerCase());
            listsNames.remove(listName);
            this.user.saveUser();
        }
        else System.out.println("The list with that name doesn't exist");
    }

    public void addProduct(String listName, Item product){
        if(createdList.get(listName) != null) {
            createdList.get(listName).products.add(product);
            createdList.get(listName).setRecommendations();
            this.user.saveUser();
        }
        else System.out.println("The list with that name doesn't exist");
    }

    public void removeProduct (String listName, Item product){
        if(createdList.get(listName) != null) {
            createdList.get(listName).products.remove(product);
            createdList.get(listName).setRecommendations();
            this.user.saveUser();
        }
        else System.out.println("The list with that name doesn't exist");
    }

}
