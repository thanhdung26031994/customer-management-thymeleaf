package org.example.customermanagementthymeleaf.controller;

import org.example.customermanagementthymeleaf.model.Customer;
import org.example.customermanagementthymeleaf.service.CustomerService;
import org.example.customermanagementthymeleaf.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private static final ICustomerService customerService = new CustomerService();
    @GetMapping("")
    public String index(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customer", new Customer());
        return "/create";
    }
    @PostMapping("/save")
    public String save(Customer customer){
        customer.setId((int) (Math.random()*10000));
        customerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "/update";
    }
    @PostMapping("/update")
    public String update(Customer customer){
        customerService.update(customer.getId(), customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes ra){
        customerService.remove(customer.getId());
        ra.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
}
