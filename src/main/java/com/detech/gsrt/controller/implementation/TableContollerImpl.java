package com.detech.gsrt.controller.implementation;

import com.detech.gsrt.controller.api.TableController;
import com.detech.gsrt.dto.TableDto;
import com.detech.gsrt.services.TableService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableContollerImpl implements TableController {

    private TableService tableService;

    @Autowired
    public void setTableService (TableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public TableDto ajouterTable (TableDto table) {
        return tableService.ajouterTable(table);
    }

    @Override
    public TableDto modifierTable (Long id, @NotNull @Valid TableDto table) {
        return tableService.modifierTable(id, table);
    }

    @Override
    public void supprimerTable (Long id) {
        tableService.supprimerTable(id);
    }

    @Override
    public TableDto trouverTable (Long id) {
        return tableService.trouverTable(id);
    }

    @Override
    public List<TableDto> listerTablesDisponibles () {
        return tableService.listerTablesDisponibles();
    }

    @Override
    public List<TableDto> listerTables () {
        return tableService.listerTables();
    }
}
