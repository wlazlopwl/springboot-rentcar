package com.appdevpwl.rentCar.repository;

import com.appdevpwl.rentCar.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

}
