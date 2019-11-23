package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szymanski.ProjektInzynierski.model.MoistureReading;
import pl.szymanski.ProjektInzynierski.repository.MoistureReadingRepo;

import java.util.List;

@Controller
public class BackendMoistureReadingController {

    @Autowired
    MoistureReadingRepo moistureReadingRepo;

    @RequestMapping("/get/moisture")
    public String getRead(Model model){
        List<MoistureReading> moistureReadings = moistureReadingRepo.getAllReadings();
        model.addAttribute("moistureReadings",moistureReadings);
        return "moistureSensorsReading";
    }
}
