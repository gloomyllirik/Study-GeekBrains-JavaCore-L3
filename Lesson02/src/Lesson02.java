import java.sql.*;

public class Lesson02 {
    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main(String[] args) throws SQLException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // создаем таблицу в БД
        stmt.executeUpdate("CREATE TABLE employers (" +
                "id        INTEGER PRIMARY KEY AUTOINCREMENT," +
                "last_name STRING," +
                "name      STRING," +
                "age       INTEGER," +
                "position  STRING," +
                "salary    INTEGER);");

        // добавляем в таблицу несколько работников
        stmt.executeUpdate("INSERT INTO employers (last_name, name, age, position, salary) " +
                "VALUES ('Ivanov', 'Petr', 30, 'manager', 50000);");
        stmt.executeUpdate("INSERT INTO employers (last_name, name, age, position, salary) " +
                "VALUES ('Petrov', 'Ivan', 35, 'driver', 30000);");
        stmt.executeUpdate("INSERT INTO employers (last_name, name, age, position, salary) " +
                "VALUES ('Sidorov', 'Oleg', 50, 'security', 20000);");

        // запрашиваем все строки из таблицы
        ResultSet rs = stmt.executeQuery("SELECT * FROM employers");
        while (rs.next()) {
            System.out.println(rs.getString("id") + " " + rs.getString("last_name") + " " +
                    rs.getString("name") + " " + rs.getInt("age") + " " +
                    rs.getString("position") + " " + rs.getInt("salary"));
        }

        // Удаляем таблицу
        stmt.executeUpdate("DROP TABLE employers");

        disconect();
    }

    public static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        stmt = conn.createStatement();
    }

    public static void disconect() throws SQLException {
        conn.close();
    }
}
