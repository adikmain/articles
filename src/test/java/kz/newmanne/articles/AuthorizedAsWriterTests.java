package kz.newmanne.articles;


import kz.newmanne.articles.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WithMockUser(
        roles = {"WRITER"}
)
public class AuthorizedAsWriterTests {
    @Autowired
    private ArticleService service;

    @BeforeEach
    void resetArticles() {
        service.getAllArticles().clear();
    }

    @Test
    public void testCreateArticle_asWriter_andGetArticles() {
        service.createArticle((ArticleHelper.createMockArticle()));
        assertFalse(service.getArticles(50, 0).isEmpty());
    }

    @Test
    public void testGetCount_unAuthorized() {
        assertThrows(
                AccessDeniedException.class,
                service::getCount
        );
    }
}
