package com.appdevpwl.rentCar.repository;

import com.appdevpwl.rentCar.model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
}
