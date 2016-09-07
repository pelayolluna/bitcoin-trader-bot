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

import com.pelayolluna.bitcointraderbot.utils.PairMarketVolume;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public final class OrderBook {

    /**
     * Configuration parameters.
     */
    private static final ResourceBundle RB_LITERALS
            = ResourceBundle.getBundle("literals");
    /**
     * Ask pair.
     */
    private PairMarketVolume<ArrayList<BigDecimal>, ArrayList<BigDecimal>> askPair;
    /**
     * Bid pair.
     */
    private PairMarketVolume<ArrayList<BigDecimal>, ArrayList<BigDecimal>> bidPair;

    /**
     * Public class constructor.
     */
    public OrderBook() {
    }

    /**
     * @return the askPair
     */
    public PairMarketVolume<ArrayList<BigDecimal>, ArrayList<BigDecimal>> getAskPair() {
        return askPair;
    }

    /**
     * @param askPair the askPair to set
     */
    public void setAskPair(PairMarketVolume<ArrayList<BigDecimal>, ArrayList<BigDecimal>> askPair) {
        this.askPair = askPair;
    }

    /**
     * @return the bidPair
     */
    public PairMarketVolume<ArrayList<BigDecimal>, ArrayList<BigDecimal>> getBidPair() {
        return bidPair;
    }

    /**
     * @param bidPair the bidPair to set
     */
    public void setBidPair(PairMarketVolume<ArrayList<BigDecimal>, ArrayList<BigDecimal>> bidPair) {
        this.bidPair = bidPair;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(RB_LITERALS.getString("asks"));
        sb.append(this.getAskPair().getMarket());
        sb.append("\n");
        sb.append(RB_LITERALS.getString("volumeAsks"));
        sb.append(this.getAskPair().getVolume());
        sb.append("\n");
        sb.append(RB_LITERALS.getString("bids"));
        sb.append(this.getBidPair().getMarket());
        sb.append("\n");
        sb.append(RB_LITERALS.getString("volumeBids"));
        sb.append(this.getBidPair().getVolume());

        return sb.toString();
    }

}
