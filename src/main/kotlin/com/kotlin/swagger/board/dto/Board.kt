package com.kotlin.swagger.board.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "게시판 DTO")
data class Board(
//    private int num;
//    private String name;
//    private String pwd;
//    private String email;
//    private String subject;
//    private String content;
//    private String ipAddr;
//    private String created;
//    private int hitCount;
    @Schema(description = "num(PK)")
    var num: Int = 0,
    val name: String = "",
    val pwd: String = "",
    var email: String? = "",
    val subject: String = "",
    val content: String = "",
    var ipAddr: String? = "",
    var created: String? = "",
    var hitCount: Int? = 0
)

@Schema(description = "게시판 리스트 DTO")
data class BoardListReq(
    var pageNum: String? = "",
    var searchKey: String = "",
    var searchValue: String = "",

    var method: String? = ""
)