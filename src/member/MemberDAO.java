package member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

  public int deleteMember(int no) {

    JDBConnection jdbc = new JDBConnection();

    // sql문 만들기
    String sql = "DELETE MEMBER WHERE NO = ?";

    int result = 0;

    try {
      // PreparedStatement 객체 생성
      jdbc.pstmt = jdbc.conn.prepareStatement(sql);

      // SQL문 매개변수에 값 추가
      jdbc.pstmt.setInt(1, no);

      result = jdbc.pstmt.executeUpdate();

      // SQL문 실행
      if (result == 1) {
        System.out.println("'NO = " + no + "' 행 삭제됨.");
      } else {
        System.out.println("없는 행입니다.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbc.close();
    }

    return result;
  }

  public int updateMember(Member member) {
    // DB 연결
    JDBConnection jdbc = new JDBConnection();

    // sql문 만들기
    String sql = "UPDATE MEMBER SET PASSWORD = ?, NICKNAME = ? WHERE NO = ?";

    int result = 0;

    try {
      // PreparedStatement 객체 생성
      jdbc.pstmt = jdbc.conn.prepareStatement(sql);

      // SQL문 실행
      jdbc.pstmt.setString(1, member.getPassword());
      jdbc.pstmt.setString(2, member.getNickname());
      jdbc.pstmt.setInt(3, member.getNo());

      result = jdbc.pstmt.executeUpdate();

      System.out.println(result + "행이 수정되었습니다.");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbc.close();
    }

    return result;
  }

  public int insertMember(Member member) {
    // DB 연결
    JDBConnection jdbc = new JDBConnection();

    // sql문 만들기
    String sql = "INSERT INTO member (no, id, password, nickname, regdate) VALUES (member_seq.NEXTVAL, ?, ?, ?, SYSDATE)";

    int result = 0;

    try {
      // PreparedStatement 객체 생성
      jdbc.pstmt = jdbc.conn.prepareStatement(sql);

      // SQL문 매개변수에 값 추가
      jdbc.pstmt.setString(1, member.getId());
      jdbc.pstmt.setString(2, member.getPassword());
      jdbc.pstmt.setString(3, member.getNickname());

      // SQL문 실행
      result = jdbc.pstmt.executeUpdate();
      if (result == 1) {
        System.out.println(result + "행 추가됨.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbc.close();
    }

    return result;
  }

  public List<Member> selectMemberAll() {

    // DB 연결
    JDBConnection jdbc = new JDBConnection();

    // sql문 만들기
    String sql = "select * from member";

    // 결과를 저장할 List 객체
    List<Member> memberList = new ArrayList<>();

    try {
      // PreparedStatement 객체 생성
      jdbc.pstmt = jdbc.conn.prepareStatement(sql);
      // SQL문 실행
      jdbc.rs = jdbc.pstmt.executeQuery();

      // ResultSet 객체에서 결과값 가져와서 출력하기
      while (jdbc.rs.next()) {
        memberList.add(new Member(jdbc.rs.getInt("no"), jdbc.rs.getString("id"), jdbc.rs.getString("password"),
            jdbc.rs.getString("nickname"), jdbc.rs.getDate("regdate")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbc.close();
    }

    return memberList;
  }

  public Member selectMember(int no) {
    // DB 연결
    JDBConnection jdbc = new JDBConnection();

    // PreparedStatement 변수 선언, ResultSet 변수 선언
    jdbc.pstmt = null;
    jdbc.rs = null;

    // sql문 만들기
    String sql = "SELECT * FROM MEMBER WHERE NO = ? ORDER BY NO";
    Member member = null;

    try {
      // PreparedStatement 객체 생성
      jdbc.pstmt = jdbc.conn.prepareStatement(sql);

      // SQL문 실행
      jdbc.pstmt.setInt(1, no);
      jdbc.rs = jdbc.pstmt.executeQuery();

      // ResultSet 객체에서 결과값 가져와서 출력하기
      while (jdbc.rs.next()) {
        member = new Member(jdbc.rs.getInt("no"), jdbc.rs.getString("id"), jdbc.rs.getString("password"),
            jdbc.rs.getString("nickname"), jdbc.rs.getDate("regdate"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // 자원 객체 닫기
      jdbc.close();
    }

    return member;
  }
}
