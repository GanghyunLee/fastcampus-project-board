package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // 명시한 필드만 검색 기능을 노출시키도록 한다.
        bindings.excludeUnlistedProperties(true);

        // content는 적절하지 않아 보이지만 일단 공부목적으로 넣어두자.
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

        // 기본적으로 exactMatch로 동작하고 있는데, 각 필드에 대해서 룰을 바꿈
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${value}%'
        // bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${value}'. %를 수동으로 넣어야 한다.

        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 시분초까지 eq 조건에 맞아야 하므로 적절한 방법은 아님.
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
