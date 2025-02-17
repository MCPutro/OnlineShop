package com.codebean.websiteui.util;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 16:35
@Last Modified 14 Feb 2025 16:35
Version 1.0
*/

import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

public class GlobalFunction {

    public static void setGlobalFragment(Model model, WebRequest webRequest) {
        model.addAttribute(Constans.USERNAME, webRequest.getAttribute(Constans.USERNAME, 1));
        model.addAttribute(Constans.USER_ID, webRequest.getAttribute(Constans.USER_ID, 1));
        model.addAttribute(Constans.ROLE, webRequest.getAttribute(Constans.ROLE, 1));
        model.addAttribute(Constans.TOKEN, webRequest.getAttribute(Constans.TOKEN, 1));
        model.addAttribute(Constans.MODULE, webRequest.getAttribute(Constans.MODULE, 1));
        model.addAttribute(Constans.PERMISSIONS, (List<String>) webRequest.getAttribute(Constans.PERMISSIONS, 1));
//        model.addAttribute("URL_IMG",webRequest.getAttribute("URL_IMG",1));
//        model.addAttribute("MENU_NAVBAR",webRequest.getAttribute("MENU_NAVBAR",1));
    }

    public static void printModel(Model model) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>.");
        Map<String, Object> map = model.asMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>.");

    }
}
