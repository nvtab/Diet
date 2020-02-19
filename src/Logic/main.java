package Logic;


import UI.MainFrame;


public class main {

    public static MainFrame mainFrame;

    public static void main(String[] args){

        ProductManager.productManager.loadProducts();
        if (ProductManager.productManager.createdProducts.isEmpty()) ProductManager.productManager.creatingProducts();

        mainFrame = new MainFrame();
    }
}
