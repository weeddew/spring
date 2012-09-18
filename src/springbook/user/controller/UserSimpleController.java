package springbook.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import springbook.user.domain.User;
import springbook.user.service.UserService;

public class UserSimpleController implements Controller {

	@Autowired
	UserService userService;

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		List<User> users = userService.getAll();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users", users);

		return new ModelAndView("/WEB-INF/view/user.jsp", model);
	}

}
