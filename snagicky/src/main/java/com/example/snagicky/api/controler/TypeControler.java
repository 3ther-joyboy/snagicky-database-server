package com.example.snagicky.api.controler;

import com.example.snagicky.api.model.Types;
import com.example.snagicky.api.repo.ApiKeysRepo;
import com.example.snagicky.api.repo.TypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/type")
public class TypeControler {

    @Autowired
    private TypesRepo typesRepo;
    @Autowired
    private ApiKeysRepo keys;


    @GetMapping(value = "/id/{id}")
    public Optional<Types> findTypeId(@PathVariable(name = "id") long Id){
        return typesRepo.findById(Id);
    }


    @DeleteMapping(value = "/delete/{id}/{apiKey}")
    public String deleteType(@PathVariable(name = "id") long id, @PathVariable(name = "apiKey") String apiKey){
        if (keys.Check(apiKey) == 1){
            try {
                typesRepo.deleteById(id);
                return "Deleted";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @PostMapping(value = "/create/{name}/{type}/{apiKey}")
    public String createPassive(@PathVariable(name = "apiKey") String apiKey,@PathVariable(name = "name") String Name,@PathVariable(name = "type") int Typ ){
        if (keys.Check(apiKey) == 1){
            try {
                typesRepo.save(new Types(Name,Typ));
                return "Saved";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @GetMapping(value = "/get")
    public Iterable<Types> getType(
            @RequestParam(required = false,name = "id") Long Id,
            @RequestParam(required = false,name = "name") String Name,
            @RequestParam(required = false,name = "type") Integer Type,

            @RequestParam(required = false,name = "offset",defaultValue = "0") Integer Offset,
            @RequestParam(required = false,name = "limit",defaultValue = "25") Integer Limit
    ){
        return typesRepo.GetType(Id,Name,Type,Offset,Limit);
    }


}
