package com.melawai.ppuc.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import com.melawai.ppuc.model.BaseObject;
/**
 * This class is used to represent available roles in the database.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Version by
 *         Dan Kibler dan@getrolling.com Extended to implement Acegi
 *         GrantedAuthority interface by David Carter david@carter.net
 */
public class Role extends BaseObject implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 3690197650654049848L;

    public static final String ROLE_NAME_ADMIN_SUPER = "ROLE_SUPER";
    public static final String ROLE_NAME_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_NAME_USER = "ROLE_USER";
    
    public Long id;
    public String name;
    
    public Role(String name){
    	this.name=name;
    }
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}

   
    
   

}

