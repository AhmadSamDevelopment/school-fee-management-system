package com.code.service;

import com.code.student.dto.CatalogDTO;
import com.code.student.dto.ItemDTO;
import com.code.student.entities.Catalog;
import com.code.student.entities.Item;
import com.code.student.exception.RecordNotFoundException;
import com.code.student.mapper.CatalogMapper;
import com.code.student.mapper.ItemMapper;
import com.code.student.repository.CatalogRepository;
import com.code.student.repository.ItemRepository;
import com.code.student.service.impl.CatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CatalogServiceImplTest {

    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CatalogMapper catalogMapper;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private CatalogServiceImpl catalogService;

    private Catalog catalog;
    private CatalogDTO catalogDTO;
    private Item item;
    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        catalog = new Catalog();
        catalog.setId(1L);
        catalog.setName("Electronics Catalog");
        catalog.setCreatedDate(Instant.now());
        catalog.setActive(true);
        catalog.setItems(new ArrayList<>());

        catalogDTO = new CatalogDTO();
        catalogDTO.setName("Electronics Catalog");
        catalogDTO.setItems(new ArrayList<>());

        item = new Item();
        item.setId(1L);
        item.setName("Laptop");

        itemDTO = new ItemDTO();
        itemDTO.setName("Laptop");

        catalog.getItems().add(item);
        catalogDTO.getItems().add(itemDTO);
    }

    @Test
    void create_ShouldReturnCreatedCatalog() {
        when(catalogMapper.toEntity(any(CatalogDTO.class))).thenReturn(catalog);
        when(catalogRepository.save(any(Catalog.class))).thenReturn(catalog);
        when(itemMapper.toEntity(any(ItemDTO.class))).thenReturn(item);
        when(catalogMapper.toDto(any(Catalog.class))).thenReturn(catalogDTO);

        CatalogDTO createdCatalog = catalogService.create(catalogDTO);

        assertNotNull(createdCatalog);
        assertEquals(catalogDTO.getName(), createdCatalog.getName());
        verify(catalogRepository, times(1)).save(any(Catalog.class));
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void findById_ShouldReturnCatalogDTO() {
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.of(catalog));
        when(catalogMapper.toDto(any(Catalog.class))).thenReturn(catalogDTO);

        CatalogDTO foundCatalog = catalogService.findById(1L);

        assertNotNull(foundCatalog);
        assertEquals(catalogDTO.getName(), foundCatalog.getName());
        verify(catalogRepository, times(1)).findById(anyLong());
    }

    @Test
    void findById_ShouldThrowRecordNotFoundException() {
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> catalogService.findById(1L));
        verify(catalogRepository, times(1)).findById(anyLong());
    }

    @Test
    void list_ShouldReturnListOfCatalogs() {
        when(catalogRepository.findAll()).thenReturn(Arrays.asList(catalog));
        when(catalogMapper.toDto(any(Catalog.class))).thenReturn(catalogDTO);

        List<CatalogDTO> catalogs = catalogService.list();

        assertNotNull(catalogs);
        assertEquals(1, catalogs.size());
        verify(catalogRepository, times(1)).findAll();
    }

    @Test
    void update_ShouldReturnUpdatedCatalog() {
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.of(catalog));
        when(catalogRepository.save(any(Catalog.class))).thenReturn(catalog);
        when(catalogMapper.toDto(any(Catalog.class))).thenReturn(catalogDTO);
        when(itemMapper.toEntity(any(ItemDTO.class))).thenReturn(item);

        CatalogDTO updatedCatalog = catalogService.update(1L, catalogDTO);

        assertNotNull(updatedCatalog);
        assertEquals(catalogDTO.getName(), updatedCatalog.getName());
        verify(itemRepository, times(1)).deleteAll(anyList());
        verify(catalogRepository, times(1)).save(any(Catalog.class));
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void activate_ShouldActivateCatalog() {
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.of(catalog));

        catalogService.activate(1L);

        assertTrue(catalog.isActive());
        verify(catalogRepository, times(1)).save(catalog);
    }

    @Test
    void deactivate_ShouldDeactivateCatalog() {
        when(catalogRepository.findById(anyLong())).thenReturn(Optional.of(catalog));

        catalogService.deactivate(1L);

        assertFalse(catalog.isActive());
        verify(catalogRepository, times(1)).save(catalog);
    }

    @Test
    void findActiveCatalog_ShouldReturnListOfActiveCatalogs() {
        when(catalogRepository.findActiveCatalogs(any(LocalDateTime.class), anyLong())).thenReturn(Arrays.asList(catalog));
        when(catalogMapper.toDto(any(Catalog.class))).thenReturn(catalogDTO);

        List<CatalogDTO> activeCatalogs = catalogService.findActiveCatalog(1L);

        assertNotNull(activeCatalogs);
        assertEquals(1, activeCatalogs.size());
        verify(catalogRepository, times(1)).findActiveCatalogs(any(LocalDateTime.class), anyLong());
    }
}
