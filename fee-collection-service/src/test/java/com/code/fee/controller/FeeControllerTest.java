package com.code.fee.controller;

import com.code.fee.dto.FeeRequest;
import com.code.fee.dto.InvoiceDTO;
import com.code.fee.entities.Invoice;
import com.code.fee.service.FeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeeControllerTest {

    @Mock
    private FeeService feeService;

    @InjectMocks
    private FeeController feeController;

    @Test
    void testCreate() {
        // Prepare test data
        FeeRequest feeRequest = new FeeRequest();  // Customize as needed
        InvoiceDTO invoiceDTO = new InvoiceDTO();

        // Mock the service call
        when(feeService.create(feeRequest)).thenReturn(invoiceDTO);

        // Call the controller method
        ResponseEntity<InvoiceDTO> response = feeController.create(feeRequest);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invoiceDTO, response.getBody());
        verify(feeService, times(1)).create(feeRequest);
    }

    @Test
    void testUpdate() {
        // Prepare test data
        InvoiceDTO invoiceDTO = new InvoiceDTO();  // Customize as needed

        // Mock the service call
        when(feeService.update(invoiceDTO)).thenReturn(invoiceDTO);

        // Call the controller method
        ResponseEntity<InvoiceDTO> response = feeController.update(invoiceDTO);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invoiceDTO, response.getBody());
        verify(feeService, times(1)).update(invoiceDTO);
    }

    @Test
    void testGetAccountTransactions() {
        // Prepare test data
        List<InvoiceDTO> invoiceDTOList = List.of(new InvoiceDTO());

        // Mock the service call
        when(feeService.list()).thenReturn(invoiceDTOList);

        // Call the controller method
        ResponseEntity<List<InvoiceDTO>> response = feeController.getAccountTransactions();

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invoiceDTOList, response.getBody());
        verify(feeService, times(1)).list();
    }

    @Test
    void testGetById() {
        // Prepare test data
        Long id = 1L;
        Invoice invoice = new Invoice();

        // Mock the service call
        when(feeService.getById(id)).thenReturn(invoice);

        // Call the controller method
        ResponseEntity<Invoice> response = feeController.getById(id);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invoice, response.getBody());
        verify(feeService, times(1)).getById(id);
    }
}
