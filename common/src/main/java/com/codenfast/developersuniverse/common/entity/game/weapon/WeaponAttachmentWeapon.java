package com.codenfast.developersuniverse.common.entity.game.weapon;

import com.codenfast.developersuniverse.EntityModel;
import com.codenfast.developersuniverse.common.converter.YesNoConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "WEAPONATTACHMENT_WEAPON", indexes = {
        @Index(columnList = "WEAPON_ID", name = "ix_weaponattachmentweapon_weapon"),
        @Index(columnList = "WEAPONATTACHMENT_ID", name = "ix_weaponattachmentweapon_attachment"),
}
)
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class WeaponAttachmentWeapon implements EntityModel {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "WEAPONATTACHMENTWEAPON_ID", unique = true, nullable = false, length = 36)
    private String id;
    @Column(name = "PASSIVE", length = 1, nullable = false)
    @Convert(converter = YesNoConverter.class)
    private Boolean passive = Boolean.FALSE;
    @CreationTimestamp
    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime = null;
    @UpdateTimestamp
    @Column(name = "UPDATE_TIME")
    private LocalDateTime  updateTime = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WEAPON_ID")
    private Weapon weapon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WEAPONATTACHMENT_ID")
    private WeaponAttachment weaponAttachment;
}
