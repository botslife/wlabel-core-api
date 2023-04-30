package org.tm.api.core.centre.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CentreVenue {
    @Id
    private Integer id;
    private Integer centreId;
    private Integer venueId;
}
