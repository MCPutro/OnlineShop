package com.codebean.websiteui.controller;

import com.codebean.websiteui.client.UserClient;
import com.codebean.websiteui.dto.client.user.UserDetailDto;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private UserClient userClient;

    @GetMapping
    public String profile(Model model, WebRequest webRequest) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<UserDetailDto> userClientByToken = this.userClient.findByToken(auth);
            model.addAttribute(Constans.NAV_PAGINATION, "user-management");

            model.addAttribute("userDetail", userClientByToken.getData());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "userManage/view";
    }
}
