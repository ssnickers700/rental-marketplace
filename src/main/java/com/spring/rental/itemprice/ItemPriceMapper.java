package com.spring.rental.itemprice;

import com.spring.rental.client.Client;
import com.spring.rental.item.Item;
import com.spring.rental.item.dto.ItemNestedResponseDTO;
import com.spring.rental.itemprice.dto.ItemPriceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemPriceMapper {

    ItemPriceMapper MAPPER = Mappers.getMapper(ItemPriceMapper.class);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "category.id", target = "categoryId")
    ItemNestedResponseDTO toItemNestedResponseDTO(Item item);

    @Mapping(source = "item", target = "itemPayloadDTO")
    ItemPriceResponseDTO toItemPriceResponseDTO(ItemPrice itemPrice);
}

