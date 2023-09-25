package hu.bme.aut.retelab2.domain;

import org.hibernate.validator.internal.util.logging.formatter.ArrayOfClassesObjectFormatter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Ad {


    @GeneratedValue
    @Id
    private Long id;

    private String title;

    private String description;

    private int price;


    private LocalDateTime date;

    private String code;

    @ElementCollection
    private ArrayList<String> tags = new ArrayList<>();

    public Ad(){}
    //A listázáshoz használt konstruktor
    //
        public Ad(Long id, String title, String description, int price, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public Ad(Long id, String title, String description, int price, LocalDateTime date, String code) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.date = date;
        this.code = code;
    }



    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public void setCode(String code) {
        this.code=code;
    }

    public String  getCode() {
        return this.code;
    }
}
