// Service Implementation
package br.unitins.joaovittor.basqueteiros.service.pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.pedido.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.dto.pedido.PedidoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;
import br.unitins.joaovittor.basqueteiros.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    public List<PedidoResponseDTO> getAll() {
        return pedidoRepository.findAll().list().stream()
                .map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO getById(Long id) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        // Map DTO fields to entity here
        pedidoRepository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO update(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        // Update fields from DTO
        pedidoRepository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        pedidoRepository.delete(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findByUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuario(usuarioId).stream()
                .map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponseDTO> findByPeriodo(String inicio, String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        return pedidoRepository.findByPeriodo(dataInicio, dataFim).stream()
                .map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}