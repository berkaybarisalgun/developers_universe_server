package com.codenfast.developersuniverse.offlinedbinfo.service;

import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import com.codenfast.developersuniverse.common.service.BaseServiceImpl;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.FilterParam;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.repository.WeaponRepository;
import com.codenfast.developersuniverse.utils.StringConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeaponServiceImpl extends BaseServiceImpl<Weapon> implements WeaponService {

    private final WeaponRepository repository;

    @Override
    public Weapon save(Weapon entity) throws CodenfastException {
        Weapon entityRef = super.save(entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public Weapon update(Weapon entity) throws CodenfastException {
        Weapon entityRef = super.update(repository, entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public Weapon delete(String id) throws CodenfastException {
        Optional<Weapon> entityOptional = repository.findById(id).filter(e -> !e.getPassive()).stream().findFirst();
        if(entityOptional.isEmpty()) {
            throw new CodenfastException(StringConstant.DATA_NOT_FOUND);
        }
        Weapon entity = entityOptional.get();
        entity.setPassive(Boolean.TRUE);
        return repository.detach(repository.save(entity));
    }

    @Override
    public List<Weapon> save(List<Weapon> entityList) throws CodenfastException {
        List<Weapon> entityRef = super.save(entityList);
        return repository.saveAll(entityRef);
    }

    @Override
    public List<Weapon> update(List<Weapon> entityList) throws CodenfastException {
        List<Weapon> entityRef = super.update(repository, entityList);
        return repository.saveAll(entityRef);
    }

    @Transactional
    @Override
    public List<Weapon> delete(List<Weapon> entityList) throws CodenfastException {
        try {
            List<String> idList = entityList.stream().map(Weapon::getId).collect(Collectors.toList());
            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put(StringConstant.PASSIVE, Boolean.TRUE);
            List<FilterParam> filterParamList = new ArrayList<>();
            filterParamList.add(new FilterParam(StringConstant.ID, StringConstant.IN, idList));
            repository.update(Weapon.class, updateParams, filterParamList);
            return entityList;
        } catch (Exception e) {
            throw new CodenfastException(e.getMessage(), e);
        }
    }

    @Override
    public List<Weapon> grid(RequestGrid requestGrid) throws CodenfastException {
        return super.grid(Weapon.class, repository, requestGrid);
    }

    @Override
    public TableModel<Weapon> gridTableModel(RequestGrid requestGrid) throws CodenfastException {
        return super.gridTableModel(Weapon.class, repository, requestGrid);
    }
}
