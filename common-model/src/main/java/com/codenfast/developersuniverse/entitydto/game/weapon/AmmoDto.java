package com.codenfast.developersuniverse.entitydto.game.weapon;

import java.time.LocalDateTime;
import java.util.List;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class AmmoDto {

    private String id;
    private Boolean passive = Boolean.FALSE;
    private LocalDateTime createTime = null;
    private LocalDateTime  updateTime = null;
    private String caliber;
    private String image;
    private List<WeaponAmmo> weaponAmmoList;
}
