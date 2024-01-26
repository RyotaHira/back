package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/* ユーザー情報 Entity 
 * データベースから取得したデータを格納 
*/
@Entity // Entityクラス
@Data // getter/setterでアクセスできる
@Table(name = "user") // テーブルとマッピング
public class User {
  /* ID */
  @Id // 主キー
  @Column(name = "id") // カラム名
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番(最適設定)
  private Long id;

  /* 名前 */
  @Column(name = "name")
  private String name;

  /* 住所 */
  @Column(name = "address")
  private String address;

  /* 電話番号 */
  @Column(name = "phone")
  private String phone;

  /* 更新日時 */
  @Column(name = "updateDate")
  private Date updateDate;

  /* 登録日時 */
  @Column(name = "createDate")
  private Date createDate;

  /* 削除日時 */
  @Column(name = "deleteDate")
  private Date deleteDate;
}
