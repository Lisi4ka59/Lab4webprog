package com.lisi4ka.lab4webdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lisi4ka.lab4webdb.db.DotRepository;
import com.lisi4ka.lab4webdb.db.RegUserRepository;
import com.lisi4ka.lab4webdb.utils.Clear;
import com.lisi4ka.lab4webdb.utils.GraphicsService;
import com.lisi4ka.lab4webdb.utils.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.lisi4ka.lab4webdb.utils.Check.checkToken;
import static com.lisi4ka.lab4webdb.utils.CurrentTime.jsonTimeNow;
import static com.lisi4ka.lab4webdb.utils.LogOut.logOutHome;
import static com.lisi4ka.lab4webdb.utils.Register.registration;
import static com.lisi4ka.lab4webdb.utils.SignIn.signIn;

@CrossOrigin(maxAge = 3600)
@SpringBootApplication
@RestController
public class Lab4webWithDbApplication {
	@Autowired
	public DotRepository dotRepository;
	@Autowired
	public RegUserRepository regUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab4webWithDbApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<ByteArrayResource> image(@RequestParam(value = "x", defaultValue = "1") String x, @RequestParam(value = "y", defaultValue = "1") String y, @RequestParam(value = "r", defaultValue = "1") String r, @RequestParam(value = "rnd", defaultValue = "0.5") String rnd, @RequestParam(value = "userId", defaultValue = "0") String userId) throws IOException {
		final ByteArrayResource inputStream = new ByteArrayResource(GraphicsService.drawGraphic(x, y, r, rnd, userId, dotRepository, regUserRepository));
		return ResponseEntity
				.status(HttpStatus.OK)
				.contentLength(inputStream.contentLength())
				.body( inputStream);
	}

	@GetMapping(value = "/table", produces = MediaType.APPLICATION_JSON_VALUE)
	public String table(@RequestParam(value = "userId", defaultValue = "0") String userId) throws JsonProcessingException {
		return TableService.jsonTable(userId, dotRepository, regUserRepository);
	}

	@GetMapping(value = "/sign_in")
	public String isSignedIn(@RequestParam(value = "login", defaultValue = "") String login, @RequestParam(value = "passwd", defaultValue = "0") String password, @RequestParam(value = "token", defaultValue = "0") long token) {
		return "[{\"result\":\"" + signIn(login, password, token, regUserRepository) + "\"}]";
	}

	@GetMapping(value = "/register")
	public String isRegister(@RequestParam(value = "login", defaultValue = "") String login, @RequestParam(value = "passwd", defaultValue = "0") String password, @RequestParam(value = "repeat_passwd", defaultValue = "0") String repeatPassword, @RequestParam(value = "token", defaultValue = "0") long token) {
		return "[{\"result\":\"" + registration(login, password, repeatPassword, token, regUserRepository) + "\"}]";
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
	@GetMapping(value = "/current_date", produces = MediaType.APPLICATION_JSON_VALUE)
	public String date() throws JsonProcessingException {
        return jsonTimeNow();
	}

	@GetMapping(value = "/check")
	public String isTokenValid(@RequestParam(value = "token", defaultValue = "0") String token) {
		return "[{\"result\":\"" + checkToken(token) + "\"}]";
	}
	@GetMapping(value = "/clear")
	public void isClearTable(@RequestParam(value = "token", defaultValue = "0") String token) {
		Clear clear = new Clear();
		clear.clear(token, dotRepository, regUserRepository);
	}
	@GetMapping(value = "/log_out")
	public void logOut(@RequestParam(value = "token", defaultValue = "0") String token) {
		logOutHome(token);
	}
}
