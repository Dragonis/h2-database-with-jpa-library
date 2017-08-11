package pl.lublin.zeto.hermesJpaHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private ArticleRepository articleRepository;
    private Environment environment;

    public ArticleService(Environment environment, ArticleRepository articleRepository) {
        this.environment = environment;
        this.articleRepository = articleRepository;
    }

    /** How it works: http://www.baeldung.com/rest-api-search-language-spring-data-specifications
        Example request: http://localhost/?page=0&size=10&sort=title,ASC&search=title:tytul

        Attention:
        Specification<Article> spec.
        Specification has toPredicate method that
        allow to use in pageable queries
        but you must be aware that the results will be paged in
        application memory!
    */
     public List<Article> getFilteredData(Specification<Article> spec, Pageable  pageable){
        Page<Article> pageData = articleRepository.findAll(spec,pageable);
        List<Article> filteredData = pageData.getContent();

        logger.info("Page number: {} , " +
                "Page size: {}, " +
                "Page sort {}, " +
                "Spec {}"
                , pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort(),
                spec.toString());

        return filteredData;
    }


}
