package com.codebean.websiteui.controller;

import com.codebean.websiteui.client.TransactionClient;
import com.codebean.websiteui.dto.client.product.CartDto;
import com.codebean.websiteui.dto.client.transaction.OrderDto;
import com.codebean.websiteui.dto.client.transaction.OrderStatusUpdateDto;
import com.codebean.websiteui.dto.client.user.RoleCreateDto;
import com.codebean.websiteui.dto.pageAttribute;
import com.codebean.websiteui.dto.response.Response;
import com.codebean.websiteui.errorHandling.ForbiddenException;
import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            model.addAttribute(Constans.CURRENT_PAGE, 1);
            model.addAttribute(Constans.TOTAL_PAGES, 1);
            model.addAttribute(Constans.NAV_PAGINATION, "transactions");
        } catch (ForbiddenException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        }

        return "transaction/transaction";
    }

    @GetMapping("/trx-management")
    public String getAllTransaction(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", required = false, defaultValue = "50") Integer size,
                                    @RequestParam(required = false) String search,
                                    @RequestParam(required = false) String filterBy,
                                    Model model, WebRequest webRequest, RedirectAttributes redirectAttributes
    ) {
        //validate session
        if (GlobalFunction.cekSession(webRequest) == null) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList("Session expired, please relogin"));
            return "redirect:/";
        }

        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.IS_MANAGEMENT, "Transactions Management");

        try {
            String auth = Constans.BEARER + webRequest.getAttribute(Constans.TOKEN, WebRequest.SCOPE_SESSION).toString();

            page = page > 0 ? page - 1 : page;
            Response<pageAttribute<OrderDto>> transactions;
            if (search != null && filterBy != null) {
                switch (filterBy) {
                    case "status":
                        transactions = this.transactionClient.getOrderByStatus(auth, page, size, search);
                        break;
                    default:
                        transactions = this.transactionClient.getAllTransaction(auth, page, size);
                        break;
                }
            } else {
                transactions = this.transactionClient.getAllTransaction(auth, page, size);
            }

//            Response<pageAttribute<OrderDto>> transactions = this.transactionClient.getAllTransaction(auth, page, size);

            model.addAttribute("transactions", transactions.getData().getContent());
            model.addAttribute(Constans.CURRENT_PAGE, transactions.getData().getCurrentPage() + 1);
            model.addAttribute(Constans.TOTAL_PAGES, transactions.getData().getTotalPage());
            model.addAttribute(Constans.NAV_PAGINATION, "trx-management");
            model.addAttribute(Constans.FILTER_BY, filterBy); //kirim data pencarian
            model.addAttribute(Constans.SEARCH, search); //kirim data pencarian
            model.addAttribute("size", size);

        } catch (ForbiddenException e) {
            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute(Constans.CURRENT_PAGE, 0);
            model.addAttribute(Constans.TOTAL_PAGES, 0);

            redirectAttributes.addFlashAttribute(Constans.ERRORS, Collections.singletonList(e.getMessage()));
            return "redirect:/trx-management";
        }

        return "transaction/transaction";
    }

    @ResponseBody
    @PostMapping("/trx-management/process")
    public ResponseEntity<Object> updateStatusOrder(@Valid @RequestBody OrderStatusUpdateDto dto,
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
            Response<Object> objectResponse = this.transactionClient.updateOrderStatus(auth, dto);

            return ResponseEntity.ok(objectResponse.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
