package pl.kurs.equationsolver.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter adapter, DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.kurs.equationsolver.models");
        emf.setDataSource(ds);
        return emf;
    }

    @Profile({"prod"})
    @Bean
    public JpaVendorAdapter createVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.valueOf(env.getProperty("prod.jdbc.dbtype")));
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Profile({"prod"})
    @Bean
    public DataSource createDateSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getProperty("prod.jdbc.url"));
        dataSource.setUsername(env.getProperty("prod.jdbc.user"));
        dataSource.setPassword(env.getProperty("prod.jdbc.pass"));
        dataSource.setDriverClassName(env.getProperty("prod.jdbc.driver"));
        return dataSource;
    }

    @Profile({"dev", "!prod & !dev"})
    @Bean
    public JpaVendorAdapter createVendorAdapter_dev() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.valueOf(env.getProperty("dev.jdbc.dbtype")));
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Profile({"dev", "!prod & !dev"})
    @Bean
    public DataSource createDateSource_dev() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getProperty("dev.jdbc.url"));
        dataSource.setUsername(env.getProperty("dev.jdbc.user"));
        dataSource.setPassword(env.getProperty("dev.jdbc.pass"));
        dataSource.setDriverClassName(env.getProperty("dev.jdbc.driver"));
        return dataSource;
    }

}
