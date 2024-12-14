// Service Interface
package br.unitins.joaovittor.basqueteiros.service.pedido;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.pedido.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.dto.pedido.PedidoResponseDTO;

public interface PedidoService {

    List<PedidoResponseDTO> getAll();

    PedidoResponseDTO getById(Long id);

    PedidoResponseDTO create(PedidoDTO pedidoDTO);

    PedidoResponseDTO update(Long id, PedidoDTO pedidoDTO);

    void delete(Long id);

    List<PedidoResponseDTO> findByUsuario(Long usuarioId);

    List<PedidoResponseDTO> findByPeriodo(String inicio, String fim);

}