package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {
    
    public List<Telefone> findByDdd(String ddd) {
        if (ddd == null)
            return null;
        return find("ddd = ?1", ddd.replaceAll("[^0-9]", "")).list();
    }

    public List<Telefone> findByNumero(String numero) {
        if (numero == null)
            return null;
        return find("numero = ?1", numero.replaceAll("[^0-9]", "")).list();
    }

    public List<Telefone> findByDddAndNumero(String ddd, String numero) {
        if (ddd == null || numero == null)
            return null;
        return find("ddd = ?1 AND numero = ?2", 
                   ddd.replaceAll("[^0-9]", ""), 
                   numero.replaceAll("[^0-9]", "")).list();
    }
    
    public boolean existsByDddAndNumero(String ddd, String numero) {
        if (ddd == null || numero == null)
            return false;
        return count("ddd = ?1 AND numero = ?2", 
                    ddd.replaceAll("[^0-9]", ""), 
                    numero.replaceAll("[^0-9]", "")) > 0;
    }
}