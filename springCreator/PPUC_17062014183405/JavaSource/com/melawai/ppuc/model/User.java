package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.melawai.ppuc.model.Role;

import com.melawai.ppuc.model.BaseObject;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:49 ICT 2014
 * @Description: Model for table user
 * @Revision	:
 */

public class User extends BaseObject implements Serializable , UserDetails {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=50)
	public String user_id;

	@Size(max=50)
	public String username;

	@Size(max=100)
	public String password;

	@Size(max=100)
	public String fullname;

	@Size(max=25)
	public String no_hp;

	@Size(max=100)
	public String email;

	@Size(max=5)
	public String kd_fungsi;

	@NotNull
	@Size(max=5)
	public String group_kd;

	public Long f_aktif;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=8)
	public String jam_create;

	@Size(max=50)
	public String user_update;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_update;

	@Size(max=8)
	public String jam_update;

	@Size(max=50)
	public String user_nonaktif;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_nonaktif;

	@Size(max=8)
	public String jam_nonaktif;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public String oldPassword;
	public String newPassword;
	public String confirmPassword;

	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public User(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getUser_id(){ return user_id; }
	public void setUser_id(String user_id){ this.user_id = user_id; }

	public String getUsername(){ return username; }
	public void setUsername(String username){ this.username = username; }

	public String getPassword(){ return password; }
	public void setPassword(String password){ this.password = password; }

	public String getFullname(){ return fullname; }
	public void setFullname(String fullname){ this.fullname = fullname; }

	public String getNo_hp(){ return no_hp; }
	public void setNo_hp(String no_hp){ this.no_hp = no_hp; }

	public String getEmail(){ return email; }
	public void setEmail(String email){ this.email = email; }

	public String getKd_fungsi(){ return kd_fungsi; }
	public void setKd_fungsi(String kd_fungsi){ this.kd_fungsi = kd_fungsi; }

	public String getGroup_kd(){ return group_kd; }
	public void setGroup_kd(String group_kd){ this.group_kd = group_kd; }

	public Long getF_aktif(){ return f_aktif; }
	public void setF_aktif(Long f_aktif){ this.f_aktif = f_aktif; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }

	public String getUser_update(){ return user_update; }
	public void setUser_update(String user_update){ this.user_update = user_update; }

	public Date getTgl_update(){ return tgl_update; }
	public void setTgl_update(Date tgl_update){ this.tgl_update = tgl_update; }

	public String getJam_update(){ return jam_update; }
	public void setJam_update(String jam_update){ this.jam_update = jam_update; }

	public String getUser_nonaktif(){ return user_nonaktif; }
	public void setUser_nonaktif(String user_nonaktif){ this.user_nonaktif = user_nonaktif; }

	public Date getTgl_nonaktif(){ return tgl_nonaktif; }
	public void setTgl_nonaktif(Date tgl_nonaktif){ this.tgl_nonaktif = tgl_nonaktif; }

	public String getJam_nonaktif(){ return jam_nonaktif; }
	public void setJam_nonaktif(String jam_nonaktif){ this.jam_nonaktif = jam_nonaktif; }


	public String getItemId() {return user_id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public String getOldPassword() {return oldPassword;	}
	public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}

	public String getNewPassword() {return newPassword;}
	public void setNewPassword(String newPassword) {this.newPassword = newPassword;}

	public String getConfirmPassword() {return confirmPassword;}
	public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;	}

	//****************** GETTER SETTER END HERE ******************/

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
		authorities.add(new Role(Role.ROLE_NAME_USER));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return locked.intValue()==1?true:false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return active.intValue()==1?true:false;
	}
}
