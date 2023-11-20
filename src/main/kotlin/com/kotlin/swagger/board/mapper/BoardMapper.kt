package com.kotlin.swagger.board.mapper

import com.kotlin.swagger.board.dto.Board
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BoardMapper {
    fun getMaxNum():  Int

    fun inserBoard(board: Board): Long
}