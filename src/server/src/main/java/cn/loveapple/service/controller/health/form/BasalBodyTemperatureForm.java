/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
 *
 * All copyright notices regarding loveapple and loveapple CoreLib
 * MUST remain intact in the scripts, documents and source code.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at loveapple
 *
 * @author: loveapple
 */
package cn.loveapple.service.controller.health.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

import cn.loveapple.service.Constant;
import cn.loveapple.service.util.DateUtil;

/**
 * 
 * TODO
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BasalBodyTemperatureForm {
	
	/**
	 * おりもの量：多め
	 */
	public static final String LEUKORRHEA_MUCH_CODE = "3";
	/**
	 * おりもの量：少なめ
	 */
	public static final String LEUKORRHEA_LITTLE_CODE = "1";
	/**
	 * おりもの量：普通
	 */
	public static final String LEUKORRHEA_USUALLY_CODE = "2";

	/**
	 * 計測日<br />
	 * 更新時に日付を設定する<br>
	 * {@linkplain DateUtil#DATE_PTTERN_YYYYMMDD YYYYMMDD}の日付文字列
	 */
	@Size(max=8, min=8)
	private String measureDay;

	/**
	 * 性交フラグ
	 */
	@Size(max=1)
	@Pattern(regexp="(" + Constant.FLG_OFF + "|" + Constant.FLG_ON + ")")
	private String coitusFlg;
	
	/**
	 * 生理期間フラグ
	 */
	@Size(max=1)
	@Pattern(regexp="(" + Constant.FLG_OFF + "|" + Constant.FLG_ON + ")")
	private String menstruationFlg;
	
	/**
	 * 生理痛フラグ
	 */
	@Size(max=1)
	@Pattern(regexp="(" + Constant.FLG_OFF + "|" + Constant.FLG_ON + ")")
	private String dysmenorrheaFlg;
	
	/**
	 * おりもの
	 */
	@Size(max=1)
	@Pattern(
			regexp="(" + LEUKORRHEA_MUCH_CODE + "|" 
			+ LEUKORRHEA_LITTLE_CODE + "|" 
			+ LEUKORRHEA_USUALLY_CODE +  ")")
	private String leukorrhea;
	
	/**
	 * 温度(°C)
	 */
	@NotNull
	@Max(43)
	@Min(35)
	private Double temperature;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 計測日<br />を取得します。
	 * @return 計測日<br />
	 */
	public String getMeasureDay() {
	    return measureDay;
	}

	/**
	 * 計測日<br />を設定します。
	 * @param measureDay 計測日<br />
	 */
	public void setMeasureDay(String measureDay) {
	    this.measureDay = measureDay;
	}

	/**
	 * 性交フラグを取得します。
	 * @return 性交フラグ
	 */
	public String getCoitusFlg() {
	    return coitusFlg;
	}

	/**
	 * 性交フラグを設定します。
	 * @param coitusFlg 性交フラグ
	 */
	public void setCoitusFlg(String coitusFlg) {
	    this.coitusFlg = coitusFlg;
	}

	/**
	 * 生理期間フラグを取得します。
	 * @return 生理期間フラグ
	 */
	public String getMenstruationFlg() {
	    return menstruationFlg;
	}

	/**
	 * 生理期間フラグを設定します。
	 * @param menstruationFlg 生理期間フラグ
	 */
	public void setMenstruationFlg(String menstruationFlg) {
	    this.menstruationFlg = menstruationFlg;
	}


	/**
	 * 生理痛フラグを取得します。
	 * @return 生理痛フラグ
	 */
	public String getDysmenorrheaFlg() {
	    return dysmenorrheaFlg;
	}

	/**
	 * 生理痛フラグを設定します。
	 * @param dysmenorrheaFlg 生理痛フラグ
	 */
	public void setDysmenorrheaFlg(String dysmenorrheaFlg) {
	    this.dysmenorrheaFlg = dysmenorrheaFlg;
	}

	/**
	 * おりものを取得します。
	 * @return おりもの
	 */
	public String getLeukorrhea() {
	    return leukorrhea;
	}

	/**
	 * おりものを設定します。
	 * @param leukorrhea おりもの
	 */
	public void setLeukorrhea(String leukorrhea) {
	    this.leukorrhea = leukorrhea;
	}

	/**
	 * 温度(°C)を取得します。
	 * @return 温度(°C)
	 */
	public Double getTemperature() {
	    return temperature;
	}

	/**
	 * 温度(°C)を設定します。
	 * @param temperature 温度(°C)
	 */
	public void setTemperature(Double temperature) {
	    this.temperature = temperature;
	}
}