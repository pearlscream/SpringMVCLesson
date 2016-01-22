package com.budko.springmvc.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.budko.springmvc.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
public class HomeController {

    private static final int WEAK_STRENGHT = 1;
    private static final int FEAR_STRENGHT = 5;
    private static final int STRONG_STRENGHT = 7;


    @Autowired
    MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@ModelAttribute User user,Locale locale, ModelMap modelMap) {
        return "home";
    }

    @ModelAttribute
    public User createUser() {
        return new User("login");
    }

    @RequestMapping(value = "check-User",method = RequestMethod.POST)
    public String chekUser(@Valid @ModelAttribute User user, BindingResult result, ModelMap modelMap, RedirectAttributes attributes,Locale locale) {
        logger.info("check-User");
        if (result.hasErrors()) {

            return "home";
        }
        attributes.addFlashAttribute("locale",messageSource.getMessage("locale",new String[] {locale.getDisplayName(locale)},locale));
        return "redirect:/redirectController";

    }

    @RequestMapping(value = "redirectController",method = RequestMethod.GET)
    public String redirectController() {
        return "main";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public ModelAndView logout(@ModelAttribute User user, SessionStatus session) {
        session.setComplete();
        user.setName("logout");
        return new ModelAndView("home","user",user);
    }

    @RequestMapping(value = "/get-json-user/{name}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public User getJSONUser(@PathVariable("name") String name) {
        User user = new User();
        user.setName("fdsa");
        return user;
    }

    @RequestMapping(value = "/set-json-user",method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<String> setJSONUser(@RequestBody User user) {
        logger.info(user.getName());
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/checkStrenght",method = RequestMethod.GET,produces = {"text/html; charset=UTF-8"})
    public @ResponseBody
    String checkStrenght(@RequestParam String password) {

        String result = "<span style=\"color:%s; font-weight:bold;\">%s</span>";
        if (password.length() >= WEAK_STRENGHT && password.length() <= FEAR_STRENGHT) {
            return String.format(result,"red","Слабый");
        } else if (password.length() >= FEAR_STRENGHT && password.length() <= STRONG_STRENGHT) {
            return String.format(result,"yellow","Средний");
        } else if (password.length() >= STRONG_STRENGHT) {
            return String.format(result,"green","Сильный");
        }
        return "";
    }

}
