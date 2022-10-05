package kz.newmanne.articles.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
public class ArticleCountByDayDTO {
    private Map<LocalDate, Long> countByDay;
}
