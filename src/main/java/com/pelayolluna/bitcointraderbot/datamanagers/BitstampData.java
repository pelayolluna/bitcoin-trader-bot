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
package com.pelayolluna.bitcointraderbot.datamanagers;

import com.pelayolluna.bitcointraderbot.beans.BtcPrice;
import com.pelayolluna.bitcointraderbot.beans.OrderBook;
import com.pelayolluna.bitcointraderbot.utils.PairMarketVolume;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.util.Pair;

import org.json.JSONObject;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public final class BitstampData {

    /**
     * Configuration parameters.
     */
    protected static final ResourceBundle RB_CONFIG
            = ResourceBundle.getBundle("config");
    /**
     * Literals parameters.
     */
    protected static final ResourceBundle RB_LITERALS
            = ResourceBundle.getBundle("literals");
    /**
     * Delay time constant.
     */
    private static final int DELAY_TIME = 60 * 1000;
    /**
     * Array size constant.
     */
    private static final int SIZE_TMP_ARRAY = 2;
    /**
     * Regular expression 1.
     */
    private static final String REGEX_1 = ",";
    /**
     * Regular expression 2.
     */
    private static final String REGEX_2 = "\\[\"|\"|\\]|\"\\]";

    /**
     * Utility class private constructor.
     */
    private BitstampData() {
    }

    /**
     * @return Ticker.
     */
    public static BtcPrice getTickerData() {

        final BtcPrice btcPrice = new BtcPrice();

        final JSONObject data = new JSONObject(callURL(
                RB_CONFIG.getString("tickerBtcUsd")));
        btcPrice.setLastPrice(new BigDecimal(
                data.getString(RB_CONFIG.getString("last"))));
        btcPrice.setHighPrice(new BigDecimal(
                data.getString(RB_CONFIG.getString("high"))));
        btcPrice.setLowPrice(new BigDecimal(
                data.getString(RB_CONFIG.getString("low"))));
        btcPrice.setBidPrice(new BigDecimal(
                data.getString(RB_CONFIG.getString("bid"))));
        btcPrice.setAskPrice(new BigDecimal(
                data.getString(RB_CONFIG.getString("ask"))));
        btcPrice.setOpenPrice(new BigDecimal(
                data.getString(RB_CONFIG.getString("open"))));

        return btcPrice;
    }

    /**
     * @return OrderBook data.
     */
    public static OrderBook getOrderBookData() {

        OrderBook orderBook = new OrderBook();

        final JSONObject data = new JSONObject(callURL(
                RB_CONFIG.getString("orderBookBtcUsd")));

        orderBook = initializeOrderBook(orderBook, data, "asks");
        orderBook = initializeOrderBook(orderBook, data, "bids");

        return orderBook;
    }

    /**
     * @return Conversion rate.
     */
    public static float getConversionRate() {

        JSONObject data = new JSONObject(callURL(
                RB_CONFIG.getString("conversionRate")));

        return Float.parseFloat(data.getString("buy"));
    }

    /**
     * @param orderBook
     * @param data
     * @param type
     * @return OrderBook initialized.
     */
    private static OrderBook initializeOrderBook(
            final OrderBook orderBook, JSONObject data, String type) {

        final List<BigDecimal> tmpList1 = new ArrayList<>();
        final List<BigDecimal> tmpList2 = new ArrayList<>();
        final Pair<ArrayList<BigDecimal>, ArrayList<BigDecimal>> tmpAsk
                = new Pair<>(new ArrayList<BigDecimal>(),
                        new ArrayList<BigDecimal>());
        final Pair<ArrayList<BigDecimal>, ArrayList<BigDecimal>> tmpBid
                = new Pair<>(new ArrayList<BigDecimal>(),
                        new ArrayList<BigDecimal>());

        String[] tmpArray = new String[SIZE_TMP_ARRAY];

        for (int i = 0; i < data.getJSONArray(type).length(); i++) {
            tmpArray = data.getJSONArray(type).get(i).toString().split(REGEX_1);
            tmpList1.add(new BigDecimal(tmpArray[0].replaceAll(REGEX_2, "")));
            tmpList2.add(new BigDecimal(tmpArray[1].replaceAll(REGEX_2, "")));
        }

        if (RB_CONFIG.getString("asks").equals(type)) {
            orderBook.setAskPair(new PairMarketVolume(tmpList1, tmpList2));
        }
        if (RB_CONFIG.getString("bids").equals(type)) {
            orderBook.setBidPair(new PairMarketVolume(tmpList1, tmpList2));
        }

        return orderBook;
    }

    /**
     * @return String url.
     *
     * @param myURL
     */
    private static String callURL(final String myURL) {
        System.out.println(RB_LITERALS.getString("urlCheck") + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(DELAY_TIME);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(
                    RB_LITERALS.getString("urlException") + myURL, e);
        }

        return sb.toString();
    }

}
