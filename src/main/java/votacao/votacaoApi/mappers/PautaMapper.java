package votacao.votacaoApi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import votacao.votacaoApi.DTO.PautaDTO;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.model.Pauta;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    PautaDTO toDto(Pauta pauta);
    Pauta toEntity(PautaDTO pautaDTO);

    PautaRequestDTO toPautaRequestDTO(Pauta pauta);

    @Mapping(target = "idPauta", ignore = true)
    Pauta toEntity(PautaRequestDTO pautaRequestDTO);
}