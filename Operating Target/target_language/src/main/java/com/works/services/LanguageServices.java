package com.works.services;

import com.works.entities.Language;
import com.works.repositories.LanguageRepository;
import com.works.util.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class LanguageServices {

    final LanguageRepository languageRepository;


    public LanguageServices(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

        public ResponseEntity<Map<REnum,Object>> save(Language language){
            Map<REnum,Object> hashMap= new LinkedHashMap<>();

            Language language1=languageRepository.save(language);
            hashMap.put(REnum.status,true);
            hashMap.put(REnum.result,language1);
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        }



        public ResponseEntity<Map<REnum,Object>> update(Language language){
            Map<REnum,Object> hashMap = new LinkedHashMap<>();
            try{
                Optional<Language> optionalCreditCard = languageRepository.findById(language.getId());
                if(optionalCreditCard.isPresent()){
                    languageRepository.saveAndFlush(language);
                    hashMap.put(REnum.status, true);
                    hashMap.put(REnum.result, language);
                    return new  ResponseEntity(hashMap, HttpStatus.OK);
                }else{
                    hashMap.put(REnum.status, false);
                    return new  ResponseEntity(hashMap, HttpStatus.BAD_REQUEST);
                }
            }catch (Exception ex){
                hashMap.put(REnum.status, false);
                hashMap.put(REnum.message, ex.getMessage());
                return new  ResponseEntity(hashMap, HttpStatus.BAD_REQUEST);
            }


        }
    public ResponseEntity<Map<REnum,Object>> delete(Long id) {
        Map<REnum, Object> hashMap = new LinkedHashMap<>();
        try {
            languageRepository.deleteById(id);
            hashMap.put(REnum.status, true);
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        } catch (Exception ex) {
            hashMap.put(REnum.status, false);
            hashMap.put(REnum.message, ex.getMessage());
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Map<REnum,Object>> list(){
        Map<REnum,Object> hashMap =new LinkedHashMap<>();
        hashMap.put(REnum.status,true);
        hashMap.put(REnum.result,languageRepository.findAll());
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }






}
