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
package com.pelayolluna.bitcointraderbot.utils;

import java.util.ResourceBundle;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 * @param <M>
 * @param <V>
 */
public final class PairMarketVolume<M, V> {
    
    private static final ResourceBundle rbLiterals = ResourceBundle.getBundle("literals");
    
    private M market;
    private V volume;

    public PairMarketVolume(M market, V volume) {
        this.market = market;
        this.volume = volume;
    }
    
    /**
     * @return the market
     */
    public M getMarket() {
        return market;
    }

    /**
     * @param market the market to set
     */
    public void setMarket(M market) {
        this.market = market;
    }

    /**
     * @return the volume
     */
    public V getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(V volume) {
        this.volume = volume;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        hashCode = 7 * 21 + (this.getMarket() != null ? this.getMarket().hashCode() : 0);
        hashCode = 7 * 21 + (this.getVolume() != null ? this.getVolume().hashCode() : 0);
        return hashCode;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PairMarketVolume)) {
            return false;
        }
        PairMarketVolume obj2 = (PairMarketVolume) obj;
        return this.market.equals(obj2.getMarket()) && this.volume.equals(obj2.getVolume());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getMarket());
        sb.append("\n");
        sb.append(this.getVolume());
        
        return sb.toString();
    }
}
