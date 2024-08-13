package com.hanghae.sleekspeedy.domain.basketProduct.controller;

import com.hanghae.sleekspeedy.domain.basketProduct.service.BasketProductService;
import com.hanghae.sleekspeedy.domain.user.entity.User;
import com.hanghae.sleekspeedy.global.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BasketProductController {

  private final BasketProductService basketProductService;
  @PostMapping("/basketProducts/{productId}")
  public ResponseEntity<ResponseDto> addProductToBasket(User user, Long productId) {
    basketProductService.addProductToBasket(user, productId);
    return ResponseEntity.ok().body(ResponseDto.success(200));
  }
}
