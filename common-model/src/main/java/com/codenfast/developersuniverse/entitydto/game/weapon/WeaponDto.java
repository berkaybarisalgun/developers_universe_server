package com.codenfast.developersuniverse.entitydto.game.weapon;

import java.time.LocalDateTime;
import java.util.List;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class WeaponDto {

    private String id;
    private Boolean passive = Boolean.FALSE;
    private LocalDateTime createTime = null;
    private LocalDateTime  updateTime = null;
    private String name;
    private String image;
    private String description;
    private CategoryDto category;
    private Integer gridX;
    private Integer gridY;
    private Float weight;
    private List<WeaponAmmo> weaponAmmoList;
    private String code;
    private List<WeaponAttachmentWeaponDto> attachmentList;

}
