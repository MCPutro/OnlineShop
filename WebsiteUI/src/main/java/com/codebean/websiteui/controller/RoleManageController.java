package com.codebean.websiteui.controller;

import com.codebean.websiteui.client.UserClient;
import com.codebean.websiteui.dto.client.user.*;
import com.codebean.websiteui.dto.pageAttribute;
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

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("role-management")
public class RoleManageController {

    @Autowired
    private UserClient userClient;

    @GetMapping
    public String findAll(@RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "10") Integer size,
                          @RequestParam(required = false) String search,
                          Model model,
                          RedirectAttributes redirectAttributes,
                          WebRequest webRequest
    ) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            page = page > 0 ? page - 1 : page;
            Response<pageAttribute<RoleDto>> Roles = null;

            if (search != null && !search.isEmpty()) {
                // search by name
                Roles = this.userClient.findRoleByName(auth, page, size, search);
            } else {
                //show all
                Roles = this.userClient.findAllRole(auth, page, size);
            }

            if (Roles != null) {
                model.addAttribute("listRole", Roles.getData().getContent());
                model.addAttribute(Constans.CURRENT_PAGE, Roles.getData().getCurrentPage() + 1);
                model.addAttribute(Constans.TOTAL_PAGES, Roles.getData().getTotalPage());
                model.addAttribute(Constans.NAV_PAGINATION, "role-management");
                model.addAttribute(Constans.SEARCH, search); //kirim data pencarian
                model.addAttribute("size", size);

//                return "roleManagement/main";
            }

        } catch (ForbiddenException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/role-management";
        }

        model.addAttribute(Constans.IS_MANAGEMENT, "Role Management");

        return "roleManagement/main";
    }


    // 🔹 Fungsi untuk membuka ui popup form add role
    @GetMapping("/add")
    public String openForm(@RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size,
                           Model model, WebRequest webRequest,
                           RedirectAttributes redirectAttributes
    ) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.NAV_PAGINATION, "role-management");

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            Response<List<ModuleDto>> allModule = this.userClient.findAllActiveModuleAndPermission(auth);

            model.addAttribute("role", new RoleDto());
            model.addAttribute("modules", allModule.getData());

            return "roleManagement/add";
        } catch (ForbiddenException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/role-management";
        }
    }

    // cal backend untuk ceare role
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<Object> createRole(@Valid @RequestBody RoleCreateDto dto,
                                             BindingResult bindingResult,
                                             Model model,
                                             WebRequest webRequest
    ) {
        if (bindingResult.hasErrors()) {
            List<String> list = bindingResult.getFieldErrors().stream().map(err -> {
                return err.getField() + " " + err.getDefaultMessage();
            }).toList();

            return ResponseEntity.badRequest().body(list.toString());
        }

        GlobalFunction.setGlobalFragment(model, webRequest);
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> response = this.userClient.createRole(auth, dto);

            return ResponseEntity.ok(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // untuk menampilkan ui edit role
    @GetMapping("/edit/{roleId}")
    public String openEditForm(@PathVariable Long roleId,
                               Model model,
                               WebRequest webRequest,
                               RedirectAttributes redirectAttributes
    ) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.NAV_PAGINATION, "role-management");

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<RoleDto> roleById = this.userClient.findRoleById(auth, roleId);

            model.addAttribute("role", roleById.getData());
            model.addAttribute("selectedPermissions", roleById.getData().getPermissions().stream().map(PermissionDto::getId).toList());

            Response<List<ModuleDto>> allModule = this.userClient.findAllActiveModuleAndPermission(auth);
            model.addAttribute("modules", allModule.getData());

            return "roleManagement/edit";

        } catch (ForbiddenException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/role-management";
        }
    }


    // untuk mentimpan perubahan
    @ResponseBody
    @PostMapping("/edit/{roleId}/save")
    public ResponseEntity<Object> updateRole(@PathVariable Long roleId,
                                             @Valid @RequestBody RoleCreateDto dto,
                                             BindingResult bindingResult,
                                             Model model,
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
            Response<String> response = this.userClient.updateRoleById(auth, roleId, dto);

            return ResponseEntity.ok(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/inactive/role/{roleId}")
    public String inActiveById(@PathVariable String roleId,
                               RedirectAttributes redirectAttributes,
                               WebRequest webRequest
    ) {
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> objectResponse = this.userClient.deleteRoleById(auth, roleId);

            redirectAttributes.addFlashAttribute("successMessage", objectResponse.getMessage());
            return "redirect:/role-management";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/role-management";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Internal Server Error"));
            return "redirect:/role-management";
        }
    }

    @GetMapping("/reactivation/{roleId}")
    public String reactivationRole(@PathVariable String roleId,
                                   RedirectAttributes redirectAttributes,
                                   WebRequest webRequest
    ) {
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<String> objectResponse = this.userClient.reactivationRoleById(auth, roleId);

            redirectAttributes.addFlashAttribute("successMessage", objectResponse.getMessage());
            return "redirect:/role-management";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/role-management";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Internal Server Error"));
            return "redirect:/role-management";
        }
    }
}

