package com.sopovs.moradanen.bouquinist;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zk.ui.http.HttpSessionListener;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
public class BouquinistApplication {

	@Bean
	public HttpSessionListener zkossSessionListener() {
		return new HttpSessionListener();
	}

	@Bean
	public ServletRegistrationBean zkLoader() {
		ServletRegistrationBean zkLoader = new ServletRegistrationBean(new DHtmlLayoutServlet(), "*.zul", "*.zhtml");
		zkLoader.getInitParameters().put("update-uri", "/zkau");
		return zkLoader;
	}

	@Bean
	public ServletRegistrationBean auEngine() {
		ServletRegistrationBean auEngine = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
		return auEngine;
	}

	@Autowired
	private EntityManagerFactory emf;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(transactionManager());
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BouquinistApplication.class);
		app.setAdditionalProfiles("bootstrap");
		app.run(args);
	}

}
