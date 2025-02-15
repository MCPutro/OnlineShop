package com.codebean.websiteui.controller;

/*
IntelliJ IDEA 2024.2.4 (Community Edition)
Build #IC-242.23726.103, built on October 23, 2024
@Author mcputro a.k.a. Mu'ti Cahyono Putro
Created on 14 Feb 2025 18:09
@Last Modified 14 Feb 2025 18:09
Version 1.0
*/

import com.codebean.websiteui.client.UserClient;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user-management")
public class UserManageController {

    @Autowired
    private UserClient userClient;

    @GetMapping
    public String findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String filterBy,
            Model model, WebRequest webRequest
    ) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.IS_MANAGEMENT, "User Management");

        try {
            String auth = webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            page = page > 0 ? page - 1 : page;
            Map<String, Object> allUser;
            if (search != null && filterBy != null) {
                switch (filterBy) {
                    case "username":
                        allUser = this.userClient.findAllBy(Constans.BEARER + auth, "username", search, page, size);
                        break;
                    case "email":
                        allUser = this.userClient.findAllBy(Constans.BEARER + auth, "email", search, page, size);
                        break;
                    case "role":
                        allUser = this.userClient.findAllBy(Constans.BEARER + auth, "rolename", search, page, size);
                        break;
                    default:
                        allUser = this.userClient.findAll(Constans.BEARER + auth, page, size);
                        break;
                }
            } else {
                allUser = this.userClient.findAll(Constans.BEARER + auth, page, size);
            }

            Map<String, Object> dataUser = (Map<String, Object>) allUser.get("data");
            List<Map<String, Object>> content = (List<Map<String, Object>>) dataUser.get("content");

            model.addAttribute("listUser", content);
            model.addAttribute(Constans.CURRENT_PAGE, (Integer) dataUser.get("current-page") + 1);
            model.addAttribute(Constans.TOTAL_PAGES, dataUser.get("total-page"));
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");
            model.addAttribute(Constans.FILTER_BY, filterBy); //kirim data pencarian
            model.addAttribute(Constans.SEARCH, search); //kirim data pencarian

        } catch (Exception e) {
            throw new RuntimeException(e);
//            model.addAttribute(Constans.ERRORS, e.getStackTrace());
//            return "/";
        }

        model.addAttribute("size", size);

        return "userManage/main";
    }
}
