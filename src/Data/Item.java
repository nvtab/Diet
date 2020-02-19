package Data;

import java.io.Serializable;

public class Item implements Serializable {
    public static final long serialVersionUID = 312465345341234L;
    public Product product;
    public int quantity;

    public Item (Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }
}
