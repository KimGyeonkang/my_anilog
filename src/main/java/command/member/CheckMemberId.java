package command.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

/**
 * Servlet implementation class CheckMemberId
 */
@WebServlet("/CheckMemberId")
public class CheckMemberId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMemberId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao dao = new MemberDao();
		int count = dao.checkMemberId(request.getParameter("t_id"));
		
//		서블릿에서 웹 페이지 곧바로 실행
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		if (count == 0) out.print("사용가능");
//		else out.print("중복");
		response.setContentType("text/html; charset=utf-8");
		if (count == 0) response.getWriter().print("아주 멋진 아이디네요! 사용할 수 있습니다.");
		else response.getWriter().print("이미 해당 아이디를 사용 중인 용사님이 계시네요. 더 멋진 이름이 있을 겁니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
