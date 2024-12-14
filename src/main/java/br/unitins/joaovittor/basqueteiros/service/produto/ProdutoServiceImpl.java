package br.unitins.joaovittor.basqueteiros.service.produto;

import br.unitins.joaovittor.basqueteiros.dto.produto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.dto.produto.ProdutoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.produto.Produto;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.repository.ProdutoRepository;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.service.produto.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    public List<ProdutoResponseDTO> getAll() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO getById(Long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null)
            throw new RuntimeException("Produto not found");
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Transactional
    @Override
    public ProdutoResponseDTO create(ProdutoDTO produtoDTO) {
        if (produtoDTO.fornecedor() == null || produtoDTO.fornecedor().id() == null) {
            throw new RuntimeException("Fornecedor ID is required");
        }

        Fornecedor fornecedor = fornecedorRepository.findById(produtoDTO.fornecedor().id());
        if (fornecedor == null)
            throw new RuntimeException("Fornecedor not found");

        Produto produto = new Produto();
        updateProdutoFromDTO(produto, produtoDTO, fornecedor);

        produtoRepository.persist(produto);
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Transactional
    @Override
    public ProdutoResponseDTO update(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null)
            throw new RuntimeException("Produto not found");

        Fornecedor fornecedor = fornecedorRepository.findById(produtoDTO.fornecedor().id());
        if (fornecedor == null)
            throw new RuntimeException("Fornecedor not found");

        updateProdutoFromDTO(produto, produtoDTO, fornecedor);

        return ProdutoResponseDTO.valueOf(produto);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null)
            throw new RuntimeException("Produto not found");
        produtoRepository.delete(produto);
    }

    private void updateProdutoFromDTO(Produto produto, ProdutoDTO dto, Fornecedor fornecedor) {
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco().floatValue());
        produto.setEstoque(dto.estoque());
        produto.setFornecedor(fornecedor);
        
        // Novos campos
        produto.setDescricao(dto.descricao());
        produto.setModelo(dto.modelo());
        produto.setTamanho(dto.tamanho());
        
        // Tratamento da marca
        if (dto.marcaId() != null) {
            Marca marca = marcaRepository.findById(dto.marcaId());
            if (marca == null) {
                throw new RuntimeException("Marca not found");
            }
            produto.setMarca(marca);
        } else {
            produto.setMarca(null);
        }
    }



    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        return produtoRepository.findByNome(nome).stream()
                .map(ProdutoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoResponseDTO> findByMarca(Long marcaId) {
        return produtoRepository.findByMarcaId(marcaId).stream()
                .map(ProdutoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoResponseDTO> findByFornecedor(Long fornecedorId) {
        return produtoRepository.findByFornecedorId(fornecedorId).stream()
                .map(ProdutoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoResponseDTO> findByPrecoBetween(Float precoMin, Float precoMax) {
        return produtoRepository.findByPrecoBetween(precoMin, precoMax).stream()
                .map(ProdutoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProdutoResponseDTO updateEstoque(Long id, int quantidade) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new RuntimeException("Produto not found");
        }
        produto.setEstoque(quantidade);
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO updatePreco(Long id, Float novoPreco) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new RuntimeException("Produto not found");
        }
        produto.setPreco(novoPreco);
        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    public long count() {
        return produtoRepository.count();
    }

    @Override
    public long countByMarca(Long marcaId) {
        return produtoRepository.countByMarcaId(marcaId);
    }

    @Override
    public long countByFornecedor(Long fornecedorId) {
        return produtoRepository.countByFornecedorId(fornecedorId);
    }
}