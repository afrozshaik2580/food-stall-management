package com.afroz.foodstall.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.afroz.foodstall.entities.Item;
import com.afroz.foodstall.entities.Orders;
import com.afroz.foodstall.entities.Stall;
import com.afroz.foodstall.entities.User;
import com.afroz.foodstall.repository.ItemRepository;
import com.afroz.foodstall.repository.OrdersRepository;
import com.afroz.foodstall.repository.StallRepository;
import com.afroz.foodstall.repository.UserRepository;

import jakarta.websocket.Session;

@Controller
@SessionAttributes({"user","stall"})
public class UserController {
	
	private StallRepository stallRepository;
	private ItemRepository itemRepository;
	private UserRepository userRepository;
	private OrdersRepository ordersRepository; 
	
	
	public UserController(StallRepository stallRepository, ItemRepository itemRepository, UserRepository userRepository,
			OrdersRepository ordersRepository) {
		super();
		this.stallRepository = stallRepository;
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
		this.ordersRepository = ordersRepository;
	}

	@GetMapping("/userpage")
	public String userpage() {
		return "userpage";
	}
	
	@GetMapping("/userpage/stalls")
	public String allStalls(ModelMap model) {
		List<Stall>stalls=stallRepository.findAll();
		model.addAttribute("stalls",stalls);
		return "stalls";
	}
	
	@GetMapping("/userpage/stalls/{id}")
	public String stallMenu(@PathVariable int id,ModelMap model) {
		Optional<Stall> stall=stallRepository.findById(id);
		List<Item> items=stall.orElseGet(null).getMenu();
		model.addAttribute("stall",stall.orElseGet(null));
		model.addAttribute("items",items);
		return "menuofstall";
	}
	
	@GetMapping("/userpage/cart")
	public String cart(ModelMap model) {
		User user=(User)model.getAttribute("user");
		List<Item>items=user.getCart();
		model.addAttribute("items",items);
		double totalAmount=0;
		for(Item item:items) {
			totalAmount+=item.getPrice();
		}
		model.addAttribute("totalAmount",totalAmount);
		return "cart";
	}
	
	@GetMapping("userpage/stalls/{stallId}/addtocart/{itemId}")
	public String addItemToCart(@PathVariable int stallId, @PathVariable int itemId,ModelMap model) {
		Optional<Stall> stall=stallRepository.findById(stallId);
		User user=(User)model.getAttribute("user");
		Item item=itemRepository.findByIdoverride(itemId);
		List<Item>cart=user.getCart();
		cart.add(item);
		user.setCart(cart);
//		List<User >users=item.getUsers();
//		users.add(user);
//		item.setUsers(users);
//		itemRepository.save(item);
		userRepository.save(user);
		return "redirect:/userpage/stalls/"+ stall.orElseGet(null).getId();
	}
	
	@GetMapping("userpage/cart/remove/{id}")
	public String removeItemFromCart(@PathVariable int id,ModelMap model) {
		User user=(User)model.getAttribute("user");
		Item item=itemRepository.findByIdoverride(id);
		List<Item>cart=user.getCart();
		for(int i=0;i<cart.size();i++) {
			if(cart.get(i).getId()==item.getId()) {
				cart.remove(i);
				break;
			}
		}
		user.setCart(cart);
		userRepository.save(user);
		return "redirect:/userpage/cart";
	}
	
	@GetMapping("userpage/myDetails")
	public String myDetails() {
		return "myDetails";
	}
	
	@PostMapping("userpage/myDetails")
	public String addMoneyTomyDetails(double amount,ModelMap model) {
		User user=(User)model.getAttribute("user");
		double balance=user.getBalance();
		System.out.println(amount);
		balance+=amount;
		user.setBalance(balance);
		userRepository.save(user);
		return myDetails();
	}
	@GetMapping("userpage/cart/placeOrder")
	public String placeorder(ModelMap model) {
		User user=(User)model.getAttribute("user");
		double balance=user.getBalance();
		List<Item>items=user.getCart();
		double totalAmount=0;
		for(Item item:items) {
			totalAmount+=item.getPrice();
		}
		if(balance<totalAmount) {
			return "redirect:/userpage/cart?insufficientBalance";
		}
		Orders order=new Orders(user, items.get(0).getStall(), items);
		ordersRepository.save(order);
		return "redirect:/userpage/afterordersaved";
	}
	
	@GetMapping("/userpage/afterordersaved")
	public String afterOrderSaved(ModelMap model) {
		User user=(User)model.getAttribute("user");
		double balance=user.getBalance();
		List<Item>items=user.getCart();
		double totalAmount=0;
		for(Item item:items) {
			totalAmount+=item.getPrice();
		}
		balance-=totalAmount;
		user.setBalance(balance);
		items.clear();
		user.setCart(items);
		userRepository.save(user);
		ordersRepository.saveAll(null);
		return "redirect:/userpage?orderPlaced";
	}
	
	@GetMapping("userpage/myOrders")
	public String userOrders(ModelMap model) {
		User user=(User)model.getAttribute("user");
		List<Orders>orders=ordersRepository.getByUserId(user.getId());
		model.addAttribute("orders",orders);
		return "userorders";
	}
}
