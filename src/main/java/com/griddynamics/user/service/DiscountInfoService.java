package com.griddynamics.user.service;

import com.griddynamics.user.dto.ClientDiscountInfoDto;

public interface DiscountInfoService {

    ClientDiscountInfoDto getClientDiscountInfo(Long userId);

    void setUserVip(Long userId);
}
