package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.Engine;

import java.util.List;

public interface EngineService {
    List<Engine> allEngine();

    Engine simpleEngine(Long id);
}
