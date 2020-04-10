package annuaire.web;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import annuaire.dao.IPersonDAO;
import annuaire.model.Group;

public class GroupeEditor extends PropertyEditorSupport{
	
	//Cannot autowired outside spring context ( GroupController.initBinder )
	private IPersonDAO dao ;
	
	public GroupeEditor(IPersonDAO dao) {
		super();
		this.dao = dao ;
	}
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
    public String getAsText() {
        Object o = this.getValue();
        if (o instanceof Group) {
        	Group g = (Group) o;
            return g.getNom();
        }
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	logger.info("Inputted text : " + text);
        try {
        	Group g = null;
        	long id = Long.parseLong(text);
        	try {
        		if(dao == null) {
        			logger.info("Dao is null");
        		}
        		g = dao.findGroup(id);
        		
        	}catch (Exception e) {
				e.printStackTrace();
			}
        	
            logger.info("Group is nill : " + (g == null));
            super.setValue(g);
        } catch (Exception e) {
        	logger.info("Our exception");
        	e.printStackTrace();
        	logger.info("End of our exception");
            throw new IllegalArgumentException("Group inexistant");
        }
    }
}
