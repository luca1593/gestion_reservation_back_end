package com.detech.gsrt.services.implementation;

import com.detech.gsrt.dto.TableDto;
import com.detech.gsrt.modeles.Latable;
import com.detech.gsrt.repository.TableRepository;
import com.detech.gsrt.services.TableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("tableservice")
@Slf4j
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private TableRepository tableRepository;

    @Override
    public TableDto ajouterTable (TableDto table) {

        if(table == null) {
            log.error("La table {} est null ou invalide", table);
            throw new RuntimeException("La table est null ou invalide");
        }

        return TableDto.fromEntity(tableRepository.save(TableDto.toEntity(table)));
    }

    @Override
    public TableDto modifierTable (Long id, TableDto table) {

        if (id == null) {
            log.error("L'id {} de la table n'exist pas", id);
            throw new RuntimeException("L'id de la table n'exist pas");
        }

        if(table == null) {
            log.error("La table {} est null ou invalide", table);
            throw new RuntimeException("La table est null ou invalide");
        }

        TableDto dto = trouverTable(id);
        if(dto != null) {
            table.setId(dto.getId());
        }else {
            log.error("La table {} n'existe pas", table);
            throw new RuntimeException("La table n'existe pas");
        }
        return TableDto.fromEntity(tableRepository.save(TableDto.toEntity(table)));
    }

    @Override
    public void supprimerTable (Long id) {
        if (id == null) {
            log.error("L'id {} de la table n'exist pas", id);
            throw new RuntimeException("L'id de la table n'exist pas");
        }
        tableRepository.deleteById(id);
    }

    @Override
    public TableDto trouverTable (Long id) {
        if (id == null) {
            log.error("L'id {} de la table n'exist pas", id);
            throw new RuntimeException("L'id de la table n'exist pas");
        }
        Optional<Latable> latable = tableRepository.findById(id);
        return TableDto.fromEntity(latable.orElse(null));
    }

    @Override
    public List<TableDto> listerTablesDisponibles () {
        return tableRepository.findAllByDisponibleTrue()
                .stream()
                .map(TableDto::fromEntity)
                .toList();
    }

    @Override
    public List<TableDto> listerTables () {
        return tableRepository.findAll()
                .stream()
                .map(TableDto::fromEntity)
                .toList();
    }
}
