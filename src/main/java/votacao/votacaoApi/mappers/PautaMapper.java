package votacao.votacaoApi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.DTO.PautaResponseDTO;
import votacao.votacaoApi.model.Pauta;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaResponseDTO toPautaResponseDTO(Pauta pauta);

    @Mapping(target = "idPauta", ignore = true)
    Pauta toEntity(PautaRequestDTO pautaRequestDTO);
}