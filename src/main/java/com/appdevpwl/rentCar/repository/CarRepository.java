package com.appdevpwl.rentCar.repository;

import com.appdevpwl.rentCar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select car from Car car where car.brand=:brand")
    List<Car> findCarByBrand(String brand);
}
