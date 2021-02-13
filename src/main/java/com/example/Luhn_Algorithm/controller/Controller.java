package com.example.Luhn_Algorithm.controller;

import com.example.Luhn_Algorithm.service.CreditCardValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @Autowired
    private CreditCardValidationImpl creditCardValidation;

    @ResponseBody
    @RequestMapping(value = "home", method = RequestMethod.POST)
    public String home(@RequestParam(value = "cardNo", required = true) String cardNo, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "CVV", required = true) String CVV, @RequestParam(value = "expiry_month_year", required = true) String expiry_month_year) throws Exception {
        if (creditCardValidation.isCardValid(cardNo, name, CVV, expiry_month_year)) {
            return "Card details are valid.";
        } else {
            return "Card details are invalid.";
        }
    }

}
