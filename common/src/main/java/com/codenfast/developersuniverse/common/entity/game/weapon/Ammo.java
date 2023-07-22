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
@Table(name = "AMMUNATION"
)
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class Ammo implements EntityModel {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "AMMUNATION_ID", unique = true, nullable = false, length = 36)
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

    @Column(name= "CALIBER", length = 50, nullable = false)
    private String caliber;


    @Column(name= "IMAGE", length = 4000, nullable = false)
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ammo")
    private List<WeaponAmmo> weaponAmmoList;
}
