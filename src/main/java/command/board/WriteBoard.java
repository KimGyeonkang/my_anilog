package command.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExecute;
import common.CommonUtil;
import dao.BoardDao;
import dto.BoardDto;

public class WriteBoard implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		BoardDao dao = BoardDao.getDao();
		
		String attachDir = CommonUtil.getBoardDir(request);

		int maxSize = 1024 * 1024 * 10; // 최대 용량(10mb)  
		/*
			request(항상 사용했던 JSP 기본 내장 객체), 첨부파일 경로, 첨부파일 최대 용량, 
			한글 깨짐 방지 인코딩, 같은 첨부파일명에 대해 임의로 다른 이름을 붙이는 정책 클래스
		*/
		MultipartRequest mpr = null;
		try {
			mpr = new MultipartRequest(request, 
										attachDir,
										maxSize,
										"utf-8",
										new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String no = dao.getBoardNo();
		String title = CommonUtil.getSingleQuot(mpr.getParameter("t_title"));
		title = CommonUtil.getDoubleQuot(title);
		String content = CommonUtil.getSingleQuot(mpr.getParameter("t_content"));
		content = CommonUtil.getDoubleQuot(content);
		String attach = mpr.getFilesystemName("t_attach");
		if (attach == null) attach = "";
		
		String reg_id = (String)request.getSession().getAttribute("sessionId");
		String reg_date = CommonUtil.getTodayTime();
		
		BoardDto board = new BoardDto(no, title, content, attach, reg_id, reg_date);
		
		int result = dao.writeBoard(board);
		
		String msg = (result == 1) ? "용사님의 소중한 기록이 등록되었습니다." : "기록을 남기는 중 강적이 나타난 것 같네요. 조금만 더 힘내요!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Board");

	}

}
