package com.kotlin.swagger.common.service

import com.kotlin.swagger.member.dto.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val member: Member) : UserDetails {

    private var enabled: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = AuthorityUtils.createAuthorityList()

    override fun getPassword(): String? = member.password

    override fun getUsername(): String? = member.password

    override fun isAccountNonExpired(): Boolean = enabled

    override fun isAccountNonLocked(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean = enabled

    override fun isEnabled(): Boolean = enabled
}