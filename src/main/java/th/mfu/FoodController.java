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

    /////////////////////////////////Buyer

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

    @Transactional
    //to login for each type of user
    @GetMapping("/login/")
    public String login(@PathVariable String email, String password) {
        if(buyerRepo.existsByEmail(email) == true) {
            Buyer buyer = buyerRepo.findByEmail(email);
            if (buyer.getPassword().equals( password)) {
                return "buyer-page";
            }
            return "redirect:/login/";
        }
        else if(sellerRepo.existsByEmail(email) == true) {
            Seller seller = sellerRepo.findByEmail(email);
            if(seller.getPassword().equals(password)){
                return"seller-page";
            }                

            return "redirect:/login/";
        }
        else if(riderRepo.existsByEmail(email) == true) {
            Rider rider = riderRepo.findByEmail(email);
            if(rider.getPassword().equals(password)){
                return"rider-page";
            }
            return "redirect:/login/";
        }
        else {
            return "redirect:/login/";
        }
    }

    //to show all shops for buyer to browse (same for showing discount, popular and delivery free items)
    @GetMapping("/buyer-page")
    public String listSeller (Model model) {
        model.addAttribute("sellers", sellerRepo.findAll());
        return "";
    }

    //Check line 151
    //to show items of the selected shop
    @GetMapping("")
    //id is seller id
    public String showItems (@PathVariable Long id, Model model) {
        model.addAttribute("shopitems", itemRepo.findAllById(id));
        return "";
    }

    //to add items to the cart
   /*@GetMapping("/buyer-add-food/{id}") 
   //id is item id
   public String addToCart (@ModelAttribute Buyer buyer, @PathVariable Long id,Model model) {
        model.addAttribute("cartItem", new Item());
        //how to get the id of the item the user clicked??
        Item item = itemRepo.findById(id).get();
        buyer.getCart().add(item);
        return ""; //redirect sthhhh
   }*/

   //to view cart
   /*@GetMapping("/cart-list/")
   public String viewCart(@PathVariable Long id, Model model) {
        //find the user by the user id
        Buyer cartBuyer = buyerRepo.findById(id).get();
        //loop through the cart of that user
        if(cartBuyer.getCart() != null){
            model.addAttribute("cartItems", cartBuyer.getCart());
        }
        return "redirect:/cart-list/";
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



   //////////////////////////////Seller

   //to create seller account
    @GetMapping
    public String addASellerSingupForm(Model model){
        model.addAttribute("seller", new Seller());
        return "";
    }

    //to save seller account
    @PostMapping
    public String saveSeller(@ModelAttribute Seller seller){
        sellerRepo.save(seller);
        return"";
    }

    //Check line 94
    //to view the seller's shop menu
    @GetMapping("")
    public String viewShopMenu(@PathVariable Long id, Model model) {
        model.addAttribute("shopItems", itemRepo.findAllById(id));
        return "";
    }

    //to create new item
    @GetMapping("")
    public String addItemCreateForm(Model model) {
        model.addAttribute("item", new Item());
        return "";
    }

    //to save new item
    // id is seller id
    /*@PostMapping("")
    public String saveItem(@ModelAttribute Item item, @PathVariable Long id) {
        Seller seller = sellerRepo.findById(id).orElse(null);
        seller.setItem(item);
        itemRepo.save(item);
        return "";
    }*/

    //to delete item from shop menu
    @GetMapping("")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
        return "";
    }

    //to show orders
    @GetMapping("")
    public String showOrders(@PathVariable Long id, Model model) {
        model.addAttribute("orders", orderRepo.findAllById(id));
        return "";
    }


    //accepting order is enough with just HTML

    //to deny customer's order
    @GetMapping("")
    // id is item id
    public String denyOrder(@PathVariable Long id) {
        orderRepo.deleteById(id);
        return "";
    }

    /////////////////////////////////////Rider
    //to add rider account
    @GetMapping("")
    public String addARiderSingupForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "";
    }

    //to save rider account
    @PostMapping
    public String saveRider(@ModelAttribute Rider rider){
        riderRepo.save(rider);
        return"";
    }

    //to show deliveries 
    @GetMapping("")
    public String showAllDeliveries(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "";
    }

    //to show delivery details
    @GetMapping("")
    // id is rider id
    public String showDeliveryDetails(@PathVariable Long id, Model model) {
        model.addAttribute("deliveryDetails", orderRepo.findById(id));
        return "";
    }
}