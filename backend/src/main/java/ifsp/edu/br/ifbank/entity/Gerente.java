package ifsp.edu.br.ifbank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gerente")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Gerente extends Usuario {

        @Column(nullable = false, unique = true)
        private String matricula;

    @OneToMany(mappedBy = "gerente")
    private List<Conta> contas; //um gerente pode abrir varias contas


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}

