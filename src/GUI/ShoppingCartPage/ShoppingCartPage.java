package GUI.ShoppingCartPage;

import Audit.Audit;
import DbPersistence.Service.AuditService.AuditService;
import DbPersistence.Service.AuditService.AuditServiceImp;
import DbPersistence.Service.DeliveryOrderService.DeliveryOrderService;
import DbPersistence.Service.DeliveryOrderService.DeliveryOrderServiceImp;
import DbPersistence.Service.TakeAwayOrderService.TakeawayOrderService;
import DbPersistence.Service.TakeAwayOrderService.TakeawayOrderServiceImp;
import Restaurant.Category.Product.Product;
import User.Order.DeliveryOrder.DeliveryOrder;
import User.Order.PaymentMethod;
import User.Order.TakeawayOrder.TakeawayOrder;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.*;

import static java.awt.Color.white;

public class ShoppingCartPage extends JFrame implements ActionListener{
    private final JFrame frame;
    private final JPanel mainPanel, mainOrderPanel, orderPanel;
    private final GridBagConstraints gbc1, gbc2;
    private final JScrollPane scrollPane;
    private final JTextArea orderTextArea = new JTextArea();
    private final JPanel paymentMethod = new JPanel(), deliveryMethod = new JPanel();
    private final ButtonGroup orderGroup, paymentGroup;
    private final JRadioButton deliveryOrder, takeawayOrder, cash, card;
    boolean takeAwaySelected = false, deliverySelected = false, cashSelected = false, cardSelected = false;
    private final JTextField deliveryAddress = new JTextField();
    private final JButton placeOrder;
    private final Map<Product, Integer> productsOrdered = new HashMap<>();
    private float productsPrice;
    private double takeawayDiscount;
    private final int randomDeliveryPrice = (int)Math.floor(Math.random()*(30-10+1)+10);
    private List<Object> waitingList = new ArrayList<>();
    private boolean orderDelay = false;
    private final AuditService auditService = new AuditServiceImp();

    public ShoppingCartPage() {

        //Parametrii Frame
        frame = new JFrame();
        frame.setTitle("Happy Food");
        frame.setBounds(950, 100, 400, 550);
        frame.setResizable(false);

        //Panou principal
        mainPanel = new JPanel(new GridLayout());
        mainPanel.setLocation(0, 0);
        mainPanel.setBackground(white);

        //Panou principal comanda
        mainOrderPanel = new JPanel(new GridBagLayout());
        gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(10,10,10,10);
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.gridwidth = 0;
        gbc1.gridx = 0;
        gbc1.gridy = 0;

        gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10,10,10,10);
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.gridx = 0;

        //Panou comanda
        orderPanel = new JPanel(new GridBagLayout());
        orderPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        orderPanel.setBackground(white);

        //Butoane alegere metoda de plata
        paymentGroup = new ButtonGroup();

        cash = new JRadioButton("Plata cash");
        cash.setContentAreaFilled(false);
        cash.setBorderPainted(false);
        cash.addActionListener(this);
        cash.addActionListener(e -> {
            cashSelected = true;
            cardSelected = false;
        });

        card = new JRadioButton("Plata cu cardul  ");
        card.setContentAreaFilled(false);
        card.setBorderPainted(false);
        card.addActionListener(this);
        card.addActionListener(e -> {
            cardSelected = true;
            cashSelected = false;
        });

        paymentGroup.add(cash);
        paymentGroup.add(card);

        //Butoane alegere metoda de livrare
        orderGroup = new ButtonGroup();

        deliveryOrder = new JRadioButton("Livrare");
        deliveryOrder.setContentAreaFilled(false);
        deliveryOrder.setBorderPainted(false);
        deliveryOrder.addActionListener(this);
        deliveryOrder.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Evidenta buton selectat
                        deliverySelected = true;
                        takeAwaySelected = false;

                        //Parametrii text area comanda
                        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
                        orderTextArea.setEditable(false);
                        orderTextArea.setLineWrap(true);
                        orderTextArea.setWrapStyleWord(true);

                        if(!orderTextArea.getText().isEmpty()) {
                            orderTextArea.setText("");
                        }

                        //Afisare produse si cantitate
                        for(Map.Entry<Product, Integer> productMap : productsOrdered.entrySet()) {
                            orderTextArea.append(productMap.getKey().getName() + " x" + productMap.getValue() + "\n");
                        }
                        orderPanel.add(orderTextArea, gbc1);
                        gbc2.gridy = gbc1.gridy + 2;

