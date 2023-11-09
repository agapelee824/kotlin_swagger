package com.kotlin.swagger.member.service

import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.mapper.MemberMapper
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberMapper: MemberMapper) {
    fun createMember(member: Member): Long? {
        val id = memberMapper.insertMember(member)
        val response = member.id

        return response
    }
}