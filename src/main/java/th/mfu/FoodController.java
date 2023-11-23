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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import th.mfu.Domain.Buyer;
import th.mfu.Domain.CustOrder;
import th.mfu.Domain.Rider;
import th.mfu.Domain.CustOrder;
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


    ///show login or signup page
    @GetMapping("/start")
    public String showStartPage(Model model) {
        return "choose-login-signup";
    }

    ////Sign up role choose
    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        return "signup-role-choose";
    }

    ////Login role choose
    @GetMapping("/login")
    public String showLoginPage(Model model) {
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
    public String saveBuyer(@ModelAttribute Buyer buyer, Model model){
        buyerRepo.save(buyer);
        Iterable<Buyer> buyerlist = buyerRepo.findAll();
        model.addAttribute("buyer", buyerlist);
        return"choose-login-signup";
    }

    //to show buyer login form
    @GetMapping("/login-buyer")
    public String showBuyerLoginForm(Model model) {
        model.addAttribute("buyer", new Buyer());
        return "buyer-login";
    }

    //to login for buyer
    @PostMapping("/login-buyer")
    public String buyerLogin(@ModelAttribute Buyer buyer, Model model) {
        if(buyerRepo.existsByEmail(buyer.getEmail())==true){
            Buyer existBuyer = buyerRepo.findByEmail(buyer.getEmail());
            if (buyer.getPassword().equals(existBuyer.getPassword())) {
                return"buyer.html";
            }
            else {
                return "login-role-choose";
            }
        } else {
            return "login-role-choose";
        }
    } 
        

    //to show all shops for buyer to browse (same for showing discount, popular and delivery free items)
    @GetMapping("/buyer-page")
    public String showBuyerPage(Model model) {
        return "buyer";
    }

    //to show the menu of selected shop
    @GetMapping("/buyer-detail") 
    public String showBuyerDetailPage(Model model) {
        model.addAttribute("buyer", new Buyer());
        model.addAttribute("newOrder", new CustOrder());
        return "buyerDetail";
    }

    @GetMapping("/buyer-detail2") 
    public String showBuyerDetailPage2(Model model) {
        model.addAttribute("buyer", new Buyer());
        model.addAttribute("newOrder", new CustOrder());
        return "buyerDetail2";
    }

    @GetMapping("/buyer-detail3") 
    public String showBuyerDetailPage3(Model model) {
        model.addAttribute("buyer", new Buyer());
        model.addAttribute("newOrder", new CustOrder());
        return "buyerDetail3";
    }

    //to make order

    @PostMapping("/make-order")
    public String makeOrder(Model model, @ModelAttribute Buyer buyer, @ModelAttribute CustOrder newOrder) {
        Buyer existBuyer = buyerRepo.findByPassword(buyer.getPassword());
        if (buyer.getPassword().equals(existBuyer.getPassword())) {
            existBuyer.setCustOrder(newOrder);
            custOrderRepo.save(newOrder);
            //will take to thank you page
            return "thankYou";
        }
        //will take back to order detail page
        return "buyerDetail";
    }

    @PostMapping("/make-order2")
    public String makeOrder2(Model model, @ModelAttribute Buyer buyer, @ModelAttribute CustOrder newOrder) {
        Buyer existBuyer = buyerRepo.findByPassword(buyer.getPassword());
        if (buyer.getPassword().equals(existBuyer.getPassword())) {
            existBuyer.setCustOrder(newOrder);
            custOrderRepo.save(newOrder);
            //will take to thank you page
            return "thankYou";
        }
        //will take back to order detail page
        return "buyerDetail2";
    }

    @PostMapping("/make-order3")
    public String makeOrder3(Model model, @ModelAttribute Buyer buyer, @ModelAttribute CustOrder newOrder) {
        Buyer existBuyer = buyerRepo.findByPassword(buyer.getPassword());
        if (buyer.getPassword().equals(existBuyer.getPassword())) {
            existBuyer.setCustOrder(newOrder);
            custOrderRepo.save(newOrder);
            //will take to thank you page
            return "thankYou";
        }
        //will take back to order detail page
        return "buyerDetail3";
    }

    //to show thankyou page
    /*@GetMapping("/thank-you")
    public String showThankYouPage(Model model) {
        return "thankYou";
    }*/
    

    
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
    public String saveRider(@ModelAttribute Rider rider, Model model){
        riderRepo.save(rider);
        return"choose-login-signup";
    }

    //to show rider login form
    @GetMapping("/login-rider")
    public String showRiderLoginForm(Model model) {
        model.addAttribute("rider", new Rider());
        return "rider-login";
    }

    //to login for rider
    @PostMapping("/login-rider")
    public String riderLogin(@ModelAttribute Rider rider, Model model) {
        if(riderRepo.existsByEmail(rider.getEmail())==true){
            Rider existRider = riderRepo.findByEmail(rider.getEmail());
            if (rider.getPassword().equals(existRider.getPassword())) {
                return"rider.html";
            }
            else {
                return "login-role-choose";
            }
        } else {
            return "login-role-choose";
        }
    }
    //to show deliveries 
    @GetMapping("/rider-page")
    public String showAllDeliveries(Model model) {
        model.addAttribute("orders", custOrderRepo.findAll());
        return "rider";
    }

    //to show delivery details
    @GetMapping("/delivery-detail")
    // id is rider id
    public String showDeliveryDetails(@PathVariable Long id, Model model) {
        model.addAttribute("deliveryDetails", custOrderRepo.findById(id));
        return "destination";
    }
}