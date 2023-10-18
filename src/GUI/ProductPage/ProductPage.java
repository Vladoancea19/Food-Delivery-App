package GUI.ProductPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import GUI.AccountPage.AccountPage;
import GUI.LoginPage.LoginPage;
import Restaurant.Category.Category;
import Restaurant.Category.Product.Product;
import Restaurant.Restaurant;
import GUI.CategoryPage.CategoryPage;
import GUI.ShoppingCartPage.ShoppingCartPage;
import User.User;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.awt.Color.white;

public class ProductPage extends JFrame implements ActionListener {

    private final JFrame frame;
    private final JMenuBar menuBar;
    private final JMenu accountMenu;
    private final JMenuItem accountSettings, logout;
    private JPanel mainPanel, mainProductPanel, productPanel;
    private final JScrollPane scrollPane;
    private JTextArea productTextArea1, productTextArea2;
    private JButton addProduct, goBack;
    private final AuditService auditService = new AuditServiceImp();

    public ProductPage(Category category, Restaurant restaurant, List<Restaurant> restaurants, User user, ShoppingCartPage scp) {

        //Parametrii frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Happy Food");
        frame.setBounds(550, 100, 400, 550);
        frame.setResizable(false);

        //Bara de meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(white);

        goBack = new JButton("<");
        goBack.setBackground(white);
        goBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        goBack.addActionListener(this);
        goBack.addActionListener(e -> {
            new CategoryPage(restaurant, restaurants, user, scp);
            frame.setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in pagina produse", timestamp);
            auditService.create(audit);
        });
        menuBar.add(goBack);

        JLabel blank = new JLabel("                                                                                    ");
        menuBar.add(blank);

        //Meniu cont
        accountMenu = new JMenu("        Cont        ");
        accountMenu.setSize(50,20);
        accountSettings = new JMenuItem("Setari Cont");
        accountSettings.addActionListener(e -> {
            new AccountPage(user, category, restaurant, restaurants, scp);
            frame.setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in setarile contului: " + user.getUserEmail(), timestamp);
            auditService.create(audit);
        });

        logout = new JMenuItem("Logout");
        logout.addActionListener(e -> {
            new LoginPage();
            frame.setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Logout: " + user.getUserEmail(), timestamp);
            auditService.create(audit);
        });

        accountMenu.add(accountSettings);
        accountMenu.add(logout);
        menuBar.add(accountMenu);

        //Panou principal
        mainPanel = new JPanel(new GridLayout());
        mainPanel.setLocation(0, 0);
        mainPanel.setBackground(white);

        //Panou principal produse
        mainProductPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(10,10,10,10);
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.gridwidth = 0;
        gbc1.gridx = 0;
        gbc1.gridy = 0;

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10,10,10,10);
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.gridx = 0;

        //Panouri produse
        Collections.sort(category.getProducts(), Comparator.comparing(Product::getName));

        for (Product product : category.getProducts()) {
            //Panou produse
            productPanel = new JPanel(new GridBagLayout());
            productPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            productPanel.setBackground(white);

            //Listare date produse
            productTextArea1 = new JTextArea(product.getName() + "\n\nIngrediente:\n" + product.getIngredients());
            productTextArea1.setFont(new Font("Arial", Font.PLAIN, 15));
            productTextArea1.setEditable(false);
            productTextArea1.setLineWrap(true);
            productTextArea1.setWrapStyleWord(true);
            productPanel.add(productTextArea1, gbc1);
            gbc2.gridy = gbc1.gridy+2;

            productTextArea2 = new JTextArea("\n" + product.getWeight() + "g" + "                             " + product.getPrice() + " lei");
            productTextArea2.setFont(new Font("Arial", Font.PLAIN, 15));
            productTextArea2.setEditable(false);
            productPanel.add(productTextArea2, gbc2);
            gbc2.gridx ++;

            //Buton adaugare produs in cosul de cumparaturi
            addProduct = new JButton("+");
            addProduct.setContentAreaFilled(false);
            addProduct.addActionListener(this);
            addProduct.addActionListener(e -> {
                scp.addProduct(product, 1);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit(user.getUserEmail() + " a adaugat " + product.getName() + " in cos", timestamp);
                auditService.create(audit);
            });
            productPanel.add(addProduct, gbc2);

            this.mainProductPanel.add(productPanel, gbc1);
            gbc1.gridy += 2;
        }

        //Panou scroll
        scrollPane = new JScrollPane(mainProductPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        mainPanel.add(scrollPane);

        //Vizualizare componente grafice
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
