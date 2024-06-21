package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
  public static void main(String[] args) {
    InsertTest test = new InsertTest();
    test.insertMember("아이디A1", "패스워드A1", "닉네임A1");
  }

  public void insertMember(String id, String pwd, String nickname) {
    // DB 연결
    Connection conn = getDBConnection();

    // PreparedStatement 변수 선언
    PreparedStatement pstmt = null;

    // sql문 만들기
    String sql = "INSERT INTO member (no, id, password, nickname, regdate) VALUES (member_seq.NEXTVAL, ?, ?, ?, SYSDATE)";

    try {
      // PreparedStatement 객체 생성
      pstmt = conn.prepareStatement(sql);

      // SQL문 매개변수에 값 추가
      pstmt.setString(1, id);
      pstmt.setString(2, pwd);
      pstmt.setString(3, nickname);

      // SQL문 실행
      int result = pstmt.executeUpdate();
      if (result > 0) {
        System.out.println(result + "행 추가됨.");
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
