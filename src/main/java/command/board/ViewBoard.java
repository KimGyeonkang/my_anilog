package command.board;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.BoardDao;

public class ViewBoard implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		BoardDao dao = BoardDao.getDao();
		String no = request.getParameter("t_no");
		
//		System.out.println(no);
		// 조회수 먼저 업데이트하고
		// 목록에서 상세 조회할 경우에만 조회수 업데이트	
		if (request.getParameter("t_gubun").equals("view")) {
			int result = dao.updateHit(no);
			if (result == 0) System.out.println("Board 게시판 조회수 증가 중 오류 발생");
		}
		
		request.setAttribute("board", dao.getBoardView(no));
	}

}
