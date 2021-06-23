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
        trade.setType("buy");
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
        trade.setType("buy");
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
        trade.setType("buy");
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
        trade.setType("sell");
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
        trade.setType("sell");
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
        trade.setType("buy");
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
        trade.setType("buy");
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
        trade.setType("buy");
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
        trade.setType("sell");
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
        trade.setType("sell");
        trade.setEntryPrice(1.28632);
        trade.setTakeProfitPrice(1.27347);

        // When
        int risk = riskRewardService.calculatePipReward(trade);

        // Then
        assertEquals(129, risk);
    }

    // #############
    // Commission
    // #############
    @Test
    public void calculateCommission_0() {

        // Given
        Trade trade = new Trade();
        trade.setLot(0.01);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(0.05, commission, 0);
    }

    @Test
    public void calculateCommission_1() {

        // Given
        Trade trade = new Trade();
        trade.setLot(0.10);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(0.5, commission, 0);
    }

    @Test
    public void calculateCommission_2() {

        // Given
        Trade trade = new Trade();
        trade.setLot(0.50);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(2.5, commission, 0);
    }

    @Test
    public void calculateCommission_3() {

        // Given
        Trade trade = new Trade();
        trade.setLot(0.70);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(3.5, commission, 0);
    }
    @Test
    public void calculateCommission_4() {

        // Given
        Trade trade = new Trade();
        trade.setLot(1.14);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(5.70, commission, 0);
    }

    @Test
    public void calculateCommission_5() {

        // Given
        Trade trade = new Trade();
        trade.setLot(1.14);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(5.70, commission, 0);
    }

    @Test
    public void calculateCommission_6() {

        // Given
        Trade trade = new Trade();
        trade.setLot(5.0);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(25, commission, 0);
    }

    @Test
    public void calculateCommission_7() {

        // Given
        Trade trade = new Trade();
        trade.setLot(0.51);

        // When
        double commission = riskRewardService.calculateCommission(trade);

        // Then
        assertEquals(2.55, commission, 0);
    }

    // #############
    // Risk Cost
    // #############
    @Test
    public void calculateRisk_1() {

        // Given
        double lot= 0.50;
        int pipRisk = 10;
        double commission = 2.5;

        // When
        double risk = riskRewardService.calculateRisk(lot, pipRisk, commission);

        // Then
        assertEquals(52.5, risk, 0);
    }

    // #############
    // Reward
    // #############
    @Test
    public void calculateReward_1() {

        // Given
        double lot= 0.10;
        int pipReward = 20;
        double commission = 2.5;

        // When
        double risk = riskRewardService.calculateReward(lot, pipReward, commission);

        // Then
        assertEquals(22.5, risk, 0);
    }
}