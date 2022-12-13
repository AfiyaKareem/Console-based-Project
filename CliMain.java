package com.Hexaware.CMS.Cli;

import java.util.Scanner;

import javax.xml.transform.stream.StreamSource;

import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Vendor;
import com.Hexaware.CMS.Persistence.OrderDb;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

/**
 * CliMain used as Client interface for java coading.
 * @author hexware
 */
public class CliMain {
    
    static Scanner sc=new Scanner(System.in);
    static String c_id;
    static int vendor_id;
    static String c_password;
    // static int order_no;
/**
 * main method  used to display the option we had in the application.
 */
    public static void main( String[] args )
    {   
        while(true){
        System.out.println("\n*****************************************************************\n");   
        System.out.println( "Canteen Management System" );      
        System.out.println("Enter your choice from the given menu:");
        System.out.println("1.View Menu");
        System.out.println("2.Customer Login");
        System.out.println("3.Vendor Login");
        System.out.println("4.Exit");
        System.out.println("\n*****************************************************************\n");
        
        int choice=sc.nextInt();
        
        switch(choice){
            case 1:
                menuList();
                
                break;

            case 2:
                System.out.println("Press 1 - I am an Existing Customer\r\nPress 2 - I am a New Customer");
                int n=sc.nextInt();
                switch(n)
                {
                    case 1:customerProfile();
                    break;
                    case 2:newCustomerProfile();
                    break;
                }
               break;
            case 3:
                System.out.println("Press 1 - I am an Existing Vendor\r\nPress 2 - I am a New Vendor");
                int x = sc.nextInt();
                switch(x){
                    case 1: vendorProfile();
                    break;
                    case 2: newVendorProfile();
                    break;
                }
                

            case 4:
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose option 1 or 2");
        }
    }
}
    /**
     * this method  is to place food order.
     */
        public static void placeOrder(){
                int customer_id = OrderDb.fetchcust(c_id);
                int balance = OrderDb.fetchwal(customer_id);
                System.out.println("Your wallet balance = "+ balance);
                System.out.println("Enter the Food id");
                int food_id=sc.nextInt();
                int food_price=OrderDb.fetchFp(food_id);
                System.out.println("Enter the Food Quantity");
                int  quantity=sc.nextInt();
                vendor_id = OrderDb.fetchVen(food_id);
                System.out.println("Vendor ID: "+vendor_id);
                OrderFactory.orderdetails(vendor_id,customer_id,food_id,quantity,"ordered");
                
                
                }

/**
 * this method is to fetch Menu list.
 */
        public static void menuList(){
        Menu m[]=OrderFactory.fetchMenu();
        System.out.println("Food Id"+"    "+"Food Name"+"    "+"Food Price"+"    "+"Vendor ID"); 
        for(int i=0;i<m.length;i++){
              System.out.println(m[i].getFood_id()+"       "+m[i].getFood_name()+"       "+m[i].getFood_price()+"       "+m[i].getVendor_id());
           }
    }

