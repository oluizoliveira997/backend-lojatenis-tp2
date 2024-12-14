package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.produto.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    
    public List<Produto> findByNome(String nome) {
        if (nome == null) 
            return null;
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%").list();
    }

    public List<Produto> findByMarcaId(Long marcaId) {
        if (marcaId == null)
            return null;
        return find("marca.id", marcaId).list();
    }

    public List<Produto> findByFornecedorId(Long fornecedorId) {
        if (fornecedorId == null)
            return null;
        return find("fornecedor.id", fornecedorId).list();
    }

    public List<Produto> findByPrecoBetween(Float precoMin, Float precoMax) {
        if (precoMin == null || precoMax == null)
            return null;
        return find("preco BETWEEN ?1 AND ?2", precoMin, precoMax).list();
    }

    public long countByMarcaId(Long marcaId) {
        if (marcaId == null)
            return 0;
        return count("marca.id", marcaId);
    }

    public long countByFornecedorId(Long fornecedorId) {
        if (fornecedorId == null)
            return 0;
        return count("fornecedor.id", fornecedorId);
    }
}