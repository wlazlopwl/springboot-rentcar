package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken getByToken(String token);

    ConfirmationToken save(ConfirmationToken confirmationToken);
}
