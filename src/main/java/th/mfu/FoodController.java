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

    //to create buyer account
    @GetMapping
    public String addABuyerSingupForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "";
    }

    //to save buyer account
    @PostMapping
    public String saveBuyer(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }
     
    //to create seller account
    @GetMapping
    public String addASellerSingupForm(Model model){
        model.addAttribute("seller", new Seller());
        return "";
    }

    //to save seller account
    @PostMapping
    public String saveSeller(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }
     
    //to add rider account
    @GetMapping
    public String addARiderSingupForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "";
    }

    //to save rider account
    @PostMapping
    public String saveRider(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }

    @Transactional
    //to login for each type of user
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

    //to show menu for buyer to browse
    @GetMapping("/buyer-homepage")
    public String listSeller (Model model) {
        model.addAttribute("sellers", sellerRepo.findAll());
        return "";
    }

    //to add items to the cart
   @GetMapping("/buyer-add-food/{id}") 
   public String addToCart (@ModelAttribute Buyer buyer, @PathVariable Long id,Model model) {
    model.addAttribute("cartItem", new Item());
    Item item = itemRepo.findById(id).get();
    buyer.getCart().add(item);
    return ""; //redirect sthhhh
   }

}
