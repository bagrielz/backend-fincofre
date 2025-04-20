package br.com.fincofre.api.models.entities.spent;

import br.com.fincofre.api.models.dtos.SpentCreateDTO;
import br.com.fincofre.api.models.entities.user.User;
import br.com.fincofre.api.models.dtos.SpentUpdateDTO;
import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Status status;

    @Column(name = "spent_value")
    @NotBlank
    private String value;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @NotBlank
    private String account;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Method method;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Spent(String date, String spent, Status status, String value, Category category, String account, Method method, User user) {
        this.date = date;
        this.spent = spent;
        this.status = status;
        this.value = value;
        this.category = category;
        this.account = account;
        this.method = method;
        this.user = user;
    }

    public static Spent fromDTO(SpentCreateDTO dto, User user) {
        return new Spent(
                dto.date(),
                dto.spent(),
                dto.status(),
                dto.value(),
                dto.category(),
                dto.account(),
                dto.method(),
                user
        );
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
