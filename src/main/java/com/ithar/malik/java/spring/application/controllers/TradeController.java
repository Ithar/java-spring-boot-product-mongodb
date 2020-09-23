package com.ithar.malik.java.spring.application.controllers;

import com.ithar.malik.java.spring.application.TradeValidator;
import com.ithar.malik.java.spring.application.commands.ProductForm;
import com.ithar.malik.java.spring.application.converters.ProductToProductForm;
import com.ithar.malik.java.spring.application.domain.Product;
import com.ithar.malik.java.spring.application.domain.Trade;
import com.ithar.malik.java.spring.application.services.ProductService;
import com.ithar.malik.java.spring.application.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TradeController {

    private final TradeValidator tradeValidator;
    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeValidator tradeValidator, TradeService tradeService) {
        this.tradeValidator = tradeValidator;
        this.tradeService = tradeService;
    }

    @GetMapping({"/", "/trade"})
    public String listProducts(Model model){
        model.addAttribute("trade", new Trade());
        model.addAttribute("trades", tradeService.listAll());
        return "trade/listing";
    }

    @GetMapping("/trade/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("trade", tradeService.getById(id));
        model.addAttribute("trades", tradeService.listAll());
        return "trade/listing";
    }

    @PostMapping(value = "/create")
    public String createOrUpdateTrade(@Valid Trade trade, BindingResult bindingResult){

        if(bindingResult.hasErrors() || !tradeValidator.validate(trade, bindingResult)) {
            return "trade/listing";
        }

        tradeService.saveOrUpdate(trade);

        return "redirect:/trade";
    }

    @GetMapping("/trade/delete/{id}")
    public String delete(@PathVariable String id){
        tradeService.delete(id);
        return "redirect:/trade";
    }
}
