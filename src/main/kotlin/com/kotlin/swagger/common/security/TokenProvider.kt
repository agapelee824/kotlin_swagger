package com.kotlin.swagger.common.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.xml.bind.DatatypeConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.Cipher.SECRET_KEY
import javax.crypto.spec.SecretKeySpec


@PropertySource("classpath:jwt.yml")
@Service
class TokenProvider(
    @Value("\${secret-key}")
    private val secretKey: String,
    @Value("\${expiration-hours}")
    private val expirationHours: Long,
    @Value("\${issuer}")
    private val issuer: String
) {
    fun createToken(userSpecification: String) = Jwts.builder()
        .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS512.jcaName)) // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
        .setSubject(userSpecification)   // JWT 토큰 제목
        .setIssuer(issuer)    // JWT 토큰 발급자
        .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
        .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰의 만료시간 설정
        .compact()!!    // JWT 토큰 생성

    fun validateTokenAndGetSubject(token: String): String? = Jwts.parserBuilder()
        .setSigningKey(secretKey.toByteArray())
        .build()
        .parseClaimsJws(token)
        .body
        .subject

    // 토큰 해독
//    @Override
//    fun getSubject(token: String): String? {
//        val claims = Jwts.parser()
//            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
//            .parseClaimsJws(token).body
//        return claims.subject
//    }

//    @Override
//    fun isUsable(jwt: String?): Boolean {
//        return try {
//            val claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
//                .parseClaimsJws(jwt).body
//            true
//        } catch (e: Exception) {
//            throw UnauthorizedException()
//        }
//    }
}
