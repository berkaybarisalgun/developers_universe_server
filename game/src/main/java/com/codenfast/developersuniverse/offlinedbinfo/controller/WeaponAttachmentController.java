package com.codenfast.developersuniverse.offlinedbinfo.controller;

import com.codenfast.developersuniverse.common.converter.EntityMapper;
import com.codenfast.developersuniverse.common.converter.EntityToDto;
import com.codenfast.developersuniverse.common.entity.game.weapon.WeaponAttachment;
import com.codenfast.developersuniverse.entitydto.game.weapon.WeaponAttachmentDto;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.service.WeaponAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "weapon-attachment")
@RestController
@lombok.RequiredArgsConstructor
@Slf4j
public class WeaponAttachmentController {

    private final WeaponAttachmentService service;

    @PostMapping("save")
    public ResponseEntity<WeaponAttachmentDto > save(@RequestBody WeaponAttachmentDto  dto) {
        try {
            EntityMapper entityMapper = EntityMapper.INSTANCE;
            WeaponAttachment entity = entityMapper.map(dto);
            entity = service.save(entity);
            WeaponAttachmentDto  result = new EntityToDto<WeaponAttachmentDto >().convertToDto(entity, WeaponAttachmentDto .class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("update")
    public ResponseEntity<WeaponAttachmentDto > update(@RequestBody WeaponAttachmentDto  dto) {
        try {
            EntityMapper entityMapper = EntityMapper.INSTANCE;
            WeaponAttachment entity = entityMapper.map(dto);
            entity = service.update(entity);
            WeaponAttachmentDto  result = new EntityToDto<WeaponAttachmentDto >().convertToDto(entity, WeaponAttachmentDto .class);
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
            WeaponAttachment entity = service.delete(id);
            return new ResponseEntity<>(entity.getPassive(), HttpStatus.OK);
        } catch (CodenfastException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("grid")
    public ResponseEntity<List<WeaponAttachmentDto >> grid(@RequestBody RequestGrid requestGrid) {
        try {
            EntityToDto<WeaponAttachmentDto > entityToDto = new EntityToDto<>();
            List<WeaponAttachment> list = service.grid(requestGrid);
            List<WeaponAttachmentDto > resultList = new java.util.ArrayList<>();
            for (WeaponAttachment entity : list) {
                resultList.add(entityToDto.convertToDto(entity, WeaponAttachmentDto .class, requestGrid.getPropertyList()));
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
    public ResponseEntity<TableModel<WeaponAttachmentDto >> gridTableModel(@RequestBody RequestGrid requestGrid) {
        try {
            EntityToDto<WeaponAttachmentDto > entityToDto = new EntityToDto<>();
            TableModel<WeaponAttachment> tableModel = service.gridTableModel(requestGrid);
            TableModel<WeaponAttachmentDto > result = new TableModel<>();
            result.setCount(tableModel.getCount());
            List<WeaponAttachmentDto > resultList = new java.util.ArrayList<>();
            for (WeaponAttachment entity : tableModel.getData()) {
                resultList.add(entityToDto.convertToDto(entity, WeaponAttachmentDto .class, requestGrid.getPropertyList()));
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
