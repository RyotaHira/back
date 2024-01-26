package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/* ユーザー情報 Controller 
 * フロントエンド、バックエンドの入出力の管理をするクラス
*/
@Controller
public class UserController {
  /* Service */
  @Autowired // 使用したいクラスをインスタンス化して変数に設定するアノテーション
  UserService userService;

  /* ユーザー情報一覧画面を表示 
   * @parame model Model
   * @return ユーザー情報一覧画面のHTML
  */
  @RequestMapping(value = "/user/list", method=RequestMethod.GET)
  public String displayList(Model model){
    List<User> userlist = userService.searchAll();
    model.addAttribute("userlist", userlist);
    return "user/list";
  }
}
