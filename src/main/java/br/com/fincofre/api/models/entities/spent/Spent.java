package br.com.fincofre.api.models.entities.spent;

import br.com.fincofre.api.models.entities.user.User;
import br.com.fincofre.api.models.dtos.SpentResponseDTO;
import br.com.fincofre.api.models.dtos.SpentUpdateDTO;
import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column(name = "spent_date")
    @NotBlank
    private String date;

    @NotBlank
    private String spent;

    @Enumerated(EnumType.STRING) // Armazena o enum como string no banco de dados
    @NotBlank
    private Status status;

    @Column(name = "spent_value")
    @NotBlank
    private String value;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Category category;

    @NotBlank
    private String account;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Method method;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public Spent(SpentResponseDTO response, User user) {
        this.date = response.date();
        this.spent = response.spent();
        this.status = response.status();
        this.value = response.value();
        this.category = response.category();
        this.account = response.account();
        this.method = response.method();
        this.user = user;
    }

    public void updateData(SpentUpdateDTO response) {
        if (response.date() != null) this.date = response.date();
        if (response.spent() != null) this.spent = response.spent();
        if (response.status() != null) this.status = response.status();
        if (response.value() != null) this.value = response.value();
        if (response.category() != null) this.category = response.category();
        if (response.account() != null) this.account = response.account();
        if (response.method() != null) this.method = response.method();
    }

}
