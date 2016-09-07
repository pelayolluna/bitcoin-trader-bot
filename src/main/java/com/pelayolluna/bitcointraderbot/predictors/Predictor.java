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
package com.pelayolluna.bitcointraderbot.predictors;

import com.pelayolluna.bitcointraderbot.beans.BtcPrice;
import com.pelayolluna.bitcointraderbot.datamanagers.BitstampData;
import com.pelayolluna.bitcointraderbot.indicators.PivotsIndicatorImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public class Predictor implements Runnable {

    /**
     * Pivot indicator values.
     */
    private static final PivotsIndicatorImpl PIVOT_INDICATOR
            = new PivotsIndicatorImpl();
    /**
     * Historic values.
     */
    private static final List<BtcPrice> HISTORIC = new ArrayList<>();
    /**
     * Bitcoin prices.
     */
    private BtcPrice btcPrice;

    /**
     * Public class constructor.
     */
    public Predictor() {
    }

    @Override
    public final void run() {
        final BigDecimal firstResistance = PIVOT_INDICATOR.getFirstResistence();

        while (true) {
            try {
                btcPrice = BitstampData.getTickerData();
                HISTORIC.add(btcPrice);

                for (BtcPrice HISTORIC1 : HISTORIC) {
                    System.out.println(HISTORIC1);
                }

                for (BtcPrice price : HISTORIC) {
                    if (price.getLastPrice().compareTo(firstResistance) == 1
                            || price.getLastPrice()
                            .compareTo(firstResistance) == -1) {

                        // Buy or sell BTCs
                        System.out.println("Buy is recommended, BTC price="
                                + btcPrice.getLastPrice());
                    }
                }
                Thread.sleep(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Predictor.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

}
