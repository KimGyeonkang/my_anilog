package dto;

public class BoardDto {
	private String no, title, content, attach, hit, 
					reg_id, reg_name, reg_date,
					update_id, update_name, update_date;

//	상세 조회, 수정 페이지, 수정
	public BoardDto(String no, String title, String content, String attach, String hit, String reg_id, String reg_name,
			String reg_date, String update_id, String update_name, String update_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.hit = hit;
		this.reg_id = reg_id;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.update_id = update_id;
		this.update_name = update_name;
		this.update_date = update_date;
	}

//	등록
	public BoardDto(String no, String title, String content, String attach, String reg_id, String reg_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}

//	목록 (content 사용 안함)
	public BoardDto(String no, String title, String content, String attach, String hit, String reg_name, String reg_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.hit = hit;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
	}

	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getAttach() {
		return attach;
	}

	public String getHit() {
		return hit;
	}

	public String getReg_id() {
		return reg_id;
	}

	public String getReg_name() {
		return reg_name;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getUpdate_id() {
		return update_id;
	}

	public String getUpdate_name() {
		return update_name;
	}

	public String getUpdate_date() {
		return update_date;
	}
	
}
