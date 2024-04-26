package org.example.springbootoraclemybatis.domain.article.article.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.springbootoraclemybatis.global.jpa.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@SequenceGenerator(name = "article_seq", sequenceName = "ARTICLE_SEQ", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_seq")
    private Long id;
    private String title;
    private String content;
}
