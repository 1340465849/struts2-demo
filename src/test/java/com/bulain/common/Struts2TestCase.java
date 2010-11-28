package com.bulain.common;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DefaultOperationListener;
import org.dbunit.IDatabaseTester;
import org.dbunit.IOperationListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public abstract class Struts2TestCase extends StrutsTestCase {
	private static final String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
	private static final String DEFAULT_CONTEXT_LOCATION = "classpath*:spring/applicationContext*.xml, classpath*:spring/propertyConfigurer-test.xml";
	
	protected static ApplicationContext applicationContext;
	
	protected IDatabaseTester dsTester;
	
	protected String getContextLocations() {
		return DEFAULT_CONTEXT_LOCATION;
	}
	
	protected void setupBeforeInitDispatcher() throws Exception {
        if (applicationContext == null) {
        	 GenericXmlContextLoader xmlContextLoader = new GenericXmlContextLoader();
             String[] contextLocations = StringUtils.tokenizeToStringArray(getContextLocations(), CONFIG_LOCATION_DELIMITERS);
             applicationContext = xmlContextLoader.loadContext(contextLocations);
        }

        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
    }
	
	protected void initServletMockObjects() {
		if(servletContext==null)servletContext = new MockServletContext(resourceLoader);
		response = new MockHttpServletResponse();
		request = new MockHttpServletRequest(servletContext);
		pageContext = new MockPageContext(servletContext, request, response);
	}
    
	@SuppressWarnings("unchecked")
	protected ActionProxy getActionProxy(String uri) {
    	Map session = ActionContext.getContext().getSession();
    	Map application = ActionContext.getContext().getApplication();
    	
        ActionProxy proxy = super.getActionProxy(uri);
        
        ServletContext context = ServletActionContext.getServletContext();
        session = (session==null? new SessionMap(request): session);
        application = (application==null? new ApplicationMap(context): application);
        
        ActionContext.getContext().setSession(session);
        ActionContext.getContext().setApplication(application);

        return proxy;
    }
	
	protected void setUpDB(String file) throws Exception {
		DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
		dsTester = new DataSourceDatabaseTester(dataSource);
		dsTester.setOperationListener(getOperationListener());
		InputStream is = new ClassPathResource(file).getInputStream();
        dsTester.setDataSet(new XmlDataSet(is));
        dsTester.onSetup();
	}
	
	protected void tearDownDB() throws Exception {
		dsTester.onTearDown();
	}
	
	protected void setUpDatabaseConfig(DatabaseConfig config){
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
    }
	
	protected IOperationListener getOperationListener() {
        return new DefaultOperationListener(){
                public void connectionRetrieved(IDatabaseConnection connection) {
                    super.connectionRetrieved(connection);
                    setUpDatabaseConfig(connection.getConfig());
                }
        	};
    }
	
	protected void setUpAction(String loginName, String password) throws Exception{
		initServletMockObjects();
		request.addParameter("login.loginName", loginName);
		request.addParameter("login.hashedPassword", password);
		ActionProxy proxy = getActionProxy("/authenticate/logon");
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}
	
	protected void tearDownAction()throws Exception {
		initServletMockObjects();
		ActionProxy proxy = getActionProxy("/authenticate/logout");
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}
}
