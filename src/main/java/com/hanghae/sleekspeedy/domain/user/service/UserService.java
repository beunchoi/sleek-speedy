package com.hanghae.sleekspeedy.domain.user.service;

import com.hanghae.sleekspeedy.domain.basket.entity.Basket;
import com.hanghae.sleekspeedy.domain.basket.repository.BasketRepository;
import com.hanghae.sleekspeedy.domain.basketProduct.dto.BasketProductResponse;
import com.hanghae.sleekspeedy.domain.basketProduct.entity.BasketProduct;
import com.hanghae.sleekspeedy.domain.basketProduct.repository.BasketProductRepository;
import com.hanghae.sleekspeedy.domain.user.dto.SignupRequestDto;
import com.hanghae.sleekspeedy.domain.user.entity.User;
import com.hanghae.sleekspeedy.domain.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final BasketRepository basketRepository;
  private final BasketProductRepository basketProductRepository;

  public void signup(SignupRequestDto request) {
    String username = request.getUsername();
    String email = request.getEmail();
    String password = passwordEncoder.encode(request.getPassword());

    if (userRepository.findByUsername(username).isPresent()) {
      throw new IllegalArgumentException("중복된 username 입니다.");
    }

    if (userRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("중복된 email 입니다.");
    }

    User user = new User(request, password);
    userRepository.save(user);
    basketRepository.save(new Basket(user));
  }

  public List<BasketProductResponse> getMyBasketProducts(User user) {
    List<BasketProduct> productList = basketProductRepository.findAllByBasketId(user.getBasket().getId());

    return productList.stream().map(BasketProductResponse::new).collect(Collectors.toList());
  }
}
