package com.techathon.common.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringWebConfig.class);
        rootContext.setServletContext(servletContext);
        rootContext.refresh();

        // listener
        servletContext.addListener(new ContextLoaderListener(rootContext));

		// servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("spring-mvc", new DispatcherServlet((rootContext)));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
    }
}
