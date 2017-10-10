package com.bookingsystem;

import com.bookingsystem.dao.CustomerDao;
import com.bookingsystem.dao.ReservationDao;
import com.bookingsystem.entities.Address;
import com.bookingsystem.entities.Customer;
import com.bookingsystem.entities.Location;
import com.bookingsystem.entities.Reservation;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by vijay on 2/18/17.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("persistence-layer.properties"));
        return propertyPlaceholderConfigurer;
    }
    @Bean(name="dataSource")
    public DataSource dataSource (@Value("${jdbc.driverClassName}") String driverClass,
                                  @Value("${jdbc.url}") String jdbcUrl,
                                  @Value("${jdbc.username}") String user,
                                  @Value("${jdbc.password}") String password ){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClass);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }


    @Bean
    public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
        return new CommonAnnotationBeanPostProcessor();
    }
    @Bean
    public RequiredAnnotationBeanPostProcessor requiredAnnotationBeanPostProcessor(){
        return new RequiredAnnotationBeanPostProcessor();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Value("#{dataSource}") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean(name="sessionFactory")
    public SessionFactory sessionFactory(@Value("#{dataSource}") DataSource dataSource,
                                         @Value("${hibernate.show_sql}") String showSql,
                                         @Value("${hibernate.dialect}") String dialect,
                                         @Value("${hibernate.format_sql}") String formatSql,
                                         @Value("${hibernate.hbm2ddl}") String hbm2ddl){

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        //sessionBuilder.setDataSource(dataSource);
        sessionBuilder.addAnnotatedClasses(Reservation.class, Customer.class, Location.class, Address.class);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("hibernate.format_sql", formatSql);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        sessionBuilder.addProperties(hibernateProperties);
        SessionFactory factory = sessionBuilder.buildSessionFactory();
        return factory;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager hibernateTransactionManager(@Value("#{sessionFactory}") SessionFactory sessionFactory){
        HibernateTransactionManager htm = new HibernateTransactionManager(sessionFactory);
        return htm;

    }


    @Bean(name = "reservationDao")
    public ReservationDao reservationDao(@Value("#{sessionFactory}")SessionFactory sessionFactory){
        return new ReservationDao(sessionFactory);
    }

    @Bean(name = "customerDao")
    public CustomerDao customerDao(@Value("#{sessionFactory}")SessionFactory sessionFactory){
        return new CustomerDao(sessionFactory);
    }
}
