package com.codenfast.developersuniverse.offlinedbinfo.controller;

import com.codenfast.developersuniverse.common.converter.EntityMapper;
import com.codenfast.developersuniverse.common.converter.EntityToDto;
import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import com.codenfast.developersuniverse.entitydto.game.weapon.WeaponDto;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.service.WeaponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "game")
@RestController
@lombok.RequiredArgsConstructor
@Slf4j
public class AmmoController {

    private final WeaponService service;

    @PostMapping("save")
    public ResponseEntity<WeaponDto> save(@RequestBody WeaponDto dto) {
        try {
            EntityMapper entityMapper = EntityMapper.INSTANCE;
            Weapon entity = entityMapper.map(dto);
            entity = service.save(entity);
            WeaponDto result = new EntityToDto<WeaponDto>().convertToDto(entity, WeaponDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("update")
    public ResponseEntity<WeaponDto> update(@RequestBody WeaponDto dto) {
        try {
            EntityMapper entityMapper = EntityMapper.INSTANCE;
            Weapon entity = entityMapper.map(dto);
            entity = service.update(entity);
            WeaponDto result = new EntityToDto<WeaponDto>().convertToDto(entity, WeaponDto.class);
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
            Weapon entity = service.delete(id);
            return new ResponseEntity<>(entity.getPassive(), HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("grid")
    public ResponseEntity<List<WeaponDto>> grid(@RequestBody RequestGrid requestGrid) {
        try {
            EntityToDto<WeaponDto> entityToDto = new EntityToDto<>();
            List<Weapon> list = service.grid(requestGrid);
            List<WeaponDto> resultList = new java.util.ArrayList<>();
            for (Weapon entity : list) {
                resultList.add(entityToDto.convertToDto(entity, WeaponDto.class, requestGrid.getPropertyList()));
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
    public ResponseEntity<TableModel<WeaponDto>> gridTableModel(@RequestBody RequestGrid requestGrid) {
        try {
            EntityToDto<WeaponDto> entityToDto = new EntityToDto<>();
            TableModel<Weapon> tableModel = service.gridTableModel(requestGrid);
            TableModel<WeaponDto> result = new TableModel<>();
            result.setCount(tableModel.getCount());
            List<WeaponDto> resultList = new java.util.ArrayList<>();
            for (Weapon entity : tableModel.getData()) {
                resultList.add(entityToDto.convertToDto(entity, WeaponDto.class, requestGrid.getPropertyList()));
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