                        //Afisare pret produse
                        orderTextArea.append("-------------------------------------------\n" + Math.round(productsPrice *100)/100.0 + " lei\n");

                        //Afisare pret livrare, pret total
                        orderTextArea.append("+" + randomDeliveryPrice + " lei (taxa livrare)\n" + "TOTAL: " + (Math.round((productsPrice +randomDeliveryPrice)*100)/100.0) + " lei");

                        //Butoane metoda de plata
                        paymentMethod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Metoda de plata:"));
                        paymentMethod.setBackground(white);
                        paymentMethod.add(cash);
                        paymentMethod.add(card);
                        orderPanel.add(paymentMethod, gbc2);
                        gbc2.gridy += 2;

                        //Butoane metoda de livrare
                        deliveryMethod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Metoda de livrare:"));
                        deliveryMethod.setBackground(white);
                        deliveryMethod.add(deliveryOrder);
                        deliveryMethod.add(takeawayOrder);
                        orderPanel.add(deliveryMethod, gbc2);
                        gbc2.gridy ++;

                        //Camp adresa de livrare
                        deliveryAddress.setBorder(BorderFactory.createTitledBorder("Adresa de livrare:"));
                        orderPanel.add(deliveryAddress, gbc2);
                        deliveryAddress.setVisible(true);
                        gbc2.gridy++;

                        //Buton plasare comanda
                        orderPanel.add(placeOrder, gbc2);

