package club.ihere.wechat.common.json;

import club.ihere.common.constant.Constant;
import club.ihere.common.util.current.JsonUtil;
import com.google.gson.JsonIOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @see org.springframework.http.converter.json.GsonHttpMessageConverter GsonHttpMessageConverter
 * @author
 */
public class JsonResultHttpMessageConverter extends AbstractGenericHttpMessageConverter<JsonResult<?>> {

	public static final Charset DEFAULT_CHARSET = Constant.CHARSET_UTF8;

	public JsonResultHttpMessageConverter() {
		super(MediaType.APPLICATION_JSON, new MediaType("application", "*+json"));
		this.setDefaultCharset(DEFAULT_CHARSET);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return JsonResult.class.isAssignableFrom(clazz) && canWrite(mediaType);
	}

	@Override
	protected void writeInternal(JsonResult<?> result, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		Charset charset = getCharset(outputMessage.getHeaders());
		OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), charset);
		try {
			String value;
			if (result == null || result.getGson() == null) {
				value = JsonUtil.toJson(result);
			} else {
				value = result.getGson().toJson(result);
			}
			writer.write(value);
			writer.close();
		} catch (JsonIOException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}

	private Charset getCharset(HttpHeaders headers) {
		if (headers == null || headers.getContentType() == null || headers.getContentType().getCharset() == null) {
			return DEFAULT_CHARSET;
		}
		return headers.getContentType().getCharset();
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected JsonResult<?> readInternal(Class<? extends JsonResult<?>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JsonResult<?> read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		throw new UnsupportedOperationException();
	}

}
