package com.code.fee.service;


import com.code.fee.dto.FeeRequest;
import com.code.fee.dto.InvoiceDTO;
import com.code.fee.entities.Invoice;

import java.util.List;

public interface FeeService {

    InvoiceDTO create(FeeRequest feeRequest);

    InvoiceDTO update(InvoiceDTO invoiceDTO);

    Invoice getById(Long invoiceId);

    List<InvoiceDTO> list();
}
