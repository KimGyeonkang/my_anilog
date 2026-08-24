package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class UpdateMember implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String tel_1 = request.getParameter("t_tel_1");
		String tel_2 = request.getParameter("t_tel_2");
		String tel_3 = request.getParameter("t_tel_3");
		String email_1 = request.getParameter("t_email_1");
		String email_2 = request.getParameter("t_email_2");
		String fav = request.getParameter("t_fav");
		String update_date = CommonUtil.getTodayTime();
		
		MemberDto member = new MemberDto(id, name, tel_1, tel_2, tel_3, email_1, email_2, fav, update_date);
		int result = dao.updateMemberInfo(member);
		
		String msg = (result == 1) ? "용사님의 소중한 정보가 변경되었습니다!" : "수정 중 강적이 나타난 것 같네요. 포기하지 말아요 용사님!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		request.setAttribute("t_id", id);
		request.setAttribute("t_gubun", "mypage");

	}

}
