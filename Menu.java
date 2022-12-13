package com.Hexaware.CMS.Model;

/**
 * Menu class used to display menu information.
 * @author hexware
 */
public class Menu {
    private int food_id;
    private String food_name;
    private int food_price;
    private int vendor_id;

    public Menu(){}

    public Menu(int food_id, String food_name, int food_price, int vendor_id){
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.vendor_id = vendor_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getFood_price() {
        return food_price;
    }

    public void setFood_price(int food_price) {
        this.food_price = food_price;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    @Override
    public String toString() {
        return "Menu [food_id=" + food_id + ", food_name=" + food_name + ", food_price=" + food_price + ", vendor_id="
                + vendor_id + "]";
    }
    
    
}
