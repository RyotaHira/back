package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;



@Controller // リクエストを受け付けるためのクラス
public class UserInfoController {
  /**
   * サービスクラス
   */
  @Autowired // インスタンス化
  private UserInfoService userInfoService;

  /**
   * ユーザー情報一覧画面の初期表示
   * @param model Model ViewとControllerのデータの受け渡し
   * @return ユーザー情報一覧画面
   */
  @GetMapping("/user/list") // リクエストを受け付ける
  public String displayList(Model model) {
    // ユーザー情報を取得する
    List<UserInfo> userList = userInfoService.findAll();

    // modelに格納
    model.addAttribute("userlist", userList); // ユーザー情報
    model.addAttribute("userSearchRequest", new UserSearchRequest()); // 検索用のid,nameの初期化
    return "user/search";
  }
  
  /**
   * ユーザー新規登録画面の初期表示(新規登録はこちらボタン)
   * @param param Model
   * @return ユーザー新規登録画面
   */
  @GetMapping("/user/add")
  public String displayAdd(Model model) {
    // modelにUserAddRequestクラスのインスタンス化を格納(validationされた値を画面に渡す)
    model.addAttribute("userAddRequest", new UserAddRequest());
    return "/user/add";
  }

  /**
   * ユーザー編集画面の初期表示(編集ボタン)
   * @param id ユーザーID
   * @param model Model 
   * @return ユーザー編集画面
   */
  @GetMapping("/user/{id}/edit") // idはパス変数
  public String displayEdit(@PathVariable Long id, Model model) {
    // ↑ @PathVariableはパス変数で渡された変数を取り出す

    // idからユーザー情報を取得する
    UserInfo user = userInfoService.findById(id);

    // userUpdateRequestクラスのインスタンス化(データの受け渡しのため)
    UserUpdateRequest userUpdateRequest = new UserUpdateRequest();

    // setterを利用してuserUpdateRequestに値を格納する
    userUpdateRequest.setId(user.getId());
    userUpdateRequest.setName(user.getName());
    userUpdateRequest.setPhone(user.getPhone());
    userUpdateRequest.setAddress(user.getAddress());

    // idで検索したユーザー情報を画面に返す
    model.addAttribute("userUpdateRequest", userUpdateRequest);
    return "user/edit";
  }
  
  /**
   * ユーザー情報検索(検索ボタン)
   * @param userSearchRequest リクエストデータ(id,nameの画面情報をuserSearchRequestで受け取る)
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @RequestMapping(value = "/user/search", method=RequestMethod.POST)
  public String search(@ModelAttribute("userSearchRequest") UserSearchRequest userSearchRequest, Model model) {
       
    // 画面に入力された値をもとにユーザー情報を検索する
    List<UserInfo> userList = userInfoService.search(userSearchRequest);

    // ユーザー情報の検索結果を画面に返す
    model.addAttribute("userlist", userList);
    return "user/search";
  }

  /**
   * ユーザー情報削除(論理削除)
   * @param id ユーザーID
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping("/user/{id}/delete") // idはパス変数
  public String delete(@PathVariable Long id, Model model) {
    // ↑ @PathVariableはパス変数で渡された変数を取り出す

    userInfoService.delete(id);
    return "redirect:/user/list"; // 画面を再描画させるためリダイレクト
  }
  
  /**
   * ユーザー新規登録(登録ボタン)
   * @param userAddRequest リクエストデータ(name,address,phoneの画面情報をuserSearchRequestで受け取る)
   * @param result 入力したバリデーションの結果が格納される
   * @param model Model
   * @return ユーザー情報一覧
   */
 
  @RequestMapping(value = "user/create", method=RequestMethod.POST)
  public String create(@ModelAttribute @Validated UserAddRequest userAddRequest, BindingResult result, Model model) {
    // ↑ @Validatedを指定するとPOST時に値の自動チェックを行う。

    if (result.hasErrors()){ // hasEoors()でエラーがあるかチェック
      // 入力チェックエラーの場合
      List<String> errorList = geteError(result);

      model.addAttribute("validationError", errorList);
      return "user/add";
    }
    // ユーザー情報を登録する
    userInfoService.insert(userAddRequest);
    return "redirect:/user/list"; // 登録したユーザーを表示させるためリダイレクト
  }
  
  /**
   * ユーザー更新(保存ボタン)
   * @param userAddRequest リクエストデータ(id,name,address,phoneの情報をUserUpdateRequestで受け取る)
   * @param result 入力したバリデーションの結果が格納される
   * @param model Model
   * @return ユーザー情報一覧
   */
  @RequestMapping(value = "/user/update", method=RequestMethod.POST)
  public String update(@ModelAttribute @Validated UserUpdateRequest UserUpdateRequest, BindingResult result, Model model) {

    if (result.hasErrors()){ // hasEoors()でエラーがあるかチェック
      // 入力チェックエラーの場合
      List<String> errorList = geteError(result);

      model.addAttribute("validationError", errorList);
      return "user/edit";
    }
    userInfoService.update(UserUpdateRequest);
    return "redirect:/user/list"; // 更新したユーザーを表示させるためリダイレクト
  }

  /**
   * エラー内容の取得
   * @param errorResult エラー内容
   * @return エラーリスト
   */
  private List<String> geteError(BindingResult errorResult){
    List<String> errorList = new ArrayList<String>();

    // getAllErrors:すべてのエラーオブジェクトを取得
    for (ObjectError error : errorResult.getAllErrors()){
      // getDefaultMessage:エラーメッセージを取得
      errorList.add(error.getDefaultMessage());
    }
    return errorList;
  }  
}
