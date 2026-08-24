package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;
import mail.SendMail;

public class SendMemberPassword implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String tel_1 = request.getParameter("t_tel_1");
		String tel_2 = request.getParameter("t_tel_2");
		String tel_3 = request.getParameter("t_tel_3");
		
//		지메일 앱 비밀번호(SendMail 클래스의 password 속성): xqmr ggrv dprj xqnd
		MemberDto memberEmail = dao.getMemberEmail(id, tel_1, tel_2, tel_3);
		
//		이메일(회원정보)가 없으면
		if (memberEmail == null) {
			request.setAttribute("t_msg", "용사님의 정보를 정확하게 입력해주세요!");
			request.setAttribute("t_url", "Member");
			request.setAttribute("t_gubun", "findPasswordForm");
		} else {
			String fromUserEmail = "v.tang3203@gmail.com"; // 보내는 사람 주소
			String fromUserPassword = "xqmr ggrv dprj xqnd"; // 구글 계정 앱 비밀번호
			
			int newPasswordLength = 4;
			String newPassword = dao.getNewPassword(newPasswordLength);
			String toUserEmail = memberEmail.getEmail_1()+"@"+memberEmail.getEmail_2();
			String mailTitle = memberEmail.getName()+" 용사님의 임시 비밀번호입니다!";
			String mailContent = "test: 새로운 비밀번호는 "+newPassword+" 입니다. 로그인 하신 후 소중한 정보 보호를 위해 꼭 비밀번호를 변경해주세요 용사님!";
			
			System.out.println(newPassword);
			
			SendMail sendMail = new SendMail(fromUserEmail, fromUserPassword);
			boolean success = sendMail.sendPassword(toUserEmail, mailTitle, mailContent);
			
//			메일 발송 성공하면
			if (success) {
//			새로운 임시 비밀번호로 회원정보 수정
				try {
					newPassword = dao.encryptSHA256(newPassword);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				int result = dao.memberPasswordUpdate(id, newPassword, Integer.toString(newPasswordLength));
				
				if (result == 1) {
					request.setAttribute("t_msg", memberEmail.getName()+" 용사님, 이메일 주소에서 임시 비밀번호를 확인해주세요!");
				} else {
					request.setAttribute("t_msg", "임시 비밀번호 발송 중 강적이 나타난 것 같네요. 길드 관리자를 찾아주세요!");
					System.out.println("임시 비밀번호 메일 발솔 후 Update 오류");
				}
			}
			
			request.setAttribute("t_url", "Member");
			request.setAttribute("t_gubun", "login");
		}

	}

}
