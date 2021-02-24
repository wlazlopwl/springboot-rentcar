package com.appdevpwl.rentCar.repository;

import com.appdevpwl.rentCar.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByToken(String confirmationToken);
}
