package com.kotlin.swagger.board.controller

import com.kotlin.swagger.board.dto.Board
import com.kotlin.swagger.board.service.BoardService
import com.kotlin.swagger.common.dto.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/board")
@RestController
@Tag(name = "게시판 API", description = "게시판 API")
class BoardController(private val boardService: BoardService) {
    @Operation(summary = "게시판 작성", description = "게시판 작성")
    @Parameter(name = "num", description = "게시판 번호")
    @PostMapping("/create")
    fun create(@RequestBody board: Board): BaseResponse<Any> {
//        val dao = BoardDAO(conn)
//
//        //form은 Object로 넘어오기때문에 다운캐스팅
//        val f: BoardForm = form as BoardForm //다운캐스팅
//
//
//        f.setNum(dao.getMaxNum() + 1)
//        f.setIpAddr(request.getRemoteAddr())
        // 7개의 데이터를 넘겨주면 됨
        // 7개의 데이터를 넘겨주면 됨
//        dao.insertData(f)

        /////////////////////////////
        board.num = boardService.getMaxNum() + 1

        val result = boardService.inserBoard(board)
//
        return BaseResponse(data = result)
    }
}