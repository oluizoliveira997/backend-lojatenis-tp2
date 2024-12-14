package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.marca.MarcaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService marcaService;

    @POST
    public Response create(@Valid MarcaDTO dto) {
        MarcaResponseDTO marcaResponse = marcaService.create(dto);
        return Response.status(Response.Status.CREATED).entity(marcaResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid MarcaDTO dto) {
        MarcaResponseDTO marcaResponse = marcaService.update(id, dto);
        return Response.ok(marcaResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        marcaService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        MarcaResponseDTO marcaResponse = marcaService.findById(id);
        return Response.ok(marcaResponse).build();
    }

    @GET
    public List<MarcaResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return marcaService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    public List<MarcaResponseDTO> findByNome(
            @QueryParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return marcaService.findByNome(nome, page, pageSize);
    }

    @GET
    @Path("/count")
    public Response count() {
        long total = marcaService.count();
        return Response.ok(total).build();
    }

    @GET
    @Path("/count/search")
    public Response countByNome(@QueryParam("nome") String nome) {
        long total = marcaService.countByNome(nome);
        return Response.ok(total).build();
    }
}