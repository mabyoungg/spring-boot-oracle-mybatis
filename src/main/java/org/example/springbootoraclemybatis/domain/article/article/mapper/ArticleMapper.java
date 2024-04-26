package org.example.springbootoraclemybatis.domain.article.article.mapper;

import org.apache.ibatis.annotations.*;
import org.example.springbootoraclemybatis.domain.article.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    @Select("""
            SELECT * FROM ARTICLE
            WHERE ID = #{id}
            """)
    public Optional<Article> findById(long id);

    default Article save(Article article) {
        if (article.getId() == null) {
            article.setCreateDate(LocalDateTime.now());
            article.setModifyDate(LocalDateTime.now());
            _insert(article);
        } else {
            article.setModifyDate(LocalDateTime.now());
            _update(article);
        }

        return article;
    }

    @Insert("""
            INSERT INTO ARTICLE (ID, CREATE_DATE, MODIFY_DATE, TITLE, CONTENT)
            VALUES (ARTICLE_SEQ.NEXTVAL, #{createDate}, #{modifyDate}, #{title}, #{content})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    long _insert(Article article);

    @Update("""
            UPDATE ARTICLE
            SET MODIFY_DATE = #{modifyDate}, TITLE = #{title}, CONTENT = #{content}
            WHERE ID = #{id}
            """)
    void _update(Article article);

    @Select("""
            SELECT *
            FROM (
            	SELECT A.*,
                ROW_NUMBER() OVER(ORDER BY A.ID DESC) RN
                FROM ARTICLE A
            ) A
            WHERE A.RN <= 1
            """)
    Optional<Article> findFirstByOrderByIdDesc();

    @Select("""
            SELECT *
            FROM (
            	SELECT A.*,
                ROW_NUMBER() OVER(ORDER BY A.ID DESC) RN
                FROM ARTICLE A
            ) A
            WHERE A.RN <= 3
            ORDER BY A.RN ASC
            """)
    List<Article> findTop3ByOrderByIdDesc();

    default Page<Article> search(List<String> kwTypes, String kw, Pageable pageable) {
        String title = kwTypes.contains("title") ? kw : null;
        String content = kwTypes.contains("content") ? kw : null;

        List<Article> articles = _searchRows(title, content, pageable.getOffset(), pageable.getOffset() + pageable.getPageSize());

        return PageableExecutionUtils.getPage(articles, pageable, () -> _searchCount(title, content));
    }

    @Select("""
            SELECT COUNT(*)
            FROM ARTICLE A
            <where>
                <choose>
                    <when test='title != null and content != null'>
                        OR LOWER(A.TITLE) LIKE '%' || LOWER(#{title}) || '%'
                        OR LOWER(A.CONTENT) LIKE '%' || LOWER(#{content}) || '%'
                    </when>
                    <when test='title != null'>
                        OR LOWER(A.TITLE) LIKE '%' || LOWER(#{title}) || '%'
                    </when>
                    <when test='content != null'>
                        OR LOWER(A.CONTENT) LIKE '%' || LOWER(#{content}) || '%'
                    </when>
                </choose>
            </where>
            """)
    long _searchCount(@Param("title") String title, @Param("content") String content);

    @Select("""
            <script>
            SELECT *
            FROM (
                SELECT A.*,
                DENSE_RANK() OVER(ORDER BY A.ID DESC) RN
                FROM ARTICLE A
                <where>
                    <choose>
                        <when test='title != null and content != null'>
                            OR LOWER(A.TITLE) LIKE '%' || LOWER(#{title}) || '%'
                            OR LOWER(A.CONTENT) LIKE '%' || LOWER(#{content}) || '%'
                        </when>
                        <when test='title != null'>
                            OR LOWER(A.TITLE) LIKE '%' || LOWER(#{title}) || '%'
                        </when>
                        <when test='content != null'>
                            OR LOWER(A.CONTENT) LIKE '%' || LOWER(#{content}) || '%'
                        </when>
                    </choose>
                </where>
            ) A
            WHERE <![CDATA[ A.RN <= #{offsetPlusLimit} ]]>
            AND <![CDATA[ A.RN > #{offset} ]]>
            ORDER BY A.RN
            </script>
            """)
    List<Article> _searchRows(@Param("title") String title, @Param("content") String content, @Param("offset") long offset, @Param("offsetPlusLimit") long offsetPlusLimit);
}
