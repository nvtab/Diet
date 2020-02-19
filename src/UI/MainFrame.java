package UI;

import Data.*;
import Data.Product_Types.Products;
import Logic.ProductManager;
import Logic.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel newUserPanel;
    private JPanel loadUserPanel;
    private JPanel userPanel;
    private JPanel newListPanel;
    private JPanel listDisplay;
    private JPanel addProductPanel;

    private JComboBox selectProduct;
    boolean hasProductTypes = false;


    public MainFrame() {
        this.setTitle("Diet");
        this.setIconImage(new ImageIcon("leaf.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setResizable(false);
        this.setVisible(true);
        initMainPanel();
        showMainPanel();
    }
    
    private void initMainPanel(){
        mainPanel = new JPanel();

        JLabel diet = new JLabel("Diet");
        JButton newUserButton = new JButton ("Nuevo Usuario");
        JButton loadUserButton = new JButton ("Cargar Usuario");
        mainPanel.add(diet);
        mainPanel.add(newUserButton);
        mainPanel.add(loadUserButton);

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initNewUserPanel();
                remove(mainPanel);
                showNewUserPanel();
            }
        });

       loadUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initLoadUserPanel();
                remove(mainPanel);
                showLoadUserPanel();
            }
        });
    }

    private void initNewUserPanel(){
        newUserPanel = new JPanel();

        newUserPanel.add(new JLabel("Nombre de Usuario"));
        JFormattedTextField username = new JFormattedTextField();
        username.setColumns(20);
        newUserPanel.add(username);
        newUserPanel.add(new JLabel("Edad"));
        JFormattedTextField age = new JFormattedTextField();
        age.setColumns(5);
        newUserPanel.add(age);
        newUserPanel.add(new JLabel("Altura (Centimetros)"));
        JFormattedTextField height = new JFormattedTextField();
        height.setColumns(5);
        newUserPanel.add(height);
        newUserPanel.add(new JLabel("Peso (Kilogramos)"));
        JFormattedTextField weight = new JFormattedTextField();
        weight.setColumns(5);
        newUserPanel.add(weight);
        JButton createUser = new JButton("Crear Usuario");
        newUserPanel.add(createUser);
        JButton back = new JButton("Retroceder");
        newUserPanel.add(back);

        createUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!(username.getText().equals("") || User.usernames.contains(username.getText()))) {
                        User u = new User(username.getText(), Integer.parseInt(age.getText()), Integer.parseInt(height.getText()), Integer.parseInt(weight.getText()));
                        remove(newUserPanel);
                        showMainPanel();
                    }
                    else JOptionPane.showMessageDialog(main.mainFrame, "Usuario ya existente o nombre inválido");
                } catch (NumberFormatException n){
                    JOptionPane.showMessageDialog(main.mainFrame, "Valores invalidos");
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(newUserPanel);
                showMainPanel();
            }
        });
    }

    private void initLoadUserPanel(){
        loadUserPanel = new JPanel();

        JLabel selectUser = new JLabel("Seleccione el usuario");
        User.loadUsers();
        JComboBox userSelector = new JComboBox(User.usernames.toArray());
        userSelector.setSize(20, 20);
        userSelector.setEditable(false);
        JButton submit = new JButton ("Seleccionar");
        JButton back = new JButton("Retroceder");
        loadUserPanel.add(selectUser);
        loadUserPanel.add(userSelector);
        loadUserPanel.add(submit);
        loadUserPanel.add(back);

       submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userSelector.getSelectedItem() != null) {
                    remove(loadUserPanel);
                    initUserPanel(User.createdUsers.get(userSelector.getSelectedItem()));
                    showUserPanel();
                }
                else JOptionPane.showMessageDialog(main.mainFrame, "Selecciona un usuario");
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(loadUserPanel);
                showMainPanel();
            }
        });
    }

    private void initUserPanel(User u){
        userPanel = new JPanel();

        JLabel username = new JLabel(u.username);
        JLabel age = new JLabel("Edad: " + u.age);
        JLabel weight = new JLabel("Peso: " + u.weight);
        JLabel height = new JLabel("Estatura: " + u.height);
        JComboBox lists = new JComboBox(u.plm.listsNames.toArray());
        JButton loadList = new JButton("Cargar lista de la compra");
        JButton newList = new JButton("Nueva lista de la compra");

        userPanel.add(username);
        userPanel.add(age);
        userPanel.add(weight);
        userPanel.add(height);
        userPanel.add(lists);
        userPanel.add(loadList);
        userPanel.add(newList);

        newList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(userPanel);
                initNewListPanel(u);
                showNewListPanel();
            }
        });

        loadList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lists.getSelectedItem() != null) {
                    remove(userPanel);
                    initListDisplayPanel(u, u.plm.createdList.get(lists.getSelectedItem()));
                    showListDisplayPanel();
                }
                else JOptionPane.showMessageDialog(main.mainFrame, "Selecciona una lista");
            }
        });

    }

    private void initNewListPanel (User u){
        newListPanel = new JPanel();

        JFormattedTextField nameInput = new JFormattedTextField();
        nameInput.setColumns(20);
        JComboBox periodicityInput = new JComboBox(Data.Periodicity.toStrings().toArray());
        JButton submit = new JButton("Crear lista");
        JButton back = new JButton("Retroceder");

        newListPanel.add(new JLabel("Nombre"));
        newListPanel.add(nameInput);
        newListPanel.add(new JLabel("Periodicidad"));
        newListPanel.add(periodicityInput);
        newListPanel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameInput.getText() != null) {
                    if (!u.plm.listsNames.contains(nameInput.getText())) {
                        Periodicity p = Periodicity.DAILY;
                        switch ((String) periodicityInput.getSelectedItem()) {
                            case "Diario":
                                p = Periodicity.DAILY;
                            case "Semanal":
                                p = Periodicity.WEEKLY;
                            case "Quincenal":
                                p = Periodicity.BIWEEKLY;
                            case "Mensual":
                                p = Periodicity.MONTHLY;
                        }
                        u.plm.createList(new ProductList(User.createdUsers.get(u.username), nameInput.getText(), p));
                        remove(newListPanel);
                        initUserPanel(u);
                        showUserPanel();
                    }
                }
                else JOptionPane.showMessageDialog(main.mainFrame, "Elige un nombre");
            }
        });
    }

    private void initListDisplayPanel(User u, ProductList list){
        listDisplay = new JPanel();

        JLabel name = new JLabel(list.name);
        JTable productList;
        JButton addProduct = new JButton ("Agregar producto");
        JButton deleteProduct = new JButton("Eliminar producto");

        listDisplay.add(name);
        if (!list.products.isEmpty()) {
            productList = new JTable(list.products.size()+1, 4);
            productList.setValueAt("Nombre", 0, 0);
            productList.setValueAt("Precio", 0, 1);
            productList.setValueAt("Marca", 0, 2);
            productList.setValueAt("Cantidad", 0, 3);
            int a = 1;
            for (Item p : list.products) {
                System.out.println(p.product.price);
                productList.setValueAt(p.product.name, a, 0);
                productList.setValueAt(p.product.price, a, 1);
                productList.setValueAt(p.product.brand.toBeautiful(), a, 2);
                productList.setValueAt(p.quantity, a, 3);
                a++;
            }

            listDisplay.add(productList);

            listDisplay.add(new JLabel("Precio total" + list.price));
            listDisplay.add(new JLabel("Calorias totales" + list.totalCalories));

            for (String r : list.recommendations) JOptionPane.showMessageDialog(main.mainFrame, r);

            deleteProduct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (productList.getSelectedRow() != 0) { u.plm.removeProduct(list.name, list.products.get(productList.getSelectedRow()-1));}
                    else JOptionPane.showMessageDialog(main.mainFrame, "Selecciona un producto");
                    remove(listDisplay);
                    initListDisplayPanel(u, list);
                    showListDisplayPanel();
                }
            });
        }
        listDisplay.add(addProduct);

        if (!list.products.isEmpty()){ listDisplay.add(deleteProduct); }

        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(listDisplay);
                initAddProduct(u, list);
                showAddProductPanel();
            }
        });
    }

    private void initAddProduct(User u, ProductList list){
        addProductPanel = new JPanel();

        addProductPanel.add(new JLabel("Agregar producto"));

        JComboBox productTypes = new JComboBox(Products.getValues());
        addProductPanel.add(productTypes);
        JButton showProducts = new JButton("Mostrar productos");
        addProductPanel.add(showProducts);
        JButton submit = new JButton("Agregar producto");
        JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));


        ArrayList<Product> milkProducts = new ArrayList<>();
        ArrayList<Product> eggProducts = new ArrayList<>();
        ArrayList<Product> cheeseProducts = new ArrayList<>();
        ArrayList<Product> cerealProducts = new ArrayList<>();
        ArrayList<Product> candyProducts = new ArrayList<>();
        ArrayList<Product> vegetableProducts = new ArrayList<>();
        ArrayList<Product> fruitProducts = new ArrayList<>();
        ArrayList<Product> grainProducts = new ArrayList<>();

        for (int a = 1; ProductManager.productManager.createdProducts.size() > a; a++){
            switch (ProductManager.productManager.createdProducts.get(a).product){
                case MILK: milkProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case EGGS: eggProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case CHEESE: cheeseProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case CEREAL: cerealProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case CANDY: candyProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case VEGETABLE: vegetableProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case FRUIT: fruitProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
                case GRAIN: grainProducts.add(ProductManager.productManager.createdProducts.get(a)); break;
            }
        }

        ArrayList<String> milkProductsNames = new ArrayList<>();
        for (Product p : milkProducts) milkProductsNames.add(p.name);
        ArrayList<String> eggProductsNames = new ArrayList<>();
        for (Product p : eggProducts) eggProductsNames.add(p.name);
        ArrayList<String> cheeseProductsNames = new ArrayList<>();
        for (Product p : cheeseProducts) cheeseProductsNames.add(p.name);
        ArrayList<String> cerealProductsNames = new ArrayList<>();
        for (Product p : cerealProducts) cerealProductsNames.add(p.name);
        ArrayList<String> candyProductsNames = new ArrayList<>();
        for (Product p : candyProducts) candyProductsNames.add(p.name);
        ArrayList<String> vegetableProductsNames = new ArrayList<>();
        for (Product p : vegetableProducts) vegetableProductsNames.add(p.name);
        ArrayList<String> fruitProductsNames = new ArrayList<>();
        for (Product p : fruitProducts) fruitProductsNames.add(p.name);
        ArrayList<String> grainProductsNames = new ArrayList<>();
        for (Product p : grainProducts) grainProductsNames.add(p.name);

        showProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch ((String) productTypes.getSelectedItem()) {
                    case "Leche": selectProduct = new JComboBox(milkProductsNames.toArray()); break;
                    case "Huevos": selectProduct = new JComboBox(eggProductsNames.toArray()); break;
                    case "Quesos": selectProduct = new JComboBox(cheeseProductsNames.toArray()); break;
                    case "Cereales": selectProduct = new JComboBox(cerealProductsNames.toArray()); break;
                    case "Dulces": selectProduct = new JComboBox(candyProductsNames.toArray()); break;
                    case "Vegetales": selectProduct = new JComboBox(vegetableProductsNames.toArray()); break;
                    case "Frutas": selectProduct = new JComboBox(fruitProductsNames.toArray()); break;
                    case "Granos": selectProduct = new JComboBox(grainProductsNames.toArray()); break;
                }
                if (hasProductTypes) addProductPanel.remove(3);
                setHasProductTypes(true);
                addProductPanel.add(selectProduct);
                addProductPanel.add(quantity);
                addProductPanel.add(submit);
                main.mainFrame.pack();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHasProductTypes(false);
                //boolean alreadyExist = false;
                /*while (list.products.iterator().hasNext()){

                    if (i.product.equals(ProductManager.productManage r.getProductByName((String)selectProduct.getSelectedItem()))) {
                        i.quantity += (int)quantity.getValue();
                        alreadyExist = true;
                    }
                }*/
                //if (!alreadyExist) {
                    u.plm.addProduct(list.name, new Item(ProductManager.productManager.getProductByName((String)selectProduct.getSelectedItem()), (int)quantity.getValue()));
                //}
                remove(addProductPanel);
                initListDisplayPanel(u, list);
                showListDisplayPanel();
            }
        });


    }

    private void showMainPanel(){
        this.add(this.mainPanel);
        this.pack();
    }

    private void showNewUserPanel(){
        this.add(this.newUserPanel);
        this.pack();
    }

    private void showLoadUserPanel(){
        this.add(this.loadUserPanel);
        this.pack();
    }

    private void showUserPanel(){
        this.add(userPanel);
        this.pack();
    }

    private void showNewListPanel(){
        this.add(newListPanel);
        this.pack();
    }

    private void showListDisplayPanel(){
        this.add(listDisplay);
        this.pack();
    }

    private void showAddProductPanel(){
        this.add(addProductPanel);
        this.pack();
    }




    private void setHasProductTypes (boolean b) {hasProductTypes = b;}
}
