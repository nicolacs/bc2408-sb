package com.bootcamp.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.model.Cat;
import com.bootcamp.demo.model.UserDTO;
import com.bootcamp.demo.util.Scheme;
import com.bootcamp.demo.util.Url;

@Service  // 如果冇@Service,  Controller個度就@Autowised 唔到
          // Component annotation -> bean
public class JPHServiceImpl implements JPHService {

  @Autowired
  @Qualifier (value = "JPHRestTemplate") // inject bean by specific bean name
  private RestTemplate restTemplate;

  // !!! @Value (inject from yml) is similar to @Autowired (inject from Spring Context)
  // Both of them has to be executed before server start
  @Autowired
  private Cat cat; // Vincent

  @Value("${api.jph.domain}")
  private String jphDomain;

  @Value("${api.jph.endpoints.users}")
  private String usersEndpoint;

    @Override
    public List<UserDTO> getUsers() {
      //!!! 舊方法
      //UserDTO[] users = new RestTemplate().getForObject(
      //  "https://jsonplaceholder.typicode.com/users", UserDTO[].class);
      
    // ! You can use UriComponentBuilder directly
    String url = Url.builder() //
        .scheme(Scheme.HTTPS) //
        .domain(this.jphDomain) //
        .endpoint(this.usersEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);

    UserDTO[] users = this.restTemplate.getForObject(url, UserDTO[].class); // 10 ms
    return List.of(users);
    }
  }
