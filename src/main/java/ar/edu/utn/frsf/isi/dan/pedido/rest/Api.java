package ar.edu.utn.frsf.isi.dan.pedido.rest;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
interface Api
{
	String BASE_PATH = "/api/v1";

	String PEDIDO_BASE_PATH = BASE_PATH + "/pedido";
	String PEDIDO_POST_ADD_DETALLE_PATH = "/{idPedido}/detalle";
	String PEDIDO_PUT_ID_PATH = "/{id}";
	String PEDIDO_DELETE_ID_PATH = "/{id}";
	String PEDIDO_DELETE_DETALLE_ID_PATH = "/{idPedido}/detalle/{idDetalle}";
	String PEDIDO_PUT_DETALLE_ID_PATH = "/{idPedido}/detalle/{idDetalle}";
	String PEDIDO_GET_ID_PATH = "/{id}";
	String PEDIDO_GET_DETALLE_ID_PATH = "/{idPedido}/detalle/{idDetalle}";
	String PEDIDO_GET_DETALLE_PATH = "/{idPedido}/detalle";
	String PEDIDO_GET_ALL_PATH = "/listar";
	String PEDIDO_GET_ID_OBRA_REQUEST_PARAM = "idObra";
	String PEDIDO_GET_ID_OBRA_PATH = "/obra/{idObra}";
	String PEDIDO_GET_ID_ESTADO_PATH = "/estado/{idEstado}";
	String PEDIDO_GET_ID_CLIENTE_PATH = "/cliente/{idCliente}";
}
