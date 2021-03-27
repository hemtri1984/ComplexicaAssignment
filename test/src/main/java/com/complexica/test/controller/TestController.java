package com.complexica.test.controller;

import com.complexica.test.model.Current;
import com.complexica.test.model.ForecastData;
import com.complexica.test.model.NameEntity;
import com.complexica.test.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NameService nameService;

    @RequestMapping("/")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("homepage.html");
        return mav;
    }

    @RequestMapping("/forecast")
    @ResponseBody
    public ResponseEntity<?> getFivedaysForecast(@RequestHeader("cityname") String cityname) {
        System.out.println("City Name: "+cityname);
        //first check if data is available in the cache
        
        //Else fetch from weather api and save into database and then send back.
        final String url = "http://api.openweathermap.org/data/2.5/forecast?q="+cityname+"&appid=fc4708b74b09c7921a14fa439aad48eb";
        final ResponseEntity<ForecastData> responseEntity = restTemplate.getForEntity(url, ForecastData.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @RequestMapping("/current")
    @ResponseBody
    public ResponseEntity<?> getCurrentWeather(@RequestHeader("cityname") String cityname) {
        System.out.println("City Name: "+cityname);
        //first check if data is available in the cache
        
        //Else fetch from weather api and save into database and then send back.
        final String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid=fc4708b74b09c7921a14fa439aad48eb";
        final ResponseEntity<Current> responseEntity = restTemplate.getForEntity(url, Current.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @RequestMapping("/welcomes-msg")
    @ResponseBody
    public ResponseEntity<?> getWelcomeMsg(){
        final List<NameEntity> names = nameService.findAllNames();
        if (CollectionUtils.isEmpty(names)) {
            return ResponseEntity.ok("No names found");
        }
        return ResponseEntity.ok(names.parallelStream().map(n -> n.getName()).collect(Collectors.joining(" and ")));
    }


}
