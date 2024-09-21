package iuh.backend.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Objects;

@Entity
@Table(name = "product_price") // Tên bảng trong database: product_price
public class Productprice {

    @EmbeddedId
    private ProductpriceId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price", nullable = false)
    private Double price;

    @Lob
    @Column(name = "note")
    private String note;

    // Constructor mặc định
    public Productprice() {
    }

    // Constructor có tham số
    public Productprice(ProductpriceId id, Product product, Double price, String note) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.note = note;
    }

    // Getter và Setter
    public ProductpriceId getId() {
        return id;
    }

    public void setId(ProductpriceId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Override toString() để dễ dàng in thông tin đối tượng
    @Override
    public String toString() {
        return "Productprice{" +
                "id=" + id +
                ", product=" + product +
                ", price=" + price +
                ", note='" + note + '\'' +
                '}';
    }

    // Override equals() để so sánh đối tượng
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productprice that = (Productprice) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(product, that.product) &&
                Objects.equals(price, that.price) &&
                Objects.equals(note, that.note);
    }

    // Override hashCode() để tính toán mã băm cho đối tượng
    @Override
    public int hashCode() {
        return Objects.hash(id, product, price, note);
    }
}
