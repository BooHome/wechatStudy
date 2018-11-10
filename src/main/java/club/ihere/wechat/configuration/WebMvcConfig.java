package club.ihere.wechat.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements  WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        // registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
        // registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        // 文件上传虚拟路径
        // registry.addResourceHandler("/upload/imgs/**").addResourceLocations("file:D://data//mblog//");

        //其他静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/public/");
    }
}
