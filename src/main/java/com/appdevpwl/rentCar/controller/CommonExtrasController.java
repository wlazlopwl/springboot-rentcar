package com.appdevpwl.rentCar.controller;

import com.appdevpwl.rentCar.model.Extras;
import com.appdevpwl.rentCar.service.impl.CommonExtrasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CommonExtrasController {


    CommonExtrasServiceImpl commonExtrasServiceImpl;

    @Autowired
    public CommonExtrasController(CommonExtrasServiceImpl commonExtrasServiceImpl) {
        this.commonExtrasServiceImpl = commonExtrasServiceImpl;
    }

    @RequestMapping("/admin/extras")
    public String getAllExtras(Model model) {
        List<Extras> extrasList = commonExtrasServiceImpl.allExtras();

        model.addAttribute("extrasList", extrasList);
        return "extras";
    }

    @RequestMapping("/admin/extras/new")
    public String showAddExtrasPage(Model model) {
        Extras extras = new Extras();
        model.addAttribute(extras);
        return "addNewExtras";
    }

    @RequestMapping(value = "/saveNewExtras", method = RequestMethod.POST)
    public String addExtras(@ModelAttribute("extras") Extras extras) {
        commonExtrasServiceImpl.addExtras(extras);
        return "redirect:/admin/extras";
    }

    @RequestMapping(value = "/admin/extras/del/{id}", method = RequestMethod.GET)
    public String deleteExtras(@PathVariable Long id)  {
        commonExtrasServiceImpl.deleteExtras(id);
        return "redirect:/admin/extras";
    }
}
