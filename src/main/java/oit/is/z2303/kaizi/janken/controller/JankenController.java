package oit.is.z2303.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2303.kaizi.janken.model.Entry;
import oit.is.z2303.kaizi.janken.model.User;
import oit.is.z2303.kaizi.janken.model.MatchMapper;
import oit.is.z2303.kaizi.janken.model.Match;
import oit.is.z2303.kaizi.janken.model.UserMapper;

@Controller
public class JankenController {

  //@Autowired
  //private Entry room;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchMapper matchMapper;

  @GetMapping("/janken")
  public String step1(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    //this.room.addUser(loginUser);
    model.addAttribute("loginUser", loginUser);
    ArrayList<User> users1 = userMapper.selectAll();
    model.addAttribute("users1", users1);
    ArrayList<Match> matches1 = matchMapper.selectAll();
    model.addAttribute("matches1", matches1);
    return "janken.html";
  }

  @GetMapping("/match")
  public String step2(@RequestParam Integer id,Principal prin,ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    User enemy = userMapper.selectAllById(id);
    model.addAttribute("enemy",enemy);
    return "match.html";
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
