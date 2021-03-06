package br.com.senac.entidade;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * @Inheritance - Somente herança na (classe pai)
 * private Endereco endereco para puxar da entidade Endereço
 * @OneToOne - 1 para 1 - relacionamento
 * Quando for Bidirecional tem que ficar com MappedBy
 * cascade é só para excluir tudo de uma vez
 * Se For lista é fetch = LAZY
 * @author marcello.nunes
 */
@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL) // fetch = EAGER/ (se não for lista)
    private Endereco endereco; // 1 endereço
    
    @OneToMany(mappedBy = "cliente")
    private List<Cartao> cartoes;

    public Cliente() {
    }

    public Cliente(String nome, String email) {      
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.senac.entidade.Cliente[ id=" + id + " ]";
    }
}