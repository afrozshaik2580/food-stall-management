package com.afroz.foodstall.controllers;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.afroz.foodstall.entities.Item;
import com.afroz.foodstall.entities.Stall;
import com.afroz.foodstall.entities.User;
import com.afroz.foodstall.repository.ItemRepository;
import com.afroz.foodstall.repository.StallRepository;
import com.afroz.foodstall.repository.UserRepository;

import jakarta.validation.Valid;



@Controller
@SessionAttributes({"user","stall"})
public class LoginController {

	private UserRepository userRepository;
	private StallRepository stallRepository;
	private ItemRepository itemRepository;
	private PasswordEncoder passwordEncoder;
	
	public LoginController(UserRepository userRepository, StallRepository stallRepository,
			ItemRepository itemRepository, PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.stallRepository = stallRepository;
		this.itemRepository = itemRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/index")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/loginAsUser")
	public String userloginPage() {
		return "loginAsUser";
	}
	
	@PostMapping("/loginAsUser")
	public String UserLogin(String username,String password,ModelMap model) {
		User user=userRepository.findByPhone(username);
		if(passwordEncoder.matches(password, user.getPassword())) {
			model.addAttribute("user", user);
			return "redirect:/userpage";
		}
		return "redirect:loginAsUser";
	}
	
	
	@GetMapping("/registerUser")
	public String userRegisterPage(User user) {
		return "registerUser";
	}
	
	@PostMapping("/registerUser")
	public String saveuser(@Valid User user, BindingResult result, String repass) {
		if(result.hasErrors()) {
			return "registerUser";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/loginAsStall")
	public String stallloginPage() {
		return "loginAsStall";
	}
	
	@PostMapping("/loginAsStall")
	public String StallLogin(String username,String password,ModelMap model) {
		Stall stall=stallRepository.findByOwnerEmail(username);
		if(passwordEncoder.matches(password, stall.getPassword())) {
			model.addAttribute("stall", stall);
			List<Item>items=itemRepository.getItemsByStallid(stall.getId());
			model.addAttribute("items",items);
			return "redirect:/stallpage";
		}
		return "redirect:loginAsStall";
	}
	
	
	@GetMapping("/registerStall")
	public String stallRegisterPage(ModelMap model) {
		model.addAttribute("stall", new Stall(null, null, null, null, null, null, null));
		return "registerStall";
	}
	
	@PostMapping("/registerStall/saveStall")
	public String stallRegister(@Valid Stall stall, BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "registerStall";
		}
		stall.setPassword(passwordEncoder.encode(stall.getPassword()));
		stallRepository.save(stall);
		return "redirect:/index";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/index";
	}
}
