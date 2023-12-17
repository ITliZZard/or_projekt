package fer.project.openapidoc;

import fer.project.author.AuthorService;
import fer.project.response.Response;
import fer.project.response.ResponseDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(path="api-docs")
public class SpecController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public SpecController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping
    public String getOpenApiSpec() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        Resource resource = resourceLoader.getResource("classpath:/static/OpenAPI/openapi.json");
        try {
            String message = "OpenAPI specification was successfully fetched.";
            String openApiContent = new String(Files.readAllBytes(Paths.get(resource.getURI())), "UTF-8");
            //return new ResponseEntity<>
                    //(new ResponseDocs(200, message, openApiContent), responseHeaders, HttpStatus.OK);
            return openApiContent;
        } catch (IOException e) {
            String message = "OpenAPI specification was not found.";
            return message;
            //return new ResponseEntity<>
                    //(new ResponseDocs(404, message, null), responseHeaders, HttpStatus.NOT_FOUND);
        }

    }
}
