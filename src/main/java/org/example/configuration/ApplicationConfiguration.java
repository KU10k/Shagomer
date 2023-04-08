package org.example.configuration;

import org.example.model.MonthData;
import org.example.model.PurposeUser;
import org.example.model.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("org.example")
public class ApplicationConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(MonthData.class)
                .addAnnotatedClass(PurposeUser.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

    }


}
