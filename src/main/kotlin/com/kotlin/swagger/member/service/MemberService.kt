package com.kotlin.swagger.member.service

import com.kotlin.swagger.common.configuration.TokenProvider
import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.dto.SignInResponse
import com.kotlin.swagger.member.mapper.MemberMapper
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberMapper: MemberMapper,
                    private val tokenProvider: TokenProvider) {
    fun createMember(member: Member): Long? {
        val id = memberMapper.insertMember(member)
        val response = member.id

        return response
    }

    fun getMemberInfo(loginId: String): Member? = memberMapper.findByLoginId(loginId)

    fun login(req: Member): SignInResponse {
        val member = memberMapper.findByLoginId(req.loginId)
        if(req.password == member.password) {
            //val token = tokenProvider.createToken("${member.id}:${member.type}")
            val token = tokenProvider.createToken("${member.id}:0") // USER
            return SignInResponse(member.name, /*member.type, */token)
        }

        throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
    }
}