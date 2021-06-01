package com.expensetracker.expensetracker.controller;

import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieAktuellerMonatDTO;
import com.expensetracker.expensetracker.service.uebersicht.UebersichtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UebersichtController {

    @Autowired
    UebersichtService uebersichtService;

    @GetMapping(value = "/AusgabeJeKategorieAktuellerMonat")
    public @ResponseBody List<AusgabeJeKategorieAktuellerMonatDTO> getAusgabeJeKategorieAktuellerMonat() {
        uebersichtService.findAusgabeJeKategorieAktuellerMonat("1");
    }


}
