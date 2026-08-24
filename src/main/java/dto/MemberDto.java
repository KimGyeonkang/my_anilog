package dto;

public class MemberDto {
	String id, password, password_length, name, tel_1, tel_2, tel_3, 
			email_1, email_2, fav, 
			reg_date, update_date, exit_date;	
	
//	등록, 상세정보
	public MemberDto(String id, String password, String password_length, String name, String tel_1, String tel_2,
			String tel_3, String email_1, String email_2, String fav, String reg_date, String update_date,
			String exit_date) {
		this.id = id;
		this.password = password;
		this.password_length = password_length;
		this.name = name;
		this.tel_1 = tel_1;
		this.tel_2 = tel_2;
		this.tel_3 = tel_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.fav = fav;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.exit_date = exit_date;
	}
	
	
//  수정
	public MemberDto(String id, String name, String tel_1, String tel_2, String tel_3, String email_1, String email_2,
		String fav, String update_date) {
		this.id = id;
		this.name = name;
		this.tel_1 = tel_1;
		this.tel_2 = tel_2;
		this.tel_3 = tel_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.fav = fav;
		this.update_date = update_date;
	}

//	(새 비밀번호 전송) 이메일
	public MemberDto(String name, String email_1, String email_2) {
		this.name = name;
		this.email_1 = email_1;
		this.email_2 = email_2;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPassword_length() {
		return password_length;
	}

	public String getName() {
		return name;
	}

	public String getTel_1() {
		return tel_1;
	}

	public String getTel_2() {
		return tel_2;
	}

	public String getTel_3() {
		return tel_3;
	}

	public String getEmail_1() {
		return email_1;
	}

	public String getEmail_2() {
		return email_2;
	}

	public String getFav() {
		return fav;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public String getExit_date() {
		return exit_date;
	}
	
}
