package com.demo.simplecalculator.backend.controllers;

import com.demo.simplecalculator.backend.models.Equation;
import com.demo.simplecalculator.backend.service.CalculatorHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class CalculatorRestApiControllerTest {
    
    final static String add = Equation.SignSymbols.get(Equation.Sign.PLUS);
    final static String sub = Equation.SignSymbols.get(Equation.Sign.MINUS);
    final static String mul = Equation.SignSymbols.get(Equation.Sign.MULTIPLICATION);
    final static String div = Equation.SignSymbols.get(Equation.Sign.DIVISION);
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    private CalculatorHistoryService calculatorHistoryService;
    
    @BeforeEach
    void setup(){
        when(calculatorHistoryService.save(any(Equation.class))).thenAnswer(i -> {
            Equation eq = (Equation) i.getArguments()[0];
            eq.setId(1);
            return eq;
        });
    }
    
    @Test
    void getEquationSolution() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.number1").value(12));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.number2").value(3));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.signSymbol").value(add));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(15));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",sub,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(9));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",mul,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(36));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",div,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(4));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","-12",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(-9));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","-12",sub,"-3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(-9));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","-12",mul,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(-36));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","-12",div,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.result").value(-4));
    }
    
    @Test
    void getEquationSolutionFiltersInvalidInput() throws Exception {
        // filter block
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","A",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}",null,add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12","5","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",null,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12","","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"?")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,null)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","12",add,"")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","2,2",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}",".2",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","2.",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","2.2.",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","2-",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","2","•","3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","3.5",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","3",add,"3.0")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotAcceptable()).andReturn();
        
        // filter pass
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","3",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
       
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","-3",add,"3")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","3",add,"1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","1",sub,"1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","1",mul,"1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/solve/{number1}/{sign}/{number2}","1",div,"1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
    }
    
    @Test
    void getHistory() throws JsonProcessingException, Exception{
        final int pageSize = CalculatorHistoryService.PAGE_SIZE;
        final int testPages = 3;
        
        List<Equation> equationList = new ArrayList();
        for ( int i = 0; i < testPages * pageSize; i++ ) {
            equationList.add(new Equation(100, 20, 50, Equation.Sign.PLUS, 70, null));
        }
        
        when(calculatorHistoryService.getHistoryByPage(any(Integer.class), nullable(String.class))).thenAnswer(i -> {
            Integer page = (Integer) i.getArguments()[0];
            if ( page > testPages - 1 ) {
                return new ArrayList();
            }
            return equationList.subList(pageSize * page, pageSize * page + pageSize);
        });
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(pageSize)))
            .andExpect(jsonPath("$[0].number1").value(20))
            .andExpect(jsonPath("$[0].result").value(70))
            .andExpect(jsonPath("$[0].id").value(100));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory/0")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(pageSize)))
            .andExpect(jsonPath("$[0].number1").value(20))
            .andExpect(jsonPath("$[0].result").value(70))
            .andExpect(jsonPath("$[0].id").value(100));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(pageSize)))
            .andExpect(jsonPath("$[0].number1").value(20))
            .andExpect(jsonPath("$[0].result").value(70))
            .andExpect(jsonPath("$[0].id").value(100));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory/6")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(0)));
    }
    
    @Test
    void ensureClientTokenSecurity() throws Exception {
        
        when(calculatorHistoryService.getHistoryByPage(any(Integer.class), nullable(String.class))).thenAnswer(i -> {
            String clientToken = (String) i.getArguments()[1];
            if ( "TOKEN-TEST-12345".equals(clientToken) ) {
                return new ArrayList(){{ add(new Equation(123,456,Equation.Sign.PLUS,579)); }};
            } else if ( "TOKEN-TEST-67890".equals(clientToken) ) {
                return new ArrayList(){{ add(new Equation(678,90,Equation.Sign.PLUS,768)); }};
            }  
            return new ArrayList(){{ add(new Equation(0,0,Equation.Sign.PLUS,0)); }};
        });
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory")
            .contentType(MediaType.APPLICATION_JSON)
            .header("client-token", "TOKEN-TEST-12345")
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].number1").value(123));
                
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory")
            .contentType(MediaType.APPLICATION_JSON)
            .header("client-token", "TOKEN-TEST-67890")
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].number1").value(678));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getHistory")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].number1").value(0));
    }
    
}
