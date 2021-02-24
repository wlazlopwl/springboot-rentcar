package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.ConfirmationToken;
import com.appdevpwl.rentCar.repository.ConfirmationTokenRepository;
import com.appdevpwl.rentCar.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }


    public ConfirmationToken getByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public ConfirmationToken save(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }
}
