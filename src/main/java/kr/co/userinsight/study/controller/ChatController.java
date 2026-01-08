package kr.co.userinsight.study.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.userinsight.study.dto.ChatMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

  @MessageMapping("/chat/{roomId}")
  @SendTo("/sub/chat/{roomId}")
  public ChatMessage sendMessage(@DestinationVariable("roomId") String roomId, ChatMessage chatMessage) {
    log.info("roomId: {}, id: {}, username: {}, message: {}",
        roomId, chatMessage.getId(), chatMessage.getUsername(), chatMessage.getMessage());

    return chatMessage;
  }
}
