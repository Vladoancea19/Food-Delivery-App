package GUI.RestaurantPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import GUI.AccountPage.AccountPage;
import GUI.LoginPage.LoginPage;
import Restaurant.Restaurant;
import GUI.CategoryPage.CategoryPage;
import GUI.ShoppingCartPage.ShoppingCartPage;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import static java.awt.Color.white;

public class RestaurantPage implements ActionListener {

    private final JFrame frame;
    private final JMenuBar menuBar;
    private final JMenu accountMenu;
    private final JMenuItem accountSettings, logout;
    private final JPanel mainPanel;
    private final JScrollPane scrollPane;
    private JButton restaurantName;
    private final List<Restaurant> restaurants;
    private final ShoppingCartPage scp = new ShoppingCartPage();
    private final AuditService auditService = new AuditServiceImp();

    public RestaurantPage(List<Restaurant> restaurants, User user) {
        this.restaurants = restaurants;

        //Parametrii frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Happy Food");
        frame.setBounds(550, 100, 400, 550);
        frame.setResizable(false);

        //Bara de meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(white);

        JButton blank1 = new JButton(" ");
        blank1.setBackground(white);
        blank1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        menuBar.add(blank1);

        JLabel blank2 = new JLabel("                                                                                     ");
        menuBar.add(blank2);

        //Meniu cont
        accountMenu = new JMenu("        Cont        ");
        accountMenu.setSize(50,20);
        accountSettings = new JMenuItem("Setari Cont");
        accountSettings.addActionListener(e -> {
            new AccountPage(user, restaurants);
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
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,0,10,0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;

        //Butoane selectare restaurant
        for (Restaurant restaurant : this.restaurants) {
            restaurantName = new JButton(restaurant.getName());
            restaurantName.setFont(new Font("Arial", Font.PLAIN, 25));
            restaurantName.setContentAreaFilled(false);
            restaurantName.setBorderPainted(false);
            restaurantName.addActionListener(this);
            restaurantName.addActionListener(e -> {
                new CategoryPage(restaurant, restaurants, user, scp);
                frame.setVisible(false);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit("Navigare in pagina: " + restaurant.getName(), timestamp);
                auditService.create(audit);
            });
            mainPanel.add(restaurantName, gbc);
            gbc.gridy += 2;
        }

        //Panou scroll
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        frame.add(scrollPane);

        //Vizualizare componente grafice
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
