package com.spring.rental.item;

import com.spring.rental.category.Category;
import com.spring.rental.category.CategoryRepository;
import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.exception.NotFoundException;
import com.spring.rental.item.dto.ItemBaseDTO;
import com.spring.rental.item.dto.ItemPayloadDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ClientRepository clientRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ClientRepository clientRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.clientRepository = clientRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("item", id));
    }

    @Transactional
    public Item createItem(ItemPayloadDTO itemPayloadDTO) {
        Client client = clientRepository
                .findById(itemPayloadDTO.getClientId())
                .orElseThrow(() -> new NotFoundException("client", itemPayloadDTO.getClientId()));

        Category category = categoryRepository
                .findById(itemPayloadDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("category", itemPayloadDTO.getCategoryId()));

        Item item = new Item(
                client,
                category,
                itemPayloadDTO.getTitle(),
                itemPayloadDTO.getDescription(),
                itemPayloadDTO.getState(),
                itemPayloadDTO.getBasePrice()
        );
        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long id, Item updatedItem) {
        Item currentItem = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("item", id));
        updatedItem.setId(id);
        updatedItem.setClient(currentItem.getClient());
        return itemRepository.save(updatedItem);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new NotFoundException("item", id);
        }
        itemRepository.deleteById(id);
    }
}
