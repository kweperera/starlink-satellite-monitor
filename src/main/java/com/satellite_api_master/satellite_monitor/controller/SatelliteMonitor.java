package com.satellite_api_master.satellite_monitor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController

@CrossOrigin("*")
public class SatelliteMonitor {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "monitorSatellite/{id}/{url}/{url2}")
    public String monitorSatellite(@PathVariable("id") Long id, @PathVariable("url") String url, @PathVariable("url2") String url2) {
        String catalog_data = "";
        String location_data = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String catalog_url = String.format("http://%s:8089/getCatalogData/%x", url, id);
        String location_url = String.format("http://%s:8087/getLocationDataById/%x", url2, id);
        catalog_data = restTemplate.exchange(catalog_url, HttpMethod.GET, entity, String.class).getBody();
        location_data = restTemplate.exchange(location_url, HttpMethod.GET, entity, String.class).getBody();
        return (catalog_data + " " + location_data);
    }

}
