package com.codenfast.developersuniverse.entitydto.game.weapon;

import java.time.LocalDateTime;


@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class WeaponAttachmentWeaponDto {
    
    private String id;
    private Boolean passive = Boolean.FALSE;
    private LocalDateTime createTime = null;
    private LocalDateTime  updateTime = null;
    private WeaponDto weapon;
    private WeaponAttachmentDto weaponAttachment;
}
