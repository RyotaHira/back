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


@Controller
public class UserInfoController {

  /** 
   * ユーザー情報 Service
   */
  @Autowired
  private UserInfoService userInfoService;

  /**
   * ユーザー情報一覧画面を初期表示
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping(value = "/user/list")
  public String displayList(Model model) {
    // 検索結果をリストに格納
    List<UserInfo> userList = userInfoService.findAll();

    // Modelにユーザー情報を登録(ControllerからViewへ渡す)
    model.addAttribute("userlist", userList);
    model.addAttribute("userSearchRequest", new UserSearchRequest());
    return "user/search";
  }

  /**
   * ユーザー新規登録画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面 
   */
  @GetMapping(value = "/user/add")
  public String displayAdd(Model model) {
    // Modelにユーザーのリクエストデータをnewして登録
    model.addAttribute("userAddRequest", new UserAddRequest());
    return "user/add";
  }
  
  /**
   * ユーザー編集画面を表示
   * @param id ユーザーID
   * @param model Model
   * @return ユーザー編集画面
   */
  @GetMapping(value = "/user/{id}/edit") // search.htmlの編集
  public String displayEdit(@PathVariable Long id, Model model){ 
    // ↑ @PathVariable："/user/{id}/edit"で指定されたidが引数Long idに渡される

    // idをもとに検索
    UserInfo user = userInfoService.findById(id);

    UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
    userUpdateRequest.setId(user.getId());
    userUpdateRequest.setName(user.getName());
    userUpdateRequest.setPhone(user.getPhone());
    userUpdateRequest.setAddress(user.getAddress());
    model.addAttribute("userUpdateRequest", userUpdateRequest);
    return "user/edit";
  }

   /**
   * ユーザー情報検索
   * @param userSearchRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @RequestMapping(value = "/user/search", method=RequestMethod.POST)
  public String search(@ModelAttribute UserSearchRequest userSearchRequest, Model model){
    // @ModelAttribute フォームから受け取ったデータをUserSearchRequestオブジェクトにマッピングする

    // マッピングされたデータ(id,name)から検索
    List<UserInfo> userList = userInfoService.search(userSearchRequest);
    model.addAttribute("userlist", userList);
    return "user/search";
  }

  /**
   * ユーザー情報削除（論理削除）
   * @param id ユーザーID
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping("user/{id}/delete")
  public String delete(@PathVariable Long id, Model model) {
    // ユーザー情報の削除
    userInfoService.delete(id);

    // リダイレクト(View名を入れるところだがリクエストパスを入れる)
    return "redirect:/user/list";
  }

  /**
   * ユーザー新規登録
   * @param userRequest リクエストデータ
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, Model model) {
    // @ModelAttribute フォームから受け取ったデータをUserSearchRequestオブジェクトにマッピングする。@validatedをつけることで入力チェックもおこなう。

    if (result.hasErrors()) {
    // 入力チェックエラーの場合
    List<String> errorList = new ArrayList<String>();

    for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
    }
    model.addAttribute("validationError", errorList);
    return "user/add";
    }
    // ユーザー情報の登録
    userInfoService.save(userRequest);
    return "redirect:/user/list";
    }
  
  /**
   * ユーザー更新
   * @param userRequest
   * @param model Model
   * @return ユーザー情報詳細画面
   */
  @RequestMapping(value = "/user/update", method=RequestMethod.POST)
  public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
    if (result.hasErrors()){
      // 入力エラーの場合
      List<String> errorList = new ArrayList<String>();
      for (ObjectError erro : result.getAllErrors()){
        errorList.add(erro.getDefaultMessage());
      }
      model.addAttribute("ValidationError", errorList);
      return "user/edit";
    }
    // ユーザー更新の情報
    userInfoService.update(userUpdateRequest);
    return "redirect:/user/list";
  }
  
}
