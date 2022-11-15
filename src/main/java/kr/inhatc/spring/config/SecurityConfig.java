package kr.inhatc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.MvcMatchersAuthorizedUrl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        //로그인 폼 설정
        http.formLogin()
            .loginPage("/member/login")        //로그인 페이지 설정
            .defaultSuccessUrl("/")            //로그인 성공 페이지
            .usernameParameter("email")        //아이디 파라미터
            .passwordParameter("pwd")
            .failureUrl("/member/login/error") //로그인 실패
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
            .logoutSuccessUrl("/");

        //잘못된 권한으로 접근했을 때 반응
        http.exceptionHandling()
            .authenticationEntryPoint(new CustomEntryPoint());
        
        //권한에 따른 접근 설정
        http.authorizeRequests()
            .mvcMatchers("/css/**", "/js/**", "/images/**").permitAll()               //누구나 권한 없이 접근 가능
            .mvcMatchers("/", "/member/**", "item/**").permitAll()      //누구나 권한 없이 접근 가능
            .mvcMatchers("admin/**").hasRole("ADMIN")                   //ADMIN만 접근 가능
            .anyRequest().authenticated();                              //그 외는 권한 받고 사용
        
        return http.build();
    }
    
    //암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
