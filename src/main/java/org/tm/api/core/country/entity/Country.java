package org.tm.api.core.country.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    private Integer id;
    private String iso3;
    private String iso2;
    private String name;
    private Double lat;
    private Double lng;

}
