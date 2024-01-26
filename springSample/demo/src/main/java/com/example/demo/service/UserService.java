package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/* ユーザー情報 Service
 * 具体的な処理(ビジネスロジック)を記述するクラス
 */
@Service
public class UserService {
  /* ユーザー情報 Repository */
  @Autowired
  UserRepository userRepository;

  public List<User> searchAll(){
    // 「user」tableの内容を全検索
    return userRepository.findAll();
  }

}
