package dao;

import java.net.Authenticator.RequestorType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
//	비밀번호 암호화
    public String encryptSHA256(String value) throws NoSuchAlgorithmException{
		String encryptData ="";
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(value.getBytes());
		
		byte[] digest = sha.digest();
		for (int i=0; i<digest.length; i++) {
		   encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
		}
		return encryptData;
    }

//  회원 등록
	public int registerMember(MemberDto member) {
		int result = 0;
		String sql = "INSERT INTO MY_김견강_MEMBER\r\n"
				+ "(id, password, password_length, name, tel_1, tel_2, tel_3, \r\n"
				+ "			email_1, email_2, fav, \r\n"
				+ "			reg_date)\r\n"
				+ "VALUES\r\n"
				+ "('"+member.getId()+"', '"+member.getPassword()+"', '"+member.getPassword_length()+"', '"+member.getName()+"', "
				+ "'"+member.getTel_1()+"', '"+member.getTel_2()+"', '"+member.getTel_3()+"',\r\n"
				+ "'"+member.getEmail_1()+"', '"+member.getEmail_2()+"', '"+member.getFav()+"',\r\n"
				+ "TO_DATE('"+member.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: registerMember() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

//	아이디 중복 체크
	public int checkMemberId(String id) {
		int count = 0;
		String sql = "SELECT COUNT(*) AS COUNT\r\n"
				+ "FROM MY_김견강_MEMBER\r\n"
				+ "WHERE ID = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) count = rs.getInt("COUNT");
			
		} catch (SQLException e) {
			System.out.println("Error: checkMemberId() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}

//	로그인(loginName)
	public String getLoginName(String id, String password) {
		String name = "";
		String sql = "SELECT NAME\r\n"
				+ "FROM MY_김견강_MEMBER\r\n"
				+ "WHERE ID = '"+id+"'\r\n"
				+ "AND PASSWORD = '"+password+"'\r\n"
				+ "AND EXIT_DATE IS NULL";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) name = rs.getString("NAME");
			
		} catch (SQLException e) {
			System.out.println("Error: getLoginName() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return name;
	}

//	회원 정보 조회(마이페이지)
	public MemberDto getMemberInfo(String id) {
		MemberDto member = null;
		String sql = "SELECT ID, PASSWORD_LENGTH, NAME, TEL_1, TEL_2, TEL_3, EMAIL_1, EMAIL_2, FAV,\r\n"
				+ "TO_CHAR(REG_DATE, 'yyyy-MM-dd hh24:mi:ss') AS REG_DATE,\r\n"
				+ "TO_CHAR(UPDATE_DATE, 'yyyy-MM-dd hh24:mi:ss') AS UPDATE_DATE,\r\n"
				+ "TO_CHAR(EXIT_DATE, 'yyyy-MM-dd hh24:mi:ss') AS EXIT_DATE\r\n"
				+ "FROM MY_김견강_MEMBER\r\n"
				+ "WHERE ID = '"+id+"'";
		//TODO. getMemberInfo 왼성
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String viewId = rs.getString("ID");
				String password_length = rs.getString("PASSWORD_LENGTH");
				String name = rs.getString("NAME");
				String tel_1 = rs.getString("TEL_1");
				String tel_2 = rs.getString("TEL_2");
				String tel_3 = rs.getString("TEL_3");
				String email_1 = rs.getString("EMAIL_1");
				String email_2 = rs.getString("EMAIL_2");
				String fav = rs.getString("FAV");
				String reg_date = rs.getString("REG_DATE");
				String update_date = rs.getString("UPDATE_DATE");
//				String exit_date = rs.getString("REG_DATE");
				
				member = new MemberDto(viewId, "password", password_length, name, tel_1, tel_2, tel_3,
										email_1, email_2, fav, reg_date, update_date, "exit_date");
			}
			
		} catch (SQLException e) {
			System.out.println("Error: getMemberInfo() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return member;
	}

	// 정보 수정 - 기존 비밀번호 체크
	public int checkPassword(String id, String password) {
		int count = 0;
		String sql = "SELECT COUNT(*) AS COUNT\r\n"
				+ "FROM MY_김견강_MEMBER\r\n"
				+ "WHERE PASSWORD = '"+password+"'\r\n"
				+ "AND ID = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) count = rs.getInt("COUNT");
			
		} catch (SQLException e) {
			System.out.println("Error: checkPassword() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}

	// 수정
	public int updateMemberInfo(MemberDto member) {
		int result = 0;
		String sql = "UPDATE MY_김견강_MEMBER\r\n"
				+ "SET NAME = ?,\r\n"
				+ "    TEL_1 = ?,\r\n"
				+ "    TEL_2 = ?,\r\n"
				+ "    TEL_3 = ?,\r\n"
				+ "    EMAIL_1 = ?,\r\n"
				+ "    EMAIL_2 = ?,\r\n"
				+ "    FAV = ?,\r\n"
				+ "    UPDATE_DATE = TO_DATE(?, 'yyyy-MM-dd hh24:mi:ss')\r\n"
				+ "WHERE ID = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, member.getName());
			ps.setString(2, member.getTel_1());
			ps.setString(3, member.getTel_2());
			ps.setString(4, member.getTel_3());
			ps.setString(5, member.getEmail_1());
			ps.setString(6, member.getEmail_2());
			ps.setString(7, member.getFav());
			ps.setString(8, member.getUpdate_date());
			ps.setString(9, member.getId());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: updateMemberInfo() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	// 회원탈퇴
	public int exitMember(String id, String exit_date) {
		int result = 0;
		String sql = "UPDATE MY_김견강_MEMBER\r\n"
				+ "SET EXIT_DATE = TO_DATE(?, 'yyyy-MM-dd hh24:mi:ss')\r\n"
				+ "WHERE ID = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, exit_date);
			ps.setString(2, id);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: exitMember() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

// 	비밀번호 변경
	public int updateMemberPassword(String id, String password, String password_length) {
		int result = 0;
		String sql = "UPDATE MY_김견강_MEMBER\r\n"
				+ "		SET PASSWORD = ?,\r\n"
				+ "			PASSWORD_LENGTH = ?\r\n"
				+ "		WHERE ID = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, password);
			ps.setString(2, password_length);
			ps.setString(3, id);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: updateMemberPassword() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

// 	(새 비밀번호 전송) 이메일 조회
	public MemberDto getMemberEmail(String id, String tel_1, String tel_2, String tel_3) {
		MemberDto memberEmail = null;
		String sql = "SELECT NAME, EMAIL_1, EMAIL_2\r\n"
				+ "FROM MY_김견강_MEMBER\r\n"
				+ "WHERE ID = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("NAME");
				String email_1 = rs.getString("EMAIL_1");
				String email_2 = rs.getString("EMAIL_2");
				
				memberEmail = new MemberDto(name, email_1, email_2);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: getMemberEmail() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return memberEmail;
	}
	
//		(새 비밀번호 전송) 새 비밀번호 생성
	   public String getNewPassword(int pwLength) {
	        StringBuffer temp =new StringBuffer();
	        Random rnd = new Random();
	        
	        for(int i=0;i<pwLength;i++)
	        {
	            int rIndex = rnd.nextInt(3);
	            switch (rIndex) {
	            case 0:
	                // a-z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	                break;
	            case 1:
	                // A-Z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                break;
	            case 2:
	                // 0-9
	                temp.append((rnd.nextInt(10)));
	                break;
	            }
	//		            System.out.println("pw :"+temp.toString());   
	        }
	        return temp.toString();      
	   }

//	   	(새 비밀번호 전송) 새 비밀번호로 변경
		public int memberPasswordUpdate(String id, String newPassword, String newPasswordLength) {
			int result = 0;
			String sql = "UPDATE MY_김견강_MEMBER\r\n"
					+ "SET PASSWORD = ?,\r\n"
					+ "    PASSWORD_LENGTH = ?\r\n"
					+ "WHERE ID = ?";
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, newPassword);
				ps.setString(2, newPasswordLength);
				ps.setString(3, id);
				
				result = ps.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Error: memberPasswordUpdate() -> " + sql);
				e.printStackTrace();
			} finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}

}
