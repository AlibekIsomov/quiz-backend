package com.quiz.entity;




import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
public class Blog extends DistributedEntity {
    @NotNull
    @Size(max = 15, min = 1)
    private String name;
    @NotNull
    @Size(max = 100, min = 5)
    private String description;
    private String link;
    @ManyToOne
    private Fayl photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Fayl getPhoto() {
        return photo;
    }

    public void setPhoto(Fayl photo) {
        this.photo = photo;
    }
}
