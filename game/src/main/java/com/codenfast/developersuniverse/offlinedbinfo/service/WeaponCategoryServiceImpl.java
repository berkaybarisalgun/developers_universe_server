package com.codenfast.developersuniverse.offlinedbinfo.service;

import com.codenfast.developersuniverse.common.entity.game.weapon.Category;
import com.codenfast.developersuniverse.common.service.BaseServiceImpl;
import com.codenfast.developersuniverse.model.CodenfastException;
import com.codenfast.developersuniverse.model.FilterParam;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.model.TableModel;
import com.codenfast.developersuniverse.offlinedbinfo.repository.WeaponCategoryRepository;
import com.codenfast.developersuniverse.utils.StringConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeaponCategoryServiceImpl extends BaseServiceImpl<Category> implements WeaponCategoryService {

    private final WeaponCategoryRepository repository;

    @Override
    public Category save(Category entity) throws CodenfastException {
        Category entityRef = super.save(entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public Category update(Category entity) throws CodenfastException {
        Category entityRef = super.update(repository, entity);
        entityRef = repository.save(entityRef);
        repository.detach(entityRef);
        return entityRef;
    }

    @Override
    public Category delete(String id) throws CodenfastException {
        Optional<Category> entityOptional = repository.findById(id).filter(e -> !e.getPassive()).stream().findFirst();
        if(entityOptional.isEmpty()) {
            throw new CodenfastException(StringConstant.DATA_NOT_FOUND);
        }
        Category entity = entityOptional.get();
        entity.setPassive(Boolean.TRUE);
        return repository.detach(repository.save(entity));
    }

    @Override
    public List<Category> save(List<Category> entityList) throws CodenfastException {
        List<Category> entityRef = super.save(entityList);
        return repository.saveAll(entityRef);
    }

    @Override
    public List<Category> update(List<Category> entityList) throws CodenfastException {
        List<Category> entityRef = super.update(repository, entityList);
        return repository.saveAll(entityRef);
    }

    @Transactional
    @Override
    public List<Category> delete(List<Category> entityList) throws CodenfastException {
        try {
            List<String> idList = entityList.stream().map(Category::getId).collect(Collectors.toList());
            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put(StringConstant.PASSIVE, Boolean.TRUE);
            List<FilterParam> filterParamList = new ArrayList<>();
            filterParamList.add(new FilterParam(StringConstant.ID, StringConstant.IN, idList));
            repository.update(Category.class, updateParams, filterParamList);
            return entityList;
        } catch (Exception e) {
            throw new CodenfastException(e.getMessage(), e);
        }
    }

    @Override
    public List<Category> grid(RequestGrid requestGrid) throws CodenfastException {
        return super.grid(Category.class, repository, requestGrid);
    }

    @Override
    public TableModel<Category> gridTableModel(RequestGrid requestGrid) throws CodenfastException {
        return super.gridTableModel(Category.class, repository, requestGrid);
    }
}
