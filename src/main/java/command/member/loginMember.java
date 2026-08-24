package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;

public class loginMember implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_password");
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String name = dao.getLoginName(id, password);
		
		if (!name.equals("")) {
			request.getSession().setAttribute("sessionId", id);
			request.getSession().setAttribute("sessionName", name);
			if (id.equals("manager")) request.getSession().setAttribute("sessionLevel", "admin");
			request.getSession().setMaxInactiveInterval(60 * 60 * 4); // 세션 유지 시간(4시간)
			
			request.setAttribute("t_msg", name + " 용사님 일어나실 시간입니다!");
			request.setAttribute("t_url", "Index");
		} else {
			request.setAttribute("t_msg", "아이디와 비밀번호를 다시 확인해보세요.");
			request.setAttribute("t_url", "Member");
		}

	}

}
