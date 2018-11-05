package club.ihere.wechat.common.json;

import club.ihere.common.util.current.JsonUtil;
import com.google.gson.Gson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class JsonResult<T> {

	@ApiModelProperty("响应状态")
	private boolean status;

	@ApiModelProperty("系统消息")
	private String message;

	@ApiModelProperty(value = "数据",notes = "只有status为true时才返回数据,当出错时返回message")
	private T data;

	@ApiModelProperty(hidden = true)
	@JsonUtil.Exclusion
	private Gson gson;

	public JsonResult(boolean status, String message, T data, Gson gson) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.gson = gson;
	}

	public JsonResult(boolean status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public JsonResult(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}
}
