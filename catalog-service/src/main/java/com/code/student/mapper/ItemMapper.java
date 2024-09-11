package com.code.student.mapper;

import com.code.student.dto.ItemDTO;
import com.code.student.entities.Item;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class ItemMapper {

    public ItemDTO toDto(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public Item toEntity(ItemDTO dto) {
        Item cus = new Item();
        cus.setName(dto.getName());
        cus.setPrice(dto.getPrice());
        return cus;
    }
}
