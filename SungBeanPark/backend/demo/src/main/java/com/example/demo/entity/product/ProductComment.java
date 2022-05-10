package com.example.demo.entity.product;

import com.example.demo.entity.like.Likes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;
    @NotNull
    @Column
    private String writer;
    @NotNull
    @Column
    private String comment;

    @Column(name="productComment_rating")
    private String rating;

    @Column
    private String fileName;

    @CreatedDate
    private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

    @UpdateTimestamp
    private LocalDateTime updDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_productId")
    private Product product;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "productComment", fetch = FetchType.EAGER)
    private Set<Likes> likes = new HashSet<>();

    public ProductComment(String comment, String writer, String fileName){
        this.comment = comment;
        this.writer= writer;
        this.fileName = fileName;
    }
}
