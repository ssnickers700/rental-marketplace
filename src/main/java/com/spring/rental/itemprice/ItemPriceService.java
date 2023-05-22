package com.spring.rental.itemprice;

import com.spring.rental.exception.NotFoundException;
import com.spring.rental.item.Item;
import com.spring.rental.item.ItemRepository;
import com.spring.rental.item.dto.ItemNestedResponseDTO;
import com.spring.rental.itemprice.dto.ItemPriceBaseDTO;
import com.spring.rental.itemprice.dto.ItemPricePayloadDTO;
import com.spring.rental.itemprice.dto.ItemPriceResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemPriceService {

    private final ItemPriceRepository itemPriceRepository;
    private final ItemRepository itemRepository;
    private final ItemPriceMapper itemPriceMapper;

    @Autowired
    public ItemPriceService(ItemPriceRepository itemPriceRepository, ItemRepository itemRepository,
                            ItemPriceMapper itemPriceMapper) {
        this.itemPriceRepository = itemPriceRepository;
        this.itemRepository = itemRepository;
        this.itemPriceMapper = itemPriceMapper;
    }

    public List<ItemPriceResponseDTO> getAllItemPrices() {
        return itemPriceRepository
                .findAll()
                .stream()
                .map(itemPriceMapper::toItemPriceResponseDTO)
                .collect(Collectors.toList());
    }

    public ItemPriceResponseDTO getItemPriceById(Long id) {
        return itemPriceMapper.toItemPriceResponseDTO(
                itemPriceRepository.findById(id).orElseThrow(() -> new NotFoundException("item price", id))
        );
    }

    @Transactional
    public ItemPriceResponseDTO createItemPrice(ItemPricePayloadDTO itemPricePayloadDTO) {
        Item item = itemRepository
                .findById(itemPricePayloadDTO.getItemId())
                .orElseThrow(() -> new NotFoundException("item", itemPricePayloadDTO.getItemId()));

        ItemPrice itemPrice = new ItemPrice(item, itemPricePayloadDTO.getMonth(), itemPricePayloadDTO.getPrice());
        itemPriceRepository.save(itemPrice);
        return itemPriceMapper.toItemPriceResponseDTO(itemPrice);
    }

    @Transactional
    public ItemPriceResponseDTO updateItemPrice(Long id, ItemPriceBaseDTO itemPriceBaseDTO) {
        ItemPrice itemPrice = itemPriceRepository.findById(id).orElseThrow(() -> new NotFoundException("item price", id));
        itemPrice.setPrice(itemPriceBaseDTO.getPrice());
        itemPrice.setMonth(itemPriceBaseDTO.getMonth());
        itemPriceRepository.save(itemPrice);
        return itemPriceMapper.toItemPriceResponseDTO(itemPrice);
    }

    public void deleteItemPrice(Long id) {
        if (!itemPriceRepository.existsById(id)) {
            throw new NotFoundException("item_price", id);
        }
        itemPriceRepository.deleteById(id);
    }
}

