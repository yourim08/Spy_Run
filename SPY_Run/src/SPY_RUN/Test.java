package SPY_RUN;

import java.sql.*;

public class Test {
   public static void main(String args[]) {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spyrundb", "root","qwer1357!");
         System.out.println("DB 연결 완료!");
      } catch (ClassNotFoundException e) {
         System.out.println("JDBC 드라이버 로드 에러");
      } catch (SQLException e) {
         System.out.println("DB 연결 오류");
      }
   }
}