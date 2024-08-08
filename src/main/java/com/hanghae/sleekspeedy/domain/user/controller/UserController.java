package com.hanghae.sleekspeedy.domain.user.controller;

import com.hanghae.sleekspeedy.domain.user.dto.MailDto;
import com.hanghae.sleekspeedy.domain.user.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final MailService mailService;

  @ResponseBody
  @PostMapping("/emailCheck")
  public String emailCheck(@RequestBody MailDto mailDto) throws MessagingException {
    String authCode = mailService.sendSimpleMessage(mailDto.getEmail());
    return authCode;
  }
}
