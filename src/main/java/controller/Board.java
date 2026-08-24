package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.board.BoardList;
import command.board.DeleteBoard;
import command.board.UpdateBoard;
import command.board.ViewBoard;
import command.board.WriteBoard;
import common.CommonUtil;

/**
 * Servlet implementation class Board
 */
@WebServlet("/Board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if (gubun == null) gubun = "list";
		String page = "";
		
//		목록
		if (gubun.equals("list")) {
			new BoardList().execute(request);
			page = "board/board_list.jsp";
//		게시글 작성 페이지	
		} else if (gubun.equals("writeForm")) {
			request.setAttribute("today", CommonUtil.getToday()); // 오늘 날짜 jsp에 넘기기
			page = "board/board_write.jsp";
//		게시글 저장
		} else if (gubun.equals("write")) {
			new WriteBoard().execute(request);
			page = "common_alert.jsp";
//		게시글 상세 조회
		} else if (gubun.equals("view")) {
			new ViewBoard().execute(request);
			page = "board/board_view.jsp";
//		게시글 수정 페이지
		} else if (gubun.equals("updateForm")) {
			new ViewBoard().execute(request);
			page = "board/board_update.jsp";
//		게시글 수정
		} else if (gubun.equals("update")) {
			new UpdateBoard().execute(request);
			page = "common_alert_view.jsp";
//		게시글 삭제	
		} else if (gubun.equals("delete")) {
			new DeleteBoard().execute(request);
			page = "common_alert.jsp";
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher(page);
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
