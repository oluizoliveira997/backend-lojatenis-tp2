package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.tenis.TenisService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tenis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TenisResource {

    @Inject
    TenisService tenisService;

    @POST
    public Response criarTenis(@Valid TenisDTO tenisDTO) {
        TenisResponseDTO tenis = tenisService.create(tenisDTO);
        return Response.status(Response.Status.CREATED).entity(tenis).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarTenisPorId(@PathParam("id") Long id) {
        TenisResponseDTO tenis = tenisService.findById(id);
        if (tenis != null) {
            return Response.ok(tenis).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarTenisPorNome(
            @PathParam("nome") String nome,
            @QueryParam("page") int page,
            @QueryParam("pageSize") int pageSize) {
        List<TenisResponseDTO> tenis = tenisService.findByNome(nome, page, pageSize)
                .stream()
                .collect(Collectors.toList());
        return Response.ok(tenis).build();
    }

    @GET
    @Path("/marca/{marcaNome}")
    public Response buscarTenisPorMarca(
            @PathParam("marcaNome") String marcaNome,
            @QueryParam("page") int page,
            @QueryParam("pageSize") int pageSize) {
        List<TenisResponseDTO> tenis = tenisService.findByMarca(marcaNome, page, pageSize)
                .stream()
                .collect(Collectors.toList());
        return Response.ok(tenis).build();
    }

    @GET
    @Path("/preco")
    public Response buscarTenisPorPrecoRange(
            @QueryParam("min") float minPreco,
            @QueryParam("max") float maxPreco,
            @QueryParam("page") int page,
            @QueryParam("pageSize") int pageSize) {
        List<TenisResponseDTO> tenis = tenisService.findByPrecoRange(minPreco, maxPreco, page, pageSize)
                .stream()
                .collect(Collectors.toList());
        return Response.ok(tenis).build();
    }

    @GET
    public Response listarTodosTenis(
            @QueryParam("page") int page,
            @QueryParam("pageSize") int pageSize) {
        List<TenisResponseDTO> tenis = tenisService.findAll(page, pageSize);
        return Response.ok(tenis).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirTenis(@PathParam("id") Long id) {
        tenisService.delete(id);
        return Response.noContent().build();
    }
}
