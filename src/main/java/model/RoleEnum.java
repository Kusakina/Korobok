package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@Data
@NoArgsConstructor

public class RoleEnum {

    @Column
    private int id;
    @Column
    private String name;

    protected RoleEnum (int id, String name) {
        this.name = name;
        this.id = id;
    }
    public String toString() {
        return name;
    }

}
