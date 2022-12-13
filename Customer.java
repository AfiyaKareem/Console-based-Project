package com.Hexaware.CMS.Model;

public class Customer {
    private int customer_id;
    private String customer_name;
    private int customer_phone;
    private String customer_email;
    private String customer_coupon;
    private int customer_walletbal;
    private String c_id;
    private String c_password;

    public Customer(){}

    public Customer(int customer_id, String customer_name, int customer_phone, String customer_email, String customer_coupon, int customer_walletbal, String c_id, String c_password){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
        this.customer_coupon = customer_coupon;
        this.customer_walletbal = customer_walletbal;
        this.c_id = c_id;
        this.c_password = c_password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(int customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_coupon() {
        return customer_coupon;
    }

    public void setCustomer_coupon(String customer_coupon) {
        this.customer_coupon = customer_coupon;
    }

    public int getCustomer_walletbal() {
        return customer_walletbal;
    }

    public void setCustomer_walletbal(int customer_walletbal) {
        this.customer_walletbal = customer_walletbal;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    @Override
    public String toString() {
        return "Customer [c_id=" + c_id + ", c_password=" + c_password + ", customer_coupon=" + customer_coupon
                + ", customer_email=" + customer_email + ", customer_id=" + customer_id + ", customer_name="
                + customer_name + ", customer_phone=" + customer_phone + ", customer_walletbal=" + customer_walletbal
                + "]";
    }

    
}
