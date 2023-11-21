package com.kotlin.swagger.board.mapper

import com.kotlin.swagger.board.dto.Board
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BoardMapper {
    fun getMaxNum():  Int

    fun inserBoard(board: Board): Long

    fun getLists(searchKey: String, searchValue: String): List<Board>?

    fun getReadData(num: Int): Board?

    fun updateHitCount(num: Int): Long
}