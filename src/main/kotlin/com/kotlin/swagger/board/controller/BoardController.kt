package com.kotlin.swagger.board.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.kotlin.swagger.board.dto.Board
import com.kotlin.swagger.board.dto.BoardListReq
import com.kotlin.swagger.board.service.BoardService
import com.kotlin.swagger.common.configuration.LoggerDelegator
import com.kotlin.swagger.common.dto.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder
import java.net.URLEncoder


@RequestMapping("/api/board")
@RestController
@Tag(name = "게시판 API", description = "게시판 API")
class BoardController(private val boardService: BoardService) {
    val log by LoggerDelegator()

    @Operation(summary = "게시판 작성", description = "게시판 작성")
    @Parameter(name = "board", description = "게시판 객체")
    @PostMapping("/create")
    fun create(@RequestBody board: Board): BaseResponse<Any> {
        log.info("create board!!!")
        board.num = boardService.getMaxNum() + 1

        val result = boardService.inserBoard(board)
        return BaseResponse(data = result)
    }

    @Operation(summary = "게시판 목록", description = "")
    @PostMapping("/list")
    fun list(@RequestBody req: BoardListReq): BaseResponse<Any> {
//        select name="searchKey" class="selectField">
//        <option value="subject">제목</option>
//        <option value="name">작성자</option>
//        <option value="content">내용</option>
//        </select>
//        <input type="text" name="searchValue" class="textField"/>

        val pageNum: String? = req.pageNum;
        var currentPage: Int = 1
        if(pageNum != "")
            currentPage = Integer.parseInt(pageNum);

        var searchKey: String = req.searchKey
        var searchValue: String = req.searchValue

        // 디코딩해서 받는다
        // null값 찾기

        // 검색을안했으면
        if(searchValue == null || searchValue == "") {
            searchKey = "subject"
            searchValue = ""
        }else { // 검색을했으면
            if(req.method == "GET") { // 대소문자 구분없이 비교
                //디코더 시킨다.
                // 값을 UTF-8로 인코딩
                searchValue = URLDecoder.decode(searchValue, "UTF-8");
            }
        }

        // 검색한 데이터 전체 개수
//        val dataCount: Int = boardService.getDataCount(searchKey, searchValue);

        // 한화면에 표시할 데이터 개수
//        int numPerPage = 5;
//		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
//
//		if(currentPage>totalPage)
//			currentPage = totalPage;

        //데이터베이스에서 가져올 시작과 끝위치 rnum
//        int start = (currentPage-1)*numPerPage+1;
//        int end = currentPage*numPerPage;

        // 검색했으면 키값 벨류 start end
        //데이터베이스에서 해당페이지를 가져온다
        val lists: List<Board>? = boardService.getLists(/*start, end, */searchKey, searchValue);

        // --------------------------------------------------------
        /* test // 리스트에서 설치키 설치벨류를 article 로 값 보내기
        var param: String? = ""

        // null을 못찾을수있으니 조건 2번줌
        if(searchValue!=null&&!searchValue.equals("")) {

            // 검색을했으면
            param = "searchKey=" + searchKey;
            param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
        }

        // 가상의 주소를 담는다.
        String listUrl = cp + "/board.do?method=list";

        if(!param.equals("")) {

            // 널이아니면 검색을 했다.
            // myUtil 뒤에 검색된 주소가 딸려간다.
            // listUrl은 ?가 없으므로 붙인것
            listUrl += "&" + param;
        }

//		String pageIndexList =
//				myUtil.pageIndexList(currentPage, totalPage, listUrl);

        // 리스트를 클릭했을때 article로 넘어가는부분

        // 글보기 주소를 만든다.
        // 검색을 안했다면

        // 현재페이지를 넘겨준다 ( 되돌아 올수있게 )
        String articleUrl = cp + "/board.do?method=article&pageNum=" + currentPage;

        // 검색을 했다면 param 까지
        // 뒤에 붙일거니까 &를 붙이는것이다.
        if(!param.equals("")) {
            // articleUrl 은 ?가 있어서
            // 현재페이지를 넘겨준다 ( 되돌아 올수있게 - 키와 값 검색을했을때 되돌아올수있게 )
            articleUrl += "&" + param;
        }

        //포워딩 페이지에 데이터 넘기기
        request.setAttribute("lists", lists);
        request.setAttribute("articleUrl", articleUrl);
//		request.setAttribute("pageIndexList", pageIndexList);
        request.setAttribute("pageNum", pageNum);
//		request.setAttribute("totalPage", totalPage);

        // dataCount는 null이아닐떄 pageIndexList를 띄우기위해
//        request.setAttribute("dataCount", dataCount);

//        return mapping.findForward("list"); */

        /////
//        val result = BaseResponse(data = lists)
        return BaseResponse(data = lists)
    }

