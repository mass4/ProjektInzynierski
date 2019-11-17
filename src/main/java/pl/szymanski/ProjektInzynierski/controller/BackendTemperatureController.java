package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;
import pl.szymanski.ProjektInzynierski.repository.TemperatureSensorRepo;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TemperatureController {

    @Autowired
    TemperatureSensorRepo temperatureSensorRepository;

    @RequestMapping("/sensors/temperature")
    public String getSensors(Model model){
        List<TemperatureSensor> temperatureSensors = temperatureSensorRepository.getAllSensors();
        model.addAttribute("temperatureSensors",temperatureSensors);
        return "temperatureSensors";
    }

    @RequestMapping(value="/sensor/temperature/delete/{id}")
    public String deleteSensor(@PathVariable("id") Long id){
        temperatureSensorRepository.deleteSensor(temperatureSensorRepository.getSensor(id));
        return "redirect:/sensors/temperature";
    }

    @RequestMapping(value="/sensor/temperature/edit/{id}")
    public String editSensor(@PathVariable("id") Long id, Model model){
        model.addAttribute("sensor",temperatureSensorRepository.getSensor(id));
        return "temperatureSensorEditForm";
    }

    @RequestMapping(value = "/updateTemperatureSensor", method = RequestMethod.POST)
    public String updateProduct(@Valid TemperatureSensor temperatureSensor, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(
                    error -> System.out.println(error.getObjectName()+" "+error.getDefaultMessage())
            );
            return  "temperatureSensorEditForm";
        }
        else {
            temperatureSensorRepository.updateSensor(temperatureSensor);
            return "redirect:/sensors/temperature";
        }
    }

    @RequestMapping("/sensor/temperature/newSensor")
    public String createProduct(Model model){
        model.addAttribute("sensor", new TemperatureSensor());
        return "temperatureSensorForm";
    }

    @RequestMapping(value = "/sensors/temperature", method = RequestMethod.POST)
    public String saveProduct(@Valid TemperatureSensor sensor, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(
                    error -> System.out.println(error.getObjectName()+" "+error.getDefaultMessage())
            );
            return "temperatureSensorForm";
        }
        else {
            temperatureSensorRepository.createSensor(sensor);
            return "redirect:/sensors/temperature";
        }
    }

}
