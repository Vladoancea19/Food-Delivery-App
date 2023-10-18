package GUI.AccountPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import DbPersistence.Service.UserService.UserService;
import DbPersistence.Service.UserService.UserServiceImp;
import GUI.LoginPage.LoginPage;
import Restaurant.Category.Category;
import Restaurant.Restaurant;
import GUI.CategoryPage.CategoryPage;
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

public class AccountPage extends JFrame implements ActionListener {

    private final Container container;
    private final JLabel pageTitle;
    private final JMenuBar menuBar;
    private final JButton goBack;
    private final JLabel emailLabel, emailLabelValue;
    private final JLabel passwordLabel;
    private final JPasswordField passwordValue;
    private final JLabel phoneLabel, phoneLabelValue;
    private final JButton changePassword;
    private final JButton deleteAccount;
    private final AuditService auditService = new AuditServiceImp();

    public AccountPage (User user, List<Restaurant> restaurants) {
        //Parametrii frame
        setTitle("HappyFood");
        setBounds(550, 100, 400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        //Bara de meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(white);

        //Buton navigare pe pagina anterioara
        goBack = new JButton("<");
        goBack.setBackground(white);
        goBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        goBack.addActionListener(this);
        goBack.addActionListener(e -> {
            new RestaurantPage(restaurants, user);
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in pagina restaurante", timestamp);
            auditService.create(audit);
        });
        menuBar.add(goBack);

        //Titlul paginii
        pageTitle = new JLabel(user.getUserNameSurname());
        pageTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        pageTitle.setSize(300, 60);
        pageTitle.setLocation(45, 40);
        container.add(pageTitle);

        //Listare date utilizator cu posibilitate de a schimba parola contului
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabel.setSize(145, 20);
        emailLabel.setLocation(45, 145);
        container.add(emailLabel);

        emailLabelValue = new JLabel(user.getUserEmail());
        emailLabelValue.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabelValue.setSize(200, 18);
        emailLabelValue.setLocation(155, 145);
        container.add(emailLabelValue);

        passwordLabel = new JLabel("Parola:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(45, 195);
        container.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordValue.setSize(150, 18);
        passwordValue.setLocation(155, 195);
        container.add(passwordValue);

        phoneLabel = new JLabel("Telefon:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabel.setSize(100, 20);
        phoneLabel.setLocation(45, 245);
        container.add(phoneLabel);

        phoneLabelValue = new JLabel(user.getUserPhoneNumber());
        phoneLabelValue.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabelValue.setSize(150, 18);
        phoneLabelValue.setLocation(155, 245);
        container.add(phoneLabelValue);

        //Buton definitivare parola noua
        changePassword = new JButton("Modifica informatii");
        changePassword.setFont(new Font("Arial", Font.PLAIN, 15));
        changePassword.setSize(150, 40);
        changePassword.setLocation(120, 335);
        changePassword.addActionListener(this);
        changePassword.addActionListener(e -> {
            if(!passwordValue.toString().isEmpty()) {
                UserService userService = new UserServiceImp();
                userService.update(new User(passwordValue.toString()), user.getUserId());
                new LoginPage();
                setVisible(false);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit(user.getUserEmail() + " a modificat informatiile contului", timestamp);
                auditService.create(audit);
            }
        });
        container.add(changePassword);

        //Buton stergere cont
        deleteAccount = new JButton("Sterge contul");
        deleteAccount.setFont(new Font("Arial", Font.PLAIN, 15));
        deleteAccount.setSize(150, 40);
        deleteAccount.setLocation(120, 395);
        deleteAccount.addActionListener(this);
        deleteAccount.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            userService.delete(user.getUserId());
            new LoginPage();
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Contul: " + user.getUserEmail() + " a fost sters", timestamp);
            auditService.create(audit);
        });
        container.add(deleteAccount);

        //Vizualizare componente grafice
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public AccountPage (User user, Restaurant restaurant, List<Restaurant> restaurants, ShoppingCartPage scp) {
        //Parametrii frame
        setTitle("HappyFood");
        setBounds(850, 100, 400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        //Bara de meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(white);

        //Buton navigare pe pagina anterioara
        goBack = new JButton("<");
        goBack.setBackground(white);
        goBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        goBack.addActionListener(this);
        goBack.addActionListener(e -> {
            new CategoryPage(restaurant, restaurants, user, scp);
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in pagina categorii", timestamp);
            auditService.create(audit);
        });
        menuBar.add(goBack);

        //Titlul paginii
        pageTitle = new JLabel(user.getUserNameSurname());
        pageTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        pageTitle.setSize(300, 60);
        pageTitle.setLocation(45, 40);
        container.add(pageTitle);

        //Listare date utilizator cu posibilitate de a schimba parola contului
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabel.setSize(145, 20);
        emailLabel.setLocation(45, 145);
        container.add(emailLabel);

        emailLabelValue = new JLabel(user.getUserEmail());
        emailLabelValue.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabelValue.setSize(200, 18);
        emailLabelValue.setLocation(155, 145);
        container.add(emailLabelValue);

        passwordLabel = new JLabel("Parola:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(45, 195);
        container.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordValue.setSize(150, 18);
        passwordValue.setLocation(155, 195);
        container.add(passwordValue);

        phoneLabel = new JLabel("Telefon:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabel.setSize(100, 20);
        phoneLabel.setLocation(45, 245);
        container.add(phoneLabel);

        phoneLabelValue = new JLabel(user.getUserPhoneNumber());
        phoneLabelValue.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabelValue.setSize(150, 18);
        phoneLabelValue.setLocation(155, 245);
        container.add(phoneLabelValue);

        //Buton definitivare parola noua
        changePassword = new JButton("Modifica informatii");
        changePassword.setFont(new Font("Arial", Font.PLAIN, 15));
        changePassword.setSize(150, 40);
        changePassword.setLocation(120, 335);
        changePassword.addActionListener(this);
        changePassword.addActionListener(e -> {
            if(!passwordValue.toString().isEmpty()) {
                UserService userService = new UserServiceImp();
                userService.update(new User(passwordValue.toString()), user.getUserId());
                new LoginPage();
                setVisible(false);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit(user.getUserEmail() + " a modificat informatiile contului", timestamp);
                auditService.create(audit);
            }
        });
        container.add(changePassword);

        //Buton stergere cont
        deleteAccount = new JButton("Sterge contul");
        deleteAccount.setFont(new Font("Arial", Font.PLAIN, 15));
        deleteAccount.setSize(150, 40);
        deleteAccount.setLocation(120, 395);
        deleteAccount.addActionListener(this);
        deleteAccount.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            userService.delete(user.getUserId());
            new LoginPage();
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Contul: " + user.getUserEmail() + " a fost sters", timestamp);
            auditService.create(audit);
        });
        container.add(deleteAccount);

        //Vizualizare componente grafice
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public AccountPage (User user, Category category, Restaurant restaurant, List<Restaurant> restaurants,  ShoppingCartPage scp) {
        //Parametrii frame
        setTitle("HappyFood");
        setBounds(550, 100, 400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        //Bara de meniu
        menuBar = new JMenuBar();
        menuBar.setBackground(white);

        //Buton navigare pe pagina anterioara
        goBack = new JButton("<");
        goBack.setBackground(white);
        goBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        goBack.addActionListener(this);
        goBack.addActionListener(e -> {
            new ProductPage(category, restaurant, restaurants, user, scp);
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in pagina produse", timestamp);
            auditService.create(audit);
        });
        menuBar.add(goBack);

        //Titlul paginii
        pageTitle = new JLabel(user.getUserNameSurname());
        pageTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        pageTitle.setSize(300, 60);
        pageTitle.setLocation(45, 40);
        container.add(pageTitle);

        //Listare date utilizator cu posibilitate de a schimba parola contului
        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabel.setSize(145, 20);
        emailLabel.setLocation(45, 145);
        container.add(emailLabel);

        emailLabelValue = new JLabel(user.getUserEmail());
        emailLabelValue.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabelValue.setSize(200, 18);
        emailLabelValue.setLocation(155, 145);
        container.add(emailLabelValue);

        passwordLabel = new JLabel("Parola:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(45, 195);
        container.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordValue.setSize(150, 18);
        passwordValue.setLocation(155, 195);
        container.add(passwordValue);

        phoneLabel = new JLabel("Telefon:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabel.setSize(100, 20);
        phoneLabel.setLocation(45, 245);
        container.add(phoneLabel);

        phoneLabelValue = new JLabel(user.getUserPhoneNumber());
        phoneLabelValue.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabelValue.setSize(150, 18);
        phoneLabelValue.setLocation(155, 245);
        container.add(phoneLabelValue);

        //Buton definitivare parola noua
        changePassword = new JButton("Modifica informatii");
        changePassword.setFont(new Font("Arial", Font.PLAIN, 15));
        changePassword.setSize(150, 40);
        changePassword.setLocation(120, 335);
        changePassword.addActionListener(this);
        changePassword.addActionListener(e -> {
            if(!passwordValue.toString().isEmpty()) {
                UserService userService = new UserServiceImp();
                userService.update(new User(passwordValue.toString()), user.getUserId());
                new LoginPage();
                setVisible(false);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit(user.getUserEmail() + " a modificat informatiile contului", timestamp);
                auditService.create(audit);
            }
        });
        container.add(changePassword);

        //Buton stergere cont
        deleteAccount = new JButton("Sterge contul");
        deleteAccount.setFont(new Font("Arial", Font.PLAIN, 15));
        deleteAccount.setSize(150, 40);
        deleteAccount.setLocation(120, 395);
        deleteAccount.addActionListener(this);
        deleteAccount.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            userService.delete(user.getUserId());
            new LoginPage();
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Contul: " + user.getUserEmail() + " a fost sters", timestamp);
            auditService.create(audit);
        });
        container.add(deleteAccount);

        //Vizualizare componente grafice
        setJMenuBar(menuBar);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
