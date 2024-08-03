package com.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /** セキュリティ適用外 */
  public void configure(WebSecurity web) throws Exception {
    web
        .ignoring()
        .requestMatchers("/webjars/**")
        .requestMatchers("/css/**")
        .requestMatchers("/js/**");

  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // ログイン不要ページの設定
    http
        .csrf().disable()
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/", "/signup", "/login").permitAll()
            .anyRequest().authenticated());

    // ログイン処理
    http
        .formLogin((form) -> form
            .loginProcessingUrl("/login") // ログイン処理のパス
            .loginPage("/login") // ログインページの指定
            .failureUrl("/login?error") // ログイン失敗時の遷移先の指定
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/task", true)); // ログイン成功時の遷移先の指定

    // ログアウト処理
    http
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout");
    return http.build();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password("{noop}password").roles("USER");
  }

  @Bean
  public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
    UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder.encode("password")) // encode password
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Configuration
  public class WebConfig<HttpPutFormContentFilter> {
      @Bean
      public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
          return new HiddenHttpMethodFilter();
      }
  }

}