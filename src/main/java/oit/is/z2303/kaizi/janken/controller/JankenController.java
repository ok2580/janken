package oit.is.z2303.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {
  @PostMapping("/janken")
  public String janken(@RequestParam String player, ModelMap model) {
    model.addAttribute("player", player);
    return "janken.html";
  }

  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
    String Result = "";
    String enemyHand = "Gu";
    if (hand.equals(enemyHand)) {
      Result = "あいこ";
    } else if (enemyHand.equals("Gu")) {
      if (hand.equals("Pa")) {
        Result = "あなたの勝ち";
      } else if (hand.equals("Tyoki")) {
        Result = "相手の勝ち";
      }
    }
    model.addAttribute("Result", Result);
    model.addAttribute("hand", hand);
    // ModelMap型変数のmodelにtasuResult2という名前の変数で，tasuResultの値を登録する．
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    return "janken.html";

  }

}
