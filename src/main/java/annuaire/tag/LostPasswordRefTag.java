package annuaire.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import annuaire.web.Mapper;

public class LostPasswordRefTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws IOException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		
		out.print(Mapper.lostPasswordControllerRoute + Mapper.lostPasswordRoute);
	}
}