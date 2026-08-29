package votacao.votacaoApi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import votacao.votacaoApi.DTO.PautaDTO;
import votacao.votacaoApi.DTO.PautaRequestDTO;
import votacao.votacaoApi.DTO.PautaResponseDTO;
import votacao.votacaoApi.mappers.PautaMapper;
import votacao.votacaoApi.mappers.VotoMapper;
import votacao.votacaoApi.model.Pauta;
import votacao.votacaoApi.repository.PautaRepository;
import votacao.votacaoApi.repository.VotoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaMapper pautaMapper;


    @Mock
    private VotoMapper votoMapper;

    @Autowired
    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve buscar uma pauta")
    void buscarPautaPeloId() {

        Pauta pauta = new Pauta();
        pauta.setIdPauta(1L);
        pauta.setDescricaoPauta("descricao");


        PautaResponseDTO pautaResponseDTO = new PautaResponseDTO();
        pautaResponseDTO.setIdPauta(1L);
        pautaResponseDTO.setDescricaoPauta("descricao");

        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setIdPauta(1L);
        pautaDTO.setDescricaoPauta("descricao");

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        when(pautaMapper.toPautaResponseDTO(pauta)).thenReturn(pautaResponseDTO);

        PautaResponseDTO resultado = pautaService.buscarPauta(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPauta());
        assertEquals("descricao", resultado.getDescricaoPauta());


    }

    @Test
    @DisplayName("Deve cadastrar uma pauta")
    void cadastrarPauta() {

        PautaRequestDTO pautaRequestDTO = new PautaRequestDTO();
        pautaRequestDTO.setDescricaoPauta("descricao");
        pautaRequestDTO.setTituloPauta("titulo");

        Pauta pautaMapeada = new Pauta();
        pautaMapeada.setDescricaoPauta("descricao");
        pautaMapeada.setTituloPauta("titulo");

        Pauta pautaSalva = new Pauta();
        pautaSalva.setIdPauta(1L);
        pautaSalva.setDescricaoPauta("descricao");
        pautaSalva.setTituloPauta("titulo");

        PautaResponseDTO pautaResponseDTO = new PautaResponseDTO();
        pautaResponseDTO.setIdPauta(1L);
        pautaResponseDTO.setDescricaoPauta("descricao");
        pautaResponseDTO.setTituloPauta("titulo");

        when(pautaRepository.existsByTituloPauta("titulo")).thenReturn(false);
        when(pautaMapper.toEntity(pautaRequestDTO)).thenReturn(pautaMapeada);
        when(pautaRepository.save(pautaMapeada)).thenReturn(pautaSalva);

        when(pautaMapper.toPautaResponseDTO(pautaSalva)).thenReturn(pautaResponseDTO);

        PautaResponseDTO resultado = this.pautaService.cadastrarPauta(pautaRequestDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPauta());
        assertEquals("descricao", resultado.getDescricaoPauta());
        assertEquals("titulo", resultado.getTituloPauta());
    }

    @Test
    void apagarPauta() {

        PautaRequestDTO pautaRequestDTO = new PautaRequestDTO();
        pautaRequestDTO.setDescricaoPauta("descricao");
        pautaRequestDTO.setTituloPauta("titulo");

        Pauta pautaMapeada = new Pauta();
        pautaMapeada.setDescricaoPauta("descricao");
        pautaMapeada.setTituloPauta("titulo");

        Pauta pautaSalva = new Pauta();
        pautaSalva.setIdPauta(1L);
        pautaSalva.setDescricaoPauta("descricao");
        pautaSalva.setTituloPauta("titulo");

        PautaResponseDTO resultado = this.pautaService.cadastrarPauta(pautaRequestDTO);

        this.pautaService.apagarPauta(1L);

        when(pautaRepository.existsByTituloPauta("titulo")).thenReturn(true);

        when(pautaMapper.toEntity(pautaRequestDTO)).thenReturn(pautaMapeada);

        when(pautaRepository.save(pautaMapeada)).thenReturn(pautaSalva);

        Long idExistente = 1L;

        verify(pautaRepository, times(1)).deleteById(idExistente);


    }

    @Test
    void abrirSessaoVotacao() {
    }

    @Test
    void fecharSessaoVotacao() {
    }

    @Test
    void obterResultadoVotacao() {
    }
}