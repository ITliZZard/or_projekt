package fer.project.pages;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(path="/")
public class PagesController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "datatable", method = RequestMethod.GET)
    public String getDatatablePage() {
        return "datatable";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String getUserPage(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("email", principal.getAttribute("email"));
        return "user";
    }

    @RequestMapping(value = "logo", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getLogo() throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/images/book_logo.png");
        byte[] bytes = IOUtils.toByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

}
