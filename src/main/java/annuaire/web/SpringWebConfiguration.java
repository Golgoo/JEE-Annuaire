package annuaire.web;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackageClasses = SpringWebConfiguration.class)
public class SpringWebConfiguration implements WebApplicationInitializer, WebMvcConfigurer {
  
	/**
	 * Quite useless ... -> ask MASSAT
	 */
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx
          = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringWebConfiguration.class);
        ctx.setServletContext(container);
 
        ServletRegistration.Dynamic servlet = container.addServlet(
          "dispatcherExample", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/annuaire/*");
        servlet.addMapping("*.htm");
     }
    
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver
          = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
 
    @Override
    public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer configurer) {
        configurer.enable("dispatcherExample");
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
          .addResourceLocations("/resources/").setCachePeriod(3600)
          .resourceChain(true).addResolver(new PathResourceResolver());
    }
    
    @Bean("localeResolver")
    public SessionLocaleResolver localeResolver() {
    	SessionLocaleResolver r = new SessionLocaleResolver();
    	r.setDefaultLocale(Locale.FRENCH);

    	return r; 
    }
    
    @Bean("localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
    	LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
    	changeInterceptor.setParamName("language");
    	return changeInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(localeChangeInterceptor());
    }
    
    @Bean("messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource r = new ResourceBundleMessageSource();
        r.setBasenames("classpath:/others", "classpath:/person", "classpath:/group");
        return r;
    }
    
    @Bean
    public LocalValidatorFactoryBean getValidator() {
    	LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    
}