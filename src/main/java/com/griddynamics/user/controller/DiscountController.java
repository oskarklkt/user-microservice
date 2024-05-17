package com.griddynamics.user.controller;

import com.griddynamics.user.dto.ClientDiscountInfoDto;
import com.griddynamics.user.exception.BaseException;
import com.griddynamics.user.exception.NoSuchElementException;
import com.griddynamics.user.service.Facade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@AllArgsConstructor
@Controller
public class DiscountController {
    private final Facade facade;

    public ClientDiscountInfoDto getClientDiscountInfo(Long userId) {
        try {
            return facade.getClientDiscountInfo(userId);
        } catch (BaseException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
        return null;
    }

    public void setUserVip(Long userId) {
        try {
            facade.setUserVip(userId);
        } catch (NoSuchElementException e) {
            log.error("{} | {}", e.getMessage(), e.getStatusCode());
        }
    }
}
