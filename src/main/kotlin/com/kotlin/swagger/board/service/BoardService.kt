package com.kotlin.swagger.board.service

import com.kotlin.swagger.board.dto.Board
import com.kotlin.swagger.board.mapper.BoardMapper
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardMapper: BoardMapper) {
    fun getMaxNum(): Int {
        val maxNum = boardMapper.getMaxNum()
        return maxNum
    }

    fun inserBoard(req: Board): Long {
        val result = boardMapper.inserBoard(req)
        return result
    }
}