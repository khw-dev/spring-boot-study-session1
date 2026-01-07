package kr.co.userinsight.study.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chat")
public class ChatController {

  @GetMapping("/enterence")
  public String enterence() {
    return "chat/enterence";
  }

  @PostMapping("/room")
  public String room(Model model, @RequestParam("username") String username) {
    // 유효성 검사
    if (StringUtils.isBlank(username)) {
      return "redirect:/chat/enterence";
    }

    model.addAttribute("username", username);
    return "chat/room";
  }
}
