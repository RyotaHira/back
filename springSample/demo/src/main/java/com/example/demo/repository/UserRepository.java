package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.User;

/* ユーザー情報 Repository 
 * データベースへアクセスするためのクラス
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 
  /*  JpaRepositoryインターフェースを継承したインターフェース 
   * データの検索が容易にできる
  */

}
// public class UserRepository {

// }
