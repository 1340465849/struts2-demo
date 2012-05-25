package com.bulain.mybatis.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * grant access
 * sqlplus sys/syspass@orcl as sysdba
 * grant select  on v_$session   to user;
 * grant select  on v_$parameter to user;
 * grant execute on dbms_monitor to user;
 * 
 * collection trace sql
 * cd c:\oracle\diag\rdbms\orcl\orcl\trace
 * set PATH=%PATH%;C:\oracle\product\11.2.0\dbhome_1\BIN
 * trcsess clientid=test01 output=ppmtrace01.trc
 * tkprof ppmtrace01.trc sys=no output=ppmprof01
 *
 */
public class TraceDemo {
    private final static String IDENTIFY_CLIENT = "{call dbms_session.set_identifier(?)}";
    private final static String TRACE_CLIENT_ENABLE = "{call dbms_monitor.client_id_trace_enable(?,true,true)}";
    private final static String TRACE_CLIENT_DISABLE = "{call dbms_monitor.client_id_trace_disable(?)}";
    private final static String SELECT_GET_PARAMETER = "select value from v$parameter where name=?";
    private final static String SELECT_SERVER_FROM_SESSION = "select server from v$session";

    private static String identifier;
    private Connection conn;

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        identifier = "test01";
    }

    @Before
    public void setUp() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "user";
        String password = "pass";

        //identify connection
        conn = DriverManager.getConnection(url, username, password);
        CallableStatement call = conn.prepareCall(IDENTIFY_CLIENT);
        call.setString(1, identifier);
        call.execute();
        call.close();

        //enable trace connection
        call = conn.prepareCall(TRACE_CLIENT_ENABLE);
        call.setString(1, identifier);
        call.execute();
        call.close();
    }

    @After
    public void tearDown() throws SQLException {
        //disable trace connection
        CallableStatement call = conn.prepareCall(TRACE_CLIENT_DISABLE);
        call.setString(1, identifier);
        call.execute();
        call.close();

        //clear identify
        call = conn.prepareCall(IDENTIFY_CLIENT);
        call.setString(1, null);
        call.execute();
        call.close();
    }

    @Test
    public void testSqlShouldInTraceFile1() throws SQLException {
        //this statement will be traced
        PreparedStatement stat = conn.prepareStatement("select /*+conn1 with id*/ 1 from dual");
        stat.execute();
        stat.close();
    }

    @Test
    public void testSqlShouldInTraceFile2() throws SQLException {
        //this statement will be traced
        PreparedStatement stat = conn.prepareStatement("select /*+conn2 with id*/ 1 from dual");
        stat.execute();
        stat.close();
    }

    @Test
    public void testSqlShouldNotInTraceFile() throws SQLException {
        //disable trace connection
        CallableStatement call = conn.prepareCall(TRACE_CLIENT_DISABLE);
        call.setString(1, identifier);
        call.execute();
        call.close();

        //this statement will be ignore
        PreparedStatement stat = conn.prepareStatement("select /*+conn3 with id*/ 1 from dual");
        stat.execute();
        stat.close();

        //enable trace connection
        call = conn.prepareCall(TRACE_CLIENT_ENABLE);
        call.setString(1, identifier);
        call.execute();
        call.close();
    }

    @Test
    public void testGetTraceInfo() throws SQLException {
        //user_dump_dest
        PreparedStatement stat = conn.prepareStatement(SELECT_GET_PARAMETER);
        stat.setString(1, "user_dump_dest");
        ResultSet resultSet = stat.executeQuery();
        if (resultSet.next()) {
            String userDumpDest = resultSet.getString(1);
            System.out.printf("user_dump_dest: %s\n", userDumpDest);
        }
        resultSet.close();
        stat.close();

        //background_dump_dest
        stat = conn.prepareStatement(SELECT_GET_PARAMETER);
        stat.setString(1, "background_dump_dest");
        resultSet = stat.executeQuery();
        if (resultSet.next()) {
            String userDumpDest = resultSet.getString(1);
            System.out.printf("background_dump_dest: %s\n", userDumpDest);
        }
        resultSet.close();
        stat.close();

        //shared_servers
        stat = conn.prepareStatement(SELECT_SERVER_FROM_SESSION);
        resultSet = stat.executeQuery();
        if (resultSet.next()) {
            String server = resultSet.getString(1);
            System.out.printf("server: %s\n", server);
        }
        resultSet.close();
        stat.close();
    }

}
