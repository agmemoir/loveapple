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
package cn.loveapple.service.cool.model;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@SuppressWarnings("serial")
@Model(kind=SITE_CONTENTS_MODEL)
public class SiteContentsModel implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * タイトル
	 */
	@Attribute(unindexed=false)
	private String title;
	

	/**
	 * ヘッダー
	 */
	@Attribute
	private String header;

	/**
	 * 言語
	 */
	@Attribute(unindexed = false)
	private String language;

	/**
	 * 内容
	 */
	@Attribute
	private String body;

	/**
	 * コンテンツタグ名
	 */
	@Attribute(lob = true)
	private String[] tags;
	
	/**
	 * カテゴリID
	 */
	@Attribute(unindexed = false)
	private Long categoryId;
	
	/**
	 * カテゴリ名
	 */
	@Attribute
	private String categoryName;
	
	/**
	 * 公開日時
	 */
	@Attribute
	private Date publicDate;
	
	/**
	 * 作成者ID
	 */
	@Attribute(unindexed = false)
	private Key creatorId;

	/**
	 * 作成者名(ペーンネーム)
	 */
	@Attribute
	private String creatorName;
	
	/**
	 * 作成者メール
	 */
	@Attribute
	private String creatorMail;

	/**
	 * キーを取得します。
	 * @return キー
	 */
	public Key getKey() {
	    return key;
	}

	/**
	 * キーを設定します。
	 * @param key キー
	 */
	public void setKey(Key key) {
	    this.key = key;
	}

	/**
	 * タイトルを取得します。
	 * @return タイトル
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * タイトルを設定します。
	 * @param title タイトル
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * ヘッダーを取得します。
	 * @return ヘッダー
	 */
	public String getHeader() {
	    return header;
	}

	/**
	 * ヘッダーを設定します。
	 * @param header ヘッダー
	 */
	public void setHeader(String header) {
	    this.header = header;
	}

	/**
	 * 言語を取得します。
	 * @return 言語
	 */
	public String getLanguage() {
	    return language;
	}

	/**
	 * 言語を設定します。
	 * @param language 言語
	 */
	public void setLanguage(String language) {
	    this.language = language;
	}

	/**
	 * 内容を取得します。
	 * @return 内容
	 */
	public String getBody() {
	    return body;
	}

	/**
	 * 内容を設定します。
	 * @param body 内容
	 */
	public void setBody(String body) {
	    this.body = body;
	}

	/**
	 * コンテンツタグ名を取得します。
	 * @return コンテンツタグ名
	 */
	public String[] getTags() {
	    return tags;
	}

	/**
	 * コンテンツタグ名を設定します。
	 * @param tags コンテンツタグ名
	 */
	public void setTags(String[] tags) {
	    this.tags = tags;
	}

	/**
	 * カテゴリIDを取得します。
	 * @return カテゴリID
	 */
	public Long getCategoryId() {
	    return categoryId;
	}

	/**
	 * カテゴリIDを設定します。
	 * @param categoryId カテゴリID
	 */
	public void setCategoryId(Long categoryId) {
	    this.categoryId = categoryId;
	}

	/**
	 * 公開日時を取得します。
	 * @return 公開日時
	 */
	public Date getPublicDate() {
	    return publicDate;
	}

	/**
	 * 公開日時を設定します。
	 * @param publicDate 公開日時
	 */
	public void setPublicDate(Date publicDate) {
	    this.publicDate = publicDate;
	}

	/**
	 * カテゴリ名を取得します。
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
	    return categoryName;
	}

	/**
	 * カテゴリ名を設定します。
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
	    this.categoryName = categoryName;
	}

	/**
	 * 作成者IDを取得します。
	 * @return 作成者ID
	 */
	public Key getCreatorId() {
	    return creatorId;
	}

	/**
	 * 作成者IDを設定します。
	 * @param creatorId 作成者ID
	 */
	public void setCreatorId(Key creatorId) {
	    this.creatorId = creatorId;
	}

	/**
	 * 作成者名(ペーンネーム)を取得します。
	 * @return 作成者名(ペーンネーム)
	 */
	public String getCreatorName() {
	    return creatorName;
	}

	/**
	 * 作成者名(ペーンネーム)を設定します。
	 * @param creatorName 作成者名(ペーンネーム)
	 */
	public void setCreatorName(String creatorName) {
	    this.creatorName = creatorName;
	}

	/**
	 * 作成者メールを取得します。
	 * @return 作成者メール
	 */
	public String getCreatorMail() {
	    return creatorMail;
	}

	/**
	 * 作成者メールを設定します。
	 * @param creatorMail 作成者メール
	 */
	public void setCreatorMail(String creatorMail) {
	    this.creatorMail = creatorMail;
	}
}
