package com.jakubeeee.webapptemplate.core.controllers;

import com.jakubeeee.webapptemplate.core.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocaleController {

    @Autowired
    LocaleService localeService;

    @PostMapping(path = "/changeLocale", consumes = "text/plain")
    public void changeLocale(@RequestBody String locale) {
        localeService.changeLocale(locale);
    }
}
