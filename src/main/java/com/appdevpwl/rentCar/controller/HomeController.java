package com.appdevpwl.rentCar.controller;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.service.impl.CarServiceImpl;
import com.appdevpwl.rentCar.service.impl.CommonExtrasServiceImpl;
import com.appdevpwl.rentCar.service.impl.EngineServiceImpl;
import com.appdevpwl.rentCar.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    CarServiceImpl carServiceImpl;
    CommonExtrasServiceImpl commonExtrasServiceImpl;
    EngineServiceImpl engineServiceImpl;
    UserServiceImpl userServiceImpl;

    @Autowired
    public HomeController(CarServiceImpl carServiceImpl, CommonExtrasServiceImpl commonExtrasServiceImpl, EngineServiceImpl engineServiceImpl, UserServiceImpl userServiceImpl) {
        this.carServiceImpl = carServiceImpl;
        this.commonExtrasServiceImpl = commonExtrasServiceImpl;
        this.engineServiceImpl = engineServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber, Model model) {

        int pageSize = 5;
        Page<Car> carsPage = carServiceImpl.getPaginatedCars(pageNumber, pageSize);
        List<Car> carList = carsPage.getContent();
        model.addAttribute("carList", carList);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", carsPage.getTotalPages());


        return "home";
    }

    @RequestMapping("/")
    public String home(Model model) {

        findPaginated(1, model);
        return "home";
    }


    @RequestMapping("/home/{id}")
    public String getCarById(@PathVariable Long id, Model model) {
        Car simpleCar = carServiceImpl.simpleCar(id);
        model.addAttribute(simpleCar);
        return "detailcar";
    }

}


