package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.pagamento.PagamentoDTO;
import br.unitins.joaovittor.basqueteiros.dto.pagamento.PagamentoResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.pagamento.PagamentoService;
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

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
    PagamentoService pagamentoService;

    @POST
    public Response create(@Valid PagamentoDTO dto) {
        PagamentoResponseDTO response = pagamentoService.create(dto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid PagamentoDTO dto) {
        PagamentoResponseDTO response = pagamentoService.update(id, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        pagamentoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        PagamentoResponseDTO response = pagamentoService.findById(id);
        return Response.ok(response).build();
    }

    @GET
    public List<PagamentoResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return pagamentoService.findAll(page, pageSize);
    }

    @GET
    @Path("/count")
    public Response count() {
        long total = pagamentoService.count();
        return Response.ok(total).build();
    }
}

