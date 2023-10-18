package GUI.RegistrationPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import DbPersistence.Service.UserService.UserService;
import DbPersistence.Service.UserService.UserServiceImp;
import User.User;
import GUI.LoginPage.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

public class RegistrationPage extends JFrame implements ActionListener {

    private final Container container;
    private final JLabel pageTitle;
    private final JLabel nameSurnameLabel;
    private final JTextField nameSurnameValue;
    private final JLabel emailLabel;
    private final JFormattedTextField emailValue;
    private final JLabel passwordLabel;
    private final JPasswordField passwordValue;
    private final JLabel phoneLabel;
    private final JTextField phoneValue;
    private final JButton registerButton;
    private final JLabel loginText;
    private final JButton loginButton;
    private final AuditService auditService = new AuditServiceImp();

    public RegistrationPage() {
        //Parametrii frame
        setTitle("HappyFood");
        setBounds(550, 100, 400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        //Titlul paginii
        pageTitle = new JLabel("Creeaza Cont");
        pageTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        pageTitle.setSize(300, 60);
        pageTitle.setLocation(100, 30);
        container.add(pageTitle);

        //Formular inregistrare
        nameSurnameLabel = new JLabel("Nume si Prenume*");
        nameSurnameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameSurnameLabel.setSize(145, 20);
        nameSurnameLabel.setLocation(45, 145);
        container.add(nameSurnameLabel);

        nameSurnameValue = new JTextField();
        nameSurnameValue.setFont(new Font("Arial", Font.PLAIN, 15));
        nameSurnameValue.setSize(150, 18);
        nameSurnameValue.setLocation(190, 145);
        container.add(nameSurnameValue);

        emailLabel = new JLabel("Email*");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabel.setSize(100, 20);
        emailLabel.setLocation(45, 195);
        container.add(emailLabel);

        emailValue = new JFormattedTextField();
        emailValue.setFont(new Font("Arial", Font.PLAIN, 15));
        emailValue.setSize(150, 18);
        emailValue.setLocation(190, 195);
        container.add(emailValue);

        passwordLabel = new JLabel("Parola*");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(45, 245);
        container.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordValue.setSize(150, 18);
        passwordValue.setLocation(190, 245);
        container.add(passwordValue);

        phoneLabel = new JLabel("Telefon*");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneLabel.setSize(100, 20);
        phoneLabel.setLocation(45, 295);
        container.add(phoneLabel);

        phoneValue = new JTextField();
        phoneValue.setFont(new Font("Arial", Font.PLAIN, 15));
        phoneValue.setSize(150, 18);
        phoneValue.setLocation(190, 295);
        container.add(phoneValue);

        //Buton inregistrare
        registerButton = new JButton("Creeaza Cont");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 15));
        registerButton.setSize(150, 40);
        registerButton.setLocation(120, 365);
        registerButton.addActionListener(this);
        registerButton.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            List<User> users = userService.read();
            boolean emailVerification = false;
            for(User userIterator : users) {
                if(userIterator.getUserEmail().compareTo(emailValue.getText()) == 0) {
                    emailVerification = true;
                }
            }
            if(!phoneValue.getText().isEmpty() && !nameSurnameValue.getText().isEmpty() && !emailValue.getText().isEmpty() && !passwordValue.getText().isEmpty()) {
                if(phoneValue.getText().length() == 10) {
                    if(emailValue.getText().indexOf('@')!=-1 && emailValue.getText().indexOf('.')!=-1) {
                        if(!emailVerification) {
                            User user = new User(nameSurnameValue.getText(), emailValue.getText(), passwordValue.getText(), phoneValue.getText());
                            userService.create(user);
                            new LoginPage();
                            setVisible(false);

                            //Serviciu audit
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            Audit audit = new Audit("Inregistrare cont cu datele... " + "nume si prenume: " + nameSurnameValue.getText()
                                    + " email: " + emailValue.getText() + " parola: " + passwordValue.getText() + " telefon: " + phoneValue.getText(), timestamp);
                            auditService.create(audit);
                        }
                    }
                }
            }
        });
        container.add(registerButton);

        //Buton logare
        loginText = new JLabel("Ai deja cont?");
        loginText.setFont(new Font("Arial", Font.PLAIN, 15));
        loginText.setSize(200, 20);
        loginText.setLocation(45, 450);
        container.add(loginText);

        loginButton = new JButton("Conecteaza-te acum!");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setSize(200, 20);
        loginButton.setLocation(120, 450);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e-> {
            new LoginPage();
            setVisible(false);

            //Serviciu audit
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Audit audit = new Audit("Navigare in pagina de logare", timestamp);
            auditService.create(audit);
        });
        container.add(loginButton);

        //Vizualizare componente grafice
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
