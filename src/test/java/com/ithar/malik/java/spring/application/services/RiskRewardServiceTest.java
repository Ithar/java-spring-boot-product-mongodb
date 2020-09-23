package com.ithar.malik.java.spring.application.services;

import com.ithar.malik.java.spring.application.domain.Trade;
import org.junit.Test;

import static org.junit.Assert.*;

public class RiskRewardServiceTest {

    RiskRewardService riskRewardService = new RiskRewardService();

    // ###########
    // PIP RISK
    // ###########
    @Test
    public void calculatePipRisk_Buy1() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("buy");
        trade.setEntryPrice(1.28800);
        trade.setStopLossPrice(1.28545);

        // When
        int risk = riskRewardService.calculatePipRisk(trade);

        // Then
        assertEquals(26, risk);
    }

    @Test
    public void calculatePipRisk_Buy2() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("buy");
        trade.setEntryPrice(1.28800);
        trade.setStopLossPrice(1.28540);

        // When
        int risk = riskRewardService.calculatePipRisk(trade);

        // Then
        assertEquals(26, risk);
    }

    @Test
    public void calculatePipRisk_Buy3() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("buy");
        trade.setEntryPrice(1.28476);
        trade.setStopLossPrice(1.27280);

        // When
        int risk = riskRewardService.calculatePipRisk(trade);

        // Then
        assertEquals(119, risk);
    }

    @Test
    public void calculatePipRisk_Sell1() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("sell");
        trade.setEntryPrice(1.29405);
        trade.setStopLossPrice(1.29706);

        // When
        int risk = riskRewardService.calculatePipRisk(trade);

        // Then
        assertEquals(30, risk);
    }

    @Test
    public void calculatePipRisk_Sell2() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("sell");
        trade.setEntryPrice(1.29399);
        trade.setStopLossPrice(1.29699);

        // When
        int risk = riskRewardService.calculatePipRisk(trade);

        // Then
        assertEquals(30, risk);
    }

    // ###########
    // PIP REWARD
    // ###########
    @Test
    public void calculatePipReward_Buy1() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("buy");
        trade.setEntryPrice(1.28800);
        trade.setTakeProfitPrice(1.28545);

        // When
        int risk = riskRewardService.calculatePipReward(trade);

        // Then
        assertEquals(26, risk);
    }

    @Test
    public void calculatePipReward_Buy2() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("buy");
        trade.setEntryPrice(1.28800);
        trade.setTakeProfitPrice(1.28540);

        // When
        int risk = riskRewardService.calculatePipReward(trade);

        // Then
        assertEquals(26, risk);
    }

    @Test
    public void calculatePipReward_Buy3() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("buy");
        trade.setEntryPrice(1.28476);
        trade.setTakeProfitPrice(1.27280);

        // When
        int risk = riskRewardService.calculatePipReward(trade);

        // Then
        assertEquals(119, risk);
    }

    @Test
    public void calculatePipReward_Sell1() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("sell");
        trade.setEntryPrice(1.27261);
        trade.setTakeProfitPrice(1.26853);

        // When
        int risk = riskRewardService.calculatePipReward(trade);

        // Then
        assertEquals(41, risk);
    }

    @Test
    public void calculatePipReward_Sell2() {

        // Given
        Trade trade = new Trade();
        trade.setBuySell("sell");
        trade.setEntryPrice(1.28632);
        trade.setTakeProfitPrice(1.27347);

        // When
        int risk = riskRewardService.calculatePipReward(trade);

        // Then
        assertEquals(129, risk);
    }
}