package com.bookingsystem.businessObjects;

import com.bookingsystem.dao.CustomerDao;

/**
 * Created by vijay on 3/9/17.
 */
public class CustomerBo {

    private CustomerDao customerDao;

    public CustomerBo(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }



}
