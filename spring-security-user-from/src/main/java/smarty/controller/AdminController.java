package smarty.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import smarty.model.User;
import smarty.repository.UserRepository;

@Controller
public class AdminController {
	@Autowired UserRepository userRepository;
	@Autowired  BCryptPasswordEncoder passwordEncode;
	
	@GetMapping("")
	public String homePage()
	{
		return "home";
	}
	
	@RequestMapping(value = "/addUser")
	public String loginProcess()
	{
		return "show-form";
		
	}
	
	@RequestMapping(value = "/addUserProcess",method = RequestMethod.POST)
	public String addUserProcess(Model model,User user,HttpSession session)
	{
		user.setPassword(passwordEncode.encode(user.getPassword()));
		userRepository.save(user);
		session.setAttribute("message", "Registration Success");
		
		
		return "redirect:/addUser";
		
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String myLogin()
	{
		System.out.println("login page ..calling");
		return "login";
		
	}
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String mydisplay(User user)
	{
		
		return "redirect:dashboard";
		
	}
	
	
	
	@RequestMapping("/dashboard")
	public String myDashboard()
	{
		return "dashboard";
		
	}
	
	
	
	
}
