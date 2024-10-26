package oit.is.z2303.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2303.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry room;

  @GetMapping("/janken")
  public String step1(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("room", this.room);

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
    return "janken.html";

  }

}
