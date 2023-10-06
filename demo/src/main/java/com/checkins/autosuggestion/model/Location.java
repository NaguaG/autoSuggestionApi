package com.checkins.autosuggestion.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
@Data
@Document
public class Location {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String fullName;
    @NonNull
    private String type;
    @NonNull
    private String state;
    @NonNull
    private String country;
    @NonNull
    private String hierarchyPath;
    @NonNull
    private double longitude;
    @NonNull
    private double latitude;

}