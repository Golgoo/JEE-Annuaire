package annuaire.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import annuaire.dao.IPersonDAO;
import annuaire.model.Person;

@Controller
public class AuthController extends BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IPersonDAO dao;
	
	@RequestMapping(value = Mapper.logginRoute, method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String email, @RequestParam String password, Model model) {
		//Named query find user from email
		Person p = dao.findPerson(email);
		if(p == null || !directoryManager.login(p.getId(), password)) {
			return new ModelAndView("redirect:/login", "invalid_auth", true);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = Mapper.logginRoute, method = RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(required = false, value = "invalid_auth") Boolean invalid_auth) {
		return new ModelAndView("loginForm", "invalid_auth", invalid_auth);
	}
	
	@RequestMapping(value = Mapper.loggoutRoute)
	public String logout() {
		directoryManager.logout();
		return "redirect:/";
	}
}
