package com.sf.jkt.k.web.config.secruity.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {
    @Autowired
//    lateinit var userDetailsService: UserDetailsService
    lateinit var myuserDetailService: MyUserDetailService

    @Autowired
    lateinit var myPasswordEncoder: MyPasswordEncoder

    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        val loadedUser = myuserDetailService.loadUserByUsername(username)
        if (loadedUser == null) {
            throw InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation")
        }
        return loadedUser
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        if (authentication?.credentials == null) {
            throw  BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"))
            val presentationPassword = authentication!!.credentials.toString()
            if (!myPasswordEncoder.matches(presentationPassword, userDetails!!.password)) {
                throw BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"))
            }
        }
    }
}