    // login as customer 
public static void customerProfile(){
    Customer [] custArray = OrderFactory.customerProfile();

    System.out.println("Enter customer login ID: ");
    String c_id = sc.next();
    System.out.println("Enter Your Password: ");
    String c_password = sc.next();
    Customer cus = OrderFactory.validateCustomer(c_id, c_password);
    if(cus == null){
        System.out.println("invalid Customer details");
        customerProfile();
    }
    else{
        System.out.println("Logged in!");
        System.out.println("************************************************************");
        int customer_id = OrderDb.fetchcust(c_id);
        Customer[] c = OrderFactory.customerDb(cus.getCustomer_id());
        // for( Customer or : c)
        //       System.out.println(or);
        System.out.println("************************************************************");
        System.out.println("Canteen Management System");
        System.out.println("Press 1 to view menu and place order");
        System.out.println("Press 2 to view your order history");
        System.out.println("Press 3 to change your password");
        System.out.println("Press 4 to Cancel order");
        System.out.println("Press 5 to exit");
        System.out.println("************************************************************");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
            menuList();
            System.out.println("************************************************************");
            placeOrder();
            break;

            case 2:
            // customer order history 
            OrderDetails[] ordArr = OrderFactory.customerOrderHistory(cus.getCustomer_id());
            //   for( OrderDetails or : ordArr)
            //       System.out.println(or);
            System.out.println("Order Number"+"       "+"Vendor ID"+"       "+"Customer ID"+"       "+"Food ID"+"       "+"Quantity"+"       "+"Order Date"+"       "+"Order Value"+"       "+"Order Status");
            for(int i=0; i<ordArr.length;i++){
                System.out.println(ordArr[i].getOrder_no()+"                   "+ordArr[i].getVendor_id()+"            "+ordArr[i].getCustomer_id()+"                  "+ordArr[i].getFood_id()+"           "+ordArr[i].getQuantity()+"           "+ordArr[i].getDateandtime()+"         "+ordArr[i].getOrder_value()+"              "+ordArr[i].getOrder_status()+"          ");
            }
                  System.out.println("\n***************************************************************\n");
              break;

            case 3:
            // to change password 
            changeCustomerPassword();
            break;

            case 4:
            // cancel order 
            OrderDetails[] pendingOrdersArr = OrderFactory.pendingOrderDetails(cus.getCustomer_id());
            System.out.println("Order Number"+"       "+"Vendor ID"+"       "+"Customer ID"+"       "+"Food ID"+"       "+"Quantity"+"       "+"Order Date"+"       "+"Order Value"+"       "+"Order Status");
            for(int i=0; i<pendingOrdersArr.length;i++){
                System.out.println(pendingOrdersArr[i].getOrder_no()+"                   "+pendingOrdersArr[i].getVendor_id()+"            "+pendingOrdersArr[i].getCustomer_id()+"                  "+pendingOrdersArr[i].getFood_id()+"           "+pendingOrdersArr[i].getQuantity()+"           "+pendingOrdersArr[i].getDateandtime()+"         "+pendingOrdersArr[i].getOrder_value()+"              "+pendingOrdersArr[i].getOrder_status()+"          ");
            }

                    System.out.print("Enter the order number you want to cancel : ");
                    int order_no = sc.nextInt();
                    OrderDetails ordD = OrderFactory.validatePendingOrder(order_no);
                    if (ordD != null) {
                        OrderFactory.cancelOrder(order_no);
                    } else {
                        System.out.println("Not Valaid Order Id");
                    }
                    System.out.println("\nOrder cancelled");
                    System.out.println("\n");
                    break;
            case 5:
            Runtime.getRuntime().halt(0);
        }

    }

}

/**
 * this method is for VendorProfile.
 */
