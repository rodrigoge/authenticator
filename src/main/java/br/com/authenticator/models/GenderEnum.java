package br.com.authenticator.models;

public enum GenderEnum {
	Masculino("Masculino"), Feminino("Feminino"), Outros("Outros");
	
	private String description;
	
	GenderEnum(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
