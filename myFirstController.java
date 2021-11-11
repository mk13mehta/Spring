package com.example.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myFirstController {
    private final String SITE_IS_UP = "Site is up!"; 
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "Incorrect URL!";

    @GetMapping("/check")
    public String UrlStatus(@RequestParam String url){
        String retunMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int resCodeCat = con.getResponseCode() / 100;
            System.out.println(resCodeCat);
            if(resCodeCat == 2){
                retunMessage = SITE_IS_UP;
            }else{
                retunMessage = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e) {
            retunMessage = INCORRECT_URL;
        } catch (IOException e) {
            retunMessage = SITE_IS_DOWN;
        }

        return retunMessage;
    }
}
