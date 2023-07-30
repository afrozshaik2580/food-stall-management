package com.afroz.foodstall.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.afroz.foodstall.entities.Item;
import com.afroz.foodstall.entities.Orders;
import com.afroz.foodstall.entities.Stall;
import com.afroz.foodstall.repository.ItemRepository;
import com.afroz.foodstall.repository.OrdersRepository;
import com.afroz.foodstall.repository.StallRepository;

import jakarta.validation.Valid;

@Controller
@SessionAttributes({"user","stall"})
public class StallController {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private StallRepository stallRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	
	@GetMapping("/stallpage")
	public String stallpage(ModelMap model) {
		Stall stall=(Stall)model.getAttribute("stall");
		List<Item>items=itemRepository.getItemsByStallid(stall.getId());
		model.addAttribute("items",items);
		return "stallpage";
	}
	
	@GetMapping("stallpage/additems")
	public String additempage(ModelMap model) {
		model.addAttribute("item",new Item(null, null, 0, null, null, null));
		return "additem";
	}
	
	@PostMapping("stallpage/additems")
	public String additem(@Valid Item item,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "additem";
		}
		Stall stall=(Stall) model.getAttribute("stall");
		List<Item> items=stall.getMenu();
		items.add(item);
		stall.setMenu(items);
		item.setStall(stall);
		stallRepository.save(stall);
		itemRepository.save(item);
		return "redirect:/stallpage";
	}
	
	@GetMapping("stallpage/edititem/{id}")
	public String edititempage(@PathVariable Integer id,ModelMap model) {
		Item item=itemRepository.findByIdoverride(id);
		model.addAttribute("item",item);
		return "additem";
	}
	@PostMapping("stallpage/edititem/{id}")
	public String updateditem(@PathVariable Integer id,@Valid Item item,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "additem";
		}
		Stall stall=(Stall)model.getAttribute("stall");
		item.setStall(stall);
		itemRepository.save(item);
		return "redirect:/stallpage";
	}
	
	@GetMapping("stallpage/removeitem/{id}")
	public String removeitem(@PathVariable Integer id,ModelMap model) {
		Stall stall=(Stall)model.getAttribute("stall");
		List<Item>items=stall.getMenu();
		for(Item item : items) {
			if(item.getId()==id) {
				items.remove(item);
				break;
			}
		}
		stallRepository.save(stall);
		itemRepository.deleteById(id);
		return "redirect:/stallpage";
	}
	
	@GetMapping("stallpage/orders")
	public String stallorders(ModelMap model) {
		Stall stall=(Stall)model.getAttribute("stall");
		List<Orders> orders=ordersRepository.findByStall_id(stall.getId());
		model.addAttribute("orders",orders);
		return "stallorders";
	}
	
	@GetMapping("stallpage/order/completed/{id}")
	public String ordercompleted(@PathVariable int id, ModelMap model) {
		Optional<Orders> orders=ordersRepository.findById(id);
		orders.orElseGet(null).setStatus(1);
		ordersRepository.save(orders.get());
		return "redirect:/stallpage/orders";
	}
	
	
	
	
	
	
	
	
	
	
}
