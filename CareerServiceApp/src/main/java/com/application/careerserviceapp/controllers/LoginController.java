package com.application.careerserviceapplication.controllers;

import com.application.careerserviceapplication.models.UserLogin;
import com.application.careerserviceapplication.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/validateUser")
    public String validateUser(@RequestParam String userid, @RequestParam String password, Model model) {
        System.out.println("inside validate user");
        UserLogin userLogin = new UserLogin();

        userLogin.setUid(userid.trim());
        userLogin.setPassword(password.trim());

        UserLogin ln = loginService.getLoginbyUser(userLogin);
        System.out.println("DB login object : " + ln);
        if (ln == null) {
            model.addAttribute("status", "failed");
            model.addAttribute("message", "User Does not exist");
            return "user_login";
        }
        String type = "";
        try {
            type = ln.getType();
            System.out.println(type);
        } catch (Exception e) {
            type = "";
        }
        Base64.Decoder simpleDecoder = Base64.getDecoder();
        String decodedString = new String(simpleDecoder.decode(ln.getPassword().getBytes()));
        System.out.println("Decoded string : " + password + "  -  " + decodedString);

        if (!type.equals("")) {
            if (decodedString.equals(userLogin.getPassword())) {
                if (ln.getApproved().equalsIgnoreCase("Y")) {
                    model.addAttribute("status", "success");
                    if (type.equals("C")) {
                        return "redirect:/candidate/" + ln.getUid();
//                        return "redirect:/user_login";
                    }
                    else if (type.equals("E"))
                        return "redirect:/employer/" + ln.getUid();
                    else if (type.equals("A"))
                        return "redirect:/admin";
                } else {
                    model.addAttribute("status", "failed");
                    model.addAttribute("message", "Not yet authorised by the manager!");
                }
            } else {
                model.addAttribute("status", "failed");
                model.addAttribute("message", "Invalid password");
            }
        } else {
            model.addAttribute("status", "failed");
            model.addAttribute("message", "Invalid username");
        }

        return "user_login";
    }

}
