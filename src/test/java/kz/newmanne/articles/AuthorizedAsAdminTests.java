package kz.newmanne.articles;

import kz.newmanne.articles.dto.ArticleDTO;
import kz.newmanne.articles.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WithMockUser(
        roles = {"ADMIN"}
)
class AuthorizedAsAdminTests {

    @Autowired
    private ArticleService service;
    @Value("${statistics.period}")
    private int statisticsPeriod;

    @BeforeEach
    void resetArticles() {
        service.getAllArticles().clear();
    }

    @Test
    public void testGetArticles_asAdmin() {
        System.out.println(service.getArticles(100, 0));

        List<ArticleDTO> articlesForLastXDays = ArticleHelper.createArticlesForLastXDays(statisticsPeriod * 2);
        articlesForLastXDays.forEach(service::createArticle);

        Map<LocalDate, Long> countByDay = service.getCount().getCountByDay();

        System.out.println(countByDay);

        assertEquals(statisticsPeriod, countByDay.size());
        countByDay.forEach((k, v) -> assertEquals(v, 2));
    }

    @Test
    public void testCreateArticle_asAdmin_andGetArticles() {
        service.createArticle((ArticleHelper.createMockArticle()));
        assertFalse(service.getArticles(50, 0).isEmpty());
    }


}
