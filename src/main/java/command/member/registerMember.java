package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class registerMember implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_password");
		String password_length = Integer.toString(password.length());
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("오류: 비밀번호 암호화 중 오류 발생!");
			e.printStackTrace();
		}
		
		String name = request.getParameter("t_name");
		String tel_1 = request.getParameter("t_tel_1");
		String tel_2 = request.getParameter("t_tel_2");
		String tel_3 = request.getParameter("t_tel_3");
		String email_1 = request.getParameter("t_email_1");
		String email_2 = request.getParameter("t_email_2");
		String fav = request.getParameter("t_fav");
		String reg_date = CommonUtil.getTodayTime();
		
		MemberDto member = new MemberDto(id, password, password_length, name, 
										tel_1, tel_2, tel_3, email_1, email_2, fav, 
										reg_date, "update_date", "exit_date");
		
		int result = dao.registerMember(member);
		
		String msg = (result == 1) ? "블로그 가입을 환영합니다! 용사님의 여정을 응원하죠." : "가입 중 강적이 나타난 것 같네요. 포기하지 말아요 용사님!";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
		
		

	}

}
