package com.hanghae.sleekspeedy.domain.user.controller;

import com.hanghae.sleekspeedy.domain.user.dto.MailAuthDto;
import com.hanghae.sleekspeedy.domain.user.dto.MailRequestDto;
import com.hanghae.sleekspeedy.domain.user.dto.SignupRequestDto;
import com.hanghae.sleekspeedy.domain.user.service.MailService;
import com.hanghae.sleekspeedy.domain.user.service.UserService;
import com.hanghae.sleekspeedy.global.response.ResponseDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final MailService mailService;
  private final UserService userService;

  /* Send Email: 인증번호 전송 버튼 click */
  @PostMapping("/signup/sendEmail")
  public Map<String, String> mailSend(@RequestBody @Valid MailRequestDto mailRequestDto)
      throws MessagingException {
    String code = mailService.sendSimpleMessage(mailRequestDto.getEmail());
    // response를 JSON 문자열으로 반환
    Map<String, String> response = new HashMap<>();
    response.put("code", code);

    return response;
  }

  /* Email Auth: 인증번호 입력 후 인증 버튼 click */
  @PostMapping("/signup/authEmail")
  public String authCheck(@RequestBody @Valid MailAuthDto mailAuthDto) {
    Boolean checked = mailService.checkAuthNum(mailAuthDto.getMail(), mailAuthDto.getAuthNum());
    if (checked) {
      return "이메일 인증 성공!";
    }
    else {
      throw new NullPointerException("이메일 인증 실패!");
    }
  }

  @PostMapping("/users/signup")
  public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto,
      BindingResult bindingResult) {

    String errorMessages = "";
    if (bindingResult.hasErrors()) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        errorMessages +=
            fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n";
      }
      return ResponseEntity.badRequest().body(ResponseDto.fail(400, errorMessages));
    }

    userService.signup(requestDto);

    return ResponseEntity.ok().body(ResponseDto.success(200));
  }
}
