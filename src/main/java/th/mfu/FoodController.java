package th.mfu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.Domain.Buyer;
import th.mfu.Domain.Seller;
import th.mfu.Domain.Rider;
import th.mfu.Domain.Order;
import th.mfu.Domain.Item;
import th.mfu.Domain.OrderItem;


@Controller
public class FoodController {
    
    @Autowired
    BuyerRepository buyerRepo;

    @Autowired
    SellerRepository sellerRepo;

    @Autowired
    RiderRepository riderRepo;

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderItemRepository OrderItemRepo;

    //BUYER
    @GetMapping
    public String addABuyerSingupForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "";
    }

    @PostMapping
    public String saveBuyer(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }
     
    @GetMapping
    public String addASellerSingupForm(Model model){
        model.addAttribute("seller", new Seller());
        return "";
    }

    @PostMapping
    public String saveSeller(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }
     
    @GetMapping
    public String addARiderSingupForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "";
    }

    @PostMapping
    public String saveRider(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }

    @Transactional
    @GetMapping("/login/{id}")
    public String login(@PathVariable Long id) {
        if(buyerRepo.existsById(id) == true) {
            return "";
        }
        else if(sellerRepo.existsById(id) == true) {
            return "";
        }
        else if(riderRepo.existsById(id) == true) {
            return "";
        }
        else {
            return "";
        }
    }

    @GetMapping("/buyer-homepage")
    public String listSeller (Model model) {
        model.addAttribute("sellers", sellerRepo.findAll());
        return "";
    }

   //addtocart lab04 
}
