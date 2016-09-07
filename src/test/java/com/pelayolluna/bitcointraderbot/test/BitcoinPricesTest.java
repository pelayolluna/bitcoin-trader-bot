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
package com.pelayolluna.bitcointraderbot.test;

import com.pelayolluna.bitcointraderbot.datamanagers.BitstampData;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public class BitcoinPricesTest {

    public static void main(String[] args) {
        
//        OrderBook ob = new OrderBook();
//        Indicable ip = new PivotsIndicatorImpl();
        
//        System.out.println(ip.computeAll());

        System.out.println(BitstampData.getConversionRate());
        System.out.println(BitstampData.getTickerData());
        System.out.println(BitstampData.getOrderBookData());
//        Thread thread = new Thread(new Predictor());
//        thread.start();
    }

}
