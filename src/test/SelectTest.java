package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
  public static void main(String[] args) {
    SelectTest test = new SelectTest();
    test.selectMember(2);
  }

  public void selectMember(int no) {
    // DB 연결
    Connection conn = getDBConnection();

    // PreparedStatement 변수 선언, ResultSet 변수 선언
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // sql문 만들기
    String sql = "SELECT * FROM MEMBER WHERE NO = ? ORDER BY NO";

    try {
      // PreparedStatement 객체 생성
      pstmt = conn.prepareStatement(sql);

      // SQL문 실행
      pstmt.setInt(1, no);
      rs = pstmt.executeQuery();
      

      // ResultSet 객체에서 결과값 가져와서 출력하기
      while (rs.next()) {
        System.out.println("No: " + rs.getInt("no") + ", ID: " + rs.getString("id") + ", Password: " + rs.getString("password") + ", Nickname: " + rs.getString("nickname") + ", Regdate: " + rs.getDate("regdate"));
      }

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
      if (rs != null) {
        try {
          rs.close();
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

  public void selectAll() {
    // DB 연결
    Connection conn = getDBConnection();

    // PreparedStatement 변수 선언, ResultSet 변수 선언
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // sql문 만들기
    String sql = "SELECT * FROM MEMBER ORDER BY NO";

    try {
      // PreparedStatement 객체 생성
      pstmt = conn.prepareStatement(sql);

      // SQL문 실행
      rs = pstmt.executeQuery();

      // ResultSet 객체에서 결과값 가져와서 출력하기
      while (rs.next()) {
        System.out.println("No: " + rs.getInt("no") + ", ID: " + rs.getString("id") + ", Password: " + rs.getString("password") + ", Nickname: " + rs.getString("nickname") + ", Regdate: " + rs.getDate("regdate"));
      }

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
      if (rs != null) {
        try {
          rs.close();
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
