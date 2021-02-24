package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.Reservations;

import java.util.List;

public interface ReservationService {

    List<Reservations> allReservations();

    Reservations saveReservation(Reservations reservations);
}
