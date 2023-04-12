package ezenweb.web.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // 스프링빈에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override // 재정의
    // HTTP 관련 보안담당 메소드
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http); // 부모클래스 호출
        http.csrf() //
                .ignoringAntMatchers("/member/info")
                .ignoringAntMatchers("/member/login");

        
    }
}
