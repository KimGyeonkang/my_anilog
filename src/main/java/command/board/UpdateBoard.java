package command.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExecute;
import common.CommonUtil;
import dao.BoardDao;
import dto.BoardDto;

public class UpdateBoard implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		BoardDao dao = BoardDao.getDao();
		String attachDir = CommonUtil.getBoardDir(request);
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mpr = null;
		
		try {
			mpr = new MultipartRequest(request, attachDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println("UpdateBoard 파일 첨부 오류");
			e.printStackTrace();
		}
		
		String no = mpr.getParameter("t_no");
		String title = CommonUtil.getSingleQuot(mpr.getParameter("t_title"));
		String content = CommonUtil.getSingleQuot(mpr.getParameter("t_content"));
		String attach = mpr.getFilesystemName("t_attach");
		if (attach == null) attach = "";
		String deleteAttach = mpr.getParameter("t_delete_checkbox"); // 삭제할 파일명
		String originalAttach = mpr.getParameter("t_original_attach"); // 기존 첨부파일명
		if (originalAttach == null) originalAttach = "";
		
		String update_id = (String)request.getSession().getAttribute("sessionId");
		String update_date = CommonUtil.getTodayTime();
		
		String dbAttachName = ""; // db에 저장할 파일명
		
//		삭제할 첨부파일이 있으면(체크박스)
		if (deleteAttach != null) {
			File file = new File(attachDir, deleteAttach); // 파일 경로, 삭제할 파일명
			if (!file.delete()) System.out.println("UpdateBoard 첨부파일 삭제 오류");
		} else {
			dbAttachName = originalAttach;
		}
		
//		새 첨부파일이 있을 때
		if (!attach.equals("")) {
			dbAttachName = attach;
//		삭제할 기존 첨부파일이 있을 때
			if (!originalAttach.equals("")) {
				File file = new File(attachDir, originalAttach);
				if (!file.delete()) System.out.println("UpdateBoard 기존 첨부파일 삭제 오류");
			}
		}
		
		BoardDto board = new BoardDto(no, title, content, dbAttachName, 
									"hit", "reg_id", "reg_name", "reg_date",
									update_id, "update_name", update_date);
		
//		int result = dao.updateBoard(board);
		if (dao.updateBoard(board) == 1) {
			request.setAttribute("t_msg", "용사님의 소중한 기록이 수정되었습니다.");
		} else {
			request.setAttribute("t_msg", "기록을 수정하는 중 강적이 나타난 것 같네요. 조금만 더 힘내요!");
		}
		request.setAttribute("t_url", "Board");
		request.setAttribute("t_gubun", "view");
		request.setAttribute("t_no", no);
	}

}
