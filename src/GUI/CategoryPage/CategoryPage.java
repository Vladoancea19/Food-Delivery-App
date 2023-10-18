package GUI.CategoryPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import GUI.AccountPage.AccountPage;
import GUI.LoginPage.LoginPage;
import Restaurant.Category.Category;
import Restaurant.Restaurant;
import GUI.ProductPage.ProductPage;
import GUI.RestaurantPage.RestaurantPage;
import GUI.ShoppingCartPage.ShoppingCartPage;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import static java.awt.Color.white;

public class CategoryPage extends JFrame implements ActionListener {

    private final JFrame frame;
    private final JMenuBar menuBar;
    private final JMenu accountMenu;
    private final JMenuItem accountSettings, logout;
    private final JPanel mainPanel, restaurantPanel;
    private final JScrollPane scrollPane;
    private JButton categoryName, goBack;
    private final AuditService auditService = new AuditServiceImp();

    public CategoryPage(Restaurant restaurant, List<Restaurant> restaurants, User user, ShoppingCartPage scp) {

        //Parametrii frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Happy Food");
        frame.setBounds(550, 100, 400, 550);
        frame.setResizable(false);
        frame.setBackground(white);

        //Bara de meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(white);

        goBack = new JButton("<");
        goBack.setBackground(white);
        goBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        goBack.addActionListener(this);
        goBack.addActionListener(e -> {
            new RestaurantPage(restaurants, user);
            frame.setVisible(false);
            scp.getFrame().setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in pagina restaurante", timestamp);
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
            new AccountPage(user, restaurant, restaurants, scp);
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
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLocation(0, 0);
        mainPanel.setBackground(white);

        //Panou categorii
        restaurantPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 1;

//        Nume restaurant + scor + review-uri
//        JTextArea reviewTextArea = new JTextArea();
//        reviewTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
//        reviewTextArea.setEditable(false);
//        reviewTextArea.setLineWrap(true);
//        reviewTextArea.setWrapStyleWord(true);
//        reviewTextArea.setOpaque(false);
//        reviewTextArea.append(restaurant.getName() + "\n\nRating: " + restaurant.getScore() + "/10" + "\n\nReview-uri:\n");
//        for (Review review : restaurant.getReviews()) {
//            reviewTextArea.append(review.getReview() + "\n\n");
//        }
//        restaurantPanel.add(reviewTextArea, gbc);
//        gbc.gridy=2;
//        reviewTextArea.append("\n\n\n");

        //Butoane selectare categorie
        for (Category category : restaurant.getCategories()) {
            categoryName = new JButton(category.getName());
            categoryName.setFont(new Font("Arial", Font.PLAIN, 25));
            categoryName.setContentAreaFilled(false);
            categoryName.setBorderPainted(false);
            categoryName.addActionListener(this);
            categoryName.addActionListener(e -> {
                new ProductPage(category, restaurant, restaurants, user, scp);
                frame.setVisible(false);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit("Navigare in pagina: " + category.getName(), timestamp);
                auditService.create(audit);
            });
            restaurantPanel.add(categoryName, gbc);
            gbc.gridy+=2;
        }

        //Panou scroll
        scrollPane = new JScrollPane(restaurantPanel);
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