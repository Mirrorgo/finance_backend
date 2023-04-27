package org.spring.springboot.service;

import org.spring.springboot.domain.City;

/**
 * 城市业务逻辑接口类
 *
 * Created by xchunzhao on 02/05/2017.
 */
public interface CityService {
    City findCityByName(String cityName);
}
