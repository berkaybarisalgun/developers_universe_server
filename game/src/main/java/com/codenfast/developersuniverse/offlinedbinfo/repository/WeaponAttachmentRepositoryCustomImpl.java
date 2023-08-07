package com.codenfast.developersuniverse.offlinedbinfo.repository;

import com.codenfast.developersuniverse.common.entity.game.weapon.WeaponAttachment;
import com.codenfast.developersuniverse.common.repository.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class WeaponAttachmentRepositoryCustomImpl extends BaseRepositoryImpl<WeaponAttachment> implements WeaponAttachmentRepositoryCustom {

    public WeaponAttachmentRepositoryCustomImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
