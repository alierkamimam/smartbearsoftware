package com.dover.assesment.utilities;

import com.dover.assesment.pages.LoginPage;
import com.dover.assesment.pages.OrderPage;
import com.dover.assesment.pages.WebOrdersPage;

public class Pages {
    private LoginPage loginPage;
    private OrderPage orderPage;
    private WebOrdersPage webOrdersPage;

    public Pages() {
        this.loginPage = new LoginPage();
        this.orderPage = new OrderPage();
        this.webOrdersPage = new WebOrdersPage();
    }


    public WebOrdersPage webOrdersPage() {
        return webOrdersPage;
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public OrderPage orderPage() {
        return orderPage;
    }


}
