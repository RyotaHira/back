// Carクラスの定義(オブジェクトの設計図)

import java.util.Random;

class Car {
  //--------------変数--------------------
  String color;
  int speed;

  //--------------メソッド--------------------
  // コンストラクタ：オブジェクトを初期化するための特別なメソッド
  public Car(String color, int speed) {
      this.color = color;
      this.speed = speed;
  }

  // 加速メソッド
  void accelerate(int amount) {
      speed += amount;
  }

  // ブレーキメソッド
  void brake(int amount) {
      speed -= amount;
  }

  // 車の情報を表示するメソッド
  void displayInfo() {
      System.out.println("車の色: " + color);
      System.out.println("車の速度: " + speed);
  }
}

public class Main {
  public static void main(String[] args) {
    //   // Carクラスを使用して車(オブジェクト)を生成・初期化
    //   Car myCar = new Car("赤", 60);
    //   Car friendCar = new Car("青", 70);

    //   // 車を操作
    //   myCar.accelerate(10);
    //   friendCar.brake(5);

    //   // 車の情報を表示
    //   System.out.println("私の車:");
    //   myCar.displayInfo();

    //   System.out.println("\n友達の車:");
    //   friendCar.displayInfo();
    int r = new Random().nextInt(90);
    System.out.println("ランダムの数字：" + r);

    Pokemon pikachu = new Pokemon("ピカチュウ", 15);
    Pokemon eevee = new Pokemon("イーブイ", 10);
    pikachu.attack(eevee);
    eevee.attack(pikachu);
  }
}

// ポケモンクラス
class Pokemon {
    String name; // ポケモン名
    int damage; // ダメージ

    // コンストラクタ
    public Pokemon(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    void attack(Pokemon target){
        System.out.println(this.name + "は" + target.name + "に" + this.damage + "のダメージを与えた！");
    }
}