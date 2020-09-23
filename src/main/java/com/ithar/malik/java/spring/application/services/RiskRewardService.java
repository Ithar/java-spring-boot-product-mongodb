package com.ithar.malik.java.spring.application.services;

import com.ithar.malik.java.spring.application.domain.Trade;
import com.ithar.malik.java.spring.application.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RiskRewardService {

    DecimalFormat df = new DecimalFormat(".0000");



    @Autowired
    public RiskRewardService() {

    }

    public void calculateRiskReward(Trade trade) {

        double risk = calculateRisk(trade);
        double reward = calculateReward(trade);
        double ratio = calculateRewardRatio(risk, reward);

        trade.setPipRisk(calculatePipRisk(trade));
        trade.setPipReward(calculatePipReward(trade));

        trade.setRisk(risk);
        trade.setReward(reward);
        trade.setRiskRewardRatio(ratio);
    }

    int calculatePipRisk(Trade trade) {

        int entry = removeLastDecimalAndPrune(trade.getEntryPrice());
        int stopLoss = removeLastDecimalAndPrune(trade.getStopLossPrice());

        if ("BUY".equals(trade.getBuySell().toUpperCase())) {
            return entry - stopLoss;
        } else {
            return stopLoss - entry;
        }
    }

    int calculatePipReward(Trade trade) {

        int entry = removeLastDecimalAndPrune(trade.getEntryPrice());
        int profit = removeLastDecimalAndPrune(trade.getTakeProfitPrice());

        if ("BUY".equals(trade.getBuySell().toUpperCase())) {
            return Math.abs(profit - entry);
        } else {
            return Math.abs(entry - profit);
        }
    }

    private double calculateRisk(Trade trade) {
        return 0;
    }

    private double calculateReward(Trade trade) {
        return 0;
    }

    private double calculateRewardRatio(double risk , double reward) {
        return 0;
    }

    private int removeLastDecimalAndPrune(double number) {
        df.setRoundingMode(RoundingMode.DOWN);
        String numStr = df.format(number);
        numStr = numStr.replace("0.", "");
        numStr = numStr.replace("1.", "");
        numStr = numStr.replaceFirst("^0+(?!$)", "");
        return Integer.parseInt(numStr);
    }


}
