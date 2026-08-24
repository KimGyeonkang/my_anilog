package command.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.BoardDao;

public class DeleteBoard implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		BoardDao dao = BoardDao.getDao();
		String no = request.getParameter("t_no");
		String originalAttach = request.getParameter("t_original_attach");
		
		int result = dao.deleteBoard(no);
//		삭제 성공하고, 첨부파일이 있으면
		if (result == 1 && !originalAttach.equals("")) {
			File file = new File(CommonUtil.getBoardDir(request), originalAttach);
			// 첨부 파일 삭제가 안 되면
			if (!file.delete()) System.out.println("DeleteBoard 첨부파일 삭제 오류");
		}
		
		if (result == 1) {
			request.setAttribute("t_msg", "용사님의 소중한 기록이 무사히 삭제되었습니다. 아쉽지만 더 좋은 기록 부탁해요!");
		} else {
			request.setAttribute("t_msg", "기록을 삭제하는 중 강적이 나타난 것 같네요. 조금만 더 힘내요!");
		}
		request.setAttribute("t_url", "Board");
		

	}

}