public static void vendorProfile(){
    System.out.println("\n***************************************************************\n");
     Vendor[] venArray = OrderFactory.vendorProfile();
     for(Vendor v : venArray){
          System.out.println(v);
     }
     System.out.println("\n***************************************************************\n");
     System.out.println("Enter Vendor Id");
     vendor_id = sc.nextInt();
     Vendor ven = OrderFactory.validateVendor(vendor_id);
     if( ven == null){
         System.out.println("Invalid vendor id");
         vendorProfile();
     }
     else{
        System.out.println("\n***************************************************************\n");
         System.out.println("Press 1 - Accept Or Reject Order \r\n"+
         "Press 2 - See Vendor Order History\r\nPress 3 - To add new Food item to Menu\r\n" + 
         "Press 4 - Exit");
         System.out.println("\n***************************************************************\n");
         int choice = sc.nextInt();
         switch(choice){
             case 1:
            //  accept or reject order 
               System.out.println(ven);
            System.out.println("\n***************************************************************\n");
               OrderDetails[] od = OrderFactory.vendorOrderPending(ven.getVendor_id());
            System.out.println("Order Number"+"       "+"Vendor ID"+"       "+"Customer ID"+"       "+"Food ID"+"       "+"Quantity"+"       "+"Order Date"+"       "+"Order Value"+"       "+"Order Status");
            for(int i=0; i<od.length;i++){
                System.out.println(od[i].getOrder_no()+"                   "+od[i].getVendor_id()+"            "+od[i].getCustomer_id()+"                  "+od[i].getFood_id()+"           "+od[i].getQuantity()+"           "+od[i].getDateandtime()+"         "+od[i].getOrder_value()+"              "+od[i].getOrder_status()+"          ");
            }
                  System.out.println("\n***************************************************************\n");
                  System.out.print("Enter the order number you want to ACCEPT/REJECT : ");
                  int order_no = sc.nextInt();
               System.out.println("ENTER ACCEPTED OR REJECTED");
               String s = sc.next();
               OrderFactory.acceptRejectOrder(ven.getVendor_id(),s, order_no);
            
               break;
             case 2:
            //  vendor order history 
             System.out.println("\n***************************************************************\n");
               OrderDetails[] odArr = OrderFactory.vendorOrderHistory(ven.getVendor_id());
            System.out.println("Order Number"+"       "+"Vendor ID"+"       "+"Customer ID"+"       "+"Food ID"+"       "+"Quantity"+"       "+"Order Date"+"       "+"Order Value"+"       "+"Order Status");
            for(int i=0; i<odArr.length;i++){
                System.out.println(odArr[i].getOrder_no()+"                   "+odArr[i].getVendor_id()+"            "+odArr[i].getCustomer_id()+"                  "+odArr[i].getFood_id()+"           "+odArr[i].getQuantity()+"           "+odArr[i].getDateandtime()+"         "+odArr[i].getOrder_value()+"              "+odArr[i].getOrder_status()+"          ");
            }
                  System.out.println("\n***************************************************************\n");
               break;
             case 3:
                newFoodItem();
                System.out.println("\n***************************************************************\n");break;
             case 4:
                Runtime.getRuntime().halt(0);
                break;
             default:
                System.out.println("Invalid choice");
         
     }
    }
}


// method for new customer profile 
public static void newCustomerProfile(){
    System.out.println("Enter Customer ID: ");
    int customer_id = sc.nextInt();
    System.out.println("Enter customer name: ");
    String customer_name = sc.next();
    System.out.println("Enter customer phone number: ");
    int customer_phone = sc.nextInt();
    System.out.println("Enter customer e-mail id: ");
    String customer_email = sc.next();
    System.out.println("Enter coupon code: ");
    String customer_coupon = sc.next();
    System.out.println("Enter your wallet balance: ");
    int customer_walletbal = sc.nextInt();
    System.out.println("Enter your login id: ");
    String c_id = sc.next();
    System.out.println("Enter login password: ");
    String c_password = sc.next();

    OrderDb.insertnewCustomer(customer_id,customer_name,customer_phone,customer_email,customer_coupon,customer_walletbal,c_id,c_password);
    System.out.println("New customer profile created!!");
}

// method for new vendor profile 
public static void newVendorProfile(){
    System.out.println("Enter vendor ID: ");
    int vendor_id = sc.nextInt();
    System.out.println("Enter vendor name: ");
    String vendor_name = sc.next();
    System.out.println("Enter vendor phone number: ");
    int vendor_phone = sc.nextInt();
    System.out.println("Enter vendor specs: ");
    String vendor_specs = sc.next();

    OrderDb.insertnewVendor(vendor_id, vendor_name, vendor_phone, vendor_specs);
}

// method for new food item 
public static void newFoodItem(){
    System.out.println("Enter food ID: ");
    int food_id = sc.nextInt();
    System.out.println("Enter food name: ");
    String food_name = sc.next();
    System.out.println("Enter food price: ");
    int food_price = sc.nextInt();
    System.out.println("Enter vendor ID: ");
    int vendor_id = sc.nextInt();

    OrderDb.insertFoodItem(food_id, food_name, food_price, vendor_id); 
}

// method to change customer password 
public static void changeCustomerPassword() {
    System.out.println("Enter the  customer Login id :");
    String c_id = sc.next();
    System.out.println("Enter the current password :");
    String c_password = sc.next();
    System.out.println("Enter the new password :");
    String c_newpass = sc.next();
    System.out.println("Password changed successfully.");
    Customer cs = OrderFactory.validateCustomer(c_id, c_password);
    if (cs == null) {
        System.out.println("Login Id or Password is Wrong");
    } else {
        OrderFactory.updatePassword(c_id, c_newpass);
    }
}

// 
}