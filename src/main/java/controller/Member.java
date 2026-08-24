package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.ExitMember;
import command.member.LogoutMember;
import command.member.SendMemberPassword;
import command.member.UpdateMember;
import command.member.UpdateMemberPassword;
import command.member.ViewMemberMypage;
import command.member.loginMember;
import command.member.registerMember;
import common.CommonExecute;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		
		if (gubun == null) gubun = "login";
		String page = "";
		
//		기본: 로그인 페이지
		if (gubun.equals("login")) page = "member/member_login.jsp";
//		회원가입 페이지
		else if (gubun.equals("join")) {
			page = "member/member_join.jsp";
//		회원 등록
		} else if (gubun.equals("register")) {
			CommonExecute reg = new registerMember();
			reg.execute(request);
			page = "common_alert.jsp";
//		로그인	
		} else if (gubun.equals("memberLogin")) {
//			CommonExecute login  = new loginMember();
			new loginMember().execute(request);
			page = "common_alert.jsp";
//		로그아웃
		} else if (gubun.equals("logout")) {
			new LogoutMember().execute(request);
			page = "common_alert.jsp";
//		마이페이지(회원정보)	
		} else if (gubun.equals("mypage")) {
//			세션 정보 만료되어 있으면
			if ((String)request.getSession().getAttribute("sessionId") == null) {
				request.setAttribute("t_msg", "용사님의 정보가 남아있지 않아요. 다시 로그인해 주세요!");
				request.setAttribute("t_url", "Member");
				page = "common_alert.jsp";
			} else {
//				CommonExecute mypage = new ViewMemberMypage();
				new ViewMemberMypage().execute(request);
				page = "member/member_mypage.jsp";
			}
//		회원정보 수정 페이지
		} else if (gubun.equals("memberUpdateForm")) {
			new ViewMemberMypage().execute(request);
			page = "member/member_update.jsp";
//		회원정보 수정
		} else if (gubun.equals("update")) {
			new UpdateMember().execute(request);
			page = "common_alert_view.jsp";
//		회원정보 삭제(탈퇴)
		} else if (gubun.equals("exit")) {
			new ExitMember().execute(request);
			page = "common_alert.jsp";
//		비밀번호 변경 페이지
		} else if (gubun.equals("updatePasswordForm")) {
			page = "member/member_modifypassword.jsp";
//		비밀번호 변경
		} else if (gubun.equals("updatePassword")) {
			new UpdateMemberPassword().execute(request);
			page = "common_alert_view.jsp";
//		비밀번호 찾기 페이지
		} else if (gubun.equals("findPasswordForm")) {
			page = "member/member_findpassword.jsp";
//		새 비밀번호 메일 전송
		} else if (gubun.equals("sendPasswordMail")) {
			new SendMemberPassword().execute(request);
			page = "common_alert_view.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
