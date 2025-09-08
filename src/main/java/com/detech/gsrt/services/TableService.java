package com.detech.gsrt.services;

import com.detech.gsrt.dto.TableDto;

import java.util.List;

public interface TableService {
    TableDto ajouterTable(TableDto table);
    TableDto modifierTable(Long id, TableDto table);
    void supprimerTable(Long id);
    TableDto trouverTable(Long id);
    List<TableDto> listerTablesDisponibles();
    List<TableDto> listerTables();
}