    @Operation(summary = "게시판 글 보기", description = "")
    @Parameter(name = "num", description = "게시판 번호")
    @GetMapping("/article")
    fun article(@PathVariable num: Int): BaseResponse<Any> {
//        val num: Int = number
//        String pageNum = request.getParameter("pageNum");

//        String searchKey = request.getParameter("searchKey");
//        String searchValue = request.getParameter("searchValue");

//        // 값이 있으면
//        if(searchValue != null) {
//            searchValue = URLDecoder.decode(searchValue, "UTF-8");
//        }

        //게시물번호(프라이머리키) 조회수 증가
        boardService.updateHitCount(num)

        // ----------------------------------------------
        // 하나의 데이터 읽어온다
        // setAttribute("dto",dto);
        val dto: Board? = boardService.getReadData(num);
//        if(dto == null) {
////            return mapping.findForward("list");
//        }

//        // 라인수
//        // setAttribute("line",lineSu);
//        int lineSu = dto.getContent().split("\n").length;
//
//        dto.setContent(dto.getContent().replace("\n", "<br/>"));
//
//        // 밑에 몇번째 게시물인지
//        String param = "pageNum=" + pageNum;
//
//        if(searchValue != null && !searchValue.equals("")) {
//
//            //검색을 했다는거
//            param += "&searchKey=" + searchKey;
//            // 인코더 시켜서 보낸다
//            param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
//
//        }
//
//        // 게시물 누르면 데이터가 보여지게 위해서 데이터를 넘김(setAttribute)
//
//        // 값을 가지고 넘어가는 변수는 param을 쓸수없다
//        // param은 이미 내부에 변수를 쓰고있다.
//        request.setAttribute("dto", dto);
//        request.setAttribute("params", param);
//        request.setAttribute("linSu", lineSu);
//        request.setAttribute("pageNum", pageNum);

        return BaseResponse(data = dto)
    }

    @Operation(summary = "게시판 수정", description = "")
    @Parameter(name = "board", description = "게시판 객체")
    @PostMapping("/update")
    fun update(@RequestBody req: ObjectNode): BaseResponse<Any> {
//        String pageNum = request.getParameter("pageNum");
//
//        String searchKey = request.getParameter("searchKey");
//        String searchValue = request.getParameter("searchValue");

        val mapper = ObjectMapper() // JSON을 Object화 하기 위한 Jackson ObjectMapper 이용

        val board: Board = mapper.treeToValue(req.get("board"), Board::class.java)
        val redirect: BoardListReq = mapper.treeToValue(req.get("redirect"), BoardListReq::class.java)

        val f: Board = board

        f.num = board.num

        //수정된 데이터를 보낸다
        val result = boardService.updateData(f);

        // ---------------------------------------------------
        //되돌아올때
        var param: String = "&pageNum=" + redirect.pageNum;

        if(redirect.searchValue != null && redirect.searchValue != "") {
            param += "&searchKey=" + redirect.searchValue;
            param += "&searchValue=" + URLEncoder.encode(redirect.searchValue, "UTF-8");
        }

//        request.setAttribute("params", param);
//        request.setAttribute("pageNum", pageNum);
//
//        ActionForward af = new ActionForward();
//        af.setRedirect(true);
//        af.setPath("/board.do?method=list" + param);
//
//        return af;

        return BaseResponse(data = result)
    }

    @Operation(summary = "게시판 삭제", description = "")
    @Parameter(name = "num", description = "게시판 번호")
    @DeleteMapping("/delete/{num}")
    fun delete(@PathVariable num: Int): BaseResponse<Any> {
        val result = boardService.deleteData(num);
        return BaseResponse(data = result)
    }
}