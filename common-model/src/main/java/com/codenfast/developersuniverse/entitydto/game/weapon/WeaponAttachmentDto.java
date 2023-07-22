package com.codenfast.developersuniverse.entitydto.game.weapon;

import java.time.LocalDateTime;
import java.util.List;


@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class WeaponAttachmentDto {


    private String id;
    private Boolean passive = Boolean.FALSE;
    private LocalDateTime createTime = null;
    private LocalDateTime  updateTime = null;
    private String name;
    private String image;
    private String description;
    private WeaponAttachmentWeaponDto weaponAttachmentWeapon;
    private List<WeaponAttachmentDto> attachmentList;
    private WeaponAttachmentDto parentWeaponAttachment;
}
