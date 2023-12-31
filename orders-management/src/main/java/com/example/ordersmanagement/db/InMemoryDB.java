package com.example.ordersmanagement.db;

import com.example.ordersmanagement.account.Account;
import com.example.ordersmanagement.category.Category;
import com.example.ordersmanagement.notification.*;
import com.example.ordersmanagement.order.Order;
import com.example.ordersmanagement.product.Product;

import java.util.ArrayList;
//import javax.management.Notification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryDB implements DB {
    private static InMemoryDB instance;
    HashMap<Integer, HashMap<Integer, Order>> orders;
    private static int order_id = 1;
    protected HashMap<Integer, Notification> notifications = new HashMap<>();
    protected HashMap<Integer, NotificationTemplate> templates = new HashMap<>();
    protected NotificationTemplate orderedTemplate = new NotificationTemplate();
    protected NotificationTemplate shippedTemplate = new NotificationTemplate();
    protected NotificationTemplate cancelTemplate = new NotificationTemplate();
    public static Map<Integer, Account> accounts = new HashMap<Integer, Account>();
    // protected NotificationTemplate signUpTemplate = new NotificationTemplate();
    private static int notificationTemplateId = 1;
    private static int notificationId = 1;
    public static Map<Integer, Product> products = new HashMap<>();
    public static Map<Integer, Category> categories = new HashMap<>();

    private InMemoryDB() {
        orders = new HashMap<Integer, HashMap<Integer, Order>>();
        
        
        setOrderedTemplate(getNextNotificationTemplateId(), "ENG",
        new EmailNotifierDecorator(null));
        setShippedTemplate(getNextNotificationTemplateId(), "ENG",
        new EmailNotifierDecorator(new SMSNotifierDecorator(null)));
        setCancelTemplate(getNextNotificationTemplateId(), "ENG",
        new EmailNotifierDecorator(new SMSNotifierDecorator(null)));;
        // setSignUpTemplate(getNextNotificationTemplateId(), "ENG",
        // new EmailNotifierDecorator(null));

        templates.put(orderedTemplate.getId(), orderedTemplate);
        templates.put(shippedTemplate.getId(), shippedTemplate);
        templates.put(cancelTemplate.getId(), cancelTemplate);
        // templates.put(signUpTemplate.getId(), signUpTemplate);

        Category electronicsCategory = new Category(1, "Electronics");
        Category clothingCategory = new Category(2, "Clothing");
        Category sportsCategory = new Category(3, "Sports");
        Category foodCategory = new Category(4, "Food");

        categories.put(electronicsCategory.getId(), electronicsCategory);
        categories.put(clothingCategory.getId(), clothingCategory);
        categories.put(sportsCategory.getId(), sportsCategory);
        categories.put(foodCategory.getId(), foodCategory);

        // Adding sports products
        for (int i = 1; i <= 5; i++) {
            Product sportsProduct = new Product(i, "Sports Product " + i, categories.get(3), 1000 + i, "Vendor " + i, 19.99 * i, 50);
            products.put(sportsProduct.getId(), sportsProduct);

            // Linking products to categories
            categories.get(3).getProducts().add(sportsProduct);
        }

        // Adding food products
        for (int i = 6; i <= 10; i++) {
            Product foodProduct = new Product(i, "Food Product " + (i - 5), categories.get(4), 2000 + i, "Vendor " + i, 9.99 * (i - 5), 30);

            products.put(foodProduct.getId(), foodProduct);

            // Linking products to categories
            categories.get(4).getProducts().add(foodProduct);
        }
        
    }

    public static InMemoryDB getInstance() {
        if (instance == null) {
            instance = new InMemoryDB();
        }
        return instance;
    }

    public ArrayList<Order> getOrders(int customer_id) {
            if(orders.containsKey(customer_id)) {
                return new ArrayList<Order>(orders.get(customer_id).values());
            }
            return new ArrayList<Order>();
    }

    public String createOrder(int customer_id, Order order) {
        if (orders.containsKey(customer_id)) {
            orders.get(customer_id).put(order.getId(), order);
        } else {
            HashMap<Integer, Order> customer_orders = new HashMap<Integer, Order>();
            customer_orders.put(order.getId(), order);
            orders.put(customer_id, customer_orders);
        }
        return  "Created order " + order.getId() + " for customer " + customer_id + ".";
    }

    public Order getOrder(int customer_id, int order_id) {
        return orders.get(customer_id).get(order_id);
    }

    public int getNextOrderId() {
        return order_id++;
    }

    public void updateOrder(int customer_id, int order_id, Order order) {
        orders.get(customer_id).put(order_id, order);
    }

    
    public NotificationTemplate getOrderedTemplate() {
        return orderedTemplate;
    }
    public NotificationTemplate getShippedTemplate() {
        return shippedTemplate;
    }
    public NotificationTemplate getCancelTemplate() {
        return cancelTemplate;
    }
    // public NotificationTemplate getSignUpTemplate() {
    //     return signUpTemplate;
    // }

    public List<Notification> getNotifications() {
        if(!notifications.isEmpty()){
            return new ArrayList<>(notifications.values());   
        }
        else{
            return new ArrayList<Notification>();
        }
    }

    public void setNotifications(HashMap<Integer, Notification> notifications) {
        this.notifications = notifications;
    }

    public void setOrderedTemplate(int id , String Language, Notifier availableChannels) {
        this.orderedTemplate.setId(id);
        this.orderedTemplate.setType(NotificationType.PLACED);
        this.orderedTemplate.setSubject("Order Placed");
        this.orderedTemplate.setContent("Dear %s , your order %s has been placed successfully");
        this.orderedTemplate.setLanguage(Language);
        this.orderedTemplate.setAvailableChannels(availableChannels);
        // this.orderedTemplate.setPlaceholders(placeholders);
        // for(int i = 0 ; i < placeholders.size() ; i++){
        //     orderedTemplate.getContent().replace("%s",placeholders.get(i));
        // }
    }
    public void setShippedTemplate(int id , String Language, Notifier availableChannels) {
        this.shippedTemplate.setId(id);
        this.shippedTemplate.setType(NotificationType.SHIPPED);
        this.shippedTemplate.setSubject("Order Shipped");
        this.shippedTemplate.setContent("Dear %s , your order %s has been shipped successfully to this address %s");
        this.shippedTemplate.setLanguage(Language);
        this.shippedTemplate.setAvailableChannels(availableChannels);
    }

    public void setCancelTemplate(int id , String Language, Notifier availableChannels) {
        this.cancelTemplate.setId(id);
        this.cancelTemplate.setType(NotificationType.CANCELLED);
        this.cancelTemplate.setSubject("Order Cancelled");
        this.cancelTemplate.setContent("Dear %s , your order %s has been cancelled successfully");
        this.cancelTemplate.setLanguage(Language);
        this.cancelTemplate.setAvailableChannels(availableChannels);
    }

    // public void setSignUpTemplate(int id , String Language, Notifier availableChannels) {
    //     this.signUpTemplate.setId(id);
    //     this.signUpTemplate.setSubject("Sign Up");
    //     this.signUpTemplate.setContent("Dear %s , you have signed up successfully");
    //     this.signUpTemplate.setLanguage(Language);
    //     this.signUpTemplate.setAvailableChannels(availableChannels);
    // }

    private int getNextNotificationTemplateId(){
        return notificationTemplateId++;
    }

    public int getNextNotificationId(){
        return notificationId++;
    }

    public void addNotification(Notification notification) {
        notifications.put(getNextNotificationId(), notification);
    }

    public Account getAccount(int id) {
        return accounts.get(id);
    }

    public void addAccount(int id, Account account) {
        accounts.put(id, account);
    }

    public ArrayList<Account> getAccounts() {
        if(!accounts.isEmpty()){
            return new ArrayList<>(accounts.values());   
        }
        else{
            return new ArrayList<Account>();
        }
    }

    public Set<Integer> getAccountIds() {
        return accounts.keySet();
    }

    public Boolean containsKey(int id) {
        return accounts.containsKey(id);
    }

    public Category getCategory(int id) {
        return categories.get(id);
    }

    public Set<Integer> getCategoryIds() {
        return categories.keySet();
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public Set<Integer> getProductIds() {
        return products.keySet();
    }
}