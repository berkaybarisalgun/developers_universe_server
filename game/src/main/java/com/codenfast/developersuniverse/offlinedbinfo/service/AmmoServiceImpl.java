package com.codenfast.developersuniverse.offlinedbinfo.service;

import com.codenfast.developersuniverse.common.entity.game.weapon.Ammo;
import com.codenfast.developersuniverse.common.service.BaseServiceImpl;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.FilterParam;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.repository.AmmoRepository;
import com.codenfast.developersuniverse.utils.StringConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmmoServiceImpl extends BaseServiceImpl<Ammo> implements AmmoService {

    private final AmmoRepository repository;

    @Override
    public Ammo save(Ammo entity) throws CodenfastException {
        Ammo entityRef = super.save(entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public Ammo update(Ammo entity) throws CodenfastException {
        Ammo entityRef = super.update(repository, entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public Ammo delete(String id) throws CodenfastException {
        Optional<Ammo> entityOptional = repository.findById(id).filter(e -> !e.getPassive()).stream().findFirst();
        if(entityOptional.isEmpty()) {
            throw new CodenfastException(StringConstant.DATA_NOT_FOUND);
        }
        Ammo entity = entityOptional.get();
        entity.setPassive(Boolean.TRUE);
        return repository.detach(repository.save(entity));
    }

    @Override
    public List<Ammo> save(List<Ammo> entityList) throws CodenfastException {
        List<Ammo> entityRef = super.save(entityList);
        return repository.saveAll(entityRef);
    }

    @Override
    public List<Ammo> update(List<Ammo> entityList) throws CodenfastException {
        List<Ammo> entityRef = super.update(repository, entityList);
        return repository.saveAll(entityRef);
    }

    @Transactional
    @Override
    public List<Ammo> delete(List<Ammo> entityList) throws CodenfastException {
        try {
            List<String> idList = entityList.stream().map(Ammo::getId).collect(Collectors.toList());
            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put(StringConstant.PASSIVE, Boolean.TRUE);
            List<FilterParam> filterParamList = new ArrayList<>();
            filterParamList.add(new FilterParam(StringConstant.ID, StringConstant.IN, idList));
            repository.update(Ammo.class, updateParams, filterParamList);
            return entityList;
        } catch (Exception e) {
            throw new CodenfastException(e.getMessage(), e);
        }
    }

    @Override
    public List<Ammo> grid(RequestGrid requestGrid) throws CodenfastException {
        return super.grid(Ammo.class, repository, requestGrid);
    }

    @Override
    public TableModel<Ammo> gridTableModel(RequestGrid requestGrid) throws CodenfastException {
        return super.gridTableModel(Ammo.class, repository, requestGrid);
    }
}
