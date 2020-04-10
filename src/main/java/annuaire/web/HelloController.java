package annuaire.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController extends BaseController{

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        return new ModelAndView("index");
    }
    
    @RequestMapping("/annuaire")
    public ModelAndView hello(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        return new ModelAndView("annuaire");
    }
}