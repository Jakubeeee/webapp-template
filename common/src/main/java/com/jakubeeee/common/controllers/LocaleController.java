package com.jakubeeee.common.controllers;

import com.jakubeeee.common.service.LocaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LocaleController {

    private final LocaleService localeService;

    @PostMapping(path = "/changeLocale", consumes = "text/plain")
    public void changeLocale(@RequestBody String locale) {
        localeService.changeLocale(locale);
    }
}
