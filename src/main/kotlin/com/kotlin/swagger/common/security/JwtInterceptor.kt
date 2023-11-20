//package com.kotlin.swagger.common.security
//
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.stereotype.Component
//
//@Component
//class JwtInterceptor(private val tokenProvider: TokenProvider) {
//    var HEADER_AUTH:kotlin.String? = "authorization"
//
//    @Throws(Exception::class)
//    fun preHandle(request: HttpServletRequest, response: HttpServletResponse?, handler: Any?): Boolean {
//        val token = request.getHeader(HEADER_AUTH)
//        println(token)
//        return if (token != null && tokenProvider.isUsable(token)) {
//            true
//        } else {
//            throw UnauthorizedException()
//        }
//    }
//}