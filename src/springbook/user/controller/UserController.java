package springbook.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import springbook.user.domain.User;
import springbook.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/user/getAll")
	public String getAll(Model model) {
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "/WEB-INF/view/user.jsp";
	}
}
