package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.model.Extras;
import com.appdevpwl.rentCar.repository.CarRepository;
import com.appdevpwl.rentCar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }


    public List<Car> allCars() {
        return carRepository.findAll();
    }

    public Car simpleCar(Long id) {
        return carRepository.getOne(id);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Page<Car> getPaginatedCars(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return carRepository.findAll(pageable);
    }

    public void addCarExtras(Long id, Extras extras) {
        carRepository.getOne(id).getCar_extras().add(extras);
    }

    public List<Car> getCarByBrand(String brand) {
        return carRepository.findCarByBrand(brand);
    }


}
