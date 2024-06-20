package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnTest {
    public static void main(String[] args) {
        final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xe";
        final String username = "C##JAVA";
        final String password = "0000";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Oracle JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 데이터베이스에 연결
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection established!");

            // 준비된 문 생성
            String sql = "SELECT * FROM MEMBER WHERE no = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 3); // 파라미터 값 설정
            // statement.setString(2, "");

            // 쿼리 실행
            resultSet = statement.executeQuery();

            // 결과 처리
            while (resultSet.next()) {
                // MEMBER 테이블에 no, id, password, nickname, regdate 열이 있다고 가정
                int no = resultSet.getInt("no");
                String id = resultSet.getString("id");
                String passwordCol = resultSet.getString("password");
                String nickname = resultSet.getString("nickname");
                java.sql.Date regdate = resultSet.getDate("regdate");

                System.out.println("No: " + no + ", ID: " + id + ", Password: " + passwordCol + ", Nickname: "
                        + nickname + ", Regdate: " + regdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 리소스 닫기
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
