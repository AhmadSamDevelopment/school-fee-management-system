package com.code.controller;

import com.code.student.controller.CatalogController;
import com.code.student.dto.CatalogDTO;
import com.code.student.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CatalogControllerTest {

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private CatalogController catalogController;

    private CatalogDTO catalogDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        catalogDTO = new CatalogDTO();
        catalogDTO.setName("Electronics Catalog");
        catalogDTO.setItems(new ArrayList<>());
    }

    @Test
    void create_ShouldReturnCreatedCatalog() {
        when(catalogService.create(any(CatalogDTO.class))).thenReturn(catalogDTO);

        ResponseEntity<CatalogDTO> response = catalogController.create(catalogDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogDTO, response.getBody());
        verify(catalogService, times(1)).create(any(CatalogDTO.class));
    }

    @Test
    void activate_ShouldReturnAcceptedStatus() {
        doNothing().when(catalogService).activate(anyLong());

        ResponseEntity<Void> response = catalogController.activate(1L);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(catalogService, times(1)).activate(anyLong());
    }

    @Test
    void deactivate_ShouldReturnAcceptedStatus() {
        doNothing().when(catalogService).deactivate(anyLong());

        ResponseEntity<Void> response = catalogController.deactivate(1L);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(catalogService, times(1)).deactivate(anyLong());
    }

    @Test
    void getById_ShouldReturnCatalog() {
        when(catalogService.findById(anyLong())).thenReturn(catalogDTO);

        ResponseEntity<CatalogDTO> response = catalogController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogDTO, response.getBody());
        verify(catalogService, times(1)).findById(anyLong());
    }

    @Test
    void list_ShouldReturnListOfCatalogs() {
        List<CatalogDTO> catalogList = new ArrayList<>();
        catalogList.add(catalogDTO);

        when(catalogService.list()).thenReturn(catalogList);

        ResponseEntity<List<CatalogDTO>> response = catalogController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogList, response.getBody());
        verify(catalogService, times(1)).list();
    }

    @Test
    void update_ShouldReturnUpdatedCatalog() {
        when(catalogService.update(anyLong(), any(CatalogDTO.class))).thenReturn(catalogDTO);

        ResponseEntity<CatalogDTO> response = catalogController.update(1L, catalogDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogDTO, response.getBody());
        verify(catalogService, times(1)).update(anyLong(), any(CatalogDTO.class));
    }

    @Test
    void getActiveCatalogs_ShouldReturnListOfActiveCatalogs() {
        List<CatalogDTO> activeCatalogList = new ArrayList<>();
        activeCatalogList.add(catalogDTO);

        when(catalogService.findActiveCatalog(anyLong())).thenReturn(activeCatalogList);

        ResponseEntity<List<CatalogDTO>> response = catalogController.getActiveCatalogs(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(activeCatalogList, response.getBody());
        verify(catalogService, times(1)).findActiveCatalog(anyLong());
    }
}
