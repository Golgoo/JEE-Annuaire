package annuaire.dao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackageClasses = SpringDAOConfiguration.class)
@EnableTransactionManagement
public class SpringDAOConfiguration {

	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("SA");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:mem:test/db");
		return dataSource;
    }

    /*
     * Construction de l'EMF à partir de la source de données et du
     * choix d'hibernate.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] { "annuaire.model" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // Configuration d'hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        em.setJpaProperties(properties);
        return em;
    }

    /*
     * Construction d'un gestionnaire de transaction
     * en liaison avec l'usine à EM.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /*
     * Activer le traitement des annotations
     * de gestion du contexte de persistence.
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor annotationProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }


	

}