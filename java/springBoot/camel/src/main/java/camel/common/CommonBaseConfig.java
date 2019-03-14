package camel.common;

import camel.domain.Book2;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Jopa on 1/5/2018.
 */
public class CommonBaseConfig {

    Connection con;
    Config config;
    Map map = new HashMap();

    public CommonBaseConfig(@Value("${config}") String configFileName, ConfigurableEnvironment environment) throws Exception {
        final File configFile = new File(configFileName);
        this.config = ConfigFactory.parseFile(configFile);
        System.out.println(config.getString("VAR"));

//        con = DatabaseUtil.getConnection();
//        if (con==null || con.isClosed()){
//            throw new Exception("Could not get a working connection.");
//        }

        environment.getPropertySources().addLast(new org.springframework.core.env.PropertySource<Config>("configFile", config) {
            @Override
            public String getProperty(String s) {
                try{
                    return source.getString(s);
                }catch(Exception e){
                    return null;
                }
            }
        });
    }

    @Bean
    Config config(){
        return this.config;
    }

    @Bean
    @Qualifier("routeConfig")
    Map<String, String> getRouteConfig(){
        return config.getConfig("routeConfig").entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().unwrapped().toString()));
    }

//    @Bean
//    Connection getConnection() {
//        return DatabaseUtil.getConnection();
//    }

    @Bean
    JAXBContext newJaxbContext2 () throws JAXBException {
        return JAXBContext.newInstance(Book2.class);
    }

    @Bean
    @Scope("prototype")
    DataFormat newJaxb (JAXBContext jaxbContext2){
        return new JaxbDataFormat(jaxbContext2);
    }

    @Bean("oracleDatasource")
    public DataSource dataSource() {

        String CACHE_NAME = "MYCACHE";
        OracleDataSource ods = null;
        try {
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        ods.setUser("system");
        ods.setPassword("q1w2e3r4t5");
        // caching parms
        ods.setConnectionCachingEnabled(true);
        ods.setConnectionCacheName(CACHE_NAME);
        Properties cacheProps = new Properties();
        cacheProps.setProperty("MinLimit", "1");
        cacheProps.setProperty("MaxLimit", "4");
        cacheProps.setProperty("InitialLimit", "1");
        cacheProps.setProperty("ConnectionWaitTimeout", "5");
        cacheProps.setProperty("ValidateConnection", "true");
        ods.setConnectionCacheProperties(cacheProps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ods;
    }


//    @Bean("derbyDatasource")
//    @Profile("multiThreading")
//    public DataSource dataSource() {
//        EmbeddedConnectionPoolDataSource dataSource = new EmbeddedConnectionPoolDataSource();
//        dataSource.setDatabaseName("testDB");
//        dataSource.setCreateDatabase("create");
//
//        Connection conn = null;
//        try {
//            conn = dataSource.getConnection();
//            Statement statement = conn.createStatement();
//            statement.execute("CREATE TABLE mytest (col1 VARCHAR(10),col2 VARCHAR(10))");
//            statement.execute("INSERT INTO mytest VALUES ('10', '10')");
//            statement.execute("INSERT INTO mytest VALUES ('20', '20')");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return dataSource;
//    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }
}
