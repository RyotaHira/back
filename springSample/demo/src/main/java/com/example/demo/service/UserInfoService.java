package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;

@Service
public class UserInfoService {
  @Autowired
  private UserInfoMapper userInfoMapper;

  /**
   * ユーザー情報全件検索
   * @return 検索結果
   */
  public List<UserInfo> findAll() {
    return userInfoMapper.findAll();
}

  /**
   * ユーザー情報主キー検索
   * @return 検索結果
   */
  public UserInfo findById(Long id) {
      return userInfoMapper.findById(id);
  }

  /**
   * ユーザー情報検索
   * @param userSearchRequest リクエストデータ
   * @return 検索結果
   */
  public List<UserInfo> search(UserSearchRequest userSearchRequest) {
      return userInfoMapper.search(userSearchRequest);
  }

  /**
   * ユーザ情報登録
   * @param userAddRequest リクエストデータ
   */
  public void save(UserAddRequest userAddRequest) {
      userInfoMapper.save(userAddRequest);
  }
  /**
   * ユーザ情報更新
   * @param userEditRequest リクエストデータ
   */
  public void update(UserUpdateRequest userUpdateRequest) {
      userInfoMapper.update(userUpdateRequest);
  }
  /**
   * ユーザー情報論理削除
   * @param id
   */
  public void delete(Long id) {
      userInfoMapper.delete(id);
  }
}
