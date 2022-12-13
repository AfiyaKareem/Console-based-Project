package com.Hexaware.CMS.Persistence;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author hexware
 */
public class OrderDb {
   static int i;
//    static int order_no = 0;
   static String url = "jdbc:mysql://127.0.0.1:3306/CMSDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    public static int insertDb(int vendor_id,int customer_id,int food_id,int quantity,String dateandtime,int order_value, String order_status){        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection( url,"root","Password123"); 
            PreparedStatement stmt=con.prepareStatement("insert into orderdetails"+"(vendor_id, customer_id, food_id, quantity, dateandtime, order_value, order_status)"+" values(?,?,?,?,?,?,?)");  
        // stmt.setInt(1,order_no);    
        stmt.setInt(1,vendor_id);  
        stmt.setInt(2,customer_id);  
        stmt.setInt(3,food_id);
        stmt.setInt(4,quantity);   
        stmt.setString(5,dateandtime);
        stmt.setInt(6,order_value);
        stmt.setString(7, order_status);
        i=stmt.executeUpdate();  
        //System.out.println(i+" records inserted");  
        }catch(Exception e)
        { System.out.println(e);}  
                return i;
            }   
    
        public static int insertnewCustomer(int customer_id,String customer_name,int customer_phone,String customer_email,String customer_coupon,int customer_walletbal,String c_id,String c_password){    
        
            try{
                 Class.forName("com.mysql.cj.jdbc.Driver");  
                 Connection con=DriverManager.getConnection( url,"root","Password123"); 
                 PreparedStatement stmt=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)"); 
                    stmt.setInt(1,customer_id);  
                    stmt.setString(2,customer_name);
                    stmt.setInt(3,customer_phone); 
                    stmt.setString(4,customer_email);   
                    stmt.setString(5,customer_coupon);
                    stmt.setInt(6,customer_walletbal);
                    stmt.setString(7,c_id);
                    stmt.setString(8,c_password);
                    i=stmt.executeUpdate();  
                    // System.out.println("New Customer added!!");  
                }catch(Exception e){ System.out.println(e);}  
            return i;
        }

        public static int insertFoodItem(int food_id,String food_name,int food_price,int vendor_id){    
            try{
                  Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  url,"root","Password123"); 
                    PreparedStatement stmt=con.prepareStatement("insert into menu values(?,?,?,?)"); 
                           stmt.setInt(1,food_id);  
                           stmt.setString(2,food_name);
                           stmt.setInt(3,food_price); 
                           stmt.setInt(4,vendor_id);   
                           i=stmt.executeUpdate();  
                           System.out.println("New Food Item inserted!!");  
               }catch(Exception e){ System.out.println(e);}  
           return i;
       }       
       public static int insertnewVendor(int vendor_id,String vendor_name,int vendor_phone,String vendor_specs){    
           try{
                 Class.forName("com.mysql.cj.jdbc.Driver");  
                   Connection con=DriverManager.getConnection(  url,"root","Password123"); 
                   PreparedStatement stmt=con.prepareStatement("insert into vendor values(?,?,?,?)"); 
                          stmt.setInt(1,vendor_id);  
                          stmt.setString(2,vendor_name);
                          stmt.setInt(3,vendor_phone); 
                          stmt.setString(4,vendor_specs);  
                          i=stmt.executeUpdate();   
                          System.out.println("New Vendor added!!");  
              }catch(Exception e){ System.out.println(e);}  
          return i;
      } 

    public static Menu[] fetchDb(){
        Menu m[]=null;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
            Statement stmt=con.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from menu");  
            ArrayList<Menu> list=new ArrayList<Menu>();          
            while(rs.next()) { 
            list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
                m=new Menu[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return m;      
        }

    public static Customer[] customerProfileDb(){
            Customer c[]=null;
            try{  
                Class.forName("com.mysql.cj.jdbc.Driver");  
               Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
                Statement stmt=con.createStatement();  
                        
                ResultSet rs=stmt.executeQuery("select * from customer");  
                ArrayList<Customer> list=new ArrayList<Customer>();          
                while(rs.next()) { 
                list.add(new Customer(rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getString(8)));
                    c=new Customer[list.size()];
                    c= list.toArray(c);
                      } 
            }catch(Exception e){ System.out.println(e);}  
                
            return c;      
            }

    public static Vendor[] vendorProfileDb(){
        Vendor v[]=null;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
            Statement stmt=con.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from vendor");  
            ArrayList<Vendor> list=new ArrayList<Vendor>();          
            while(rs.next()) { 
            list.add(new Vendor(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
                v=new Vendor[list.size()];
                v= list.toArray(v);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return v;      
        
    }

// validate customer id
public static Customer validateCustomer(String c_id,String c_password){
    Customer cus = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
       Connection con=DriverManager.getConnection(
           url,"root","Password123");
           String sql = "select * from Customer where c_id = ? and c_password = ?";
           PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1,c_id);
           stmt.setString(2,c_password);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()){
               cus = new Customer(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getString(8)
               );

           }
           stmt.close();
           con.close();    
       
    }catch(Exception e ){
        System.out.println(e);
 }
    return cus;
}

public static OrderDetails[] customerOrderHistoryDb(int customer_id){
    OrderDetails[] ordArr = null;
    try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer ID:");
        customer_id = sc.nextInt();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,"root","Password123");
        
        PreparedStatement stmt = con.prepareStatement("select order_no, vendor_id, Customer_id, food_id, quantity, dateandtime, order_value, order_status from orderdetails where customer_id=?");
        stmt.setInt(1,customer_id);
        ResultSet rs = stmt.executeQuery();
        ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
        while( rs.next() ){
            OrderDetails od = new OrderDetails(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getDate(6),
                rs.getInt(7),
                rs.getString(8));
            list.add(od);                
        }
        ordArr = new OrderDetails[list.size()];
        ordArr = list.toArray(ordArr);

        stmt.close();
        con.close();

    } catch(Exception e){
        System.out.println(e);
    }
    return ordArr;      

}


