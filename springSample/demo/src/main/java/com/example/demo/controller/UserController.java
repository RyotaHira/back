package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.dto.UserSearchRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/* ユーザー情報 Controller 
 * フロントエンド、バックエンドの入出力の管理をするクラス
*/
@Controller
public class UserController {
  /* Service */
  @Autowired // 使用したいクラスをインスタンス化して変数に設定するアノテーション
  UserService userService;

  /* ユーザー情報検索画面を表示 
   * @param model Model
   * @return ユーザー情報一覧画面
  */
  @GetMapping("/user/search")
  public String displaySearch(Model model){
    model.addAttribute("userSearchRequest", new UserSearchRequest());
    return "user/search";
  }

  /* ユーザー情報検索
   * @param userSearchRequest リクエストデータ
   * @param model Model
   * @param ユーザー情報一覧画面
   */
  @RequestMapping(value = "/user/id_search", method=RequestMethod.POST)
  public String search(@ModelAttribute UserSearchRequest userSearchRequest, Model model) { // @ModelAttribute ビューからのリクエストパラメータの属性とUserSearchRequestのフィールドが一致していればリクエストパラメータの値をオブジェクトの変数に割り当ててくれる。 
    
    // Mapperオブジェクトの呼び出し
    User user = userService.search(userSearchRequest);
    model.addAttribute("userinfo", user);
    return "user/search";
  }
}
