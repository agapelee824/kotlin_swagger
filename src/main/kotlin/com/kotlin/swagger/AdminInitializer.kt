package com.kotlin.swagger

import com.kotlin.swagger.common.enum.MemberType
import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.service.MemberService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AdminInitializer(
    private val memberService: MemberService,
    private val encoder: PasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        memberService.createMember(Member(0, "admin", "admin", null, null, null, null, MemberType.ADMIN))
    }
}