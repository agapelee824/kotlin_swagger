package com.kotlin.swagger.member.mapper

import com.kotlin.swagger.member.dto.Member
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MemberMapper {
    fun insertMember(member: Member): Long
}