// ItemRepository.java
package br.unitins.joaovittor.basqueteiros.repository;

import br.unitins.joaovittor.basqueteiros.model.item.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}

