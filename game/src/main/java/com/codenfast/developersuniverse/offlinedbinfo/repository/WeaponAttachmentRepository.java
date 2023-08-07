package com.codenfast.developersuniverse.offlinedbinfo.repository;

import com.codenfast.developersuniverse.common.entity.game.weapon.WeaponAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponAttachmentRepository extends JpaRepository<WeaponAttachment, String>, WeaponAttachmentRepositoryCustom {
}
