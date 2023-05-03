package com.example.SeatingManagement.Controller;

import com.example.SeatingManagement.Entity.SwiftData;
import com.example.SeatingManagement.Repository.SwiftRepository;
import com.example.SeatingManagement.utils.EmployeeDataFromAccolite;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/test")
public class EmployeeDataController {

    @Autowired
    private SwiftRepository swiftRepository;

        @GetMapping("/employees")
        public Map<String, String> getEmployees() throws IOException {
            String url = "https://eolblxg3qe42qa0.m.pipedream.net/";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String username = "au_master_prod_user";
            String password = "bScK@LMjTsVVa#E2PiUQrfEE";
            String credentials = username + ":" + password;
            byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
            byte[] encodedBytes = Base64.encodeBase64(credentialsBytes);
            String encodedCredentials = new String(encodedBytes, StandardCharsets.UTF_8);

            headers.add("Authorization", "Basic " + encodedCredentials);

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("api_key", "6hhyw+gpg%xgl8oa4863wc6*!7ffpqm1rvtjcm@*so#vnujyvufed7!u%lrje4ikrkcggmmjsj$qie+b2hdyfk|inetl!uvj4wipzownl0vq5|%q|#q$ntx^cdiknl2");
            requestBody.put("dataset_key", "fgjc%xnezu^udptat8ibfsn+bw1mer%dv%ao?hnch+r9o!e$wzht@uj+ea7jgbbzfn8ptcp*qiw6oyt?pcmi!azvtckz+unyyqslsowakrvcbwv3v#i|ltqaf!920jej");

            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseEntity.getBody(), new TypeReference<Map<String, Object>>(){});

            List<EmployeeDataFromAccolite> employeeDataList = objectMapper.convertValue(responseMap.get("employee_data"), new TypeReference<List<EmployeeDataFromAccolite>>(){});
            Map<String, String> employeeMap = new HashMap<>();
            for(EmployeeDataFromAccolite employeeData : employeeDataList) {
                SwiftData swiftData=new SwiftData();
                swiftData.setEmail(employeeData.getCompany_email_id());
                swiftData.setEmpId(employeeData.getEmployee_id());
                this.swiftRepository.save(swiftData);
                employeeMap.put(employeeData.getCompany_email_id(), employeeData.getEmployee_id());
            }
            return employeeMap;
        }
    }


