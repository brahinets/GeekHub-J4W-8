package org.geekhub.lesson19.db.persistence;

import org.geekhub.lesson19.listener.UserListener;
import org.hibernate.annotations.Fetch;
import org.hibernate.hql.spi.id.persistent.PersistentTableBulkIdStrategy;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(UserListener.class)
public class User implements Persistable<Integer> {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private List<License> licenses;

    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    @Transient
    @Override
    public boolean isNew() {
        return null == getId();
    }
}
