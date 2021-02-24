package com.appdevpwl.rentCar.controller;

import com.appdevpwl.rentCar.model.Car;
import com.appdevpwl.rentCar.model.Engine;
import com.appdevpwl.rentCar.model.Extras;
import com.appdevpwl.rentCar.model.User;
import com.appdevpwl.rentCar.service.impl.CarServiceImpl;
import com.appdevpwl.rentCar.service.impl.CommonExtrasServiceImpl;
import com.appdevpwl.rentCar.service.impl.EngineServiceImpl;
import com.appdevpwl.rentCar.service.impl.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    CarServiceImpl carServiceImpl;
    CommonExtrasServiceImpl commonExtrasServiceImpl;
    EngineServiceImpl engineServiceImpl;
    UserServiceImpl userServiceImpl;

    @Autowired
    public AdminController(CarServiceImpl carServiceImpl, CommonExtrasServiceImpl commonExtrasServiceImpl, EngineServiceImpl engineServiceImpl, UserServiceImpl userServiceImpl) {
        this.carServiceImpl = carServiceImpl;
        this.commonExtrasServiceImpl = commonExtrasServiceImpl;
        this.engineServiceImpl = engineServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping("/admin")
    public String getAdminPanel() {

        return "admin";
    }

    @RequestMapping(value = "/updateCar")
    public String updateCar(@ModelAttribute("car") Car car,
                            @RequestParam("idEngineChecked") long idEngineChecked) {

        car.setEngine(engineServiceImpl.simpleEngine(idEngineChecked));
        carServiceImpl.saveCar(car);

        return "redirect:/admin/car/update/" + car.getId();
    }

    @RequestMapping(value = "/addExtrasToCar", method = RequestMethod.POST)
    public String addExtrasToCar(@RequestParam("extrasId") Long extrasId,
                                 @RequestParam("carId") Long carId) {


        Set<Car> carSet = new HashSet<>();
        Set<Extras> extrasSet = carServiceImpl.simpleCar(carId).getCar_extras();

        Extras extras = commonExtrasServiceImpl.simpleExtras(extrasId);
        Car car1 = carServiceImpl.simpleCar(carId);
        extrasSet.add(extras);
        car1.setCar_extras(extrasSet);
        carSet.add(car1);
        extras.setCars(carSet);

        carServiceImpl.saveCar(car1);

        return "redirect:/admin/car/update/" + carId;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteExtrasInCar(@RequestParam("carId") Long carId,
                                    @RequestParam("extrasId") Long extrasId) {


        Set<Extras> extrasSet = carServiceImpl.simpleCar(carId).getCar_extras();
        Extras extras = commonExtrasServiceImpl.simpleExtras(extrasId);
        Car car = carServiceImpl.simpleCar(carId);
        extrasSet.remove(extras);
        car.setCar_extras(extrasSet);

        carServiceImpl.saveCar(car);

        return "redirect:/admin/car/update/" + carId;
    }


    @RequestMapping("/admin/users")
    public String allUsers(Model model) {
        List<User> userList = userServiceImpl.allUser();

        model.addAttribute("userList", userList);
        return "allUser";
    }

    @RequestMapping(value = "/admin/users/del/{id}", method = RequestMethod.GET)
    public String deleteExtras(@PathVariable Long id) throws NotFoundException {
        userServiceImpl.deleteById(id);
        return "redirect:/admin/users";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addNewCar(@ModelAttribute("car") Car car,
                            @RequestParam("idChecked") List<String> checkedExtrasId,
                            @RequestParam("idEngineChecked") String checkedEngineId) {


        if (checkedExtrasId != null) {
            Set<Car> carSet = new HashSet<>();
            Set<Extras> extrasSet = new HashSet<>();

            for (String id : checkedExtrasId) {
                long longExtrasId = Long.parseLong(id);

                Extras extras = commonExtrasServiceImpl.simpleExtras(longExtrasId);
                extrasSet.add(extras);
                car.setCar_extras(extrasSet);
                carSet.add(car);
                extras.setCars(carSet);


            }
        }
        long longEngineId = Long.parseLong(checkedEngineId);
        car.setEngine(engineServiceImpl.simpleEngine(longEngineId));
        carServiceImpl.saveCar(car);
        return "redirect:/admin/car";
    }

    @RequestMapping(value = "/admin/car/del/{id}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable Long id) {
        carServiceImpl.deleteCar(id);
        return "redirect:/admin/car";
    }

    @RequestMapping("/admin/car")
    public String allCar(Model model) {
        List<Car> carList = carServiceImpl.allCars();

        model.addAttribute("carList", carList);
        return "CarManagement";
    }

    @RequestMapping("admin/car/new")
    public String showAddVehiclePage(Model model) {
        Car car = new Car();
        List<Extras> extrasList = commonExtrasServiceImpl.allExtras();
        List<Engine> engineList = engineServiceImpl.allEngine();
        model.addAttribute(car);
        model.addAttribute(extrasList);
        model.addAttribute(engineList);


        return "addCarManagement";
    }

    @RequestMapping("admin/car/update/{id}")
    public String showUpdateVehiclePage(@PathVariable("id") Long id, Model model) {
        Car car = carServiceImpl.simpleCar(id);
        Set<Extras> carExtrasSet = car.getCar_extras();
        List<Extras> carExtrasList = new ArrayList<>(carExtrasSet);
        Engine engine = car.getEngine();
        List<Engine> engineList = engineServiceImpl.allEngine();
        List<Extras> extrasList = commonExtrasServiceImpl.allExtras();
        List<Extras> notSelectedExtras = new ArrayList<>();

        List<Extras> extrasFromTwoList = new ArrayList<>();
        extrasFromTwoList.addAll(extrasList);
        extrasFromTwoList.addAll(carExtrasList);

        for (int i = 0; i <= extrasFromTwoList.size() - 1; i++) {

            int counter = 0;
            for (int j = 0; j <= extrasFromTwoList.size() - 1; j++) {
                if (j != i) {
                    if (extrasFromTwoList.get(i).getId().equals(extrasFromTwoList.get(j).getId())) {
                        counter++;
                    }
                }
            }
            if (counter == 0) {
                Extras extras = extrasFromTwoList.get(i);
                notSelectedExtras.add(extras);
            }
        }


        model.addAttribute(car);
        model.addAttribute("notSelected", notSelectedExtras);
        model.addAttribute(extrasList);
        model.addAttribute(engineList);
        model.addAttribute(engine);

        return "updateCar";
    }

}
