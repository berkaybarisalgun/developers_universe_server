
package com.codenfast.developersuniverse.offlinedbinfo.repository;


import com.codenfast.developersuniverse.common.entity.game.weapon.Ammo;
import com.codenfast.developersuniverse.common.entity.game.weapon.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmmoRepository extends JpaRepository<Ammo, String>, AmmoRepositoryCustom {
}
