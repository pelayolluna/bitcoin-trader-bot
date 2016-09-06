/*
 * Copyright 2016 Pelayo Jose Lluna Gonzalez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pelayolluna.bitcointraderbot.indicators;

import com.pelayolluna.bitcointraderbot.beans.BtcPrice;
import com.pelayolluna.bitcointraderbot.datamanagers.BitstampData;
import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public final class PivotsIndicatorImpl implements Indicable {

    private final ResourceBundle rbLiterals = ResourceBundle.getBundle("literals");

    private PivotsIndicatorImpl pivotIndicator;
    private static final BtcPrice BTC_PRICE = BitstampData.getTickerData();
    private static final BigDecimal DIV = new BigDecimal(3);
    private static final BigDecimal MUL = new BigDecimal(2);

    private BigDecimal pivotPoint;
    private BigDecimal firstResistence;
    private BigDecimal firstSupport;
    private BigDecimal secondResistence;
    private BigDecimal secondSupport;
    private BigDecimal thirdResistence;
    private BigDecimal thirdSupport;

    public PivotsIndicatorImpl() {
    }

    @Override
    public Indicable computeAll() {
        pivotIndicator = new PivotsIndicatorImpl();

        this.pivotPoint = this.getPivotPoint();
        this.firstResistence = this.getFirstResistence();
        this.firstSupport = this.getFirstSupport();
        this.secondResistence = this.getSecondResistence();
        this.secondSupport = this.getSecondSupport();
        this.thirdResistence = this.getThirdResistence();
        this.thirdSupport = this.getThirdSupport();

        return pivotIndicator;
    }

    /**
     * @return the pivotPoint
     */
    public BigDecimal getPivotPoint() {
        return (BTC_PRICE.getHighPrice()
                .add(BTC_PRICE.getLowPrice()
                        .add(BTC_PRICE.getOpenPrice()))).divide(DIV);
    }

    /**
     * @param pivotPoint the pivotPoint to set
     */
    public void setPivotPoint(BigDecimal pivotPoint) {
        this.pivotPoint = pivotPoint;
    }

    /**
     * @return the firstResistence
     */
    public BigDecimal getFirstResistence() {
        return (this.pivotPoint
                .multiply(MUL))
                .subtract(BTC_PRICE.getLowPrice());
    }

    /**
     * @param firstResistence the firstResistence to set
     */
    public void setFirstResistence(BigDecimal firstResistence) {
        this.firstResistence = firstResistence;
    }

    /**
     * @return the firstSupport
     */
    public BigDecimal getFirstSupport() {
        return (this.pivotPoint
                .multiply(MUL))
                .subtract(BTC_PRICE.getHighPrice());
    }

    /**
     * @param firstSupport the firstSupport to set
     */
    public void setFirstSupport(BigDecimal firstSupport) {
        this.firstSupport = firstSupport;
    }

    /**
     * @return the secondResistence
     */
    public BigDecimal getSecondResistence() {
        return this.pivotPoint
                .add(BTC_PRICE.getHighPrice()
                        .subtract(BTC_PRICE.getLowPrice()));
    }

    /**
     * @param secondResistence the secondResistence to set
     */
    public void setSecondResistence(BigDecimal secondResistence) {
        this.secondResistence = secondResistence;
    }

    /**
     * @return the secondSupport
     */
    public BigDecimal getSecondSupport() {
        return this.pivotPoint
                .subtract(BTC_PRICE.getHighPrice()
                        .subtract(BTC_PRICE.getLowPrice()));
    }

    /**
     * @param secondSupport the secondSupport to set
     */
    public void setSecondSupport(BigDecimal secondSupport) {
        this.secondSupport = secondSupport;
    }

    /**
     * @return the thirdResistence
     */
    public BigDecimal getThirdResistence() {
        return BTC_PRICE.getHighPrice()
                .add(MUL.multiply(this.pivotPoint
                                .subtract(BTC_PRICE.getLowPrice())));
    }

    /**
     * @param thirdResistence the thirdResistence to set
     */
    public void setThirdResistence(BigDecimal thirdResistence) {
        this.thirdResistence = thirdResistence;
    }

    /**
     * @return the thirdSupport
     */
    public BigDecimal getThirdSupport() {
        return BTC_PRICE.getLowPrice()
                .subtract(MUL.multiply(BTC_PRICE.getHighPrice()
                                .subtract(this.pivotPoint)));
    }

    /**
     * @param thirdSupport the thirdSupport to set
     */
    public void setThirdSupport(BigDecimal thirdSupport) {
        this.thirdSupport = thirdSupport;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        this.computeAll();
        sb.append("\n");
        sb.append(rbLiterals.getString("pivotPoint")).append(getPivotPoint());
        sb.append("\n");
        sb.append(rbLiterals.getString("firstResistance")).append(getFirstResistence());
        sb.append("\n");
        sb.append(rbLiterals.getString("firstSupport")).append(getFirstSupport());
        sb.append("\n");
        sb.append(rbLiterals.getString("secondResistance")).append(getSecondResistence());
        sb.append("\n");
        sb.append(rbLiterals.getString("secondSupport")).append(getSecondSupport());
        sb.append("\n");
        sb.append(rbLiterals.getString("thirdResistance")).append(getThirdResistence());
        sb.append("\n");
        sb.append(rbLiterals.getString("thirdSupport")).append(getThirdSupport());
        sb.append("\n");

        return sb.toString();
    }

}
