package club.ihere.wechat.common.json;


import com.google.gson.Gson;

public class JsonResultBuilder<T> {

	private boolean status = true;

	private String message = "success";

	private T data;

	private Gson gson;

	private JsonResultBuilder() {
	}

	public static <T> JsonResultBuilder<T> create() {
		return new JsonResultBuilder<>();
	}

	public JsonResultBuilder<T> setStatus(boolean status) {
		this.status = status;
		return this;
	}

	public JsonResultBuilder<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public JsonResultBuilder<T> setData(T data) {
		this.data = data;
		return this;
	}

	public JsonResultBuilder<T> setGson(Gson gson) {
		this.gson = gson;
		return this;
	}

	public JsonResult<T> build() {
		return new JsonResult<T>(status, message, data, gson);
	}

	public static <T> JsonResult<T> build(T data) {
		JsonResultBuilder<T> builder = JsonResultBuilder.create();
		return builder.setData(data).build();
	}

	public static JsonResult<?> buildVoid() {
		return JsonResultBuilder.create().build();
	}

}
