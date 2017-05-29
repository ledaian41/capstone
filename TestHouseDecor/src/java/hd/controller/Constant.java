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
    public static final String LOAD_PROJECT_SERVLET = "LoadProjectsServlet";
    /*-------------------PAGE--------------------------*/
    public static final String LOGIN_PAGE = "admin/login.html";
    public static final String ERROR_PAGE = "admin/error.jsp";
    public static final String BLOCK_LIST_PAGE = "admin/block-accounts.jsp";
    public static final String BLOCK_PROJECTS_PAGE = "admin/block-projects.jsp";
    public static final String ADMIN_PAGE = "admin/home.html";
    public static final String MANAGE_ACCOUNT_MEMBER_PAGE = "admin/manage-member.jsp";
    public static final String MANAGE_ACCOUNT_PROFESSIONAL_PAGE = "admin/manage-professional.jsp";
    public static final String ACCOUNT_INFO_PAGE = "admin/account-info.jsp";
    public static final String MANAGE_PROJECT_PAGE = "admin/manage-project.jsp";
    /*-------------------STRING BUTTON--------------------------*/
    public static final String ADMIN = "admin";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String VIEWDETAIL = "viewdetail";
    public static final String BAN_ACCOUNT = "banaccount";
    public static final String UNBAN_ACCOUNT = "unbanaccount";
    public static final String LOAD = "load";
    public static final String LOAD_PROJECT = "loadprojects";
    /*-------------------BUTTON--------------------------*/
    public static final String BTN_ACTION = "btnAction";
    /*-------------------PARAM--------------------------*/
    public static final String PARAM_USER_ID = "txtUserId";
    public static final String PARAM_STATUS = "status";
    public static final String PARAM_USERNAME = "txtUsername";
    public static final String PARAM_PASSWORD = "txtPassword";
    public static final String PARAM_ROLE = "role";
    /*-------------------ATTRIBUTE--------------------------*/
    public static final String ATT_ADMIN = "ADMIN";
    public static final String ATT_ACCOUNT_LIST = "ACCOUNTLIST";
    public static final String ATT_DETAIL = "DETAIL";
    public static final String ATT_PROJECT_LIST = "PROJECTLIST";
    /*-------------------STATUS CODE--------------------------*/
    public static final int STATUS_DEACTIVE = 0;
    public static final int STATUS_ACTIVE = 1;
    public static final int ROLE_MEMBER = 1;
    public static final int ROLE_PROFESSIONAL = 2;
}
