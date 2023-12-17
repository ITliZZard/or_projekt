package fer.project.openapidoc;

import fer.project.response.ResponseDocs;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SpecService {

    public ResponseEntity<ResponseDocs> getOpenApiSpec() {
        /*HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        var resource = new ClassPathResource("static/OpenAPI/openapi.json");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
        Resource resource = resourceLoader.getResource("/static/OpenAPI/openapi.json");
        try {
            String message = "OpenAPI specification was successfully fetched.";
            String openApiContent = new String(Files.readAllBytes(Paths.get(resource.getURI())), "UTF-8");
            return new ResponseEntity<>
                    (new ResponseDocs(200, message, openApiContent), responseHeaders, HttpStatus.OK);
        } catch (IOException e) {
            String message = "OpenAPI specification was not found.";
            return new ResponseEntity<>
                    (new ResponseDocs(404, message, null), responseHeaders, HttpStatus.NOT_FOUND);
        }*/
        return null;
    }
}
