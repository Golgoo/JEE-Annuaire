package annuaire.web;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import annuaire.model.Group;
import annuaire.model.Person;

@Controller
@RequestMapping(value = Mapper.groupControllerRoute)
public class GroupController extends BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(value = Mapper.groupListRoute)
    public ModelAndView groupList(
    		@RequestParam(required = false, name = "sort", defaultValue = "id_asc") String sortType,
    		@RequestParam(required = false, name = "pattern", defaultValue =  "") String pattern
    	) {
    	ArrayList<Group> groups = new ArrayList<Group>(directoryManager.findAllGroup(pattern));
    	entitySorter.sortGroups(groups, sortType);
    	return new ModelAndView("group_list", "groups", groups);
    }
        
    @ModelAttribute("group")
    public Group addGroupToModel(
    			@RequestParam(value = "id", required = false) Long groupId
    	) {
    	if(groupId != null){
    		return directoryManager.findGroup(groupId);
    	}
    	return null;
    }
    
    @RequestMapping(value = Mapper.groupShowRoute)
    public ModelAndView show(
    		@RequestParam(required = false, name = "sort", defaultValue = "id_asc") String sortType,
    		@RequestParam(required = false, name = "pattern", defaultValue = "") String pattern,
    		@ModelAttribute("group") Group g
    		) {
    	if(g != null) {
    		ArrayList<Person> persons = new ArrayList<Person>(directoryManager.findAllPersonsFromGroup(g.getId(), pattern));
    		entitySorter.sortPersons(persons, sortType);
    		g.setMembers(new LinkedHashSet<Person>(persons));
    	}
    	return new ModelAndView("group");
    }
}
