package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
  public static void main(String[] args) {
    UpdateTest test = new UpdateTest();
    test.updateMember(3, "4321", "nick003");
  }

  public void updateMember(int no, String pw, String nn) {
    // DB 연결
    Connection conn = getDBConnection();

    // PreparedStatement 변수 선언
    PreparedStatement pstmt = null;

    // sql문 만들기
    String sql = "UPDATE MEMBER SET PASSWORD = ?, NICKNAME = ? WHERE NO = ?";

    try {
      // PreparedStatement 객체 생성
      pstmt = conn.prepareStatement(sql);

      // SQL문 실행
      pstmt.setString(1, pw);
      pstmt.setString(2, nn);
      pstmt.setInt(3, no);

      System.out.println(pstmt.executeUpdate() + "행이 수정되었습니다.");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // 자원 객체 닫기
      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

  }

  public Connection getDBConnection() {
    final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xe";
    final String username = "C##JAVA";
    final String password = "0000";

    Connection connection = null;

    try {
      // Oracle JDBC 드라이버 로드
      Class.forName("oracle.jdbc.driver.OracleDriver");

      // 데이터베이스에 연결
      connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("오라클 DB 연결 성공");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return connection;
  }
}
