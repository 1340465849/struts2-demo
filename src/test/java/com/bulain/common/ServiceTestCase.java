package com.bulain.common;

import java.io.InputStream;

import javax.sql.DataSource;

import junit.framework.TestCase;

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
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.util.StringUtils;

public abstract class ServiceTestCase extends TestCase {
	private static final String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
	private static final String DEFAULT_CONTEXT_LOCATION = "classpath*:spring/applicationContext*.xml, classpath*:spring/propertyConfigurer-test.xml";
	
	protected static ApplicationContext applicationContext;
	
	protected IDatabaseTester dsTester;
	
	protected String getContextLocations() {
		return DEFAULT_CONTEXT_LOCATION;
	}
	
	protected void setupBeforeInitDispatcher() throws Exception {
		if(applicationContext==null){
	        GenericXmlContextLoader xmlContextLoader = new GenericXmlContextLoader();
	        String[] contextLocations = StringUtils.tokenizeToStringArray(getContextLocations(), CONFIG_LOCATION_DELIMITERS);
	        applicationContext = xmlContextLoader.loadContext(contextLocations);
		}
    }
	
	protected void setUp() throws Exception {
		super.setUp();
		setupBeforeInitDispatcher();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
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
}
