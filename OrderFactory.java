package com.Hexaware.CMS.Factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;
import com.Hexaware.CMS.Persistence.OrderDb;

/**
 * OrderFactory class used to fetch and insert data to database.
 * @author hexware
 */
public class OrderFactory {
    
    public static int orderdetails(int vendor_id,int customer_id,int food_id,int quantity,String order_status){
       int food_price = OrderDb.fetchFp(food_id);
       int order_value=quantity*food_price;
       int result = 0;
       int  customer_walletbal= OrderDb.fetchwal(customer_id);
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       String dateandtime = dtf.format(now);
    //    String order_status;
       
       if(customer_walletbal>order_value){  
            customer_walletbal = customer_walletbal-order_value;
            // order_status = "ordered";
            result = OrderDb.insertDb(vendor_id,customer_id,food_id,quantity,dateandtime,order_value,order_status); 
            // System.out.println(order_status);  
            System.out.println("Order Placed!");
            updateCustomer(customer_walletbal,customer_id);
        }
        else{
            System.out.println("You don't have sufficient balance to place the order!!");
        }
       return result;
    }

    public static Menu[] fetchMenu(){
        Menu menu[]=OrderDb.fetchDb();
        return menu;
    }

    public static Customer[] customerProfile(){
        return OrderDb.customerProfileDb();
    }
    public static int updateCustomer(int customer_walletbal,int customer_id){
        return OrderDb.updatecustomerDb(customer_walletbal,customer_id);
    }
    public static Vendor[] vendorProfile(){
        return OrderDb.vendorProfileDb();
    }

    public static Vendor validateVendor(int vendor_id){
        return OrderDb.validateVendor(vendor_id);
    }

    public static Customer validateCustomer(String c_id,String c_password){
        return OrderDb.validateCustomer(c_id,c_password);
    }

    public static Customer[] customerDb(int customer_id){
        return OrderDb.customerDb(customer_id);
    }
    
    public static OrderDetails[] customerOrderHistory(int customer_id){
        return OrderDb.customerOrderHistoryDb(customer_id);
    }

    public static OrderDetails[] vendorOrderHistory(int vendId){
         return OrderDb.vendorOrderHistoryDb(vendId);
     }
     public static OrderDetails[] vendorOrderPending(int vendId){
        return OrderDb.vendorOrderPendingDb(vendId);
    }
    public static void acceptRejectOrder(int vendor_id,String s, int order_no){
        OrderDb.acceptOrRejectOrderDb(vendor_id,s, order_no);
    }

    public static void updatePassword(String c_id,String c_newpass){
        OrderDb.updatePassword(c_id,c_newpass);
    }

    // 
    public static OrderDetails[] pendingOrderDetails(int customer_id){
        return OrderDb.pendingOrderDetails(customer_id);
    }
    public static OrderDetails validatePendingOrder(int order_no) {
        return OrderDb.validatePendingOrder(order_no);

    }
    public static void cancelOrder(int order_no){
        OrderDb.cancelOrder(order_no);
    }
}
