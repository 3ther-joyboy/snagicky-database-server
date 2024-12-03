package com.example.snagicky.api.controler;

import com.example.snagicky.api.model.Editions;
import com.example.snagicky.api.repo.ApiKeysRepo;
import com.example.snagicky.api.repo.EditionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/edition")
public class EditionsControler {

    @Autowired
    private EditionsRepo editionsRepo;
    @Autowired
    private ApiKeysRepo keys;

    @GetMapping(value = "/id/{id}")
    public Optional<Editions> findEditionId(@PathVariable(name = "id") long Id){
        return editionsRepo.findById(Id);
    }


    @DeleteMapping(value = "/delete/{id}/{apiKey}")
    public String deleteEdition(@PathVariable(name = "id") long id, @PathVariable(name = "apiKey") String apiKey){
        if (keys.Check(apiKey) == 1){
            try {
                editionsRepo.deleteById(id);
                return "Deleted";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @PostMapping(value = "/create/{name}/{apiKey}")
    public String createCard(@PathVariable(name = "apiKey") String apiKey, @PathVariable(name = "name") String name){
        if (keys.Check(apiKey) == 1){
            try {
                editionsRepo.save(new Editions(name));
                return "Saved";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @GetMapping(value = "/get")
    public Iterable<Editions> getEditions(
            @RequestParam(required = false,name = "id") Long Id,
            @RequestParam(required = false,name = "name") String Name,

            @RequestParam(required = false,name = "offset",defaultValue = "0") Integer Offset,
            @RequestParam(required = false,name = "limit",defaultValue = "25") Integer Limit
    ){
        return editionsRepo.FindEdition(Id,Name,Offset,Limit);
    }
}
