package com.code.fee.controller;

import com.code.fee.dto.FeeRequest;
import com.code.fee.dto.InvoiceDTO;
import com.code.fee.entities.Invoice;
import com.code.fee.service.FeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/fees")
public class FeeController {

    private final FeeService feeService;

    @PostMapping
    public ResponseEntity<InvoiceDTO> create(@RequestBody @Valid FeeRequest feeRequest) {
        InvoiceDTO invoice = feeService.create(feeRequest);
        return ResponseEntity.ok(invoice);
    }

    @PutMapping
    public ResponseEntity<InvoiceDTO> update(@RequestBody @Valid InvoiceDTO invoiceDto) {
        InvoiceDTO updatedInvoice = feeService.update(invoiceDto);
        return ResponseEntity.ok(updatedInvoice);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAccountTransactions() {
        List<InvoiceDTO> invoices = feeService.list();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
        Invoice invoice = feeService.getById(id);
        return ResponseEntity.ok(invoice);
    }
}
