package com.codenfast.developersuniverse.offlinedbinfo.repository;


import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import com.codenfast.developersuniverse.common.entity.game.weapon.WeaponAttachment;
import com.codenfast.developersuniverse.common.repository.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class WeaponAttacmentRepositoryCustomImpl extends BaseRepositoryImpl<WeaponAttachment> implements WeaponAttachmentRepositoryCustom {

    public WeaponAttacmentRepositoryCustomImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
