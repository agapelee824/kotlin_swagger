package com.kotlin.swagger.common.service

import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.service.MemberService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val memberService: MemberService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member: Member = memberService.getMemberInfo(username) ?: throw UsernameNotFoundException("존재하지 않는 username 입니다.")

        return UserDetailsImpl(member)
    }
}