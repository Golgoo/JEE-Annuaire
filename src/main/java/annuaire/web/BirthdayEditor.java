package annuaire.web;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BirthdayEditor extends PropertyEditorSupport{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
    public String getAsText() {
        Object o = this.getValue();
        if (o instanceof Date) {
        	Date d = (Date) o;
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(d);
        	int year = cal.get(Calendar.YEAR);
        	int month = cal.get(Calendar.MONTH);
        	int day = cal.get(Calendar.DAY_OF_MONTH);
        	return year + "-" + month+1 + "-" + day ;
        }
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	logger.info("Inputted text : " + text);
        try {
        	Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(text);  
        	super.setValue(date1);
        } catch (Exception e) {
        	logger.info("Our exception");
        	e.printStackTrace();
        	logger.info("End of our exception");
            throw new IllegalArgumentException("Date Invalide");
        }
    }
}
