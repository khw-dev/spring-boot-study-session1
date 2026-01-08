package kr.co.userinsight.study.api;

import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.userinsight.study.dto.ChatMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/chat")
public class ApiChatController {

  private final List<String> randomMessages = List.of(
      "반가워! 오늘 스프링 공부는 잘 되고 있어?",
      "음... 저녁 메뉴는 정했니? 배고프다.",
      "오류가 났을 땐 로그를 꼼꼼히 읽어보는 게 좋아!",
      "스프링 부트는 정말 편리한 프레임워크야, 그렇지?",
      "가끔은 모니터에서 눈을 떼고 스트레칭도 좀 해!",
      "너의 코딩 실력이 일취월장하고 있구나!",
      "MVC 패턴이 처음엔 헷갈려도 익숙해지면 정말 편해.",
      "API 연결은 잘 이해했어?",
      "어라? 방금 무슨 말 했어? 잠깐 딴생각 중이었어 😅",
      "화이팅! 넌 훌륭한 개발자가 될 수 있을 거야.");

  private final Random random = new Random();

  @PostMapping("/message")
  public ResponseEntity<ChatMessage> message(@RequestBody ChatMessage chatMessage) {
    log.info("username: {}, message: {}", chatMessage.getUsername(), chatMessage.getMessage());

    int randomIndex = random.nextInt(randomMessages.size());
    String randomReply = randomMessages.get(randomIndex);

    return ResponseEntity.ok(new ChatMessage("스프링 봇", randomReply));
  }
}
