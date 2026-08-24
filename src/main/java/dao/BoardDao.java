package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
import dto.BoardDto;

public class BoardDao {

//	싱글턴 패턴
	private BoardDao() {}
	static BoardDao dao = new BoardDao();
	public static BoardDao getDao() {return dao;}
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
//	게시글 번호 생성
	public String getBoardNo() {
		String no = "";
		String sql = "SELECT NVL(MAX(NO), 'N000') AS NO\r\n"
				+ "FROM MY_김견강_BOARD";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			rs = ps.executeQuery();
			
//			System.out.println("getBoardNo() original query -> "+ ps.toString());
			
			if (rs.next()) {
				no = rs.getString("NO"); // "N000"
				no = no.substring(1); // "000"
				no = Integer.toString(Integer.parseInt(no) + 1); // "1"
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(Integer.parseInt(no)); // "N001"
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error: getBoardNo() -> " + sql);
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return no;
	}

//	등록
	public int writeBoard(BoardDto board) {
		int result = 0;
		String sql = "INSERT INTO MY_김견강_BOARD\r\n"
				+ "(NO, TITLE, CONTENT, ATTACH, REG_ID, REG_DATE)\r\n"
				+ "VALUES\r\n"
				+ "(?, ?, ?, ?, ?\r\n"
				+ ", TO_DATE(?, 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			
			ps.setString(1, board.getNo());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			ps.setString(4, board.getAttach());
			ps.setString(5, board.getReg_id());
			ps.setString(6, board.getReg_date());
			
//			System.out.println("writeBoard() original query -> "+ ps.toString());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: writeBoard() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	
//	게시글 조회
	public List<BoardDto> getBoardList(String select, String search, int start, int end) {
		ArrayList<BoardDto> boardList = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM (\r\n"
				+ "    SELECT ROWNUM AS RNUM, TBL.* \r\n"
				+ "    FROM (\r\n"
				+ "        SELECT B.NO, B.TITLE, B.ATTACH, M.NAME, \r\n"
				+ "        TO_CHAR(B.REG_DATE, 'yyyy-MM-dd') AS REG_DATE, \r\n"
				+ "        B.HIT\r\n"
				+ "        FROM MY_김견강_BOARD B, MY_김견강_MEMBER M\r\n"
				+ "        WHERE B.REG_ID = M.ID\r\n"
				+ "        AND B."+select+" LIKE '%"+search+"%'\r\n"
				+ "        ORDER BY B.NO DESC\r\n"
				+ "    ) TBL\r\n"
				+ ")\r\n"
				+ "WHERE RNUM >= "+start+" AND RNUM <= " + end;
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
//			System.out.println("getBoardList() original query -> "+ ps.toString());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String attach = rs.getString("ATTACH");
				String reg_name = rs.getString("NAME");
				String reg_date = rs.getString("REG_DATE");
				String hit = rs.getString("HIT");
				
				BoardDto board = new BoardDto(no, title, "content", attach, hit, reg_name, reg_date);
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: getBoardList() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return boardList;
	}

//	페이징 totalCount
	public int getTotalCount(String select, String search) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) AS TOTALCOUNT\r\n"
				+ "FROM MY_김견강_BOARD\r\n"
				+ "WHERE "+select+" LIKE '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
//			System.out.println("getTotalCount() original query -> "+ ps.toString());
			rs = ps.executeQuery();
			
			if (rs.next()) {
				totalCount = rs.getInt("TOTALCOUNT");
			}
//			System.out.println(sql);
			
		} catch (SQLException e) {
			System.out.println("Error: getTotalCount() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return totalCount;
	}

//	게시글 조회수 업데이트
	public int updateHit(String no) {
		int result = 0;
		String sql = "UPDATE MY_김견강_BOARD\r\n"
				+ "SET HIT = HIT + 1\r\n"
				+ "WHERE NO = ?";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			ps.setString(1, no);

//			System.out.println("updateHit() original query -> "+ ps.toString());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: updateHit() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

//	게시글 상세 조회
	public BoardDto getBoardView(String no) {
		BoardDto board = null;
		String sql = "SELECT B.TITLE, B.CONTENT, B.ATTACH,\r\n"
				+ "        B.REG_ID, M.NAME AS REG_NAME, \r\n"
				+ "        TO_CHAR(B.REG_DATE, 'yyyy-MM-dd HH24:MI:SS') AS REG_DATE,\r\n"
				+ "        B.UPDATE_ID, M2.NAME AS UPDATE_NAME, \r\n"
				+ "        TO_CHAR(B.UPDATE_DATE, 'yyyy-MM-dd HH24:MI:SS') AS UPDATE_DATE, \r\n"
				+ "        B.HIT\r\n"
				+ "FROM MY_김견강_BOARD B, MY_김견강_MEMBER M, MY_김견강_MEMBER M2\r\n"
				+ "WHERE B.REG_ID = M.ID\r\n"
				+ "AND B.UPDATE_ID = M2.ID(+)\r\n"
				+ "AND B.NO = ?";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			ps.setString(1, no);
//			System.out.println("getBoardView() original query -> "+ ps.toString());
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String attach = rs.getString("ATTACH");
				String hit = rs.getString("HIT");
				String reg_name  = rs.getString("REG_NAME");
				String reg_date = rs.getString("REG_DATE");
				String update_name = rs.getString("UPDATE_NAME");
				String update_date = rs.getString("UPDATE_DATE");
				
				board = new BoardDto(no, title, content, attach, 
									hit, "reg_id", reg_name, reg_date, 
									"update_id", update_name, update_date);
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error: getBoardView() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return board;
	}

//	게시글 수정
	public int updateBoard(BoardDto board) {
		int result = 0;
		String sql = "UPDATE MY_김견강_BOARD\r\n"
					+ "SET TITLE = ?,\r\n"
					+ "    CONTENT = ?,\r\n"
					+ "    ATTACH = ?,\r\n"
					+ "    UPDATE_ID = ?,\r\n"
					+ "    UPDATE_DATE = TO_DATE(?, 'yyyy-MM-dd HH24:MI:SS')\r\n"
					+ "WHERE NO = ?";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getAttach());
			ps.setString(4, board.getUpdate_id());
			ps.setString(5, board.getUpdate_date());
			ps.setString(6, board.getNo());
//			System.out.println("updateBoard() original query -> "+ ps.toString());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: updateBoard() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

//	게시글 삭재
	public int deleteBoard(String no) {
		int result = 0;
		String sql = "DELETE FROM MY_김견강_BOARD\r\n"
				+ "WHERE NO = ?";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			ps.setString(1, no);

//			System.out.println("deleteBoard() original query -> "+ ps.toString());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: deleteBoard() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

// 	최근 게시글 목록(index)
	public List<BoardDto> getRecentlyBoardList(String select, String search, int start, int end) {
		ArrayList<BoardDto> recentlyBoardList = new ArrayList<>();
		String sql = "SELECT * FROM (\r\n"
				+ "    SELECT ROWNUM AS RNUM, TBL.* \r\n"
				+ "    FROM (\r\n"
				+ "        SELECT NO, TITLE, CONTENT, \r\n"
				+ "        TO_CHAR(REG_DATE, 'yy-MM-dd') AS REG_DATE\r\n"
				+ "        FROM MY_김견강_BOARD\r\n"
				+ "        WHERE "+select+" LIKE ?\r\n"
				+ "        ORDER BY NO DESC\r\n"
				+ "    ) TBL\r\n"
				+ ")\r\n"
				+ "WHERE RNUM >= ? AND RNUM <= ?";
		
		try {
			con = DBConnection.getConnection();
			LogPreparedStatement ps = new LogPreparedStatement(con, sql); // 최종 완성된 sql 출력용
			ps.setString(1, "%"+search+"%");
			ps.setInt(2, start);
			ps.setInt(3, end);
//			System.out.println("getRecentlyBoardList() original query -> "+ ps.toString());
			rs = ps.executeQuery();
			   while (rs.next()) {
				   String no = rs.getString("NO");
				   String title = rs.getString("TITLE");
				   String content = rs.getString("CONTENT");
				   String reg_date = rs.getString("REG_DATE");
				   
				   BoardDto board = new BoardDto(no, title, content, "attach", "reg_id", reg_date);
				   recentlyBoardList.add(board);
			   }
			
			
		} catch (SQLException e) {
			System.out.println("Error: getRecentlyBoardList() -> " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return recentlyBoardList;
	}
	
}
