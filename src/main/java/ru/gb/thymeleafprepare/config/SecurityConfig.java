package ru.gb.thymeleafprepare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
Аннотация @Configuration говорит о том, что данный класс является конфигурационным.
@EnableWebSecurity отключает стандартные настройки безопасности Spring Security и начинает
использовать правила, прописанные в SecurityConfig. @EnableGlobalMethodSecurity активирует
возможность ставить защиту на уровне методов (для этого над методами ставятся аннотации
@Secured и @PreAuthorized).
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*
Непосредственно к защите приложения. За
настройку безопасности отвечает метод configure(HttpSecurity http). В нем можно выполнить настройку
процесса логина и логаута, правил доступа к определенным частям веб-приложения, и т.д. Пример
настройки представлен ниже.
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                (requests) -> {
                    requests.antMatchers("/").permitAll();
                    requests.antMatchers("/product/all").permitAll();
                    requests.antMatchers(HttpMethod.GET, "/product").hasRole("ADMIN");
                    requests.antMatchers(HttpMethod.POST, "/product").hasRole("ADMIN");
                    requests.mvcMatchers(HttpMethod.GET, "/product/{productId}").permitAll();
                }
        );
        http.authorizeRequests((requests) -> {
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
        });
        // Добавляем страницу при возникновении отказа пользователю в доступе.
        // Если пользователь пытается попасть на не доступную для себя страницу.
        http.exceptionHandling().accessDeniedPage("/access-denied");
        http.formLogin();
        http.httpBasic();
    }

//    За настройку способа аутентификации отвечает метод configure(AuthenticationManagerBuilder auth)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("$2a$10$CtjdavwwBXwFZ1liD.nfhu4ua.g7ArJxojCcZ98IdBv0dpSeUdDti")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("$2a$10$pxwuknQocnU34d4ooFz/ougUnHI0xSlbgqhDbOtYFhjetJuZY1E92")
//                .roles("ADMIN");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    protected UserDetailsService userDetailsService() {
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
