package com.hanghae.sleekspeedy.domain.user.service;

import com.hanghae.sleekspeedy.domain.basket.entity.Basket;
import com.hanghae.sleekspeedy.domain.basket.repository.BasketRepository;
import com.hanghae.sleekspeedy.domain.user.dto.SignupRequestDto;
import com.hanghae.sleekspeedy.domain.user.entity.User;
import com.hanghae.sleekspeedy.domain.user.entity.UserRoleEnum;
import com.hanghae.sleekspeedy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final BasketRepository basketRepository;

  private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

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

    UserRoleEnum role = UserRoleEnum.USER;
    if (ADMIN_TOKEN.equals(request.getAdminToken())) {
      role = UserRoleEnum.ADMIN;
    }

    User user = new User(request, password, role);
    userRepository.save(user);
    basketRepository.save(new Basket(user));
  }
}