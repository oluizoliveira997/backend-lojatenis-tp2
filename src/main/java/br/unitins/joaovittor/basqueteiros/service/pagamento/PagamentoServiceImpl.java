package br.unitins.joaovittor.basqueteiros.service.pagamento;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.pagamento.PagamentoDTO;
import br.unitins.joaovittor.basqueteiros.dto.pagamento.PagamentoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.pagamento.Pagamento;
import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;
import br.unitins.joaovittor.basqueteiros.repository.CartaoRepository;
import br.unitins.joaovittor.basqueteiros.repository.PagamentoRepository;
import br.unitins.joaovittor.basqueteiros.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    CartaoRepository cartaoRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public PagamentoResponseDTO create(PagamentoDTO dto) {
        validate(dto);

        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(dto.tipoPagamento());
        pagamento.setValor(dto.valor());

        // Associar Pedido
        Pedido pedido = pedidoRepository.findByIdOptional(dto.idPedido())
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
        pagamento.setPedido(pedido);

        // Associar Cartão (opcional)
        if (dto.idCartao() != null) {
            Cartao cartao = cartaoRepository.findByIdOptional(dto.idCartao())
                    .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));
            pagamento.setCartao(cartao);
        }

        // Configurar outras propriedades
        pagamento.setChavePix(dto.chavePix());
        pagamento.setNumeroBoleto(dto.numeroBoleto());

        pagamentoRepository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO update(Long id, PagamentoDTO dto) {
        validate(dto);

        Pagamento pagamento = pagamentoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado"));

        pagamento.setTipoPagamento(dto.tipoPagamento());
        pagamento.setValor(dto.valor());

        // Atualizar Pedido
        Pedido pedido = pedidoRepository.findByIdOptional(dto.idPedido())
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
        pagamento.setPedido(pedido);

        // Atualizar Cartão (opcional)
        if (dto.idCartao() != null) {
            Cartao cartao = cartaoRepository.findByIdOptional(dto.idCartao())
                    .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));
            pagamento.setCartao(cartao);
        }

        pagamento.setChavePix(dto.chavePix());
        pagamento.setNumeroBoleto(dto.numeroBoleto());

        pagamentoRepository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!pagamentoRepository.deleteById(id)) {
            throw new NotFoundException("Pagamento não encontrado");
        }
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado"));
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public List<PagamentoResponseDTO> findAll(int page, int pageSize) {
        return pagamentoRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return pagamentoRepository.count();
    }

    private void validate(PagamentoDTO dto) {
        Set<ConstraintViolation<PagamentoDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
