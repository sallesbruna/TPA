package exercicio;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="LANCE")
public class Lance {
	
	private Long id;
	private double valor;		
	private Date dataLance;
	private long produto;
	
	public Lance()
	{
	}
	
	public Lance(double valor, Date dataLance, long produto)
	{
		this.valor = valor;		
		this.dataLance = dataLance;
		this.produto = produto;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="VALOR")
	public double getValor() {
		return valor;
	}
	
	@Transient
	public String getValorMasc()
	{
		return Util.doubleToStr(valor);
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Column(name="DATA_LANCE")
	public Date getDataLance() {
		return dataLance;
	}

	public void setDataLance(Date dataLance) {
		this.dataLance = dataLance;
	}
	
	@Column(name="PRODUTO")
	public long getProduto()
	{
		return this.produto;
	}
	
	public void setProduto(long umProduto)
	{
		this.produto = umProduto;
	}
	
}
