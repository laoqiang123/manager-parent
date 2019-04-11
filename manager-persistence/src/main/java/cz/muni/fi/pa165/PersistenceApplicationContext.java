package cz.muni.fi.pa165;

import org.hibernate.jpa.HibernatePersistenceProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * Represents an application context class.
 * Created by Stefan Malcek on 24.10.2017.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = "cz.muni.fi.pa165")
public class PersistenceApplicationContext {

    @Bean
    public PersistenceExceptionTranslationPostProcessor postProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
    	LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
    	localEntityManagerFactoryBean.setPersistenceUnitName("lost");
        //jpaFactoryBean.setLoadTimeWeaver(instrumentationLoadTimeWeaver());
        //jpaFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return localEntityManagerFactoryBean;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public LoadTimeWeaver instrumentationLoadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
    //将jpa 的persistence.xml 注入spring 获取数据源
    @Bean
    public DataSource getDatabase() {
        return entityManagerFactory().getDataSource();
    }
}
