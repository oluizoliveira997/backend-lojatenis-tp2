package br.unitins.joaovittor.basqueteiros.service.item;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.item.ItemDTO;
import br.unitins.joaovittor.basqueteiros.dto.item.ItemResponseDTO;

public interface ItemService {
    List<ItemResponseDTO> getAll();
    ItemResponseDTO getById(Long id);
    ItemResponseDTO create(ItemDTO itemDTO);
    ItemResponseDTO update(Long id, ItemDTO itemDTO);
    void delete(Long id);
}