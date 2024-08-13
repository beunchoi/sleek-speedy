package com.hanghae.sleekspeedy.domain.order.controller;

import com.hanghae.sleekspeedy.domain.order.dto.OrderProductResponse;
import com.hanghae.sleekspeedy.domain.order.service.OrderService;
import com.hanghae.sleekspeedy.domain.user.entity.User;
import com.hanghae.sleekspeedy.global.response.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/orders")
  public ResponseEntity<ResponseDto> createOrder(User user) {
    orderService.createOrder(user);
    return ResponseEntity.ok().body(ResponseDto.success(200));
  }

  @GetMapping("/orders/{orderId}")
  public List<OrderProductResponse> getOrderProducts(@PathVariable Long orderId, User user) {
    return orderService.getOrderProducts(orderId, user);
  }

  @PutMapping("/orders/{orderProductId}")
  public ResponseEntity<ResponseDto> cancelOrderProduct(@PathVariable Long orderProductId, User user) {
    orderService.cancelOrderProduct(orderProductId, user);
    return ResponseEntity.ok().body(ResponseDto.success(200));
  }

  @PutMapping("/orders/{orderProductId}")
  public ResponseEntity<ResponseDto> returnOrderProduct(@PathVariable Long orderProductId, User user) {
    orderService.returnOrderProduct(orderProductId, user);
    return ResponseEntity.ok().body(ResponseDto.success(200));
  }
}
