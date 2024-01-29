package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserSearchRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserMapper;

/* ユーザー情報 Service
 * 具体的な処理(ビジネスロジック)を記述するクラス
 */
@Service
public class UserService {
  /* ユーザー情報 Repository(Mapper) */
  @Autowired
  private UserMapper userMapper;

  /* ユーザー情報検索
   * @param userSearchRequest リクエストデータ
   * @return 検索結果
   */
  public User search(UserSearchRequest userSearchRequest) {
    return userMapper.search(userSearchRequest);
  }
}
