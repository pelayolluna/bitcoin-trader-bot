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
package com.pelayolluna.bitcointraderbot.beans;

import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public final class BtcPrice {

    /**
     * Configuration parameters.
     */
    private static final ResourceBundle RB_LITERALS
            = ResourceBundle.getBundle("literals");
    /**
     * Bitcoin last price.
     */
    private BigDecimal lastPrice;
    /**
     * Bitcoin highest price.
     */
    private BigDecimal highPrice;
    /**
     * Bitcoin lowest price.
     */
    private BigDecimal lowPrice;
    /**
     * Bitcoin bid price.
     */
    private BigDecimal bidPrice;
    /**
     * Bitcoin ask price.
     */
    private BigDecimal askPrice;
    /**
     * Bitcoin open price.
     */
    private BigDecimal openPrice;

    /**
     * @return the lastPrice
     */
    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    /**
     * @param lastPrice the lastPrice to set
     */
    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    /**
     * @return the highPrice
     */
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    /**
     * @param highPrice the highPrice to set
     */
    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    /**
     * @return the lowPrice
     */
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    /**
     * @param lowPrice the lowPrice to set
     */
    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * @return the bidPrice
     */
    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    /**
     * @param bidPrice the bidPrice to set
     */
    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * @return the askPrice
     */
    public BigDecimal getAskPrice() {
        return askPrice;
    }

    /**
     * @param askPrice the askPrice to set
     */
    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    /**
     * @return the openPrice
     */
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    /**
     * @param openPrice the openPrice to set
     */
    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(RB_LITERALS.getString("lastPrice"));
        sb.append(lastPrice);
        sb.append(", ");
        sb.append(RB_LITERALS.getString("highPrice"));
        sb.append(highPrice);
        sb.append(", ");
        sb.append(RB_LITERALS.getString("lowPrice"));
        sb.append(lowPrice);
        sb.append(", ");
        sb.append(RB_LITERALS.getString("bidPrice"));
        sb.append(bidPrice);
        sb.append(", ");
        sb.append(RB_LITERALS.getString("askPrice"));
        sb.append(askPrice);
        return sb.toString();
    }

}
