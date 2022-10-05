package kz.newmanne.articles;


import kz.newmanne.articles.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@WithAnonymousUser
public class UnauthorizedUserTests {

    @Autowired
    private ArticleService service;

    @Test
    public void testCreateArticle_unAuthorized() {
        assertThrows(
                AccessDeniedException.class,
                () -> service.createArticle(ArticleHelper.createMockArticle())
        );
    }

    @Test
    public void testGetArticles_unAuthorized() {
        assertThrows(
                AccessDeniedException.class,
                () -> service.getArticles(50, 0)
        );
    }

    @Test
    public void testGetCount_unAuthorized() {
        assertThrows(
                AccessDeniedException.class,
                service::getCount
        );
    }
}
