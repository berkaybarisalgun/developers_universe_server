package com.codenfast.developersuniverse.common.entity.game.weapon;

import com.codenfast.developersuniverse.EntityModel;
import com.codenfast.developersuniverse.common.converter.YesNoConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WEAPON_AMMO", indexes = {
        @Index(columnList = "WEAPON_ID", name = "ix_weaponammo_weapon"),
        @Index(columnList = "AMMO_ID", name = "ix_weaponammo_ammo"),
}
)
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class WeaponAmmo implements EntityModel {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "WEAPONAMMO_ID", unique = true, nullable = false, length = 36)
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
    @JoinColumn(name = "AMMO_ID")
    private Ammo ammo;
}
