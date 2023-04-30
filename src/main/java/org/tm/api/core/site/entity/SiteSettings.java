package org.tm.api.core.site.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteSettings {

    @Id
    private Integer id;
    private Integer siteId;

}
