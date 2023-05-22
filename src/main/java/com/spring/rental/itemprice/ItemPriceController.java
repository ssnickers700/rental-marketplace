package com.spring.rental.itemprice;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.rental.View;
import com.spring.rental.itemprice.dto.ItemPriceBaseDTO;
import com.spring.rental.itemprice.dto.ItemPricePayloadDTO;
import com.spring.rental.itemprice.dto.ItemPriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item-prices")
public class ItemPriceController {

    private final ItemPriceService itemPriceService;

    @Autowired
    public ItemPriceController(ItemPriceService itemPriceService) {
        this.itemPriceService = itemPriceService;
    }

    @GetMapping
    public ResponseEntity<List<ItemPriceResponseDTO>> getAllItemPrices() {
        List<ItemPriceResponseDTO> itemPriceResponseDTOS = itemPriceService.getAllItemPrices();
        return new ResponseEntity<>(itemPriceResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPriceResponseDTO> getItemPriceById(@PathVariable Long id) {
        ItemPriceResponseDTO itemPriceResponseDTO = itemPriceService.getItemPriceById(id);
        return new ResponseEntity<>(itemPriceResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemPriceResponseDTO> createItemPrice(@RequestBody ItemPricePayloadDTO itemPricePayloadDTO) {
        ItemPriceResponseDTO itemPriceResponseDTO = itemPriceService.createItemPrice(itemPricePayloadDTO);
        return new ResponseEntity<>(itemPriceResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPriceResponseDTO> updateItemPrice(@PathVariable Long id, @RequestBody ItemPriceBaseDTO itemPriceBaseDTO) {
        ItemPriceResponseDTO itemPrice = itemPriceService.updateItemPrice(id, itemPriceBaseDTO);
        return new ResponseEntity<>(itemPrice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPrice(@PathVariable Long id) {
        itemPriceService.deleteItemPrice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

