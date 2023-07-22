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
@Table(name = "WEAPON_ATTACHMENT"
)
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
public class WeaponAttachment implements EntityModel {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "WEAPONATTACHMENT_ID", unique = true, nullable = false, length = 36)
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
    @JoinColumn(name = "WEAPONATTACHMENTWEAPON_ID")
    private WeaponAttachmentWeapon weaponAttachmentWeapon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentWeaponAttachment")
    private List<WeaponAttachment> attachmentList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_WEAPONATTACHMENT_ID")
    private WeaponAttachment parentWeaponAttachment;
}
