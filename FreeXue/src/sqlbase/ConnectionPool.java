package sqlbase;

import java.sql.Connection;
import java.sql.SQLException;



import org.apache.tomcat.dbcp.dbcp.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;



class ConnectionPool {
	
static DataSource datasource;
	
   static Connection getConnection() throws SQLException{
		if(datasource == null)
		{	
			datasource = new DataSource();
			ConfigureDataSource(datasource);
		}
		return datasource.getConnection();
	}
	 
    public static  void  returnConnection(Connection conn) throws SQLException {
       conn.close();
    }
   
	public static  DataSource  ConfigureDataSource(DataSource ds) {
		  PoolProperties p = new PoolProperties();
		  p.setUrl("jdbc:mysql://223.4.134.183:3306/freexue");
		  System.out.println("ConnectionPool: jdbc:mysql://223.4.134.183:3306/freexue");
		  p.setDriverClassName("com.mysql.jdbc.Driver");
		  p.setUsername("root");
		  p.setPassword("eagle"); 
		  p.setJmxEnabled(true);
		  p.setTestWhileIdle(false);
		  p.setTestOnBorrow(true);
		  p.setValidationQuery("SELECT 1");
		  p.setTestOnReturn(false);
		  p.setValidationInterval(3000000);
		  p.setTimeBetweenEvictionRunsMillis(3000000);
		  p.setMaxActive(100);
		  p.setInitialSize(10);
		  p.setMaxWait(10000);
		  p.setRemoveAbandonedTimeout(60);
		  p.setMinEvictableIdleTimeMillis(30000);
		  p.setMinIdle(10);
		  p.setLogAbandoned(true);
		  p.setRemoveAbandoned(true);
	      p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		  
	      if(ds == null)
	      {
	      	  ds= new DataSource();	  
		  }
	      ds.setPoolProperties(p);
	      return ds;
	 }
}