package entity;

import javax.persistence.*;

@Entity
@Table(name = "Purchases")
public class Purchase {

    private Integer id;

    @Column(name = "`Что купить`")
    private String name;

    @Column(name = "`Количество`")
    private Integer number;

    @Column(name = "`Стоимость, кр`")
    private Double amount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Что купить {" +
                "id=" + id +
                ", Наименование = '" + name + '\'' +
                ", Количество = " + number +
                ", Стоимость, кр = " + amount +
                '}';
    }
}