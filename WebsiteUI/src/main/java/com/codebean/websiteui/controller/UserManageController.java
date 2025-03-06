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
import com.codebean.websiteui.dto.ChangePassDto;
import com.codebean.websiteui.dto.UserAddressDto;
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
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "15", required = false) Integer size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String userRole,
            @RequestParam(required = false) Boolean status,
            Model model, WebRequest webRequest,
            RedirectAttributes redirectAttributes
    ) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.IS_MANAGEMENT, "User Management");

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            userRole = (userRole == null || userRole.equalsIgnoreCase("")) ? null : userRole;

            page = page > 0 ? page - 1 : page;
            Map<String, Object> allUser = this.userClient.findAllBy(auth, page, size, null, null, username, email, name, userRole, status);

            Map<String, Object> dataUser = (Map<String, Object>) allUser.get("data");
            List<Map<String, Object>> content = (List<Map<String, Object>>) dataUser.get("content");

            model.addAttribute("listUser", content);
            model.addAttribute(Constans.CURRENT_PAGE, (Integer) dataUser.get("current-page") + 1);
            model.addAttribute(Constans.TOTAL_PAGES, dataUser.get("total-page"));
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");


        } catch (ForbiddenException fe) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(fe.getMessage()));
            return "redirect:";
        } catch (RuntimeException e) {
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/user-management";
        } catch (Exception e) {
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);
            model.addAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
        }

        //data search
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("userRole", userRole);
        model.addAttribute("status", status);
        model.addAttribute("name", name);
        model.addAttribute("size", size);
        return "userManage/main";
    }


    // 🔹 Fungsi untuk membuka ui popup form add user
    @GetMapping("/add")
    public String add(Model model, WebRequest webRequest, RedirectAttributes redirectAttributes) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

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
                                     WebRequest webRequest,
                                     RedirectAttributes redirectAttributes
    ) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        try {
            GlobalFunction.setGlobalFragment(model, webRequest);
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");

            String auth = webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<UserDetailDto> response = this.userClient.findById(Constans.BEARER + auth, userId);

            model.addAttribute("userDetail", response.getData());

        } catch (Exception e) {
            model.addAttribute(Constans.ERRORS, e.getMessage());
        }

        return "userManage/view";
    }


    @GetMapping("/detail/my")
    public String showUserDetailByToken(Model model, WebRequest webRequest, RedirectAttributes redirectAttributes) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

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
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

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
                           WebRequest webRequest,
                           RedirectAttributes redirectAttributes
    ) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            //
            Response<UserDetailDto> response = this.userClient.findById(auth, userId);
            model.addAttribute("userDetail", response.getData());
            GlobalFunction.setGlobalFragment(model, webRequest);
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");

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


    // 🔹 Fungsi untuk menampilkan ui web user detail berdasarkan token
    @GetMapping("/edit/profile")
    public String editProfileByToken(Model model, WebRequest webRequest, RedirectAttributes redirectAttributes) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<UserDetailDto> userByToken = this.userClient.findByToken(auth);
            model.addAttribute("userDetail", userByToken.getData());
            GlobalFunction.setGlobalFragment(model, webRequest);
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");
        } catch (ForbiddenException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "userManage/edit";
    }

    // 🔹 Fungsi untuk mengakses user service untuk update user detail berdasarkan token
    @ResponseBody
    @PostMapping("/edit/profile")
    public ResponseEntity<String> updateUserByToken(@RequestBody @Valid UserUpdateProfileDto dto,
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

            Response<String> stringResponse = this.userClient.updateUserProfileByToken(auth, dto);

            return ResponseEntity.ok(stringResponse.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 🔹  ini untuk nampilin ui ubah password
    @GetMapping("/{userId}/change-password")
    public String changePassword(@PathVariable Long userId, Model model, WebRequest webRequest, RedirectAttributes redirectAttributes) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.NAV_PAGINATION, "user-management");
        model.addAttribute("userId", userId);
        return "userManage/changePassword";
    }

    // ini untuk ke backend ubah password
    @ResponseBody
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePassDto dto,
                                                 BindingResult bindingResult,
                                                 Model model,
                                                 WebRequest webRequest
    ) {
        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream()
                    .map(err -> err.getField() + " " + err.getDefaultMessage()
                    ).toList();

            return ResponseEntity.badRequest().body(list.toString());
        }

        // check password
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Confirm password does not match");
        }

        if (dto.getCurrentPassword().equals(dto.getNewPassword())) {
            return ResponseEntity.badRequest().body("New password doesn't change from before");
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> stringResponse = this.userClient.changePassword(auth, dto);
            return ResponseEntity.ok(stringResponse.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    // show ui to add address
    @GetMapping("/address")
    public String address(Model model, WebRequest webRequest) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute("address", new UserAddressDto());
        model.addAttribute(Constans.NAV_PAGINATION, "user-management");
        return "userManage/userAddress";
    }

    @GetMapping("/address/{addressId}")
    public String addressById(Model model, WebRequest webRequest, @PathVariable Long addressId) {
        GlobalFunction.setGlobalFragment(model, webRequest);

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<UserAddressDto> addressById = this.userClient.findAddressById(auth, addressId);

            model.addAttribute("address", addressById.getData());
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");
        } catch (ForbiddenException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return "userManage/userAddress";
    }

    // save
    @ResponseBody
    @PostMapping("/address")
    public ResponseEntity<String> address(@RequestBody @Valid UserAddressDto dto,
                                          BindingResult bindingResult,
                                          WebRequest webRequest
    ) {
        System.out.println("-9");
        if (bindingResult.hasErrors()) {
            System.out.println("0");
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();
            return ResponseEntity.badRequest().body(list.toString());
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> stringResponse;

            System.out.println("1");

            // update
            if (dto.getId() != null) {
                System.out.println("2");

                stringResponse = this.userClient.updateAddress(auth, dto, dto.getId());
            } else {
                System.out.println("3");

                // insert new
                stringResponse = this.userClient.addNewAddress(auth, dto);
            }
            System.out.println("4");

            return ResponseEntity.ok(stringResponse.getMessage());
        } catch (Exception e) {
            System.out.println("5");

            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

//        return ResponseEntity.ok(userAddressDto.toString());
    }


    // 🔹 Fungsi untuk soft delete user
    @GetMapping("/address/delete/{addressId}")
    public String deleteAddressById(@PathVariable("addressId") Long addressId,
                                    Model model,
                                    RedirectAttributes redirectAttributes,
                                    WebRequest webRequest
    ) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> objectResponse = this.userClient.deleteAddressById(auth, addressId);

            redirectAttributes.addFlashAttribute("successMessage", objectResponse.getMessage());
            return "redirect:/profile";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/profile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Internal Server Error"));
            return "redirect:/profile";
        }

    }
}
