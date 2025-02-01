package br.com.fincofre.api.spent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "spents") // Define o nome da tabela no banco de dados
@Entity(name = "Spent") // Marca esta classe como uma entidade JPA
@Getter // Gera automaticamente os métodos getter (Lombok)
@NoArgsConstructor // Gera um construtor sem argumentos (Lombok)
@AllArgsConstructor // Gera um construtor com todos os argumentos (Lombok)
@EqualsAndHashCode(of = "id") // Implementa equals() e hashCode() com base no campo "id" (Lombok)
public class Spent {

    @Id // Define este campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz com que o banco gere automaticamente o ID sequencialmente
    private Long id;
    private String date;
    private String spent;

    @Enumerated(EnumType.STRING) // Armazena o enum como string no banco de dados
    private Status status;

    @Column(name = "spent_value")
    private String value;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String account;

    @Enumerated(EnumType.STRING)
    private Method method;

    public Spent(SpentResponseDTO response) {
        this.date = response.date();
        this.spent = response.date();
        this.status = response.status();
        this.value = response.value();
        this.category = response.category();
        this.account = response.account();
        this.method = response.method();
    }
}
