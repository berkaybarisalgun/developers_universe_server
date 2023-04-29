package com.codenfast.developersuniverse.common.entity.schema;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/GameAvailabilityEnumeration


/**For a VideoGame, such as used with a PlayGameAction, an enumeration of the kind of game availability offered.*/
public class GameAvailabilityEnumeration {
                        private String id;
            private Boolean passive = Boolean.FALSE;
            private LocalDateTime createTime = null;
            private LocalDateTime updateTime = null;


        private String name;

}