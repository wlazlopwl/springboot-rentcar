package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.model.Extras;

import java.util.List;

public interface CommonExtrasService {
    List<Extras> allExtras();

    Extras addExtras(Extras extras);

    void deleteExtras(Long id);

    Extras simpleExtras(Long id);

    void addExtrasCar(Long id, Car car);
}
