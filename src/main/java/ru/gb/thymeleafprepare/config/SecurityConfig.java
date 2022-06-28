package ru.gb.thymeleafprepare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
1. Сделать так, чтобы добавлять товары в корзину могли только авторизованные пользователи.
Чтобы кнопка добавления товара в корзину была, но при нажатии на нее неавторизованному клиенту должна показываться страница с сообщением,
что в корзину можно добавлять товары авторизованным пользователям с предложением перейти на страницу авторизации.
*/


@Configuration
@EnableWebSecurity // Включаем безопасность
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                (requests) -> {
                    requests.antMatchers("/").permitAll();
                    requests.antMatchers("/product/all").permitAll();
                    requests.antMatchers(HttpMethod.GET, "/product").permitAll();
                    requests.antMatchers(HttpMethod.POST, "/product").hasRole("ADMIN");
                    requests.mvcMatchers(HttpMethod.GET, "/product/{productId}").permitAll();
                } // antMatchers - воспринимает имя четко, а если передаем параметры то ставим mvcMatchers
        );
        //Данный метод говорит, что любой запрос только по авторизации (как deny any - any)
        http.authorizeRequests((requests) -> {
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
        });

        http.formLogin();  // Подключаем форму для логина
        http.httpBasic(); // Тип авторизации

    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("$2a$10$AVDIlwAy28At6A3ye7Lnw.dGP8ULPXvalccc9hzn8AhuQnOEaxfy2")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("$2a$10$1BSlD/3auXr93tLscTVhHOmioiDeivLilHiJb6.1lZbynEDZUcLwm")
//                .roles("ADMIN");
//
//
//    }
    // Создаем бин для шифрования пароля
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        // Тут вместо application.yml настраиваем user(пользователя), то есть создаем бин с учетными записями и кидаем их в память.
//        // userDetailsService - в реальном проекте обращается к БД за учётной записью пользователя.
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
//        return new InMemoryUserDetailsManager(admin,user);
//    }
}
