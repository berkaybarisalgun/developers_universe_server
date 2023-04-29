package com.codenfast.developersuniverse.common.entity.schema;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/FrontWheelDriveConfiguration


/**Front-wheel drive is a transmission layout where the engine drives the front wheels.*/
public class FrontWheelDriveConfiguration {
                        private String id;
            private Boolean passive = Boolean.FALSE;
            private LocalDateTime createTime = null;
            private LocalDateTime updateTime = null;


        private String name;

}