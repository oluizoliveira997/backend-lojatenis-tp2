// Resource Class
package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.pedido.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.dto.pedido.PedidoResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.pedido.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @GET
    public List<PedidoResponseDTO> getAll() {
        return pedidoService.getAll();
    }

    @GET
    @Path("/{id}")
    public PedidoResponseDTO getById(@PathParam("id") Long id) {
        return pedidoService.getById(id);
    }

    @POST
    public Response create(PedidoDTO pedidoDTO) {
        PedidoResponseDTO created = pedidoService.create(pedidoDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public PedidoResponseDTO update(@PathParam("id") Long id, PedidoDTO pedidoDTO) {
        return pedidoService.update(id, pedidoDTO);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        pedidoService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public List<PedidoResponseDTO> findByUsuario(@PathParam("usuarioId") Long usuarioId) {
        return pedidoService.findByUsuario(usuarioId);
    }

    @GET
    @Path("/periodo")
    public List<PedidoResponseDTO> findByPeriodo(@QueryParam("inicio") String inicio, @QueryParam("fim") String fim) {
        return pedidoService.findByPeriodo(inicio, fim);
    }
}
