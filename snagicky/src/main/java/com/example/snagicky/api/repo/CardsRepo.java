package com.example.snagicky.api.repo;

import com.example.snagicky.api.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepo extends JpaRepository<Card, Long> {
    @Query(nativeQuery = true,value = "SELECT " +
                    "* " +
                    "FROM card_edition INNER JOIN cards on cards.id = card_edition.card_id " +
                    "WHERE " +
                    "(:Id is null or cards.id = :Id) and " +
                    "(:Edition is null or card_edition.edition_id = :Edition) and " +
                    "(:Name is null or INSTR(cards.name,:Name)) and " +
                    "(:Type is null or cards.type = :Type) and " +
                    "(:Rarity is null or cards.rarity = :Rarity) and " +
                    "(:Creator is null or INSTR(cards.creator,:Creator)) and " +
                    "(:Description is null or INSTR(cards.description,:Description)) and " +
                    "(:Story is null or INSTR(cards.story,:Story)) and " +
                    "(:Attack is null or cards.attack = :Attack) and " +
                    "(:Defense is null or cards.defense = :Defense) and " +
                    "(:AttDeff is null or (cards.attack + cards.defense) = :AttDeff) and " +

                    "(CASE :TotalToggle " +
                        "WHEN -1 THEN (:TotalCost is null or (cards.white + cards.green + cards.blue + cards.red + cards.multi) < :TotalCost) " +
                        "WHEN 1 THEN (:TotalCost is null or (cards.white + cards.green + cards.blue + cards.red + cards.multi) >= :TotalCost) " +
                        "ELSE (:TotalCost is null or (cards.white + cards.green + cards.blue + cards.red + cards.multi) = :TotalCost) " +
                    "END) and " +
                    "(CASE :MultiToggle " +
                        "WHEN -1 THEN (:MultiCost is null or cards.multi < :MultiCost) " +
                        "WHEN 1 THEN (:MultiCost is null or cards.multi >= :MultiCost) " +
                        "ELSE (:MultiCost is null or cards.multi = :MultiCost) " +
                    "END) and " +
                    "(CASE :WhiteToggle " +
                        "WHEN -1 THEN (:WhiteCost is null or cards.white < :WhiteCost) " +
                        "WHEN 1  THEN (:WhiteCost is null or cards.white >= :WhiteCost) " +
                        "ELSE (:WhiteCost is null or cards.white = :WhiteCost) " +
                    "END) and " +
                    "(CASE :GreenToggle " +
                        "WHEN -1 THEN (:GreenCost is null or cards.green < :GreenCost) " +
                        "WHEN 1 THEN (:GreenCost is null or cards.green >= :GreenCost) " +
                        "ELSE (:GreenCost is null or cards.green = :GreenCost) " +
                    "END) and " +
                    "(CASE :BlueToggle " +
                        "WHEN -1 THEN (:BlueCost is null or cards.blue < :BlueCost) " +
                        "WHEN 1 THEN (:BlueCost is null or cards.blue >= :BlueCost) " +
                        "ELSE (:BlueCost is null or cards.blue = :BlueCost) " +
                    "END) and " +
                    "(CASE :RedToggle " +
                        "WHEN -1 THEN (:RedCost is null or cards.red < :RedCost) " +
                        "WHEN 1 THEN (:RedCost is null or cards.red >= :RedCost) " +
                        "ELSE (:RedCost is null or cards.red = :RedCost) " +
                    "END) " +

                    "ORDER BY cards.name DESC LIMIT :Limit OFFSET :Offset"
            )
    Iterable<Card> FindCard(
            @Param("Id") Integer id,
            @Param("Edition") Integer edition,

            @Param("Name") String name,
            @Param("Type") Integer type,
            @Param("Rarity") String rarity,
            @Param("Creator") String creator,
            @Param("Description") String description,
            @Param("Story") String story,

            @Param("Attack") Integer attack,
            @Param("Defense") Integer defense,
            @Param("AttDeff") Integer attDef,

            @Param("MultiCost") Integer multi,
            @Param("TotalCost") Integer cost,
            @Param("WhiteCost") Integer white,
            @Param("GreenCost") Integer green,
            @Param("BlueCost") Integer blue,
            @Param("RedCost") Integer red,

            @Param("MultiToggle") Integer multi_toggle,
            @Param("TotalToggle") Integer cost_toggle,
            @Param("WhiteToggle") Integer white_toggle,
            @Param("GreenToggle") Integer green_toggle,
            @Param("BlueToggle") Integer blue_toggle,
            @Param("RedToggle") Integer red_toggle,

            @Param("Offset") Integer offset,
            @Param("Limit") Integer limit
            );

    @Query(nativeQuery = true,value = "SELECT * from cards ORDER BY cards.updated DESC LIMIT :Limit OFFSET :Offset")
    Iterable<Card> FindNewCards(@Param("Offset") Integer offset,@Param("Limit") Integer limit);

}
