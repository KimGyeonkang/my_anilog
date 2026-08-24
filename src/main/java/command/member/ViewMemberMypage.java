package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class ViewMemberMypage implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = (String)request.getSession().getAttribute("sessionId");
		
		MemberDto member = dao.getMemberInfo(id);
		request.setAttribute("t_member", member);

	}

}
