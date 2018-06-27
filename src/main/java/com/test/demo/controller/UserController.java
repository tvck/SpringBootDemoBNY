package com.test.demo.controller;

import com.test.demo.model.Address;
import com.test.demo.model.Company;
import com.test.demo.model.Geo;
import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        //return userRepository.findById(id).map((e)->{return e;}).orElse(null);
        String sql="SELECT * from users,geo where users.id=geo.id and users.id="+id;
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setWebsite(resultSet.getString("website"));
                Address address=new Address();
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setSuite(resultSet.getString("suite"));
                address.setZipcode(resultSet.getString("zipcode"));
                Geo geo=new Geo();
                geo.setLat(resultSet.getDouble("lat"));
                geo.setLat(resultSet.getDouble("lng"));
                address.setGeo(geo);
                user.setAddress(address);
                Company company=new Company();
                company.setBs(resultSet.getString("bs"));
                company.setCatchPhrase(resultSet.getString("catchPhrase"));
                company.setName(resultSet.getString("company_name"));
                user.setCompany(company);
                return user;
            }
        });

    }
}
