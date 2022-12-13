package com.Hexaware.CMS.Model;

public class Vendor {
    private int vendor_id;
    private String vendor_name;
    private int vendor_phone;
    private String vendor_specs;

    public Vendor(){}

    public Vendor(int vendor_id, String vendor_name, int vendor_phone, String vendor_specs){
        this.vendor_id = vendor_id;
        this.vendor_name = vendor_name;
        this.vendor_phone = vendor_phone;
        this.vendor_specs = vendor_specs;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public int getVendor_phone() {
        return vendor_phone;
    }

    public void setVendor_phone(int vendor_phone) {
        this.vendor_phone = vendor_phone;
    }

    public String getVendor_specs() {
        return vendor_specs;
    }

    public void setVendor_specs(String vendor_specs) {
        this.vendor_specs = vendor_specs;
    }

    @Override
    public String toString() {
        return "Vendor [vendor_id=" + vendor_id + ", vendor_name=" + vendor_name + ", vendor_phone=" + vendor_phone
                + ", vendor_specs=" + vendor_specs + "]";
    }

    
}
