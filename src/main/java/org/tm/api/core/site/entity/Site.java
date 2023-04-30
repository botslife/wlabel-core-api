package org.tm.api.core.site.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Site {
    @Id
    private Integer id;
    private String name;
    private Integer countryId;
    private String status;
    private String domain;

}
