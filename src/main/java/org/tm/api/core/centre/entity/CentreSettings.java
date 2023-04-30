package org.tm.api.core.centre.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CentreSettings {
    @Id
    private Integer id;
    private Integer centreId;
}
