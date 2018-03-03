package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.utils.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @RequestMapping("logout")
    public String login(Model model, String name, String password) {
        UserContext.setCurrentEmp(null);
        return "redirect:/login.jsp";
    }
}
