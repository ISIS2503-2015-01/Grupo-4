package models;

/**
 * Created by Clau on 10/02/2015.
 */



import javax.persistence.*;

@Entity
public class Doctor{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;


}
