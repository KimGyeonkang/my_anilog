package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;

public class ExitMember implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = (String)request.getSession().getAttribute("sessionId");
		String exit_date = CommonUtil.getTodayTime();
		
		int result = dao.exitMember(id, exit_date);
		
		if (result == 1) request.getSession().invalidate();
		String msg = (result == 1) ? "또 다른 여정을 떠나시는군요. 용사님의 새로운 미래를 응원합니다." 
										: "탈퇴 중 강적이 나타난 것 같네요. 마지막까지 힘을 내봅시다.";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");

	}

}
