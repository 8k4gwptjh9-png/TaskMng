-- ユーザーテーブル
   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL
   );
   
   -- タスクテーブル
   CREATE TABLE tasks (
       id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT NOT NULL,
       title VARCHAR(100) NOT NULL,
       description TEXT NOT NULL,
       deadline DATE NOT NULL,
       priority INT NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );