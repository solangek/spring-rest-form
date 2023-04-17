package hac.form;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/form")
public class FormController {

    /** GET /form/requestmap
     a GET controller that extract all request paramaters as a Map of (key,value)
     can be used in regular submit of form
     try for example: http://localhost:8080/form/requestmap?a=10&message=hello
     *
     * @param values
     * @return an empty HTTP response code 200
     */
    @GetMapping("/requestmap")
    public ResponseEntity getController(@RequestParam MultiValueMap<String, String> values) {
        System.out.println("Values:{}" + values);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    /** POST /form/request
     *  a POST conntroller that extract an object from the request (must be passed as JSON by client)
     * @param loginForm
     * @return an empty HTTP response code 200
     */
    @PostMapping(value = "/request")
    public ResponseEntity postController(@RequestBody LoginFormData loginForm) {
        System.out.println("POST received - serializing LoginForm: " + loginForm.getPassword() + " " + loginForm.getUsername());
        // authenticate(loginForm);
        // ResponseEntity is a Spring class to manipulate HTTP responses
        // see https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /** GET /form/request
     a GET controller that extract an object from the request (must be passed as JSON by client)
     *
     * @param loginForm
     * @return an empty HTTP response code 200
     */
    @GetMapping(value = "/request")
    public ResponseEntity getController(@RequestBody LoginFormData loginForm) {
        System.out.println("POST received - serializing LoginForm: " + loginForm.getPassword() + " " + loginForm.getUsername());
        // authenticate(loginForm);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * POST /form/response
     * @param loginForm
     * @return a JSON object (because of the @ResponseBody annotation) using the class ResponseTransfer
     */
    @PostMapping("/response")
    @ResponseBody
    public ResponseData postResponseController(@RequestBody LoginFormData loginForm) {
        System.out.println("POST received - serializing LoginForm: " + loginForm.getPassword() + " " + loginForm.getUsername());
        return new ResponseData("Thanks For Posting!!!");
    }

    /** POST /form/content
     *  you can also specify the type of media returned - default is JSON with  @ResponseBody annotation
     * @param loginForm
     * @return a JSON object (because of the @ResponseBody annotation + produces =) using the class ResponseTransfer
     */
    @PostMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData postResponseJsonContent(@RequestBody LoginFormData loginForm) {
        System.out.println("POST received - serializing LoginForm: " + loginForm.getPassword() + " " + loginForm.getUsername());
        return new ResponseData("JSON Content!");
    }

    /** POST /form/content returning XML
     * because we specify the type of media returned - default is JSON with  @ResponseBody annotation
     * @param loginFormData
     * @return XML content
     */
    @PostMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseData postResponseXmlContent(@RequestBody LoginFormData loginFormData) {
        System.out.println("POST received - serializing LoginForm: " + loginFormData.getPassword() + " " + loginFormData.getUsername());
        return new ResponseData("XML Content!");
    }
}
