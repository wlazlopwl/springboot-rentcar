package com.appdevpwl.rentCar.controller;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.model.Extras;
import com.appdevpwl.rentCar.model.Reservations;
import com.appdevpwl.rentCar.model.User;
import com.appdevpwl.rentCar.service.impl.CarServiceImpl;
import com.appdevpwl.rentCar.service.impl.CommonExtrasServiceImpl;
import com.appdevpwl.rentCar.service.impl.ReservationsServiceImpl;
import com.appdevpwl.rentCar.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ReservationController {

    ReservationsServiceImpl reservationsServiceImpl;
    CarServiceImpl carServiceImpl;
    CommonExtrasServiceImpl commonExtrasServiceImpl;
    UserServiceImpl userServiceImpl;
    @Autowired
    public ReservationController(ReservationsServiceImpl reservationsServiceImpl, CarServiceImpl carServiceImpl, CommonExtrasServiceImpl commonExtrasServiceImpl, UserServiceImpl userServiceImpl) {
        this.reservationsServiceImpl = reservationsServiceImpl;
        this.carServiceImpl = carServiceImpl;
        this.commonExtrasServiceImpl = commonExtrasServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping("/booking/{id}")
    public String showReservationPickDate(@PathVariable("id") Long carId, Model model) {
        Car car = carServiceImpl.simpleCar(carId);
        model.addAttribute(car);
        return "reservations-date";
    }

    @RequestMapping("/booking/{id}/confirm")
    public String showConfirmBooking(@PathVariable("id") Long carId,
                                     @RequestParam("pickupDate") String pickUpDate,
                                     @RequestParam("returnDate") String returnDate,
                                     @RequestParam("pickupHour") String pickUpHour,
                                     @RequestParam("returnHour") String returnHour,
                                     @RequestParam(value = "idChecked", required = false) List<String> checkedExtrasId, Model model) {

        Set<Extras> selectedExtras = new HashSet<>();
        int price = carServiceImpl.simpleCar(carId).getRentPrice();
        if (checkedExtrasId != null) {

            for (String id : checkedExtrasId) {
                long longExtrasId = Long.parseLong(id);

                Extras extras = commonExtrasServiceImpl.simpleExtras(longExtrasId);
                selectedExtras.add(extras);
                price = price + Integer.parseInt(extras.getPrice());
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        //return true if has role USER
        boolean userHasRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
        if (userHasRole) {
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
            User user = userServiceImpl.findUserByEmail(username);
            Car car = carServiceImpl.simpleCar(carId);
            model.addAttribute(car);
            model.addAttribute("pickupDate", pickUpDate);
            model.addAttribute("pickupHour", pickUpHour);
            model.addAttribute("returnDate", returnDate);
            model.addAttribute("returnHour", returnHour);
            model.addAttribute("selectedExtras", selectedExtras);
            model.addAttribute("summaryPrice", price);
            model.addAttribute(user);
            return "reservations-confirm";
        } else {
            //if user no logged -> must login
            return "";
        }


    }

    @RequestMapping(value = "/newBooking", method = RequestMethod.POST)
    public String finalizationBooking(
            @RequestParam("name") String firstName,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone) {


        Reservations reservations = new Reservations();
        reservations.setBrand(firstName);

        reservationsServiceImpl.saveReservation(reservations);

        return "testempty";
    }
}




