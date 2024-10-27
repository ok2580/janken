package oit.is.z2303.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

  @GetMapping("/fight")
  @Transactional
  public String fight(@RequestParam Integer id, @RequestParam String hand,ModelMap model,Principal prin) {
    Match match1= new Match();
    String loginUserName = prin.getName();
    String Result = "";
    String enemyHand = "Gu";
    if (hand.equals(enemyHand)) {
      Result = "Draw";
    } else if (enemyHand.equals("Gu")) {
      if (hand.equals("Pa")) {
        Result = "Win";
      } else if (hand.equals("Choki")) {
        Result = "Lose";
      }
    }
    User loginUser = userMapper.selectAllByuserName(loginUserName);

    match1.setUser1(loginUser.getId());
    match1.setUser2(id);
    match1.setUser1Hand(hand);
    match1.setUser2Hand(enemyHand);

    model.addAttribute("Result", Result);
    model.addAttribute("hand", hand);
    model.addAttribute("enemyHand", enemyHand);
    model.addAttribute("loginUserName", loginUserName);
    User enemy = userMapper.selectAllById(id);
    model.addAttribute("enemy", enemy);
    matchMapper.insertMatch(match1);

    return "match.html";

  }

}
