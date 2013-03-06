package com.medcare.mundo;

public class Medico {

	private String nombre;
	
	private String ciudad;
	
	private String telefono;
	
	private String direccion;
	
	private String especialidad;

	public Medico(String nombre, String ciudad, String telefono, String direccion, String especialidad) 
	{
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.direccion = direccion;
		this.especialidad = especialidad;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getCiudad() 
	{
		return ciudad;
	}

	public void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}

	public String getTelefono() 
	{
		return telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	public String getEspecialidad() 
	{
		return especialidad;
	}

	public void setEspecialidad(String especialidad) 
	{
		this.especialidad = especialidad;
	}
	
	@Override
	public String toString()
	{
		return nombre +"\n"+ telefono+"\n" + direccion;
	}
	
}
