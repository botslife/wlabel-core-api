package org.tm.api.core.venue.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Venue {

    @Id
    private Integer id;
    private String name;
    private String address1;
    private String address2;
    private String postcode;
    private String briefAddress;
    private Integer countryId;
    private String addressNotes;
    private int totalPlaces;
    private Double lat;
    private Double lng;

}
