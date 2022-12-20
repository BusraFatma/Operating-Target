package com.works.restcontroller;

import com.works.entities.Language;
import com.works.services.LanguageServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/language")
public class LanguageRestController {
    final LanguageServices languageServices;

    public LanguageRestController(LanguageServices languageServices) {
        this.languageServices = languageServices;
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Language language){
        return languageServices.save(language);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return languageServices.list();
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Language language){
        return languageServices.update(language);
    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Long id){
        return languageServices.delete(id);
    }




}
