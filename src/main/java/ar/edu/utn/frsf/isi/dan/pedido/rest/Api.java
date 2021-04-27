package ar.edu.utn.frsf.isi.dan.pedido.rest;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
interface Api {
	public static String BASE_PATH = "/api/v1";
	
	public static String PEDIDO_BASE_PATH = BASE_PATH + "/pedido";
	public static String PEDIDO_POST_ADD_DETALLE_PATH = "/{idPedido}/detalle";
	public static String PEDIDO_PUT_ID_PATH = "/{id}";
	public static String PEDIDO_DELETE_ID_PATH = "/{id}";
	public static String PEDIDO_DELETE_DETALLE_ID_PATH = "/{idPedido}/detalle/{idDetalle}";
	public static String PEDIDO_GET_ID_PATH = "/{id}";
	public static String PEDIDO_GET_DETALLE_ID_PATH = "/idPedido}/detalle/{idDetalle}";
	public static String PEDIDO_GET_ALL_PATH = "/listar";
	public static String PEDIDO_GET_ID_OBRA_REQUEST_PARAM = "idObra";
}
