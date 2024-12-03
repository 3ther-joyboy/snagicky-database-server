package com.example.snagicky.api.controler;

import com.example.snagicky.api.model.Passives;
import com.example.snagicky.api.repo.ApiKeysRepo;
import com.example.snagicky.api.repo.PassivesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/passive")
public class PassivesControler {


    @Autowired
    private PassivesRepo passivesRepo;
    @Autowired
    private ApiKeysRepo keys;


    @GetMapping(value = "/id/{id}")
    public Optional<Passives> findPassiveId(@PathVariable(name = "id") long Id){
        return passivesRepo.findById(Id);
    }


    @DeleteMapping(value = "/delete/{id}/{apiKey}")
    public String deletePassive(@PathVariable(name = "id") long id, @PathVariable(name = "apiKey") String apiKey){
        if (keys.Check(apiKey) == 1){
            try {
                passivesRepo.deleteById(id);
                return "Deleted";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @PostMapping(value = "/create/{name}/{des}/{apiKey}")
    public String createPassive(@PathVariable(name = "name") String name,@PathVariable(name = "des", required = false) String des, @PathVariable(name = "apiKey") String apiKey ){
        if (keys.Check(apiKey) == 1){
            try {
                passivesRepo.save(new Passives(name, des));
                return "Saved";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @GetMapping(value = "/get")
    public Iterable<Passives> getPassive(@PathVariable(name = "id",required = false) Long Id,@PathVariable(name = "str",required = false)String Str){
        return passivesRepo.GetPassive(Id,Str);
    }

}
