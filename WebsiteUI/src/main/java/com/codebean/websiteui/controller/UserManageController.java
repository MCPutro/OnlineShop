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
import com.codebean.websiteui.dto.client.user.UserCreateDto;
import com.codebean.websiteui.dto.client.user.UserDetailDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/user-management")
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
                    case "rolename":
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

            model.addAttribute("size", size);

        } catch (RuntimeException e) {
            System.out.println("Error : " + e.getMessage());
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

            model.addAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));

            return "userManage/main";
        } catch (Exception e) {
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);
            model.addAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
//            return "/";
        }

        return "userManage/main";
    }

    @GetMapping("/add")
    public String add(Model model, WebRequest webRequest) {
        try {
            String auth = webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            //get role list
            Map<String, Object> activeRole = this.userClient.findActiveRole(Constans.BEARER + auth, 0, 500);
            Map<String, Object> data = (Map<String, Object>) activeRole.get("data");
            List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("content");

            Map<Object, Object> role = new HashMap<>();
            for (int i = 0; i < content.size(); i++) {
                Object id = content.get(i).get("id");
                Object name = content.get(i).get("name");
                role.put(id, name);
            }
            model.addAttribute("listRole", role);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "userManage/add";
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserCreateDto user, BindingResult bindingResult, WebRequest webRequest) {

        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();

            return ResponseEntity.badRequest().body(list.toString());
        }


        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("passwords don't match");
        }

        try {
            String auth = webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> user1 = this.userClient.createUser(Constans.BEARER + auth, user);
            return ResponseEntity.ok(user1.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail/{userId}")
    public String show(@PathVariable("userId") Long userId, Model model, WebRequest webRequest) {
        try {
            GlobalFunction.setGlobalFragment(model, webRequest);

            String auth = webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<UserDetailDto> response = this.userClient.findById(Constans.BEARER + auth, userId);

            model.addAttribute("userDetail", response.getData());

        } catch (Exception e) {
            model.addAttribute(Constans.ERRORS, e.getMessage());
        }

        return "userManage/view";
    }

    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") Long userId, Model model, RedirectAttributes redirectAttributes, WebRequest webRequest) {
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<Object> objectResponse = this.userClient.deleteById(auth, userId);

            redirectAttributes.addFlashAttribute("successMessage", objectResponse.getMessage());
            return "redirect:/user-management";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/user-management";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Internal Server Error"));
            return "redirect:/user-management";
        }

    }


    @GetMapping("/edit")
    public String edit(Model model, WebRequest webRequest) {
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            //get role list
            Map<String, Object> activeRole = this.userClient.findActiveRole(auth, 0, 500);
            Map<String, Object> data = (Map<String, Object>) activeRole.get("data");
            List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("content");

            Map<Object, Object> role = new HashMap<>();
            for (int i = 0; i < content.size(); i++) {
                Object id = content.get(i).get("id");
                Object name = content.get(i).get("name");
                role.put(id, name);
            }
            model.addAttribute("listRole", role);


//            this.userClient.findById(auth, )
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "userManage/edit";
    }
}
