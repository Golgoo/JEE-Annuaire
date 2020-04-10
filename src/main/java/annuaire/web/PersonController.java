package annuaire.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import annuaire.dao.IPersonDAO;
import annuaire.model.Group;
import annuaire.model.Person;

@Controller
@RequestMapping(value = Mapper.personControllerRoute)
public class PersonController extends BaseController{

	protected final Log logger = LogFactory.getLog(getClass());
		
	@RequestMapping(value = Mapper.personListRoute)
    public ModelAndView personList(
    		@RequestParam(required = false, name = "sort", defaultValue = "id_asc") String sortType,
    		@RequestParam(required = false, name = "pattern", defaultValue = "") String pattern
    	) {
		ArrayList<Person> persons = new ArrayList<Person>(directoryManager.findAllPersons(pattern));
		entitySorter.sortPersons(persons, sortType);
    	return new ModelAndView("person_list", "persons", persons);
    }
    
    @ModelAttribute("person")
    public Person addPersonToModel(
    			@RequestParam(value = "id", required = false) Long personId
    	) {
    	if(personId != null){
    		return directoryManager.findPerson(personId);
    	}
    	return null;
    }
    
    @Autowired
    private IPersonDAO dao ;
    
    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Group.class, new GroupeEditor(dao));
        b.registerCustomEditor(Date.class, new BirthdayEditor());
    }
    
    @RequestMapping(value = Mapper.personShowRoute)
    public ModelAndView personShow(@ModelAttribute("person") Person p) {
    	boolean editable = false ;
    	if(p != null && directoryManager.getUser().getPersonId() != null) {
    		if(directoryManager.getUser().getPersonId() == p.getId()) {
    			editable = true;
    		}
    	}
    	return new ModelAndView("person", "editable", editable);
    }
        
    @RequestMapping(value = Mapper.personEditRoute, method = { RequestMethod.GET })
	public ModelAndView editPerson(@ModelAttribute Person p) {
    	return new ModelAndView("personForm", "groups", groupList());
	}
    
    @RequestMapping(value = Mapper.personEditRoute, method = RequestMethod.POST)
    public ModelAndView savePerson(@ModelAttribute @Valid Person p, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("personForm", "groups", groupList());
        }
        logger.info("Id session : " + directoryManager.getUser().getPersonId() + " Id update : " + p.getId());
        directoryManager.savePerson(p);
        return new ModelAndView("redirect:" + Mapper.personControllerRoute + Mapper.personShowRoute, "id", p.getId());
    }
    
    private Map<Long, Group> groupList() {
        Map<Long, Group> types = new LinkedHashMap<>();
        Collection<Group> groups = directoryManager.findAllGroup();
        for(Group g : groups) {
        	types.put(g.getId(), g);
        }
        return types;
    }
}
