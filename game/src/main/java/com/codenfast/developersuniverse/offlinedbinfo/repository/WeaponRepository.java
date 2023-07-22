package com.codenfast.developersuniverse.offlinedbinfo.repository;


import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, String>, WeaponRepositoryCustom {
}
