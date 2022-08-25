package org.irbis.parser.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .messageConverters(new HttpMessageConverter<String>() {

                    @Override
                    public boolean canRead(Class<?> clazz, MediaType mediaType) {
                        return true;
                    }

                    @Override
                    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                        return true;
                    }

                    @Override
                    public List<MediaType> getSupportedMediaTypes() {
                        return List.of(MediaType.TEXT_HTML);
                    }

                    @Override
                    public String read(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                        byte[] bytes = inputMessage.getBody().readAllBytes();
                        return new String(bytes, Charset.forName("windows-1251"));
                    }

                    @Override
                    public void write(String s, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

                    }
                })
                .build();
    }
}
