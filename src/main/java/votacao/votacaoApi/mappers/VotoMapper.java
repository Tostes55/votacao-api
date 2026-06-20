package votacao.votacaoApi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import votacao.votacaoApi.DTO.VotoDTO;
import votacao.votacaoApi.model.Voto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VotoMapper {
    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    VotoDTO toVotoDTO(Voto voto);
    Voto toEntity(VotoDTO votoDTO);

    List<VotoDTO> toVotoDTOs(List<Voto> votos);
    List<Voto> toEntities(List<VotoDTO> dtos);



}
