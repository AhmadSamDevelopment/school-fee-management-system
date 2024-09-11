package com.code.fee.mapper;

import com.code.fee.dto.InvoiceDTO;
import com.code.fee.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface InvoiceMapper {

    InvoiceDTO toDto(Invoice invoice);

}
