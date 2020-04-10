package annuaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import annuaire.business.IDirectoryManager;
import annuaire.business.IEntitySorter;

public class BaseController {

	@Autowired
	protected IDirectoryManager directoryManager ;
	
	@Autowired
	protected IEntitySorter entitySorter;
		
	@ModelAttribute("isLogged")
	public Boolean isLogged() {
		return directoryManager.getUser().getPersonId() != null;
	}
		
}
