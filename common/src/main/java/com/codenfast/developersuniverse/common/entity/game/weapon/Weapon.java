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
@Table(name = "WEAPON"
)
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class Weapon implements EntityModel {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "WEAPON_ID", unique = true, nullable = false, length = 36)
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

    @Column(name= "NAME", length = 100, nullable = false)
    private String name;

    @Column(name= "IMAGE", length = 4000, nullable = false)
    private String image;

    @Column(name= "DESCRIPTION", length = 4000, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name= "GRID_X")
    private Integer gridX;

    @Column(name= "GRID_Y")
    private Integer gridY;

    @Column(name= "WEIGHT")
    private Float weight;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "weapon")
    private List<WeaponAmmo> weaponAmmoList;

    @Column(name= "CODE")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "weapon")
    private List<WeaponAttachmentWeapon> attachmentList;

}
