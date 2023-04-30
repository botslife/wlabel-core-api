package org.tm.api.core.centre.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Centre {
    @Id
    private Integer id;
    private String name;
    private String description;
    private String type;
    private String phone;
    private String mobile;
    private Integer chairmanTeacherId;
    private String email;
    private String uri;
    private String status;
    private String thumbImage;
    private String fullImage;
    private Integer siteId;
}
