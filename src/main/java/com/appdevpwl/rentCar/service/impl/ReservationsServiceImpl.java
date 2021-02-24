package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.Reservations;
import com.appdevpwl.rentCar.repository.ReservationsRepository;
import com.appdevpwl.rentCar.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationsServiceImpl implements ReservationService {

    ReservationsRepository reservationsRepository;

    @Autowired
    public ReservationsServiceImpl(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    public List<Reservations> allReservations() {
        return reservationsRepository.findAll();
    }

    public Reservations saveReservation(Reservations reservations) {
        return reservationsRepository.save(reservations);
    }
}

