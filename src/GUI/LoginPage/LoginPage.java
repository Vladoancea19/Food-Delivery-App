package GUI.LoginPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import DbPersistence.Service.RestaurantService.RestaurantService;
import DbPersistence.Service.RestaurantService.RestaurantServiceImp;
import DbPersistence.Service.UserService.UserService;
import DbPersistence.Service.UserService.UserServiceImp;
import GUI.RegistrationPage.RegistrationPage;
import Restaurant.Restaurant;
import GUI.RestaurantPage.RestaurantPage;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.List;

public class LoginPage extends JFrame implements ActionListener {

    private final Container container;
    private final JLabel pageTitle;
    private final JLabel emailLabel;
    private final JFormattedTextField emailValue;
    private final JLabel passwordLabel;
    private final JPasswordField passwordValue;
    private final JButton loginButton;
    private final JLabel registerLabel;
    private final JButton registerButton;
    private final AuditService auditService = new AuditServiceImp();

    public LoginPage()
    {
        //Parametrii frame
        setTitle("HappyFood");
        setBounds(550, 100, 400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        //Titlul paginii
        pageTitle = new JLabel("Conecteaza-te");
        pageTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        pageTitle.setSize(300, 60);
        pageTitle.setLocation(95, 30);
        container.add(pageTitle);

        //Formular logare
        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabel.setSize(100, 20);
        emailLabel.setLocation(45, 170);
        container.add(emailLabel);

        emailValue = new JFormattedTextField();
        emailValue.setFont(new Font("Arial", Font.PLAIN, 15));
        emailValue.setSize(150, 18);
        emailValue.setLocation(175, 170);
        container.add(emailValue);

        passwordLabel = new JLabel("Parola");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(45, 220);
        container.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordValue.setSize(150, 18);
        passwordValue.setLocation(175, 220);
        container.add(passwordValue);

        //Buton logare
        loginButton = new JButton("Conectare");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setSize(150, 40);
        loginButton.setLocation(115, 300);
        loginButton.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            List<User> users = userService.read();
            User validatedUser = new User();
            RestaurantService restaurantService = new RestaurantServiceImp();
            List<Restaurant> restaurants = restaurantService.read();
            boolean accountVerification = false;
            for(User userIterator : users) {
                if(userIterator.getUserEmail().compareTo(emailValue.getText()) == 0 && userIterator.getUserPassword().compareTo(passwordValue.getText()) == 0) {
                    accountVerification = true;
                    validatedUser = userIterator;
                }
            }
            if(accountVerification) {
                new RestaurantPage(restaurants, validatedUser);
                setVisible(false);

                //Serviciu audit
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Audit audit = new Audit("Logare: " + validatedUser.getUserEmail(), timestamp);
                auditService.create(audit);
            }
        });
        container.add(loginButton);

        //Buton inregistrare
        registerLabel = new JLabel("Nu ai cont?");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        registerLabel.setSize(200, 20);
        registerLabel.setLocation(45, 420);
        container.add(registerLabel);

        registerButton = new JButton("Inregistreaza-te acum!");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 15));
        registerButton.setSize(200, 20);
        registerButton.setLocation(120, 420);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e-> {
            new RegistrationPage();
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Cerere formular de inregistrare", timestamp);
            auditService.create(audit);
        });
        container.add(registerButton);

        //Vizualizare componente grafice
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}

