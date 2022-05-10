package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(sequenceName="UpdateFileSeq",
			name="update_file_generator",initialValue=1,allocationSize=1)
@Table(name="UpdateFile")
public class UpdateFile implements Serializable{

	private static final long serialVersionUID = 6215131145097672912L;

	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="update_file_generator")
	private int id;
	
	private UpdateFileKind updateFileKind = UpdateFileKind.Diversos;
	
	private boolean published=false;
	
	@ManyToOne
	private Arquivo currentFile;
	
	@ManyToOne
	private Arquivo installationFile;
	
	@ManyToOne
	private Update updateBean;
	
	@OrderBy(value="id")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="UPDATEFILEVERSIONS"
			,joinColumns={@JoinColumn(name="UPDATEFILE_ID")}
			,inverseJoinColumns={@JoinColumn(name="VERSIONEDFILE_ID")}
	)
	private List<UpdateFile> previousUpdates;
	
	@Column(nullable=false,length=10,name="version")
	private String version;
	
	@Column(nullable=false,length=1500,name="observation")
	private String obs;
	
	private Date date;
	
	@OrderBy(value="id")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="UPDATEFILEAUTORITIES"
			,joinColumns={@JoinColumn(name="UPDATEFILE_ID")}
			,inverseJoinColumns={@JoinColumn(name="PARTICIPANT_ID")}
	)
	private List<Participante> autorizedUsers;
	
	@Column(name="user_restricted")
	private boolean restrict=true;
	

	@Column(nullable=true,length=500,name="whatChanged")
	private String whatChanged;
	
	@Column(nullable=true,length=500,name="whyChanged")
	private String whyChanged;
	
	@Column(nullable=true,length=500,name="whoChanged")
	private String whoChanged;

	public String getWhatChanged() {
		return whatChanged;
	}

	public void setWhatChanged(String whatChanged) {
		this.whatChanged = whatChanged;
	}

	public String getWhyChanged() {
		return whyChanged;
	}

	public void setWhyChanged(String whyChanged) {
		this.whyChanged = whyChanged;
	}

	public String getWhoChanged() {
		return whoChanged;
	}

	public void setWhoChanged(String whoChanged) {
		this.whoChanged = whoChanged;
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UpdateFileKind getUpdateFileKind() {
		return updateFileKind;
	}

	public void setUpdateFileKind(UpdateFileKind updateFileKind) {
		this.updateFileKind = updateFileKind;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Arquivo getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(Arquivo currentFile) {
		this.currentFile = currentFile;
	}

	public Update getUpdateBean() {
		return updateBean;
	}

	public void setUpdateBean(Update updateBean) {
		this.updateBean = updateBean;
	}

	public List<UpdateFile> getPreviousUpdates() {
		return previousUpdates;
	}

	public void setPreviousUpdates(List<UpdateFile> previousUpdates) {
		this.previousUpdates = previousUpdates;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UpdateFile newVersion() {
		UpdateFile uf = new UpdateFile();
		uf.currentFile = currentFile;
		uf.date = date;//new Date();
		uf.setObs(obs);
		uf.setVersion(version);
		uf.published=published;
		uf.updateFileKind=updateFileKind;
		return uf;
	}

	public Arquivo getInstallationFile() {
		return installationFile;
	}

	public void setInstallationFile(Arquivo installationFile) {
		this.installationFile = installationFile;
	}

	public List<Participante> getAutorizedUsers() {
		return autorizedUsers;
	}

	public void setAutorizedUsers(List<Participante> autorizedUsers) {
		this.autorizedUsers = autorizedUsers;
	}

	public boolean isRestrict() {
		return restrict;
	}

	public void setRestrict(boolean restrict) {
		this.restrict = restrict;
	}
	
	@Override
	public boolean equals(Object obj) {
		UpdateFile uf = (UpdateFile) obj;
		return uf.getId() == id;
	}
	
	public boolean isAutorized(Participante p){
		if(autorizedUsers != null && autorizedUsers.size()>0){
			for(Participante part: autorizedUsers){
				if(p.getId() == part.getId())
					return true;
			}
		}
		return false;
	}
	
	public boolean isAutorized(long partid){
		if(autorizedUsers != null && autorizedUsers.size()>0){
			for(Participante part: autorizedUsers){
				if(partid == part.getId())
					return true;
			}
		}
		return false;
	}
}
