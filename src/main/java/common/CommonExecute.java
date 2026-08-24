package common;

import javax.servlet.http.HttpServletRequest;

public interface CommonExecute {
	// 공통 활용 메서드 정의(추상 메서드)
	public void execute(HttpServletRequest request);
}
