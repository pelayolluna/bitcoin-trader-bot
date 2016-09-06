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

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public class BitstampData {

    protected static final ResourceBundle rbConfig = ResourceBundle.getBundle("config");
    protected static final ResourceBundle rbLiterals = ResourceBundle.getBundle("literals");
    private static final int SIZE_TMP_ARRAY = 2;
    private static final String REGEX_1 = ",";
    private static final String REGEX_2 = "\\[\"|\"|\\]|\"\\]";

    public static BtcPrice getTickerData() {

        final BtcPrice btcPrice = new BtcPrice();
        JSONObject data = null;

        try {
            data = new JSONObject(callURL(rbConfig.getString("tickerBtcUsd")));
            btcPrice.setLastPrice(new BigDecimal(data.getString(rbConfig.getString("last"))));
            btcPrice.setHighPrice(new BigDecimal(data.getString(rbConfig.getString("high"))));
            btcPrice.setLowPrice(new BigDecimal(data.getString(rbConfig.getString("low"))));
            btcPrice.setBidPrice(new BigDecimal(data.getString(rbConfig.getString("bid"))));
            btcPrice.setAskPrice(new BigDecimal(data.getString(rbConfig.getString("ask"))));
            btcPrice.setOpenPrice(new BigDecimal(data.getString(rbConfig.getString("open"))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return btcPrice;
    }

    public static OrderBook getOrderBookData() {

        OrderBook orderBook = new OrderBook();
        JSONObject data = null;

        try {
            data = new JSONObject(callURL(rbConfig.getString("orderBookBtcUsd")));

            orderBook = initializeOrderBook(orderBook, data, "asks");
            orderBook = initializeOrderBook(orderBook, data, "bids");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderBook;
    }

    public static float getConversionRate() {

        JSONObject data = null;

        try {
            data = new JSONObject(callURL(rbConfig.getString("conversionRate")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Float.parseFloat(data.getString("buy"));
    }

    private static OrderBook initializeOrderBook(OrderBook orderBook, JSONObject data, String type) {

        final List<BigDecimal> tmpList1 = new ArrayList<>();
        final List<BigDecimal> tmpList2 = new ArrayList<>();
        final Pair<ArrayList<BigDecimal>, ArrayList<BigDecimal>> tmpAsk
                = new Pair<>(new ArrayList<BigDecimal>(), new ArrayList<BigDecimal>());
        final Pair<ArrayList<BigDecimal>, ArrayList<BigDecimal>> tmpBid
                = new Pair<>(new ArrayList<BigDecimal>(), new ArrayList<BigDecimal>());

        String[] tmpArray = new String[SIZE_TMP_ARRAY];

        for (int i = 0; i < data.getJSONArray(type).length(); i++) {
            tmpArray = data.getJSONArray(type).get(i).toString().split(REGEX_1);
            tmpList1.add(new BigDecimal(tmpArray[0].replaceAll(REGEX_2, "")));
            tmpList2.add(new BigDecimal(tmpArray[1].replaceAll(REGEX_2, "")));
        }

        if (rbConfig.getString("asks").equals(type)) {
            orderBook.setAskPair(new PairMarketVolume(tmpList1, tmpList2));
        }
        if (rbConfig.getString("bids").equals(type)) {
            orderBook.setBidPair(new PairMarketVolume(tmpList1, tmpList2));
        }

        return orderBook;
    }

    private static String callURL(String myURL) {
        System.out.println(rbLiterals.getString("urlCheck") + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
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
            throw new RuntimeException(rbLiterals.getString("urlException") + myURL, e);
        }

        return sb.toString();
    }

}
