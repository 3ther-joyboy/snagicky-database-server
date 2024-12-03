package com.example.snagicky.api.controler;

import com.example.snagicky.api.model.*;
import com.example.snagicky.api.repo.ApiKeysRepo;
import com.example.snagicky.api.repo.CardsRepo;
import com.example.snagicky.api.repo.EditionsRepo;
import com.example.snagicky.api.repo.PassivesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController()
@RequestMapping("/card")
public class CardController {

    @Autowired
    private PassivesRepo passivesRepo;
    @Autowired
    private EditionsRepo editionsRepo;
    @Autowired
    private CardsRepo cardsRepo;
    @Autowired
    private ApiKeysRepo keys;

    @GetMapping(value = "/id/{id}")
    public Optional<Card> findCardId(@PathVariable(name = "id") long Id){
        return cardsRepo.findById(Id);
    }
    @DeleteMapping(value = "/delete/{id}/{apiKey}")
    public String deleteCard(@PathVariable(name = "id") long id, @PathVariable(name = "apiKey") String apiKey){
        if (keys.Check(apiKey) == 1){
            try {
                cardsRepo.deleteById(id);
                return "Deleted";
            }
            catch (Exception e){
                return "Failed";
            }
        }else{return "Perrmission dinaed";}
    }
    @PostMapping(value = "/create/{apiKey}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createCard(@RequestBody Card card,@PathVariable(name = "apiKey") String apiKey, @RequestParam(name = "editionId") long edition){
        if (keys.Check(apiKey) == 1){
            try {
                Editions put = editionsRepo.FindById(edition);
                card.Editions.add(put);

                Card newCard = cardsRepo.save(card);
                return "" + newCard.Id;
            }
            catch (Exception e){
                return "Something went wrong " + e.getMessage();
            }
        }else{return "Perrmission dinaed";}
    }

    @PutMapping("/passives/{cardId}/{apiKey}")
    public void joinCardToPassive(@RequestBody Long[] passiveId,@PathVariable(name = "cardId") long id, @PathVariable(name = "apiKey") String apiKey){
        if (keys.Check(apiKey) == 1) {
            Card card = cardsRepo.findById(id).get();
            for (int i = 0; i < passiveId.length; i++) {
                card.Passives.add(passivesRepo.findById(passiveId[i]).get());
            }
            cardsRepo.save(card);
        }
    }
    @PutMapping("/edition/{cardId}/{editionId}/{apiKey}")
    public void joinCardToEdition(@PathVariable(name = "apiKey") String apiKey, @PathVariable long cardId, @PathVariable long editionId){
        if (keys.Check(apiKey) == 1) {
            Card card = cardsRepo.findById(cardId).get();
            card.Editions.add(editionsRepo.FindById(editionId));

            cardsRepo.save(card);
        }
    }


    @GetMapping(value = "/get/new")
    public Iterable<Card> findNewCard(
            @RequestParam(required = false,name = "offset",defaultValue = "0") Integer Offset,
            @RequestParam(required = false,name = "limit",defaultValue = "25") Integer Limit
    ){
       Iterable<Card> Response = cardsRepo.FindNewCards(Offset,Limit);
       return Response;
    }
    @GetMapping(value = "/get")
    public Iterable<Card> findCard(
            @RequestParam(required = false,name = "id") Integer Id,
            @RequestParam(required = false,name = "edition") Integer Edition,

            @RequestParam(required = false,name = "name") String Name,
            @RequestParam(required = false,name = "type") Integer Type,
            @RequestParam(required = false,name = "rarity") String Rarity,
            @RequestParam(required = false,name = "creator") String Creator,
            @RequestParam(required = false,name = "description") String Description,
            @RequestParam(required = false,name = "story") String Stroy,
            @RequestParam(required = false,name = "attack") Integer Attack,
            @RequestParam(required = false,name = "defense") Integer Defense,
            @RequestParam(required = false,name = "att-def") Integer AttDef,

            @RequestParam(required = false,name = "white") Integer White,
            @RequestParam(required = false,name = "green") Integer Green,
            @RequestParam(required = false,name = "blue") Integer Blue,
            @RequestParam(required = false,name = "red") Integer Red,
            @RequestParam(required = false,name = "multi") Integer Multi,
            @RequestParam(required = false,name = "total") Integer Total,

            @RequestParam(required = false,name = "whiteToggle") Integer WhiteT,
            @RequestParam(required = false,name = "greenToggle") Integer GreenT,
            @RequestParam(required = false,name = "blueToggle") Integer BlueT,
            @RequestParam(required = false,name = "redToggle") Integer RedT,
            @RequestParam(required = false,name = "multiToggle") Integer MultiT,
            @RequestParam(required = false,name = "totalToggle") Integer TotalT,

            @RequestParam(required = false,name = "offset",defaultValue = "0") Integer Offset,
            @RequestParam(required = false,name = "limit",defaultValue = "25") Integer Limit
    ){
        Iterable<Card> Response = cardsRepo.FindCard(Id,Edition,Name,Type,Rarity,Creator,Description,Stroy,Attack,Defense,AttDef, Multi, Total, White, Green, Blue, Red, MultiT, TotalT, WhiteT, GreenT, BlueT, RedT, Offset, Limit);
        return Response;
    }

}