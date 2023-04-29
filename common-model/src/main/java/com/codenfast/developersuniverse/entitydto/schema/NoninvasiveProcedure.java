package com.codenfast.developersuniverse.entitydto.schema;

import com.codenfast.developersuniverse.EntityModel;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/NoninvasiveProcedure


/**A type of medical procedure that involves noninvasive techniques.*/
public class NoninvasiveProcedure implements EntityModel {
private String id;
private Boolean passive = Boolean.FALSE;
private LocalDateTime createTime = null;
private LocalDateTime updateTime = null;




}