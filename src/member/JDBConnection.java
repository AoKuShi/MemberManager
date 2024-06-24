package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBConnection {
  public Connection conn;
  public PreparedStatement pstmt;
  public ResultSet rs;

  public JDBConnection() {
    final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xe";
    final String username = "C##JAVA";
    final String password = "0000";

    conn = null;

    try {
      // Oracle JDBC 드라이버 로드
      Class.forName("oracle.jdbc.driver.OracleDriver");

      // 데이터베이스에 연결
      conn = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("오라클 DB 연결 성공");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      if (conn != null) conn.close();
      if (pstmt != null) pstmt.close();
      if (rs != null) rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