public static Vendor validateVendor(int vendor_id){
    Vendor ven = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");  
     Connection con=DriverManager.getConnection(  
         url,"root","Password123");
         String sql = "select * from vendor where vendor_id = ?";
         PreparedStatement stmt = con.prepareStatement(sql);
         stmt.setInt(1,vendor_id);
         ResultSet rs = stmt.executeQuery();
         if(rs.next()){
           ven = new Vendor(
                 rs.getInt("vendor_id"),
                 rs.getString("Vendor_Name"),
                 rs.getInt("Vendor_Phone"),
                 rs.getString("Vendor_Specs")
             );
         }
         stmt.close();
         con.close();

    }catch(Exception e){
        System.out.println(e);
    }
    return ven;
}

public static OrderDetails[] vendorOrderHistoryDb(int vendor_id){
    OrderDetails[] odArr = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  
        url,"root","Password123");
         
        String sql = " select * from OrderDetails where Vendor_id = ?  ";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,vendor_id);
        ResultSet rs = stmt.executeQuery();
        ArrayList<OrderDetails> list=new ArrayList<OrderDetails>();  
        while( rs.next() ){
            OrderDetails od = new OrderDetails(
                rs.getInt("order_no"),
                rs.getInt("vendor_id"),
                rs.getInt("customer_id"),
                rs.getInt("food_id"),
                rs.getInt("quantity"),
                rs.getDate("dateandtime"),
                rs.getInt("order_value"),
                rs.getString("order_status"));
            list.add(od);
        }
        odArr=new OrderDetails[list.size()];
        odArr= list.toArray(odArr);

        stmt.close();
        con.close();
    } catch(Exception e){
        System.out.println( e );
    }
    return odArr;
   }

   public static OrderDetails[] vendorOrderPendingDb(int vendor_id){
    OrderDetails[] odArr = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  
        url,"root","Password123");
         
        String sql = " select * from OrderDetails where Vendor_id = ?  and order_status='ordered'";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,vendor_id);
        ResultSet rs = stmt.executeQuery();
        ArrayList<OrderDetails> list=new ArrayList<OrderDetails>();  
        while( rs.next() ){
            OrderDetails od = new OrderDetails(
                rs.getInt("order_no"),
                rs.getInt("vendor_id"),
                rs.getInt("customer_id"),
                rs.getInt("food_id"),
                rs.getInt("quantity"),
                rs.getDate("dateandtime"),
                rs.getInt("order_value"),
                rs.getString("order_status"));
            list.add(od);
        }
        odArr=new OrderDetails[list.size()];
        odArr= list.toArray(odArr);

        stmt.close();
        con.close();
    } catch(Exception e){
        System.out.println( e );
    }
    return odArr;
   }

public static void acceptOrRejectOrderDb(int vendor_id,String s, int order_no){
           
    String preOrdSts="ordered";
    OrderDetails ord = null;
    int i=0;
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(
            url,"root","Password123");
            String sql = "update  orderdetails set order_status=? where vendor_id=? and order_status=? and order_no=? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,s);
            stmt.setInt(2,vendor_id);
            stmt.setString(3,preOrdSts);
            stmt.setInt(4,order_no);
            i=stmt.executeUpdate();
            stmt.close();
            con.close();    
        
     }catch(Exception e )
     {
         System.out.println(e);
     }
     if(i!=0)
     {
         System.out.println("");
     }
     else{
         System.out.println("");
     }
}

public static int fetchcust(String c_id){
                
    int r=0;
    try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter login id: ");
            c_id = sc.next();
          Class.forName("com.mysql.cj.jdbc.Driver");  
              Connection con=DriverManager.getConnection(url,"root","Password123");
                  String sql ="select customer_id from customer where c_id=?";
                  PreparedStatement stmt=con.prepareStatement(sql);  
                  stmt.setString(1,c_id);     
                  ResultSet rs=stmt.executeQuery();
                  if(rs.next()){
                  r= rs.getInt("customer_id");
                  }         
      }catch(Exception e){ System.out.println(e);}  
                  
      return r;      
  }

  public static int fetchVen(int food_id){
    int r=0;
    try{
       Class.forName("com.mysql.cj.jdbc.Driver");  
       Connection con=DriverManager.getConnection( url,"root","Password123");
       String sql ="select vendor_id from menu where food_id=?";
       PreparedStatement stmt=con.prepareStatement(sql);  
       stmt.setInt(1,food_id);     
       ResultSet rs=stmt.executeQuery();  
       if(rs.next()){
       r= rs.getInt("vendor_id");
       }         
       }catch(Exception e){ System.out.println(e);}      
           return r;      
}

