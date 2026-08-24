package command.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.BoardDao;
import dto.BoardDto;

public class IndexList implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		BoardDao dao = BoardDao.getDao();
		
		String select = "title";
		String search = "";
		
		/* paging 설정 start*/
		int totalCount = dao.getTotalCount(select,search); // 전체 행수(게시글 건수)
		int list_setup_count = 5;  //한페이지당 출력 행수(페이지당 게시글 몇 건?)
		int pageNumber_count = 3;  //한페이지당 출력 페이지 갯수(페이지 번호는 몇 개까지 출력할까?)
		
		String nowPage = request.getParameter("t_nowPage"); // 현재 클릭한 페이지 번호(아래 페이지 목록에서 몇 번을 클릭했는지?)
		/* 위 4줄 개념을 이해하는 게 가장 중요! */
		int current_page = 0; // 현재페이지 번호
		int total_page = 0;    // 전체 페이지 수
		
		if(nowPage == null || nowPage.equals("")) current_page = 1; 
		else current_page = Integer.parseInt(nowPage);
		
		total_page = totalCount / list_setup_count;  // 몫 : 2
		int rest = 	totalCount % list_setup_count;   // 나머지:1
		if(rest !=0) total_page = total_page + 1;     // 3
		
		int start = (current_page -1) * list_setup_count + 1;
		int end   = current_page * list_setup_count;
		/* paging 설정 end*/
		
		List<BoardDto> recentlyBoardList = dao.getRecentlyBoardList(select, search, start, end);
//		System.out.println(recentlyBoardList.get(0).getTitle());
		List<BoardDto> subList = recentlyBoardList.subList(1, 5); // 인덱스 최근 글 목록에 띄울 리스트(2~5번째 글)
		
		request.setAttribute("list", recentlyBoardList);
		request.setAttribute("subList", subList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
	}

}
