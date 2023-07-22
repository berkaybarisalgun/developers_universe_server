package com.codenfast.developersuniverse.offlinedbinfo.service;

import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import com.codenfast.developersuniverse.common.entity.game.weapon.WeaponAttachment;
import com.codenfast.developersuniverse.common.service.BaseServiceImpl;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.FilterParam;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.repository.WeaponAttachmentRepository;
import com.codenfast.developersuniverse.offlinedbinfo.repository.WeaponRepository;
import com.codenfast.developersuniverse.utils.StringConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeaponAttachmentServiceImpl extends BaseServiceImpl<WeaponAttachment> implements WeaponAttachmentService {

    private final WeaponAttachmentRepository repository;

    @Override
    public WeaponAttachment save(WeaponAttachment entity) throws CodenfastException {
        WeaponAttachment entityRef = super.save(entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public WeaponAttachment update(WeaponAttachment entity) throws CodenfastException {
        WeaponAttachment entityRef = super.update(repository, entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public WeaponAttachment delete(String id) throws CodenfastException {
        Optional<WeaponAttachment> entityOptional = repository.findById(id).filter(e -> !e.getPassive()).stream().findFirst();
        if(entityOptional.isEmpty()) {
            throw new CodenfastException(StringConstant.DATA_NOT_FOUND);
        }
        WeaponAttachment entity = entityOptional.get();
        entity.setPassive(Boolean.TRUE);
        return repository.detach(repository.save(entity));
    }

    @Override
    public List<WeaponAttachment> save(List<WeaponAttachment> entityList) throws CodenfastException {
        List<WeaponAttachment> entityRef = super.save(entityList);
        return repository.saveAll(entityRef);
    }

    @Override
    public List<WeaponAttachment> update(List<WeaponAttachment> entityList) throws CodenfastException {
        List<WeaponAttachment> entityRef = super.update(repository, entityList);
        return repository.saveAll(entityRef);
    }

    @Transactional
    @Override
    public List<WeaponAttachment> delete(List<WeaponAttachment> entityList) throws CodenfastException {
        try {
            List<String> idList = entityList.stream().map(WeaponAttachment::getId).collect(Collectors.toList());
            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put(StringConstant.PASSIVE, Boolean.TRUE);
            List<FilterParam> filterParamList = new ArrayList<>();
            filterParamList.add(new FilterParam(StringConstant.ID, StringConstant.IN, idList));
            repository.update(WeaponAttachment.class, updateParams, filterParamList);
            return entityList;
        } catch (Exception e) {
            throw new CodenfastException(e.getMessage(), e);
        }
    }

    @Override
    public List<WeaponAttachment> grid(RequestGrid requestGrid) throws CodenfastException {
        return super.grid(WeaponAttachment.class, repository, requestGrid);
    }

    @Override
    public TableModel<WeaponAttachment> gridTableModel(RequestGrid requestGrid) throws CodenfastException {
        return super.gridTableModel(WeaponAttachment.class, repository, requestGrid);
    }
}
