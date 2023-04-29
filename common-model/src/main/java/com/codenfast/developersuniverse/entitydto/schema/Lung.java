package com.codenfast.developersuniverse.entitydto.schema;

import com.codenfast.developersuniverse.EntityModel;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/Lung


/**Lung and respiratory system clinical examination.*/
public class Lung implements EntityModel {
    private String id;
    private Boolean passive = Boolean.FALSE;
    private LocalDateTime createTime = null;
    private LocalDateTime updateTime = null;


    private String name;

}