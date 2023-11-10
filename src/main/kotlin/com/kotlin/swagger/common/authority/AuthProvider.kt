package com.kotlin.swagger.common.authority

import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component


@Component
class AuthProvider : AuthenticationProvider {
    @Autowired
    private val memberService: MemberService? = null

    @Override
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        //val /*email*/loginId: String = authentication.getPrincipal() as String // 로그인 창에 입력한 email
        val loginId: String = authentication.name
        //val password: String = authentication.getCredentials() as String // 로그인 창에 입력한 password
        val password: String = authentication.credentials as String
        //val passwordEncoder: PasswordEncoder = memberService.passwordEncoder()
        val token: UsernamePasswordAuthenticationToken
        //val member: Member = memberService.getUserByEmail(email)
        val member: Member? = memberService?.getMemberInfo(loginId)
        //if (member != null && passwordEncoder.matches(password, member.password))
        if (member != null && password == member.password)
        { // 일치하는 user 정보가 있는지 확인
            val roles: MutableList<GrantedAuthority> = ArrayList()
            roles.add(SimpleGrantedAuthority("USER")) // 권한 부여
            token = UsernamePasswordAuthenticationToken(member.id, null, roles)
            // 인증된 user 정보를 담아 SecurityContextHolder에 저장되는 token

            return token
        }
        throw BadCredentialsException("No such user or wrong password.")
        // Exception을 던지지 않고 다른 값을 반환하면 authenticate() 메서드는 정상적으로 실행된 것이므로 인증되지 않았다면 Exception을 throw 해야 한다.
    }

    @Override
    override fun supports(authentication: Class<*>?): Boolean {
        //return true
        return authentication!!.equals(UsernamePasswordAuthenticationToken::class.java)
        //return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
        //return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication))
    }
}
