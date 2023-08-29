package com.demo.simplecalculator.backend.service;

import com.demo.simplecalculator.backend.models.Equation;
import com.demo.simplecalculator.backend.repository.CalculatorHistoryRepository;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CalculatorHistoryService {
    
    final public static int PAGE_SIZE = 1;

    private CalculatorHistoryRepository calculatorHistoryRepository;
    
    public CalculatorHistoryService(CalculatorHistoryRepository calculatorHistoryRepository) {
        this.calculatorHistoryRepository = calculatorHistoryRepository;
    }
    
    public List<Equation> getHistory(){
        return this.getHistoryByPage(0, null);
    }
    
    public List<Equation> getHistory(String clientToken){
        return this.getHistoryByPage(0, clientToken);
    }
    
    public List<Equation> getHistoryByPage(int pageOffset){
        return this.getHistoryByPage(pageOffset, null);
    }
    
    public List<Equation> getHistoryByPage(int pageOffset, String clientToken){
        Pageable pageable = PageRequest.of(pageOffset, PAGE_SIZE, Sort.by("id").descending());
        return calculatorHistoryRepository.findByClientToken(clientToken, pageable).getContent();
    }
    
    public Equation save(Equation equation) {
        return calculatorHistoryRepository.save(equation);
    }
    
}
