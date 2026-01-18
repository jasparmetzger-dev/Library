package data;

public class MakeTables {
    static void main(String[] args) {
        String makeUsers = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    read_books TEXT
                );
                """;
        String makeBooks = """
                CREATE TABLE IF NOT EXISTS BOOKS (
                id INT PRIMARY KEY AUTO_INCREMENT,
                title VARCHAR(255) UNIQUE,
                author_id INT FOREIGN KEY,
                published DATE,
                content TEXT,
                status VARCHAR(255)
//                )
//                """;
//    }
//}
