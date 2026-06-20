package votacao.votacaoApi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import votacao.votacaoApi.DTO.SessaoDTO;
import votacao.votacaoApi.model.Sessao;

@Mapper(componentModel = "spring")
public interface SessaoMapper {
    SessaoMapper INSTANCE = Mappers.getMapper(SessaoMapper.class);
    SessaoDTO toDto(Sessao sessao);
    Sessao toEntity(SessaoDTO sessaoDTO);


}
