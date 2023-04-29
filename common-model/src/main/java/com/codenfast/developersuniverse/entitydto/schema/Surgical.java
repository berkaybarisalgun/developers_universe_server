package com.codenfast.developersuniverse.entitydto.schema;

import com.codenfast.developersuniverse.EntityModel;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/Surgical


/**A specific branch of medical science that pertains to treating diseases, injuries and deformities by manual and instrumental means.*/
public class Surgical implements EntityModel {
                        private String id;
            private Boolean passive = Boolean.FALSE;
            private LocalDateTime createTime = null;
            private LocalDateTime updateTime = null;


        private String name;

}