package com.Hexaware.CMS.Model;

import java.sql.Date;

public class OrderDetails {
    private int order_no;
    private int vendor_id;
    private int customer_id;
    private int food_id;
    private int quantity;
    private Date dateandtime;
    private int order_value;
    private String order_status;

    public OrderDetails(){}

    public OrderDetails(int order_no, int vendor_id, int customer_id, int food_id, int quantity, Date dateandtime, int order_value, String order_status){
        this.order_no = order_no;
        this.vendor_id = vendor_id;
        this.customer_id = customer_id;
        this.food_id = food_id;
        this.quantity = quantity;
        this.dateandtime = dateandtime;
        this.order_value = order_value;
        this.order_status = order_status;
    }

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(Date dateandtime) {
        this.dateandtime = dateandtime;
    }

    public int getOrder_value() {
        return order_value;
    }

    public void setOrder_value(int order_value) {
        this.order_value = order_value;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "OrderDetails [customer_id=" + customer_id + ", dateandtime=" + dateandtime + ", food_id=" + food_id
                + ", order_no=" + order_no + ", order_status=" + order_status + ", order_value=" + order_value
                + ", quantity=" + quantity + ", vendor_id=" + vendor_id + "]";
    }

    
}
