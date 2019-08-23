package com.sf.jkt.k.web.config.secruity.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

// https://www.cnblogs.com/lori/p/10400564.html
// UsernamePasswordAuthenticationFilter authenticationFilter
//


@Component
class MyUserDetailService : UserDetailsService {

    @Autowired
    lateinit var myPasswordEncoder: MyPasswordEncoder

    override fun loadUserByUsername(username: String?): UserDetails {
        val udList = mutableListOf<UserDetails>()
        udList.add(User.builder().username("user").password(myPasswordEncoder.encode("user")).authorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
        ).build())
        udList.add(User.builder().username("admin").password("admin").authorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN")
        ).build())
        return udList.stream().filter { o -> o.username.equals(username) }.findFirst().orElse(null)
    }

    fun getUserDetailFromDb(username: String): UserDetailsService? {
        return null
    }
}

