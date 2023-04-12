package ezenweb.web.config;


import ezenweb.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링빈에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;
    // 인증[로그인] 관련 보안 메소드

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // auth.userDetailsService(구현된 서비스 대입).서비스 안에서 로그인 패스워드 검증시 사용된 암호화 객체 대입(사용안할시 생략)
        auth.userDetailsService(memberService).passwordEncoder(new BCryptPasswordEncoder());

         // super.configure(auth);
    }

    @Override // 재정의
    // HTTP 관련 보안담당 메소드
    // 시큐리티 사용전 : 클라이언트 요청시 --------> 디스패쳐 서블릿 ----> 핸들러매핑 [controller]
    // 사용후 : 클라이언트 요청시 ----필터(시큐리티)----> 디스패쳐 서블릿 ----> 핸들러매핑 [controller]
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http); // 부모클래스 호출
        http.csrf() // 사이트간 요청 [post / put / delete 잠금 ]
                .ignoringAntMatchers("/member/info") // 차단 해제
                .ignoringAntMatchers("/member/login") // 차단 해제
                .and() // and() 기능추가 메소드
                .formLogin()
                    .loginPage("/member/login") // 로그인으로 사용할 url
                    .loginProcessingUrl("/member/login") // 로그인 처리시 사용할 url
                    .defaultSuccessUrl("/") // 로그인 성공시 이동할 url
                    .failureUrl("/member/login") // 로그인 실패시 사용할 url
                    .usernameParameter("memail") // 로그인시 사용할 계정 아이디의 필드명
                    .passwordParameter("mpassword") // 로그인시 사용할 계정 비밀번호의 필드명
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 처리를 요청할 url
                    .logoutSuccessUrl("/") // 로그아웃 성공했을때 이동할 url
                    .invalidateHttpSession(true); // 세션 초기화
    }
}
