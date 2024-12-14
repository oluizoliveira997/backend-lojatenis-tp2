package br.unitins.joaovittor.basqueteiros.service.pagamento;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.pagamento.PagamentoDTO;
import br.unitins.joaovittor.basqueteiros.dto.pagamento.PagamentoResponseDTO;

public interface PagamentoService {
    PagamentoResponseDTO create(PagamentoDTO dto);

    PagamentoResponseDTO update(Long id, PagamentoDTO dto);

    void delete(Long id);

    PagamentoResponseDTO findById(Long id);

    List<PagamentoResponseDTO> findAll(int page, int pageSize);

    long count();
}
