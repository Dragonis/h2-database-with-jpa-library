package pl.lublin.zeto.hermesJpaHelper;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lublin.zeto.jpaspecificationhelper.JpaSpecificationFactory;

import java.util.List;

@RestController
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String pleaseToVisitExampleURLUsingPageableAndSpecificationPagination(){

        String titleSite = "Spring Pagination and Search website for tests";
        String descriptionSite = "Example link using SpringFramework.Pageable interface and SpringFramework.Specification to pagination data";

        String requestUrl = "http://localhost/test?page=0&size=10&sort=title,ASC&search=title:title3";
        String linkDescription = "get entity with key title and value title";

        String requestUrl2 = "http://localhost/test?page=0&size=10&sort=title,ASC&search=text:exampleText";
        String linkDescription2 =  "get data with the same text";

        String url1 = "<a href='" + requestUrl + " '>" + linkDescription + "</a>";
        String url2 = "<a href='" + requestUrl2 + " '>" + linkDescription2 + "</a>";

        return String.format("<b>%s</b> <br> " +
                             "%s <br>" +
                             "%s <br>" +
                             "%s <br>"
                ,titleSite, descriptionSite, url1, url2);


    }

    @GetMapping("/test")
    public List<Article> index(Pageable pageable, String search) {
        Specification<Article> spec = new JpaSpecificationFactory<Article>().get(search);
        return articleService.getFilteredData(spec,pageable);
    }

}
