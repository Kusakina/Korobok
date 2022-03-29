package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserRole extends DatabaseEntity{
    @Column
    private int userId;
    //private int roleId;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleEnum roleEnum;

    public UserRole (int userId, int roleId) {
        this.userId = userId;
       // this.roleId = roleId;
    }
}
