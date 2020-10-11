package com.mall.api.config;

import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.ServletException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.beans.factory.annotation.Value;
////import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.code.kaptcha.servlet.KaptchaServlet;



/**
 * 开启Mvc,自动注入spring容器。 WebMvcConfigurerAdapter：配置视图解析器 （SpringBoot 2.0该方法过时，替代方法：implements WebMvcConfigurer extends WebMvcConfigurationSupport）
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean
 * 
 * @author xiangze
 *
 */
@Configuration
// 等价于<mvc:annotation-driven/>
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {
	// Spring容器
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 静态资源配置
	 * 
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
		//registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/baidu/work/image/upload/");
		//registry.addResourceHandler("/webapp/**").addResourceLocations("classpath:/webapp/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

	}

	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		return null;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {

	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

	}

	/**
	 * 定义默认的请求处理器
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}

	/**
	 * 创建viewResolver
	 * 
	 * @return
	 */
	@Bean(name = "viewResolver")
	public ViewResolver createViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 设置Spring 容器
		viewResolver.setApplicationContext(this.applicationContext);
		// 取消缓存
		viewResolver.setCache(false);
		// 设置解析的前缀
		viewResolver.setPrefix("/WEB-INF/html/");
		// 设置试图解析的后缀
		viewResolver.setSuffix(".html");
		return viewResolver;
	}


	@Value("${kaptcha.border}")
	private String border;

	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;

	@Value("${kaptcha.image.width}")
	private String width;

	@Value("${kaptcha.textproducer.char.string}")
	private String cString;

	@Value("${kaptcha.image.height}")
	private String height;

	@Value("${kaptcha.textproducer.font.size}")
	private String fsize;

	@Value("${kaptcha.noise.color}")
	private String nColor;

	@Value("${kaptcha.textproducer.char.length}")
	private String clength;

	@Value("${kaptcha.textproducer.font.names}")
	private String fnames;

	/**
	 * 由于web.xml不生效了，需要在这里配置Kaptcha验证码Servlet
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() throws ServletException {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/Kaptcha");
		servlet.addInitParameter("kaptcha.border", border);// 无边框
		servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor); // 字体颜色
		servlet.addInitParameter("kaptcha.image.width", width);// 图片宽度
		servlet.addInitParameter("kaptcha.textproducer.char.string", cString);// 使用哪些字符生成验证码
		servlet.addInitParameter("kaptcha.image.height", height);// 图片高度
		servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);// 字体大小
		servlet.addInitParameter("kaptcha.noise.color", nColor);// 干扰线的颜色
		servlet.addInitParameter("kaptcha.textproducer.char.length", clength);// 字符个数
		servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);// 字体
		return servlet;
	}
}
