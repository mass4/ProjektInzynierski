package pl.szymanski.ProjektInzynierski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szymanski.ProjektInzynierski.model.MoistureSensor;
import pl.szymanski.ProjektInzynierski.repository.MoistureSensorRepo;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MoistureController {

    @Autowired
    MoistureSensorRepo moistureSensorRepository;

    @RequestMapping("/sensors/moisture")
    public String getSensors(Model model){
        List<MoistureSensor> moistureSensors = moistureSensorRepository.getAllSensors();
        model.addAttribute("moistureSensors",moistureSensors);
        return "moistureSensors";
    }

    @RequestMapping(value="/sensor/moisture/delete/{id}")
    public String deleteSensor(@PathVariable("id") Long id){
        moistureSensorRepository.deleteSensor(moistureSensorRepository.getSensor(id));
        return "redirect:/sensors/moisture";
    }

    @RequestMapping(value="/sensor/moisture/edit/{id}")
    public String editSensor(@PathVariable("id") Long id, Model model){
        model.addAttribute("sensor",moistureSensorRepository.getSensor(id));
        return "moistureSensorEditForm";
    }

    @RequestMapping(value = "/updateMoistureSensor", method = RequestMethod.POST)
    public String updateProduct(@Valid MoistureSensor moistureSensor, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(
                    error -> System.out.println(error.getObjectName()+" "+error.getDefaultMessage())
            );
            return  "moistureSensorEditForm";
        }
        else {
            moistureSensorRepository.updateSensor(moistureSensor);
            return "redirect:/sensors/moisture";
        }
    }

    @RequestMapping("/sensor/moisture/newSensor")
    public String createProduct(Model model){
        model.addAttribute("sensor", new MoistureSensor());
        return "moistureSensorForm";
    }

    @RequestMapping(value = "/sensors/moisture", method = RequestMethod.POST)
    public String saveProduct(@Valid MoistureSensor sensor, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(
                    error -> System.out.println(error.getObjectName()+" "+error.getDefaultMessage())
            );
            return "moistureSensorForm";
        }
        else {
            moistureSensorRepository.createSensor(sensor);
            return "redirect:/sensors/moisture";
        }
    }

}
