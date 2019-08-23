package com.sf.jkt.k.web.config.secruity.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*


/***
LindUserNameAuthenticationFilter
LindAuthenticationProvider.retrieveUser
LindAuthenticationProvider.additionalAuthenticationChecks
UserDetailsService
Authentication
 */
@Configuration
@EnableWebSecurity(debug = true)
//@PreAuthorize，@PreFilte
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var customAuthenticationProvider: CustomAuthenticationProvider

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.inMemoryAuthentication().passwordEncoder(MyPasswordEncoder()).withUser("user").password("user")
                .roles("USER")
                .and().withUser("admin").password("admin").roles("ADMIN", "USER")
    }

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .antMatchers("/**/**").permitAll()
                .antMatchers("/js/**", "/css/**", "/img/**", "/login/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/welcome")
//                .successHandler(AuthenticationSuccessHandler)
//                .failureHandler(AuthenticationSuccessHandler)
                .permitAll()
                .and()
                .addFilterAt(customUserNameAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .authorizeRequests().and().logout().permitAll()
    }

    @Bean
    fun customUserNameAuthenticationFilter(): CustomUserNameAuthenticationFilter {
        val phoneAuth = CustomUserNameAuthenticationFilter()
        val providerManager = ProviderManager(Collections.singletonList(customAuthenticationProvider as AuthenticationProvider))
        phoneAuth.setAuthenticationManager(providerManager)
//        phoneAuth.setAuthenticationSuccessHandler()
//        phoneAuth.setAuthenticationFailureHandler()
        return phoneAuth
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}