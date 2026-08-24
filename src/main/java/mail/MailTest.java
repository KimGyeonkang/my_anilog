package mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailTest
 */
@WebServlet("/MailTest")
public class MailTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromUserEmail = "v.tang3203@gmail.com"; // 보내는 사람 주소
		String fromUserPassword = "xqmr ggrv dprj xqnd"; // 구글 계정 앱 비밀번호
		
		SendMail sendMail = new SendMail(fromUserEmail, fromUserPassword);
		String toUserEmail = "rlarusrkd07@naver.com";
		String mailTitle = "임시 비밀번호 발송";
		String mailContent = "test: 새로운 비밀번호는 1234 입니다.";
		
		boolean success = sendMail.sendPassword(toUserEmail, mailTitle, mailContent);
		System.out.println(" mail이 잘 전송되었습니까?! : " + success);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