                        //Vizualizare componente grafice
                        frame.setVisible(true);
                    }
                });

        takeawayOrder = new JRadioButton("Ridicare personala");
        takeawayOrder.setContentAreaFilled(false);
        takeawayOrder.setBorderPainted(false);
        takeawayOrder.addActionListener(this);
        takeawayOrder.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Evidenta buton selectat
                        takeAwaySelected = true;
                        deliverySelected = false;

                        //Parametrii text area comanda
                        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
                        orderTextArea.setEditable(false);
                        orderTextArea.setLineWrap(true);
                        orderTextArea.setWrapStyleWord(true);

                        if(!orderTextArea.getText().isEmpty()) {
                            orderTextArea.setText("");
                        }

                        //Afisare produse si cantitate
                        for(Map.Entry<Product, Integer> productMap : productsOrdered.entrySet()) {
                            orderTextArea.append(productMap.getKey().getName() + " x" + productMap.getValue() + "\n");
                        }
                        orderPanel.add(orderTextArea, gbc1);
                        gbc2.gridy = gbc1.gridy+2;

                        //Afisare pret produse
                        orderTextArea.append("-------------------------------------------\n" + Math.round(productsPrice *100)/100.0 + " lei\n");

                        //Afisare discount takeaway, pret total
                        Calendar calendar = Calendar.getInstance();
                        if(calendar.get(Calendar.DAY_OF_WEEK) >= 2 && calendar.get(Calendar.DAY_OF_WEEK) <= 6) {
                            takeawayDiscount = 0.1;
                        }
                        else {
                            takeawayDiscount = 0.25;
                        }

                        orderTextArea.append("-" + takeawayDiscount *100 + "% (pentru ridicare personala)\n" + "TOTAL: " + (Math.round((productsPrice - takeawayDiscount * productsPrice)*100)/100.0) + " lei");

                        //Butoane metoda de plata
                        paymentMethod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Metoda de plata:"));
                        paymentMethod.setBackground(white);
                        paymentMethod.add(cash);
                        paymentMethod.add(card);
                        orderPanel.add(paymentMethod, gbc2);
                        gbc2.gridy += 2;

                        //Butoane metoda de livrare
                        deliveryMethod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Metoda de livrare:"));
                        deliveryMethod.setBackground(white);
                        deliveryMethod.add(deliveryOrder);
                        deliveryMethod.add(takeawayOrder);
                        orderPanel.add(deliveryMethod, gbc2);
                        gbc2.gridy ++;

                        //Camp adresa de livrare
                        deliveryAddress.setText("");
                        deliveryAddress.setVisible(false);

                        //Buton plasare comanda
                        orderPanel.add(placeOrder, gbc2);

                        //Vizualizare componente grafice
                        frame.setVisible(true);
                    }
                });

        orderGroup.add(deliveryOrder);
        orderGroup.add(takeawayOrder);

        //Buton plasare comanda
        placeOrder = new JButton("Plaseaza Comanda");
        placeOrder.setContentAreaFilled(false);
        placeOrder.addActionListener(this);
        placeOrder.addActionListener(e -> {
            if(!orderDelay) {
                if (takeAwaySelected) {
                    if (cashSelected) {
                        TakeawayOrder takeawayOrderToCreate = new TakeawayOrder(productsOrdered, PaymentMethod.CASH, (int) (takeawayDiscount * 100));
                        TakeawayOrderService takeAwayOrderService = new TakeawayOrderServiceImp();
                        takeAwayOrderService.create(takeawayOrderToCreate);
                        frame.setVisible(false);
                        productsOrdered.clear();
                        deliveryAddress.setText("");
                        paymentGroup.clearSelection();
                        orderGroup.clearSelection();

                        //Serviciu audit
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        Audit audit = new Audit("A fost plasata o comanda", timestamp);
                        auditService.create(audit);
                    } else if (cardSelected) {
                        TakeawayOrder takeawayOrderToCreate = new TakeawayOrder(productsOrdered, PaymentMethod.CARD, (int) (takeawayDiscount * 100));
                        TakeawayOrderService takeAwayOrderService = new TakeawayOrderServiceImp();
                        takeAwayOrderService.create(takeawayOrderToCreate);
                        frame.setVisible(false);
                        productsOrdered.clear();
                        deliveryAddress.setText("");
                        paymentGroup.clearSelection();
                        orderGroup.clearSelection();

                        //Serviciu audit
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        Audit audit = new Audit("A fost plasata o comanda", timestamp);
                        auditService.create(audit);
                    }
                } else if (deliverySelected) {
                    if (cashSelected) {
                        DeliveryOrder deliveryOrderToCreate = new DeliveryOrder(productsOrdered, PaymentMethod.CASH, randomDeliveryPrice, deliveryAddress.getText());
                        DeliveryOrderService deliveryOrderService = new DeliveryOrderServiceImp();
                        deliveryOrderService.create(deliveryOrderToCreate);
                        frame.setVisible(false);
                        productsOrdered.clear();
                        deliveryAddress.setText("");
                        paymentGroup.clearSelection();
                        orderGroup.clearSelection();

                        //Serviciu audit
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        Audit audit = new Audit("A fost plasata o comanda", timestamp);
                        auditService.create(audit);
                    } else if (cardSelected) {
                        DeliveryOrder deliveryOrderToCreate = new DeliveryOrder(productsOrdered, PaymentMethod.CARD, randomDeliveryPrice, deliveryAddress.getText());
                        DeliveryOrderService deliveryOrderService = new DeliveryOrderServiceImp();
                        deliveryOrderService.create(deliveryOrderToCreate);
                        frame.setVisible(false);
                        productsOrdered.clear();
                        deliveryAddress.setText("");
                        paymentGroup.clearSelection();
                        orderGroup.clearSelection();

                        //Serviciu audit
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        Audit audit = new Audit("A fost plasata o comanda", timestamp);
                        auditService.create(audit);
                    }
                }

                orderDelay = true;
                Timer timer1 = new Timer();
                TimerTask task1 = new TimerTask() {
                    @Override
                    public void run() {
                        if(waitingList.isEmpty()) {
                            orderDelay = false;
                        }
                        else {
                            if (waitingList.get(0) instanceof TakeawayOrder) {
                                TakeawayOrderService takeAwayOrderService = new TakeawayOrderServiceImp();
                                takeAwayOrderService.create((TakeawayOrder) waitingList.get(0));
                                waitingList.remove(0);

                                //Serviciu audit
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                Audit audit = new Audit("A fost plasata o comanda", timestamp);
                                auditService.create(audit);
                            } else {
                                DeliveryOrderService deliveryOrderService = new DeliveryOrderServiceImp();
                                deliveryOrderService.create((DeliveryOrder) waitingList.get(0));
                                waitingList.remove(0);

                                //Serviciu audit
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                Audit audit = new Audit("A fost plasata o comanda", timestamp);
                                auditService.create(audit);
                            }
                        }
                    }
                };
                timer1.scheduleAtFixedRate(task1, 15000, 15000);
            }
            else {
                if(waitingList.size() < 2) {
                    if (takeAwaySelected) {
                        if (cashSelected) {
                            TakeawayOrder takeawayOrderToCreate = new TakeawayOrder(productsOrdered, PaymentMethod.CASH, (int) (takeawayDiscount * 100));
                            waitingList.add(takeawayOrderToCreate);
                        } else if (cardSelected) {
                            TakeawayOrder takeawayOrderToCreate = new TakeawayOrder(productsOrdered, PaymentMethod.CARD, (int) (takeawayDiscount * 100));
                            waitingList.add(takeawayOrderToCreate);
                        }
                    } else if (deliverySelected) {
                        if (cashSelected) {
                            DeliveryOrder deliveryOrderToCreate = new DeliveryOrder(productsOrdered, PaymentMethod.CASH, randomDeliveryPrice, deliveryAddress.getText());
                            waitingList.add(deliveryOrderToCreate);
                        } else if (cardSelected) {
                            DeliveryOrder deliveryOrderToCreate = new DeliveryOrder(productsOrdered, PaymentMethod.CARD, randomDeliveryPrice, deliveryAddress.getText());
                            waitingList.add(deliveryOrderToCreate);
                        }
                    }
                }
            }
        });
        mainOrderPanel.add(orderPanel, gbc1);
        gbc1.gridy += 2;

        //Panou Scroll
        scrollPane = new JScrollPane(mainOrderPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        mainPanel.add(scrollPane);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    public void addProduct(Product product, Integer quantity) {
        //Adaugare produse in comanda
        boolean existingProduct = false;

        for(Map.Entry<Product, Integer> productMap : productsOrdered.entrySet()) {
            if(product.getName().compareTo(productMap.getKey().getName()) == 0) {
                productMap.setValue(productMap.getValue()+1);
                existingProduct = true;
            }
        }
        if(!existingProduct) {
            productsOrdered.put(product, quantity);
        }

        //Parametrii text area comanda
        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        orderTextArea.setEditable(false);
        orderTextArea.setLineWrap(true);
        orderTextArea.setWrapStyleWord(true);

        if(!orderTextArea.getText().isEmpty()) {
            orderTextArea.setText("");
        }

        orderPanel.add(orderTextArea, gbc1);
        gbc2.gridy = gbc1.gridy+2;

        //Afisare produse si cantitate, calculare pret produse
        productsPrice = 0;

        for(Map.Entry<Product, Integer> productMap : productsOrdered.entrySet()) {
            orderTextArea.append(productMap.getKey().getName() + " x" + productMap.getValue() + "\n");
            productsPrice += productMap.getKey().getPrice() * productMap.getValue();
        }

        //Afisare pret produse
        orderTextArea.append("--------------------------------------------\n" + Math.round(productsPrice *100)/100.0 + " lei\n");

        if(takeAwaySelected) {
            //Afisare discount takeaway, pret total
            Calendar calendar = Calendar.getInstance();
            if(calendar.get(Calendar.DAY_OF_WEEK) >= 2 && calendar.get(Calendar.DAY_OF_WEEK) <= 6) {
                takeawayDiscount = 0.1;
            }
            else {
                takeawayDiscount = 0.25;
            }

            orderTextArea.append("-" + takeawayDiscount *100 + "% (pentru ridicare personala)\n" + "TOTAL: " + (Math.round((productsPrice - takeawayDiscount * productsPrice)*100)/100.0) + " lei");
        }
        else if(deliverySelected) {
            //Afisare pret livrare, pret total
            orderTextArea.append("+" + randomDeliveryPrice + " lei (taxa livrare)\n" + "TOTAL: " + (Math.round((productsPrice +randomDeliveryPrice)*100)/100.0) + " lei");
        }

        //Butoane metoda de plata
        paymentMethod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Metoda de plata:"));
        paymentMethod.setBackground(white);
        paymentMethod.add(cash);
        paymentMethod.add(card);
        orderPanel.add(paymentMethod, gbc2);
        gbc2.gridy += 2;

        //Butoane metoda de livrare
        deliveryMethod.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Metoda de livrare:"));
        deliveryMethod.setBackground(white);
        deliveryMethod.add(deliveryOrder);
        deliveryMethod.add(takeawayOrder);
        orderPanel.add(deliveryMethod, gbc2);
        gbc2.gridy++;

        //Buton plasare comanda
        orderPanel.add(placeOrder, gbc2);

        //Vizualizare componente grafice
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
