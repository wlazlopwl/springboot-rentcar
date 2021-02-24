package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.model.Extras;
import com.appdevpwl.rentCar.repository.CommonExtrasRepository;
import com.appdevpwl.rentCar.service.CommonExtrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonExtrasServiceImpl implements CommonExtrasService {

    CommonExtrasRepository commonExtrasRepository;

    @Autowired
    public CommonExtrasServiceImpl(CommonExtrasRepository commonExtrasRepository) {
        this.commonExtrasRepository = commonExtrasRepository;
    }

    public List<Extras> allExtras(){
        return commonExtrasRepository.findAll();
    }

    public Extras addExtras(Extras extras){
        return commonExtrasRepository.save(extras);
    }

    public void deleteExtras(Long id) {
        commonExtrasRepository.deleteById(id);
    }

    public Extras simpleExtras(Long id) {
        return commonExtrasRepository.getOne(id);
    }

    public void addExtrasCar(Long id, Car car) {
        commonExtrasRepository.getOne(id).getCars().add(car);
    }


}
