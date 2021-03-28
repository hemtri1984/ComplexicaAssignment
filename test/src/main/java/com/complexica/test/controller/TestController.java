package com.complexica.test.controller;

import com.complexica.test.model.Current;
import com.complexica.test.model.ForecastData;
import com.complexica.test.model.Main;
import com.complexica.test.model.NameEntity;
import com.complexica.test.model.Weather;
import com.complexica.test.model.WeatherEntity;
import com.complexica.test.service.ItineraryService;
import com.complexica.test.service.NameService;
import com.complexica.test.service.WeatherService;
import com.complexica.test.utils.AppUtility;
import com.complexica.test.utils.IAppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NameService nameService;
    @Autowired
    private WeatherService weatherService;
    /*@Autowired
    private ItineraryService itineraryService;*/

    @RequestMapping("/")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("homepage.html");
        return mav;
    }

    @RequestMapping("/itinerary")
    @ResponseBody
    public ResponseEntity<?> getFivedaysForecast(@RequestHeader("traveldate") String date) {
        System.out.println("Itinerary Date: "+date);
        //first check if data is available in the cache
        List<WeatherEntity> weatherData = weatherService.findWeatherByDate(date);
        if(weatherData != null && weatherData.size() > 0) {
            System.out.println("Weather data size: "+weatherData.size());
            return ResponseEntity.ok(weatherData);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/forecast")
    @ResponseBody
    public ResponseEntity<?> getFivedaysForecast(@RequestHeader("cityname") String cityname, @RequestHeader("traveldate") String date) {
        System.out.println("City Name: "+cityname+", Date: "+date);
        //first check if data is available in the cache
        List<WeatherEntity> weatherData = weatherService.findWeatherOfCity(cityname, date);
        if(weatherData != null && weatherData.size() > 0) {
            System.out.println("Found data into cache, check if its invalid or not");
            long currentTime = new Date().getTime();
            long cacheTime = weatherData.get(0).getRecordTime().getTime();
            if((currentTime - cacheTime) > IAppConstants.millisInHour) {//invalidate cache, expired
                System.out.println("Cache data invalidated. Refreshing cache");
                weatherData = getWeatherDataFromOpenweathermap(true, cityname, date);
            }
            System.out.println("Cache data not expired. Returning cache data>>>>>");
        } else {
            System.out.println("Returning data from openweather service and saving it into cache");
            weatherData = getWeatherDataFromOpenweathermap(false, cityname, date);
        }
        return ResponseEntity.ok(weatherData);
    }

    private List<WeatherEntity> getWeatherDataFromOpenweathermap(boolean invalidate, String cityname, String date) {
        //Else fetch from weather api and save into database and then send back.
        final String url = "https://api.openweathermap.org/data/2.5/forecast?q="+cityname+"&units=metric&appid=fc4708b74b09c7921a14fa439aad48eb";
        final ResponseEntity<ForecastData> responseEntity = restTemplate.getForEntity(url, ForecastData.class);
        ForecastData currentWeather = responseEntity.getBody();
        String cityName = currentWeather.getCity().getName().toLowerCase();
        String countryName = currentWeather.getCity().getCountry().toLowerCase();
        System.out.println(">>>> City name: "+cityName+"; country: "+countryName+"; date: "+date);
        List<WeatherEntity> resultDataList = new ArrayList<>();
        if(invalidate) {
            System.out.println("Invalidating all weather forecast data of City: "+cityName);
            //invalidate expired data and refresh
            for(com.complexica.test.model.List weatherList: currentWeather.getList()) {
                LocalDateTime localdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(weatherList.getDt() * 1000L), ZoneId.systemDefault());
                if(localdateTime.getHour() > 12 && localdateTime.getHour() < 18) {
                    System.out.println("Hour: "+localdateTime.getHour()+"; mins"+localdateTime.getMinute()+"; date "+localdateTime.getDayOfMonth()+"; month "+localdateTime.getMonthValue()+"; year: "+localdateTime.getYear());
                    Weather weather = weatherList.getWeather().get(0);
                    Main tempDetails = weatherList.getMain();
                    WeatherEntity weatherData = new WeatherEntity();
                    weatherData.setCityName(cityName);
                    weatherData.setCountryName(countryName);
                    weatherData.setTemperature((float)(tempDetails.getTemp()));
                    weatherData.setWeather(weather.getMain());
                    weatherData.setDate(AppUtility.getAppUtilityInstance().convertMillisToLocalDate(weatherList.getDt()*1000L));
                    weatherData.setWeathertime(AppUtility.getAppUtilityInstance().convertMillisToLocalTime(weatherList.getDt()*1000L));
                    weatherData.setRecordTime(new Date());
                    weatherData = weatherService.invalidateAndRefreshWeatherData(weatherData.getCityName(), "", "", weatherData.getTemperature(), weatherData.getWeather(), weatherData.getDate(), weatherData.getWeathertime());
                    weatherData.setCache(true);
                    System.out.println("Record date: "+weatherData.getDate());
                    if(weatherData.getDate().equalsIgnoreCase(date)) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Record matched by date");
                        resultDataList.add(weatherData);
                    }
                }
            }
        } else {
            System.out.println("Creating all weather forecast data of City: "+cityName);
            //save weather details into cache
            for(com.complexica.test.model.List weatherList: currentWeather.getList()) {
                LocalDateTime localdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(weatherList.getDt() * 1000L), ZoneId.systemDefault());
                if(localdateTime.getHour() > 12 && localdateTime.getHour() < 18) {
                    System.out.println("Hour: "+localdateTime.getHour()+"; mins"+localdateTime.getMinute()+"; date "+localdateTime.getDayOfMonth()+"; month "+localdateTime.getMonthValue()+"; year: "+localdateTime.getYear());
                    Weather weather = weatherList.getWeather().get(0);
                    Main tempDetails = weatherList.getMain();
                    WeatherEntity weatherData = new WeatherEntity();
                    weatherData.setCityName(cityName);
                    weatherData.setCountryName(countryName);
                    weatherData.setTemperature((float)(tempDetails.getTemp()));
                    weatherData.setWeather(weather.getMain());
                    weatherData.setDate(AppUtility.getAppUtilityInstance().convertMillisToLocalDate(weatherList.getDt()*1000L));
                    weatherData.setWeathertime(AppUtility.getAppUtilityInstance().convertMillisToLocalTime(weatherList.getDt()*1000L));
                    weatherData.setRecordTime(new Date());
                    weatherData.setCache(false);
                    weatherService.createWeatherData(weatherData.getCityName(), "", "", weatherData.getTemperature(), weatherData.getWeather(), weatherData.getDate(), weatherData.getWeathertime());
                    System.out.println("Record date: "+weatherData.getDate());
                    if(weatherData.getDate().equalsIgnoreCase(date)) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Record matched by date");
                        resultDataList.add(weatherData);
                    }
                }
            }
        }
        return resultDataList;
}

    @RequestMapping("/current")
    @ResponseBody
    public ResponseEntity<?> getCurrentWeather(@RequestHeader("cityname") String cityname) {
        System.out.println("City Name: "+cityname);
        //converting cityname in small case
        cityname = cityname.toLowerCase();
        //first check if data is available in the cache
        List<WeatherEntity> weatherData = weatherService.findWeatherOfCity(cityname);
        if(weatherData != null && weatherData.size() > 0) {
            System.out.println("Found data into cache, check if its invalid or not");
            long currentTime = new Date().getTime();
            long cacheTime = weatherData.get(0).getRecordTime().getTime();
            if((currentTime - cacheTime) > IAppConstants.millisInHour) {//invalidate cache, expired
                System.out.println("Cache data invalidated. Refreshing cache");
                weatherData = getWeatherDataFromOpenweathermap(true, cityname, false);
            }
            System.out.println("Cache data not expired. Returning cache data>>>>>");
        } else {
            System.out.println("Returning data from openweather service and saving it into cache");
            weatherData = getWeatherDataFromOpenweathermap(false, cityname, false);
        }
        return ResponseEntity.ok(weatherData);
    }

    private List<WeatherEntity> getWeatherDataFromOpenweathermap(boolean invalidate, String cityname, boolean cacheStatus) {
        System.out.println("Get current weather information of city "+cityname);
        List<WeatherEntity> resultDataList = new ArrayList<>();
        //Else fetch from weather api and save into database and then send back.
        final String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityname+"&units=metric&appid=fc4708b74b09c7921a14fa439aad48eb";
        final ResponseEntity<Current> responseEntity = restTemplate.getForEntity(url, Current.class);
        Current currentWeather = responseEntity.getBody();
        WeatherEntity weatherData = new WeatherEntity();
        weatherData.setCityName(currentWeather.getName().toLowerCase());
        weatherData.setTemperature((float)(currentWeather.getMain().getTemp()));
        weatherData.setWeather(currentWeather.getWeather().get(0).getMain());
        weatherData.setDate(AppUtility.getAppUtilityInstance().convertMillisToLocalDate(currentWeather.getDt()*1000L));
        weatherData.setWeathertime(AppUtility.getAppUtilityInstance().convertMillisToLocalTime(currentWeather.getDt()*1000L));
        weatherData.setRecordTime(new Date());
        weatherData.setCache(cacheStatus);
        if(invalidate) {
            System.out.println("Invalidating and refresh weather data: "+weatherData.getDate());
            //invalidate expired data and refresh
            weatherService.invalidateAndRefreshWeatherData(weatherData.getCityName(), "", "", weatherData.getTemperature(), weatherData.getWeather(), weatherData.getDate(), weatherData.getWeathertime());
        } else {
            System.out.println("Saving fresh data into cache: "+weatherData.getDate());
            //save weather details into cache
            weatherService.createWeatherData(weatherData.getCityName(), "", "", weatherData.getTemperature(), weatherData.getWeather(), weatherData.getDate(), weatherData.getWeathertime());
        }
        resultDataList.add(weatherData);
        return resultDataList;
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
