package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.produto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.dto.produto.ProdutoResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.produto.ProdutoService;
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

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    public List<ProdutoResponseDTO> getAll() {
        return produtoService.getAll();
    }

    @GET
    @Path("/{id}")
    public ProdutoResponseDTO getById(@PathParam("id") Long id) {
        return produtoService.getById(id);
    }

    @POST
    public ProdutoResponseDTO create(ProdutoDTO produtoDTO) {
        return produtoService.create(produtoDTO);
    }

    @PUT
    @Path("/{id}")
    public ProdutoResponseDTO update(@PathParam("id") Long id, ProdutoDTO produtoDTO) {
        return produtoService.update(id, produtoDTO);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        produtoService.delete(id);
    }
}

