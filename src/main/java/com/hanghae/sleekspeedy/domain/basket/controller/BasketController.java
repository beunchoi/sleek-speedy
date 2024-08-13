package com.hanghae.sleekspeedy.domain.basket.controller;

import com.hanghae.sleekspeedy.domain.basket.dto.BasketProductResponse;
import com.hanghae.sleekspeedy.domain.basket.service.BasketService;
import com.hanghae.sleekspeedy.domain.user.entity.User;
import com.hanghae.sleekspeedy.global.response.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BasketController {

  private final BasketService basketService;
  @PostMapping("/basketProducts/{productId}")
  public ResponseEntity<ResponseDto> addProductToBasket(User user, Long productId) {
    basketService.addProductToBasket(user, productId);
    return ResponseEntity.ok().body(ResponseDto.success(200));
  }

  @GetMapping("/myBasketProducts")
  public List<BasketProductResponse> getMyBasketProducts(User user) {
    return basketService.getMyBasketProducts(user);
  }
}
