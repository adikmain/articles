package kz.newmanne.articles.service;


import kz.newmanne.articles.dto.ArticleCountByDayDTO;
import kz.newmanne.articles.dto.ArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    private final List<ArticleDTO> articleDTOList;
    @Value("${statistics.period}")
    private int statisticsPeriod;

    public ArticleService(List<ArticleDTO> articleDTOList) {
        this.articleDTOList = articleDTOList;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ArticleCountByDayDTO getCount() {
        return new ArticleCountByDayDTO(
                articleDTOList
                        .stream()
                        .sorted(Comparator.comparing(ArticleDTO::getPublishDate).reversed())
                        .filter(
                                dto -> dto
                                        .getPublishDate()
                                        .isAfter(LocalDate.now().minusDays(statisticsPeriod))
                        )
                        .collect(Collectors.groupingBy(ArticleDTO::getPublishDate, Collectors.counting()))
        );
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WRITER')")
    public void createArticle(ArticleDTO articleDTO) {
        articleDTOList.add(articleDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WRITER')")
    public List<ArticleDTO> getArticles(int pageSize, int page) {
        int totalItems = articleDTOList.size();
        int fromIndex = page * pageSize;
        int toIndex = fromIndex + pageSize;

        if (fromIndex <= totalItems) {
            if (toIndex > totalItems)
                toIndex = totalItems;
            return articleDTOList.subList(fromIndex, toIndex);
        } else {
            return Collections.emptyList();
        }
    }

    public List<ArticleDTO> getAllArticles() {
        return articleDTOList;
    }
}
