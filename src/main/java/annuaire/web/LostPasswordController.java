package annuaire.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import annuaire.business.ILostPasswordService;


@Controller
@RequestMapping(value = Mapper.lostPasswordControllerRoute)
public class LostPasswordController {
	protected final Log logger = LogFactory.getLog(getClass());
		
	@Autowired
	private ILostPasswordService lostPasswordService ;
	
	@ModelAttribute("error")
	public boolean setErrorFlag(@RequestParam(required = false, value="error", defaultValue = "false") boolean error) {
		return error;
	}
	
	@RequestMapping(value = Mapper.lostPasswordRoute, method = { RequestMethod.GET })
    public ModelAndView lostPwGet() {
		return new ModelAndView(lostPasswordService.getNextStep(), "question", lostPasswordService.getSecretQuestion());
    }
	
	@RequestMapping(value = Mapper.lostPasswordRoute, method = { RequestMethod.POST })
	public ModelAndView lostPwPost(
			@RequestParam(value = "newpassword", required = false) String newPassword,
			@RequestParam(value = "answer", required = false) String answer,
			@RequestParam(value = "email", required = false) String email
		) {
		boolean sucess = false;
		if(newPassword != null) {
			sucess = lostPasswordService.updatePassword(newPassword);
		}else if(answer != null) {
			sucess = lostPasswordService.updateSecretAnswer(answer);
		}else if(email != null) {
			sucess = lostPasswordService.updateEmail(email);
		}
		return new ModelAndView("redirect:" + Mapper.lostPasswordControllerRoute + Mapper.lostPasswordRoute, "error", ! sucess);
	}
	
	@RequestMapping(value = Mapper.lostPasswordCancelRoute)
	public String cancel() {
		lostPasswordService.clear();
		return "redirect:" + Mapper.logginRoute;
	}
}
