package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.Engine;
import com.appdevpwl.rentCar.repository.EngineRepository;
import com.appdevpwl.rentCar.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    EngineRepository engineRepository;
    @Autowired
    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public List<Engine> allEngine() {
        return engineRepository.findAll();
    }

    public Engine simpleEngine(Long id) {
        return engineRepository.getOne(id);
    }
}
