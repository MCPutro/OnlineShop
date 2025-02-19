package com.codebean.websiteui.controller;

import com.codebean.websiteui.util.Constans;
import com.codebean.websiteui.util.GlobalFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ReportController {

    @GetMapping("/report")
    public String report(Model model, WebRequest webRequest) {
        GlobalFunction.setGlobalFragment(model, webRequest);
        model.addAttribute(Constans.IS_MANAGEMENT, "Report");
        model.addAttribute(Constans.NAV_PAGINATION, "Report");
//        return "commingSoon";
        return "report/downloadReport";
    }
}
