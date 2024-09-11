package com.code.fee.service;

import com.code.fee.dto.CatalogDTO;
import com.code.fee.dto.FeeRequest;
import com.code.fee.dto.GradeDto;
import com.code.fee.dto.InvoiceDTO;
import com.code.fee.dto.StudentDTO;
import com.code.fee.entities.Invoice;
import com.code.fee.enums.ErrorCode;
import com.code.fee.exception.RecordNotFoundException;
import com.code.fee.integration.CatalogServiceOrchestrator;
import com.code.fee.integration.StudentServiceOrchestrator;
import com.code.fee.mapper.InvoiceMapper;
import com.code.fee.repository.InvoiceRepository;
import com.code.fee.service.impl.FeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FeeServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private StudentServiceOrchestrator studentServiceOrchestrator;

    @Mock
    private CatalogServiceOrchestrator catalogServiceOrchestrator;

    @InjectMocks
    private FeeServiceImpl feeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_Success() {
        // Arrange
        FeeRequest feeRequest = new FeeRequest();
        feeRequest.setStudentId(1L);
        feeRequest.setGradeId(1L);

        GradeDto gradeDTO = new GradeDto();
        gradeDTO.setName("First");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setGrade(gradeDTO);

        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setId(1L);
        catalogDTO.setActivationDate(LocalDateTime.now());
        catalogDTO.setExpiryDate(LocalDateTime.now());

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(studentServiceOrchestrator.getStudentById(any(Long.class))).thenReturn(studentDTO);
        when(catalogServiceOrchestrator.getActiveCatalogs(any(Long.class))).thenReturn(List.of(catalogDTO));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toDto(any(Invoice.class))).thenReturn(new InvoiceDTO());

        // Act
        InvoiceDTO result = feeServiceImpl.create(feeRequest);

        // Assert
        assertNotNull(result);
        verify(studentServiceOrchestrator, times(1)).getStudentById(1L);
        verify(catalogServiceOrchestrator, times(1)).getActiveCatalogs(1L);
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }

    @Test
    void testCreate_RecordNotFoundException_WhenNoCatalogs() {
        // Arrange
        FeeRequest feeRequest = new FeeRequest();
        feeRequest.setGradeId(1L);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");

        when(studentServiceOrchestrator.getStudentById(any(Long.class))).thenReturn(studentDTO);
        when(catalogServiceOrchestrator.getActiveCatalogs(any(Long.class))).thenReturn(Collections.emptyList());

        // Act & Assert
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> feeServiceImpl.create(feeRequest));
        assertEquals(ErrorCode.NOT_ANY_CATALOG_FOUND, exception.getErrorCode());
        verify(catalogServiceOrchestrator, times(1)).getActiveCatalogs(1L);
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setStudentName("John Doe");
        invoiceDTO.setGrade("Grade 1");
        invoiceDTO.setCatalogId(1L);

        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toDto(any(Invoice.class))).thenReturn(invoiceDTO);

        // Act
        InvoiceDTO result = feeServiceImpl.update(invoiceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(invoiceDTO.getStudentName(), result.getStudentName());
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }

    @Test
    void testList_Success() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setStudentName("John Doe");

        when(invoiceRepository.findAll()).thenReturn(List.of(invoice));
        when(invoiceMapper.toDto(any(Invoice.class))).thenReturn(invoiceDTO);

        // Act
        List<InvoiceDTO> result = feeServiceImpl.list();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(invoiceRepository.findById(any(Long.class))).thenReturn(Optional.of(invoice));

        // Act
        Invoice result = feeServiceImpl.getById(1L);

        // Assert
        assertNotNull(result);
        verify(invoiceRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_RecordNotFoundException() {
        // Arrange
        when(invoiceRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Act & Assert
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> feeServiceImpl.getById(1L));
        assertEquals(ErrorCode.NOT_ANY_CATALOG_FOUND, exception.getErrorCode());
        verify(invoiceRepository, times(1)).findById(1L);
    }
}
