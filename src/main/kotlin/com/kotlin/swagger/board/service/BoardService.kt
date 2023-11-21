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

    fun getLists(searchKey: String, searchValue: String): List<Board>? {
        val result = boardMapper.getLists(searchKey, searchValue)
        return result
    }

    fun getReadData(num: Int): Board? {
        val result = boardMapper.getReadData(num)
        return result
    }

    fun updateHitCount(num: Int): Long {
        val result = boardMapper.updateHitCount(num)
        return result
    }

    fun updateData(board: Board): Long {
        val result = boardMapper.updateData(board)
        return result
    }

    fun deleteData(num: Int): Long {
        val result = boardMapper.deleteData(num)
        return result
    }
}