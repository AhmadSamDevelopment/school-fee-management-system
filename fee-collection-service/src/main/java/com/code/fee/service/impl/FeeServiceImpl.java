package com.code.fee.service.impl;

import com.code.fee.dto.CatalogDTO;
import com.code.fee.dto.FeeRequest;
import com.code.fee.dto.InvoiceDTO;
import com.code.fee.dto.StudentDTO;
import com.code.fee.entities.Invoice;
import com.code.fee.enums.ErrorCode;
import com.code.fee.exception.RecordNotFoundException;
import com.code.fee.integration.CatalogServiceOrchestrator;
import com.code.fee.integration.StudentServiceOrchestrator;
import com.code.fee.mapper.InvoiceMapper;
import com.code.fee.repository.InvoiceRepository;
import com.code.fee.service.FeeService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FeeServiceImpl implements FeeService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final StudentServiceOrchestrator studentServiceOrchestrator;
    private final CatalogServiceOrchestrator catalogServiceOrchestrator;

    @Override
    @Transactional
    public InvoiceDTO create(FeeRequest feeRequest) {
        StudentDTO student = studentServiceOrchestrator.getStudentById(feeRequest.getStudentId());

        List<CatalogDTO> catalogs = catalogServiceOrchestrator.getActiveCatalogs(feeRequest.getGradeId());
        if (catalogs.isEmpty()) throw new RecordNotFoundException(ErrorCode.NOT_ANY_CATALOG_FOUND);
//
        CatalogDTO catalog = catalogs.get(0);

        Invoice invoice = new Invoice();
        invoice.setStudentName(student.getFirstName() + " " + student.getLastName());
        invoice.setGrade(student.getGrade().getName());
        invoice.setCatalogId(catalog.getId());
        invoice.setStartMonth(feeRequest.getStartMonth());
        invoice.setEndMonth(feeRequest.getEndMonth());
        invoice.setCreatedDate(Instant.now());
        invoiceRepository.save(invoice);
        return invoiceMapper.toDto(invoice);
    }


    @Override
    @Transactional
    public InvoiceDTO update(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();


        invoice.setStudentName(invoiceDTO.getStudentName());
        invoice.setGrade(invoiceDTO.getGrade());
        invoice.setCatalogId(invoiceDTO.getCatalogId());
        invoice.setStartMonth(invoiceDTO.getStartMonth());
        invoice.setEndMonth(invoiceDTO.getEndMonth());
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(invoice);
    }


    @Override
    public List<InvoiceDTO> list() {
        return invoiceRepository.findAll().stream().map(invoiceMapper::toDto).toList();
    }

    public Invoice getById(Long id) {
        return invoiceRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ErrorCode.NOT_ANY_CATALOG_FOUND));
    }


}
