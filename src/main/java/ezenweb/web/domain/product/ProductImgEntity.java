package ezenweb.web.domain.product;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="productimg")
public class ProductImgEntity {
    // 이미지 식별이름
    @Id
    private String uuidFile;
    // 이미지 이름
    @Column
    private String originalFilename;
    // fk
    @ManyToOne
    @JoinColumn(name="id")
    @ToString.Exclude
    private ProductEntity productEntity;

}
