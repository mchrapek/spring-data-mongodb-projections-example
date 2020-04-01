package pl.marek.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Builder
@Document
class Film {

    @Id
    String id;

    String name;

    String director;
}
