package command.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.BoardDao;
import dto.BoardDto;

public class BoardList implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		BoardDao dao = BoardDao.getDao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		// 검색어 따옴표 처리
		if (select == null) {
			select = "title";
			search = "";
		}
		search = CommonUtil.getSingleQuot(search);
		
		/* paging 설정 start*/
		int totalCount = dao.getTotalCount(select,search); // 전체 행수(게시글 건수)
		int list_setup_count = 5;  //한페이지당 출력 행수(페이지당 게시글 몇 건?)
		int pageNumber_count = 5;  //한페이지당 출력 페이지 갯수(페이지 번호는 몇 개까지 출력할까?)
		
		String nowPage = request.getParameter("t_nowPage"); // 현재 클릭한 페이지 번호(아래 페이지 목록에서 몇 번을 클릭했는지?)
		/* 위 4줄 개념을 이해하는 게 가장 중요! */
		int current_page = 0; // 현재페이지 번호
		int total_page = 0;    // 전체 페이지 수
		
		if(nowPage == null || nowPage.equals("")) current_page = 1; 
		else current_page = Integer.parseInt(nowPage);
		
//		if (nowPage != null && !nowPage.equals("")) {
//		    try {
//		        current_page = Integer.parseInt(nowPage);
//		    } catch (NumberFormatException e) {
//		        // 숫자가 아닌 문자열이 들어오면 예외를 잡고 기본값 1로 유지
//		        current_page = 1; 
//		    }
//		} else {
//		    current_page = 1;
//		}
		
		total_page = totalCount / list_setup_count;  // 몫 : 2
		int rest = 	totalCount % list_setup_count;   // 나머지:1
		if(rest !=0) total_page = total_page + 1;     // 3
		
		int start = (current_page -1) * list_setup_count + 1;
		int end   = current_page * list_setup_count;
		/* paging 설정 end*/
		// 게시글 순번(원래 게시글 번호 대신 출력할 번호) 정의
		int order = totalCount - (start - 1); // 출력 예시: 정수
		
		List<BoardDto> boardList = dao.getBoardList(select, search, start, end);
		request.setAttribute("list", boardList);
		request.setAttribute("select", select);
		request.setAttribute("search", search);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("paging", CommonUtil.getPageSetting(current_page, total_page, pageNumber_count));
		request.setAttribute("order", order);

	}

}
