package kz.newmanne.articles.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ArticleDTO {

    @JsonProperty(required = true)
    @Size(max = 100)
    private String title;

    @JsonProperty(required = true)
    private String author;

    @JsonProperty(required = true)
    private String content;

    @JsonProperty(required = true)
    private LocalDate publishDate;
}
