package com.spring.rental.item;

import com.spring.rental.item.dto.ItemResponseDTO;
import com.spring.rental.itemprice.ItemPriceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemPriceMapper MAPPER = Mappers.getMapper(ItemPriceMapper.class);

    ItemResponseDTO toItemResponseDTO(Item item);
}
