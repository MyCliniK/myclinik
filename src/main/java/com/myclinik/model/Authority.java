package com.myclinik.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "authorities")
public class Authority{

	@GeneratedValue(strategy=GenerationType.AUTO, generator="authorities_id_seq")
	private @Id Long id;

    @OneToOne
    @JoinColumn(name = "username")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	private String authority;

    public Authority() {}

    public Authority(User user, String authority) {
		this.user = user;
		this.authority = authority;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority {" +
		"user=" + user.getUsername() +
		", authority=" + authority +
		"}";
    }

}
