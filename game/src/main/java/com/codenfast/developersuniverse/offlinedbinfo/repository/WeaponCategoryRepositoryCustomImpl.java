package com.codenfast.developersuniverse.offlinedbinfo.repository;


import com.codenfast.developersuniverse.common.entity.game.weapon.Category;
import com.codenfast.developersuniverse.common.repository.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class WeaponCategoryRepositoryCustomImpl extends BaseRepositoryImpl<Category> implements WeaponCategoryRepositoryCustom {

    public WeaponCategoryRepositoryCustomImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
