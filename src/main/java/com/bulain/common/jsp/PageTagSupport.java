package com.bulain.common.jsp;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts2.views.jsp.TagUtils;

import com.bulain.common.util.Util;
import com.opensymphony.xwork2.util.ValueStack;

public class PageTagSupport extends BodyTagSupport {
	private static final long serialVersionUID = 3983330010529798802L;

	private String css;
	private String page;
	private String totalPage;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int doEndTag() throws JspException {
		ValueStack valueStack = TagUtils.getStack(pageContext);
		Integer iPage = (Integer) valueStack.findValue(page);
		Integer iTotalPage = (Integer) valueStack.findValue(totalPage);
		
		try {
			int curr = iPage.intValue();
			int total = iTotalPage.intValue();
			
			List list = Util.getPageList(curr, total);
			
			if(css==null)css="pagination";
			
			pageContext.getOut().print("<div class='"+css+"'>");
			if(curr>1){
				pageContext.getOut().print("<a rel='prev' class='prev_page' href='?page.page="+(curr-1)+"'>« Previous</a>");
			}else{
				pageContext.getOut().print("<span class='disabled prev_page'>« Previous</span>");
			}
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				int index = (Integer) iter.next();
				if(index == curr){
					pageContext.getOut().print("<span class='current'>"+curr+"</span>");
				}else if(index<0){
					pageContext.getOut().print("<span class='gap'>…</span>");
				}else{
					pageContext.getOut().print("<a href='?page.page="+index+"'>"+index+"</a>");
				}
			}
			if(curr<total){
				pageContext.getOut().print("<a rel='next' class='next_page' href='?page.page="+(curr+1)+"'>Next »</a>");
			}else{
				pageContext.getOut().print("<span class='disabled next_page'>Next »</span>");
			}
			pageContext.getOut().print("</div>");
		} catch (Exception ex) {
			throw new JspTagException("PageTagSupport: " + ex.getMessage());
		}

		return SKIP_BODY;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

}
