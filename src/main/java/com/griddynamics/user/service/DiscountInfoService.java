package com.griddynamics.user.service;

import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.mapper.modelToDto.ClientDiscountInfoDtoMapper;
import com.griddynamics.user.model.User;
import com.griddynamics.user.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

@AllArgsConstructor
public class DiscountInfoService {
    private final UserRepository userRepository;
    private final ClientDiscountInfoDtoMapper clientDiscountInfoDtoMapper;

    public ClientDiscountInfoDto getClientDiscountInfo(Long userId) {
        User user = userRepository.getUser(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        return clientDiscountInfoDtoMapper.apply(user);
    }

    public void setUserVip(Long userId) {
        userRepository.setUserVip(userId);
    }
}
