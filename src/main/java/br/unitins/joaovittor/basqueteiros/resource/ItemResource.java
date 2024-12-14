package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.item.ItemDTO;
import br.unitins.joaovittor.basqueteiros.dto.item.ItemResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.item.ItemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    public List<ItemResponseDTO> getAll() {
        return itemService.getAll();
    }

    @GET
    @Path("/{id}")
    public ItemResponseDTO getById(@PathParam("id") Long id) {
        return itemService.getById(id);
    }

    @POST
    public ItemResponseDTO create(ItemDTO itemDTO) {
        return itemService.create(itemDTO);
    }

    @PUT
    @Path("/{id}")
    public ItemResponseDTO update(@PathParam("id") Long id, ItemDTO itemDTO) {
        return itemService.update(id, itemDTO);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        itemService.delete(id);
    }
}
