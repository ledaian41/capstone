/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.controller;

import java.io.Serializable;

/**
 *
 * @author Lê Đại An
 */
public class Constant implements Serializable{
    /*-------------------SERVLET--------------------------*/
    public static final String MAIN_ADMIN_SERVLET = "MainAdminServlet";
    public static final String LOGOUT_ADMIN_SERVLET = "LogoutAdminServlet";
    public static final String LOGIN_ADMIN_SERVLET = "LoginAdminServlet";
    public static final String LOAD_ACCOUNTS_SERVLET = "LoadAccountsServlet";
    public static final String LOAD_ACCOUNT_INFO_SERVLET = "LoadAccountInfoServlet";
    public static final String SET_STATUS_ACCOUNT_SERVLET = "SetStatusAccountServlet";
    /*-------------------PAGE--------------------------*/
    public static final String LOGIN_PAGE = "/admin/login.html";
    public static final String ERROR_PAGE = "/admin/error.jsp";
    public static final String BLOCK_LIST_PAGE = "/admin/block-list.jsp";
    public static final String MANAGE_ACCOUNT_PAGE = "/admin/admin.jsp";
    public static final String ACCOUNT_INFO_PAGE = "/admin/account-info.jsp";
    /*-------------------STRING--------------------------*/
    public static final String STRING_ADMIN = "admin";
    public static final String STRING_LOGIN = "login";
    public static final String STRING_LOGOUT = "logout";
    public static final String STRING_VIEWDETAIL = "viewdetail";
    public static final String STRING_BAN_ACCOUNT = "banaccount";
    public static final String STRING_UNBAN_ACCOUNT = "unbanaccount";
    /*-------------------BUTTON--------------------------*/
    public static final String BTN_ACTION = "btnAction";
    /*-------------------PARAM--------------------------*/
    public static final String PARAM_USER_ID = "txtUserId";
    public static final String PARAM_STATUS = "status";
    public static final String PARAM_USERNAME = "txtUsername";
    public static final String PARAM_PASSWORD = "txtPassword";
    /*-------------------ATTRIBUTE--------------------------*/
    public static final String ATT_ADMIN = "ADMIN";
    public static final String ATT_ACCOUNT_LIST = "ACCOUNTLIST";
    public static final String ATT_DETAIL = "DETAIL";
    /*-------------------STATUS CODE--------------------------*/
    public static final int STATUS_CODE_0 = 0;
    public static final int STATUS_CODE_1 = 1;
}
