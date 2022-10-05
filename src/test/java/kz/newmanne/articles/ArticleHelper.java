package kz.newmanne.articles;

import kz.newmanne.articles.dto.ArticleDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleHelper {

    public static ArticleDTO createMockArticle() {
        return new ArticleDTO("test", "test", "test", LocalDate.now());
    }

    public static List<ArticleDTO> createArticlesForLastXDays(int days) {
        ArrayList<ArticleDTO> articles = new ArrayList<>();
        int daysCounter = 0;
        for (int i = 0; i < 30; i++) {
            if (daysCounter == days + 1)
                daysCounter = 0;

            articles.add(
                    new ArticleDTO(
                            String.valueOf(i),
                            String.valueOf(i),
                            String.valueOf(i),
                            LocalDate.now().minusDays(daysCounter)
                    )
            );
            daysCounter++;
        }
        return articles;
    }
}
