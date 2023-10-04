package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "extractions_order_detail")
public class MasterExtractionsOrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "totalQuantity")
    private Integer totalQuantity;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "orderQuantity")
    private Integer orderQuantity;
    @OneToOne()
    @JoinColumn(name = "itemId")
    @JsonIgnore
    private MasterExtractionsEntity masterDepartmentEntity;

    public static MasterExtractionsOrderDetailEntityBuilder builder() {
        return new MasterExtractionsOrderDetailEntityBuilder();
    }

    public static class MasterExtractionsOrderDetailEntityBuilder {
        private Integer id;
        private Integer totalQuantity;
        private Double totalPrice;
        private Integer orderQuantity;
        private MasterExtractionsEntity masterExtractionsEntity;

        MasterExtractionsOrderDetailEntityBuilder() {
        }

        public MasterExtractionsOrderDetailEntityBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public MasterExtractionsOrderDetailEntityBuilder totalQuantity(Integer totalQuantity) {
            this.totalQuantity = totalQuantity;
            return this;
        }

        public MasterExtractionsOrderDetailEntityBuilder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public MasterExtractionsOrderDetailEntityBuilder orderQuantity(Integer orderQuantity) {
            this.orderQuantity = orderQuantity;
            return this;
        }

        @JsonIgnore
        public MasterExtractionsOrderDetailEntityBuilder masterExtractionsEntity(MasterExtractionsEntity masterExtractionsEntity) {
            this.masterExtractionsEntity = masterExtractionsEntity;
            return this;
        }

        public MasterExtractionsOrderDetailEntity build() {
            return new MasterExtractionsOrderDetailEntity(this.id, this.totalQuantity, this.totalPrice, this.orderQuantity, this.masterExtractionsEntity);
        }

        public String toString() {
            return "MasterExtractionsOrderDetailEntity.MasterExtractionsOrderDetailEntityBuilder(id=" + this.id + ", totalQuantity=" + this.totalQuantity + ", totalPrice=" + this.totalPrice + ", orderQuantity=" + this.orderQuantity + ", masterExtractionsEntity=" + this.masterExtractionsEntity + ")";
        }
    }
}
