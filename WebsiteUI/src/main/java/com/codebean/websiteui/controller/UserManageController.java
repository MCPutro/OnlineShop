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
import com.codebean.websiteui.dto.client.user.UserUpdateProfileDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.errorHandling.ForbiddenException;
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

    // 🔹 Fungsi untuk membuka ui web untuk menampilkan semua user
    @GetMapping
    public String findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String filterBy,
            Model model, WebRequest webRequest,
            RedirectAttributes redirectAttributes
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

        } catch (ForbiddenException fe) {
            System.out.println("masuk  A");
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(fe.getMessage()));
            return "redirect:";
        } catch (RuntimeException e) {
            System.out.println("masuk  B");
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

//            model.addAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/user-management";
        } catch (Exception e) {
            System.out.println("masuk  C");
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);
            model.addAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
//            return "/";
        }

        return "userManage/main";
    }


    // 🔹 Fungsi untuk membuka ui popup form add user
    @GetMapping("/add")
    public String add(Model model, WebRequest webRequest
    ) {
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


    // 🔹 Fungsi untuk memanggil user service untuk save new user, bukan ke halaman web
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserCreateDto user,
                                           BindingResult bindingResult,
                                           WebRequest webRequest
    ) {

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


    // 🔹 Fungsi untuk membuka ui web user detail
    @GetMapping("/detail/{userId}")
    public String showUserDetailById(@PathVariable("userId") Long userId,
                                     Model model,
                                     WebRequest webRequest) {
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


    @GetMapping("/detail/my")
    public String showUserDetailByToken(Model model,
                                        WebRequest webRequest) {
        try {
            GlobalFunction.setGlobalFragment(model, webRequest);

            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<UserDetailDto> userClientByToken = this.userClient.findByToken(auth);
        } catch (RuntimeException e) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "userManage/view";
    }

    // 🔹 Fungsi untuk soft delete user
    @GetMapping("/delete/{userId}")
    public String deleteById(@PathVariable("userId") Long userId,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             WebRequest webRequest
    ) {
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

    // 🔹 Fungsi untuk menampilkan ui web user detail
    @GetMapping("/edit/{userId}")
    public String editById(@PathVariable("userId") Long userId,
                           Model model,
                           WebRequest webRequest
    ) {
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            //
            Response<UserDetailDto> response = this.userClient.findById(auth, userId);
            model.addAttribute("userDetail", response.getData());

//            this.userClient.findById(auth, )
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "userManage/edit";
    }


    // 🔹 Fungsi untuk memanggil user service untuk menupdate data user profile
    @PostMapping("/edit/{userId}")
    @ResponseBody
    public ResponseEntity<String> updateUserById(@PathVariable Long userId,
                                                 @RequestBody @Valid UserUpdateProfileDto dto,
                                                 BindingResult bindingResult,
                                                 WebRequest webRequest
    ) {
        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();

            return ResponseEntity.badRequest().body(list.toString());
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            Response<String> stringResponse = this.userClient.updateUserProfileById(auth, userId, dto);

            return ResponseEntity.ok(stringResponse.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
