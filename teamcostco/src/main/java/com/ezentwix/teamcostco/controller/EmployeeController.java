package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.config.BCryptUtils;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.dto.filter.EmployeeFilterDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.EmailService;
import com.ezentwix.teamcostco.service.EmployeeDetailService;
import com.ezentwix.teamcostco.service.EmployeeFixService;
import com.ezentwix.teamcostco.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeDetailService employeeDetailService;
    private final EmployeeFixService employeeFixService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @GetMapping("")
    public String showEmployee(Model model,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @ModelAttribute EmployeeFilterDTO empFilterDTO) {

        employeeService.configureModel(model);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(empFilterDTO, Map.class);
        PaginationResult<EmployeeDTO> result = employeeService.getPage(query, page, size, map);

        List<EmployeeDTO> empList = employeeService.getEmpList();

        model.addAttribute("empList", empList);
        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @GetMapping("/detail/{emp_id}")
    public String getMethodName(
            @PathVariable(name = "emp_id") Integer emp_id,
            HttpSession session, Model model) {
        if (emp_id == null) {
            emp_id = 0;
        }

        EmployeeDTO empDTO = employeeDetailService.getOne(emp_id);

        int age = employeeDetailService.calculateAge(empDTO.getBirthday());

        model.addAttribute("age", age);

        employeeDetailService.configureModel(model);
        model.addAttribute("empDetail", empDTO);
        return "index";
    }

    @GetMapping("/modify/{emp_id}")
    public String empFix(@PathVariable Integer emp_id, Model model) {
        employeeFixService.configureModel(model);

        model.addAttribute("getOne", employeeFixService.getOne(emp_id));

        return "index";
    }

    @PostMapping("/modify")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> empFix(
            @ModelAttribute EmployeeDTO empDTO,
            @RequestParam Integer emp_id,
            Model model) {

        Map<String, Object> response = new HashMap<>();

        // 기존 직원 정보를 가져옴
        EmployeeDTO existingEmployee = employeeFixService.getOne(emp_id);

        // 비밀번호 해시화 및 정보 업데이트
        empDTO.setEmp_id(emp_id);
        empDTO.setLogin_pw(BCryptUtils.hashPassword(empDTO.getLogin_pw()));
        employeeFixService.fix(empDTO);

        // 이메일이 변경되었는지 확인
        boolean emailChanged = !existingEmployee.getEmp_email().equals(empDTO.getEmp_email());

        if (emailChanged) {
            // 새로운 토큰 생성
            String newToken = UUID.randomUUID().toString();

            // 이메일 인증 토큰 업데이트
            employeeFixService.updateEmailVerificationToken(existingEmployee.getLogin_id(), newToken);
            employeeFixService.email(empDTO.getLogin_id());

            // 이메일 전송
            emailService.sendnewToken(empDTO.getEmp_email(), newToken);

            response.put("success", true);
            response.put("message", "이메일이 변경되어 재인증이 필요합니다.");

        } else {
            response.put("success", false);
            response.put("message", "회원정보가 변경되었습니다.");
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/newtoken")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verifyEmail(
        @RequestParam("token") String token) {

        Map<String, Object> response = new HashMap();

        if (employeeFixService.verifyEmail(token)) {
            response.put("success", true);
            response.put("message", "이메일 인증이 완료되었습니다!");
        } else {
            response.put("success", false);
            response.put("message", "인증 실패. 유효하지 않은 토큰입니다.");
        }
        
        return ResponseEntity.ok(response);
    }
}
