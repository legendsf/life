package com.sf.jkt.k.web.config.secruity.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class MyPasswordEncoder : PasswordEncoder {
    override fun encode(p0: CharSequence?): String {
        return p0.toString()
    }

    override fun matches(p0: CharSequence?, p1: String?): Boolean {
        return p0!!.equals(p1)
    }
}