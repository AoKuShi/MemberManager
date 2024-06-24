package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest {
  public static void main(String[] args) {
    DeleteTest test = new DeleteTest();
    test.deleteMember(4);
  }

  public void deleteMember(int no) {
    // DB 연결
    Connection conn = getDBConnection();

    // PreparedStatement 변수 선언
    PreparedStatement pstmt = null;

    // sql문 만들기
    String sql = "DELETE MEMBER WHERE NO = ?";

    try {
      // PreparedStatement 객체 생성
      pstmt = conn.prepareStatement(sql);

      // SQL문 매개변수에 값 추가
      pstmt.setInt(1, no);

      // SQL문 실행
      if (pstmt.executeUpdate() == 1) {
        System.out.println("'NO = " + no + "' 행 삭제됨.");
      } else {
        System.out.println("없는 행입니다.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // 자원 객체 닫기
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (pstmt != null) {
        try {
          pstmt.close();
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
