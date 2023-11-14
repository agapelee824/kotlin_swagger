package com.kotlin.swagger.member.service

import com.kotlin.swagger.common.security.TokenProvider
import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.dto.SignInResponse
import com.kotlin.swagger.member.mapper.MemberMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberMapper: MemberMapper,
                    private val tokenProvider: TokenProvider,
                    private val encoder: PasswordEncoder
) {
    fun createMember(req: Member): Boolean {
        val member = memberMapper.findByLoginId(req.loginId)
        if(member == null) {
            req.password = encoder.encode(req.password)
            val id = memberMapper.insertMember(req)
//            val response = member?.id
//
//            return response
            return true;
        } else if(req.loginId != "admin") {
            throw IllegalArgumentException("이미 사용중인 아이디입니다.")
        }

        return false;
    }

    fun login(req: Member): SignInResponse {
        val member = memberMapper.findByLoginId(req.loginId)
            ?.takeIf { encoder.matches(req.password, it.password) }	// 암호화된 비밀번호와 비교하도록 수정
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        val token = tokenProvider.createToken("${member.id}:${member.type}")
//        val token = tokenProvider.createToken("${member.id}:0")
        return SignInResponse(member.name, member.type, token)
    }

    fun getMemberInfo(id: String): Member? = memberMapper.findById(id.toLong())

    fun getMemberInfoById(id: Long): Member? = memberMapper.findById(id)
}