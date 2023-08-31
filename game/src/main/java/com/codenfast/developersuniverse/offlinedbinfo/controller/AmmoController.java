package com.codenfast.developersuniverse.offlinedbinfo.controller;

import com.codenfast.developersuniverse.common.converter.EntityMapper;
import com.codenfast.developersuniverse.common.converter.EntityToDto;
import com.codenfast.developersuniverse.common.entity.game.weapon.Ammo;
import com.codenfast.developersuniverse.entitydto.game.weapon.AmmoDto;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.service.AmmoService;
import com.codenfast.developersuniverse.offlinedbinfo.service.AmmoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "ammo")
@RestController
@lombok.RequiredArgsConstructor
@Slf4j
public class AmmoController {

    private final AmmoService service;

    @PostMapping("save")
    public ResponseEntity<AmmoDto> save(@RequestBody AmmoDto dto) {
        try {
            EntityMapper entityMapper = EntityMapper.INSTANCE;
            Ammo entity = entityMapper.map(dto);
            entity = service.save(entity);
            AmmoDto result = new EntityToDto<AmmoDto>().convertToDto(entity, AmmoDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("update")
    public ResponseEntity<AmmoDto> update(@RequestBody AmmoDto dto) {
        try {
            EntityMapper entityMapper = EntityMapper.INSTANCE;
            Ammo entity = entityMapper.map(dto);
            entity = service.update(entity);
            AmmoDto result = new EntityToDto<AmmoDto>().convertToDto(entity, AmmoDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        try {
            Ammo entity = service.delete(id);
            return new ResponseEntity<>(entity.getPassive(), HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("grid")
    public ResponseEntity<List<AmmoDto>> grid(@RequestBody RequestGrid requestGrid) {
        try {
            EntityToDto<AmmoDto> entityToDto = new EntityToDto<>();
            List<Ammo> list = service.grid(requestGrid);
            List<AmmoDto> resultList = new java.util.ArrayList<>();
            for (Ammo entity : list) {
                resultList.add(entityToDto.convertToDto(entity, AmmoDto.class, requestGrid.getPropertyList()));
            }
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("/grid-table-model")
    public ResponseEntity<TableModel<AmmoDto>> gridTableModel(@RequestBody RequestGrid requestGrid) {
        try {
            EntityToDto<AmmoDto> entityToDto = new EntityToDto<>();
            TableModel<Ammo> tableModel = service.gridTableModel(requestGrid);
            TableModel<AmmoDto> result = new TableModel<>();
            result.setCount(tableModel.getCount());
            List<AmmoDto> resultList = new java.util.ArrayList<>();
            for (Ammo entity : tableModel.getData()) {
                resultList.add(entityToDto.convertToDto(entity, AmmoDto.class, requestGrid.getPropertyList()));
            }
            result.setData(resultList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
