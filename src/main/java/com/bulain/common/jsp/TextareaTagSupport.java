package com.bulain.common.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.util.ValueStack;

public class TextareaTagSupport extends BodyTagSupport {
	private static final long serialVersionUID = -4855902415028843359L;
	
	private String value;
	
	public TextareaTagSupport(){
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		ValueStack valueStack = TagUtils.getStack(pageContext);
		String str = (String) valueStack.findValue(value);
		
		try {
			if(str==null)str="";
			str = str.replaceAll("\n", "<br/>");
			pageContext.getOut().print(str);
		} catch (IOException ex) {
			throw new JspTagException("TextareaTagSupport: " + ex.getMessage());
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
