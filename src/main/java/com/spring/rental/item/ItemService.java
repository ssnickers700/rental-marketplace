package com.spring.rental.item;

import com.spring.rental.category.Category;
import com.spring.rental.category.CategoryRepository;
import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.exception.NotFoundException;
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
    public Item createItem(ItemDTO itemDTO) {
        Client client = clientRepository
                .findById(itemDTO.clientId())
                .orElseThrow(() -> new NotFoundException("client", itemDTO.clientId()));

        Category category = categoryRepository
                .findById(itemDTO.categoryId())
                .orElseThrow(() -> new NotFoundException("category", itemDTO.categoryId()));

        Item item = new Item(
                client,
                category,
                itemDTO.title(),
                itemDTO.description(),
                itemDTO.state(),
                itemDTO.basePrice()
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
