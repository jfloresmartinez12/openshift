package lu.lllc;

import java.io.Serializable;

public class Cita implements Serializable {
	static final long serialVersionUID = 12345L;
	private long id;
	private String establecimiento;
	private String tipoDocumento;
	private long numDocumento;
	private String apellidoPaterno;
	private String consultaSolicitada;
	
	public Cita() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(long numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getConsultaSolicitada() {
		return consultaSolicitada;
	}

	public void setConsultaSolicitada(String consultaSolicitada) {
		this.consultaSolicitada = consultaSolicitada;
	}

}
