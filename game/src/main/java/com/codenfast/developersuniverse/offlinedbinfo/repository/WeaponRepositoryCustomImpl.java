package com.codenfast.developersuniverse.offlinedbinfo.repository;


import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import com.codenfast.developersuniverse.common.repository.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class WeaponRepositoryCustomImpl extends BaseRepositoryImpl<Weapon> implements WeaponRepositoryCustom {

    public WeaponRepositoryCustomImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
