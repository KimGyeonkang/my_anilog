package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;

public class LogoutMember implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		String name = (String)request.getSession().getAttribute("sessionName");
		// 세션 만료 여부 판단
		String msg = (name == null) 
				? "용사님이 다시 돌아오길 기다리겠습니다." : name+" 용사님이 다시 돌아오길 기다리겠습니다.";
		
		request.getSession().invalidate(); // 세션 정보 삭제
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Index");
	}

}
