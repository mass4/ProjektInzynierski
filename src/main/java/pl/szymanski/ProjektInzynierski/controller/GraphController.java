package pl.szymanski.ProjektInzynierski.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.szymanski.ProjektInzynierski.model.TemperatureReading;
import pl.szymanski.ProjektInzynierski.model.TemperatureSensor;
import pl.szymanski.ProjektInzynierski.repository.TemperatureReadingRepo;
import pl.szymanski.ProjektInzynierski.repository.TemperatureSensorRepo;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GraphController {

    @Autowired
    TemperatureReadingRepo temperatureReadingRepo;

    @Autowired
    TemperatureSensorRepo temperatureSensorRepo;

    @RequestMapping("/linechartdata")
    @ResponseBody
    public String getDataFromDB() throws JsonProcessingException {
        JSONObject json = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();

        //String xx = objectMapper.writeValueAsString(temperatureReadingRepo.getAllSensorReadings());
        String xx = objectMapper.writeValueAsString(temperatureReadingRepo.getSensorReadingsBetween(new Date(1572291424000L),new Date(1572291435000L)));
        System.out.println(xx);
        return  xx;
    }

    @GetMapping("/displayBarGraph")
    public String barGraph(Model model){

        Map<String,Integer> surveyMap = new HashMap<>();
        surveyMap.put("Java",40);
        surveyMap.put("Dev ops",25);
        surveyMap.put("Python",20);
        surveyMap.put(".Net",15);
        model.addAttribute("surveyMap",surveyMap);

        return "barGraph";
    }

    @GetMapping("/displayLineGraph")
    public String lineGraph(Model model){

        List<TemperatureReading> temperatureReadings = temperatureReadingRepo.getAllReadings();
        model.addAttribute("temperatureReadings",temperatureReadings);

        List<TemperatureSensor> temperatureSensors = temperatureSensorRepo.getAllSensors();
        model.addAttribute("temperatureSensors",temperatureSensors);

        temperatureSensors.forEach(temperatureSensor -> temperatureSensor.getId());

        //List<Date> dates = temperatureReadings.stream().map(TemperatureReading::getTime).collect(Collectors.toList());
        //System.out.println(dates);
        //System.out.println("-=======-");

        List<Float> values = temperatureReadings.stream().map(TemperatureReading::getValue).collect(Collectors.toList());

        //Map<Long,TemperatureReading> temperatureReadingMap = temperatureReadings.stream().collect(Collectors.toMap(TemperatureReading::getId, temperatureReading -> temperatureReading));



        return "lineGraph";
    }
}
