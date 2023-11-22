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
import th.mfu.Domain.Rider;
import th.mfu.Domain.custOrder;
import th.mfu.Domain.Item;

@Controller
public class FoodController {
    
    @Autowired
    BuyerRepository buyerRepo;

    @Autowired
    RiderRepository riderRepo;

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    custOrderRepository custOrderRepo;


    ////Sign up role choose
    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup-role-choose";
    }

    ////Login role choose
    @GetMapping("/login")
    public String showLoginPage() {
        return "login-role-choose";
    }

    /////////////////////////////////Buyer

    //to create buyer account
    @GetMapping("/buyer-signup")
    public String addABuyerSingupForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "buyer-signup-form";
    }

    //to save buyer account
    @PostMapping("/save-buyer")
    public String saveBuyer(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"redirect:/login";
    }

    //to show buyer login form
    @GetMapping("/buyer-login")
    public String showBuyerLoginForm() {
        return "buyer-login";
    }

    @Transactional
    //to login for buyer
    @GetMapping("/buyers")
    public String buyerLogin(@PathVariable String email, String password) {
        Buyer buyer = buyerRepo.findByEmail(email);
        if (buyer.getPassword().equals( password)) {
                return "buyer-page";
            }
        return "redirect:/login";
    
        
        
        /*if(buyerRepo.existsByEmail(email) == true) {
            Buyer buyer = buyerRepo.findByEmail(email);
            if (buyer.getPassword().equals( password)) {
                return "buyer-page";
            }
            return "login-role-choose";
        }
        else if(riderRepo.existsByEmail(email) == true) {
            Rider rider = riderRepo.findByEmail(email);
            if(rider.getPassword().equals(password)){
                return"rider-page";
            }
            return "login-role-choose";
        }
        else {
            return "login-role-choose";
        }*/
    }

    //to show all shops for buyer to browse (same for showing discount, popular and delivery free items)
    @GetMapping("/buyer-page")
    public String listSeller () {
        return "buyer";
    }

    
   //********************************************* */
   //to make payment(to move items from cart to order)
   /*@GetMapping("")
   public String makeOrder(@ModelAttribute Buyer buyer, @PathVariable Long id, Model model) {
        //add an order
        Order order = new Order();

        //add items to orderItem
        List<Item> cartItems = buyer.getCart();

        if(!cartItems.isEmpty()){
            List<OrderItem> orderItems = new ArrayList<>();

            for (Item cartItem : cartItems){
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(cartItem);
                orderItems.add(orderItem);
            }
            order.setOrderItem((OrderItem) orderItems);
            orderRepo.save(order);

            buyer.setOrder(order);
            buyer.getCart().clear();
            buyerRepo.save(buyer);

            List<Rider> availableRiders = (List<Rider>) riderRepo.findAll();

            if(!availableRiders.isEmpty()){
                int randomIndex = (int) (Math.random() * availableRiders.size());
                Rider randomRider = availableRiders.get(randomIndex);
                order.setRider(randomRider);
                orderRepo.save(order);
            }
        }
        return ""; // thank you page
   }*/



   //////////////////////////////RIDER


    //to add rider account
    @GetMapping("/rider-signup")
    public String addARiderSingupForm(Model model){
        model.addAttribute("rider", new Rider());
        return "rider-signup-form";
    }

    //to save rider account
    @PostMapping("/save-rider")
    public String saveRider(@ModelAttribute Rider rider){
        riderRepo.save(rider);
        return"redirect:/login";
    }

    //to show rider login form
    @GetMapping("/rider-login")
    public String showRiderLoginForm() {
        return "rider-login";
    }

    //to login for rider
    @GetMapping("/riders")
    public String riderLogin(@PathVariable String email, String password) {
        Rider rider = riderRepo.findByEmail(email);
        if (rider.getPassword().equals( password)) {
                return "rider-page";
            }
        return "redirect:/login";
        }
    //to show deliveries 
    /*@GetMapping("")
    public String showAllDeliveries(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "";
    }*/

    //to show delivery details
    /*@GetMapping("")
    // id is rider id
    public String showDeliveryDetails(@PathVariable Long id, Model model) {
        model.addAttribute("deliveryDetails", orderRepo.findById(id));
        return "";
    }*/
}