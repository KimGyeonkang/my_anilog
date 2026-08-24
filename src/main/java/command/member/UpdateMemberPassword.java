package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;

public class UpdateMemberPassword implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_modify_password");
		String password_length = Integer.toString(password.length());
		try {
			password = dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("UpdateMemberPassword 비밀번호 암호화 중 오류 발생");
			e.printStackTrace();
		}

		int result = dao.updateMemberPassword(id, password, password_length);
		
		if (result == 1) {
//			String msg = "용사님의 소중한 비밀번호가 변경되었습니다! 다시 로그인 해주세요.";
//			String url = "Member";
			request.getSession().invalidate(); // 세션 삭제
			request.setAttribute("t_msg", "용사님의 소중한 비밀번호가 변경되었습니다! 다시 로그인 해주세요.");
			request.setAttribute("t_url", "Member");
			request.setAttribute("t_gubun", "login");
		} else {
			request.setAttribute("t_msg", "비밀번호 변경 중 강적이 나타난 것 같네요. 끝까지 포기하지 말아요!");
			request.setAttribute("t_url", "Member");
			request.setAttribute("t_gubun", "updatePasswordForm");
		}
	}

}
