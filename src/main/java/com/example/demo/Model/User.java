package com.example.demo.Model;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Constant.UserConstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= UserConstant.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userId;

    @PrePersist
    public void prePersist() {
        if (this.userId == null) {
            this.userId = UUID.randomUUID().toString();
        }
    }
	@NotEmpty(message = UserConstant.ERROR_EMPTY)
	private String name;
	

	@NotEmpty(message = UserConstant.ERROR_EMPTY)
	private String email;
    
    @NotEmpty(message = UserConstant.ERROR_EMPTY)
    private String password;
    
  
    
    @NotEmpty(message = UserConstant.ERROR_EMPTY)
	private String mobileNo;
	

	
	private String roles;
	

	@OneToOne
	@JoinColumn(referencedColumnName = "cart_id", name="user_cart_id")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY) // or @JsonIgnore
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"productAndQuantity", "hibernateLazyInitializer"})
	private Cart cart;
	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.email;
	}
	
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Address> addresses = new ArrayList<>();

	    // getters and setters...
	    
	    public List<Address> getAddresses() {
	        return addresses;
	    }

	    public void setAddresses(List<Address> addresses) {
	        this.addresses = addresses;
	    }

	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Arrays.stream(this.getRoles().split(",")).map((role)->{
			return new SimpleGrantedAuthority(role);
		}).collect(Collectors.toList());
		
	}



	
	


}
