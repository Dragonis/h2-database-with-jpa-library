package pl.lublin.zeto.hermesJpaHelper;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>,
                                           JpaSpecificationExecutor<Article> {
    @Query("SELECT a FROM Article a ")
    List<Article> findData(Specification<Article> test, Pageable pageable);

}