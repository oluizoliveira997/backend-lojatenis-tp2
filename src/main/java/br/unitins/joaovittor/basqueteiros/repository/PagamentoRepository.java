package br.unitins.joaovittor.basqueteiros.repository;

import br.unitins.joaovittor.basqueteiros.model.pagamento.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

}
