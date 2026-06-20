package votacao.votacaoApi.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssociadoDTO {
    @Enumerated(EnumType.ORDINAL)
    private UUID idAssociado;
    private String nome;
    @CPF
    private String cpf;
    private String email;
    private Boolean ativo;
}
