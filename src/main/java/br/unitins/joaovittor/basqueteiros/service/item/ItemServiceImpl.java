package br.unitins.joaovittor.basqueteiros.service.item;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.item.ItemDTO;
import br.unitins.joaovittor.basqueteiros.dto.item.ItemResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.item.Item;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.repository.ItemRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {

    @Inject
    ItemRepository itemRepository;

    @Inject
    TenisRepository tenisRepository;

    @Override
    public List<ItemResponseDTO> getAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponseDTO getById(Long id) {
        Item item = itemRepository.findById(id);
        if (item == null)
            throw new RuntimeException("Item não encontrado ou indisponível");
        return ItemResponseDTO.valueOf(item);
    }

    @Transactional
    @Override
    public ItemResponseDTO create(ItemDTO itemDTO) {
        Tenis tenis = tenisRepository.findById(itemDTO.idTenis());
        if (tenis == null)
            throw new RuntimeException("Tenis não criado");

        Item item = new Item();
        item.setQuantidade(itemDTO.quantidade());
        item.setPreco(tenis.getPreco()); // Presume que o preco vem do tenis
        item.setTenis(tenis);

        itemRepository.persist(item);
        return ItemResponseDTO.valueOf(item);
    }

    @Transactional
    @Override
    public ItemResponseDTO update(Long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id);
        if (item == null)
            throw new RuntimeException("Item não encontrado ou indisponível");

        Tenis tenis = tenisRepository.findById(itemDTO.idTenis());
        if (tenis == null)
            throw new RuntimeException("Tenis não encontrado ou indisponível");

        item.setQuantidade(itemDTO.quantidade());
        item.setTenis(tenis);
        item.setPreco(tenis.getPreco());

        return ItemResponseDTO.valueOf(item);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Item item = itemRepository.findById(id);
        if (item == null)
            throw new RuntimeException("Item não emcontado");
        itemRepository.delete(item);
    }
}
