package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.model.Extras;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    List<Car> allCars();

    Car simpleCar(Long id);

    Car saveCar(Car car);

    void deleteCar(Long id);

    Page<Car> getPaginatedCars(int pageNo, int pageSize);

    void addCarExtras(Long id, Extras extras);

    List<Car> getCarByBrand(String brand);
}
