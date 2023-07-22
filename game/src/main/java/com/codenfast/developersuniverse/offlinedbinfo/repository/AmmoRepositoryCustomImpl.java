package com.codenfast.developersuniverse.offlinedbinfo.repository;


import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import com.codenfast.developersuniverse.common.repository.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class AmmoRepositoryCustomImpl extends BaseRepositoryImpl<Weapon> implements WeaponRepositoryCustom {

    public AmmoRepositoryCustomImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
