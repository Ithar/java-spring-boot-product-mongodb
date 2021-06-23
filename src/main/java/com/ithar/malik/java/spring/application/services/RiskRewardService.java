package com.ithar.malik.java.spring.application.services;

import com.ithar.malik.java.spring.application.domain.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
public class RiskRewardService {

    private static final DecimalFormat df = new DecimalFormat(".0000");
    private static final DecimalFormat dfc = new DecimalFormat("0.00");

    @Autowired
    public RiskRewardService() {

    }

    public void calculateRiskReward(Trade trade) {

        double commission = calculateCommission(trade);
        int pipRisk = calculatePipRisk(trade);
        int pipReward = calculatePipReward(trade);
        double risk = calculateRisk(trade.getLot(), pipRisk, commission);
        double reward = calculateReward(trade.getLot(), pipReward, commission);

        double ratio = calculateRewardRatio(risk, reward);

        trade.setCommission(commission);
        trade.setPipRisk(pipRisk);
        trade.setPipReward(pipReward);
        trade.setRisk(risk);
        trade.setReward(reward);
        trade.setRiskRewardRatio(ratio);
    }

    double calculateCommission(Trade trade) {
        dfc.setRoundingMode(RoundingMode.UP);
        return Double.parseDouble(dfc.format(trade.getLot() * 5));
    }

    int calculatePipRisk(Trade trade) {

        int entry = removeLastDecimalAndPrune(trade.getEntryPrice());
        int stopLoss = removeLastDecimalAndPrune(trade.getStopLossPrice());

        if ("BUY".equals(trade.getType().toUpperCase())) {
            return entry - stopLoss;
        } else {
            return stopLoss - entry;
        }
    }

    int calculatePipReward(Trade trade) {

        int entry = removeLastDecimalAndPrune(trade.getEntryPrice());
        int profit = removeLastDecimalAndPrune(trade.getTakeProfitPrice());

        if ("BUY".equals(trade.getType().toUpperCase())) {
            return Math.abs(profit - entry);
        } else {
            return Math.abs(entry - profit);
        }
    }

    double calculateRisk(double lot, int pipRisk, double commission) {
        dfc.setRoundingMode(RoundingMode.DOWN);

        double lotMultiple = lot * 10;
        double res = Double.parseDouble(dfc.format((lotMultiple * pipRisk)));

        return res + commission;
    }

    double calculateReward(double lot, int pipReward, double commission) {
        dfc.setRoundingMode(RoundingMode.DOWN);

        double lotMultiple = lot * 10;
        double res = Double.parseDouble(dfc.format((lotMultiple * pipReward)));

        return res + commission;
    }

    double calculateRewardRatio(double risk , double reward) {
        dfc.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(dfc.format(reward/risk));
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