public static int fetchFp(int food_id){
    int r=0;
    try{
       Class.forName("com.mysql.cj.jdbc.Driver");  
       Connection con=DriverManager.getConnection(url,"root","Password123");
       String sql ="select food_price from menu where food_id=?";
       PreparedStatement stmt=con.prepareStatement(sql);  
       stmt.setInt(1,food_id);     
       ResultSet rs=stmt.executeQuery();  
       if(rs.next()){
       r= rs.getInt("food_price");
       }
                   
       }catch(Exception e){ System.out.println(e);}  
   return r;      
}

public static int fetchwal(int customer_id){
    int r=0;
    try{
       Class.forName("com.mysql.cj.jdbc.Driver");  
       Connection con=DriverManager.getConnection( url,"root","Password123");
       String sql ="select customer_walletbal from customer where customer_id=?";
       PreparedStatement stmt=con.prepareStatement(sql);  
       stmt.setInt(1,customer_id);     
       ResultSet rs=stmt.executeQuery();  
       if(rs.next()){
       r= rs.getInt("customer_walletbal");
       }         
       }catch(Exception e){ System.out.println(e);}      
           return r;      
}

public static int updatecustomerDb(int customer_walletbal,int customer_id){        
    try{

        Class.forName("com.mysql.cj.jdbc.Driver");  
       Connection con=DriverManager.getConnection(  
        url,"root","Password123"); 
        PreparedStatement stmt=con.prepareStatement("update customer set customer_walletbal=? where customer_id=?"); 
        stmt.setInt(1,customer_walletbal);
        stmt.setInt(2,customer_id);
        i=stmt.executeUpdate();  
        System.out.println("Customer Wallet Balance updated");  
    }catch(Exception e){ System.out.println(e);}  
    return i;
}

public static Customer[] customerDb(int customer_id){
    Customer[] cusArr = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(  
        url,"root","Password123");
        String sql = "select * from customer where Customer_id = ? ";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,customer_id);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Customer> list = new ArrayList<Customer>();
        while( rs.next() ){
            Customer cu = new Customer(
                rs.getInt("customer_id"),
                rs.getString("customer_name"),
                rs.getInt("customer_phone"),
                rs.getString("customer_email"),
                rs.getString("Customer_Coupon"),
                rs.getInt("customer_walletbal"),
                rs.getString("c_id"),
                rs.getString("c_password")
            );
         list.add(cu);                
        }
        cusArr = new Customer[list.size()];
        cusArr = list.toArray(cusArr);

        stmt.close();
        con.close();

    } catch(Exception e){
        System.out.println(e);
    }
    return cusArr;      

}

public static void updatePassword(String c_id, String c_newpass) {

    try {
        Connection con=DriverManager.getConnection(url,"root","Password123");
        String sql = "update customer set c_password = ? where c_id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, c_newpass);
        stmt.setString(2, c_id);
        i = stmt.executeUpdate();
    } catch (Exception e) {
        System.out.print(e);
    }
}


// 
public static OrderDetails[] pendingOrderDetails(int customer_id) {
    OrderDetails[] odArr = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,"root","Password123");
        String sql = "select * from orderdetails where Customer_id = ? and order_status='ordered'";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,customer_id);
        ResultSet rs = stmt.executeQuery();
        ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
        while( rs.next() ){
           OrderDetails od = new OrderDetails(
                rs.getInt("order_no"),
                rs.getInt("vendor_id"),
                rs.getInt("customer_id"),
                rs.getInt("food_id"),
                rs.getInt("quantity"),
                rs.getDate("dateandtime"),
                rs.getInt("order_value"),
                rs.getString("order_status"));
            list.add(od);
        }
        odArr = new OrderDetails[list.size()];
        odArr = list.toArray(odArr);
        stmt.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return odArr;

}

public static OrderDetails validatePendingOrder(int order_no) {
    OrderDetails ordObj = null;
    try {
        Connection con=DriverManager.getConnection(url,"root","Password123");
        String sql = "select * from OrderDetails where order_no=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, order_no);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            ordObj = new OrderDetails(
                    rs.getInt("order_no"),
                    rs.getInt("vendor_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("food_id"),
                    rs.getInt("quantity"),
                    rs.getDate("dateandtime"),
                    rs.getInt("order_value"),
                    rs.getString("order_status"));
        }
        stmt.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return ordObj;

}
public static void cancelOrder(int order_no) {
    try {
        Connection con=DriverManager.getConnection(url,"root","Password123");
        String updateCostumerSql = "delete from OrderDetails where Order_No=?";
        PreparedStatement stmt = con.prepareStatement(updateCostumerSql);
        stmt.setInt(1, order_no);
        i = stmt.executeUpdate();
    } catch (Exception e) {
        System.out.print(e);
    }
}
}
