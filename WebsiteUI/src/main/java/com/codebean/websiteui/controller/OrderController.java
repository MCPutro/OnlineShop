package com.codebean.websiteui.controller;

import com.codebean.websiteui.client.TransactionClient;
import com.codebean.websiteui.dto.client.product.CartDto;
import com.codebean.websiteui.dto.client.transaction.OrderDto;
import com.codebean.websiteui.dto.pageAttribute;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.errorHandling.ForbiddenException;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private TransactionClient transactionClient;

    @GetMapping("/transactions")
    public String getTransactionByToken(Model model, WebRequest webRequest, RedirectAttributes redirectAttributes) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, webRequest);

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();
            Response<pageAttribute<OrderDto>> transactionByToken = this.transactionClient.getTransactionByToken(auth, 0, 1000);

            model.addAttribute("transactions", transactionByToken.getData().getContent());

        } catch (ForbiddenException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        }

        return "transaction/transaction";
    }

}
