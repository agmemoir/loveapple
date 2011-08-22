/*
+* ------------------------------------------------------------------------
 * Current-Module: $RCSfile:$
 * Release-Date: $Date$
 * Release-Version: $Revision$
 * First-Created: 2011/08/22 AW hcl
 * Modifier: $Author$
%* ------------------------------------------------------------------------
 * Module-Description:
 *
-* ------------------------------------------------------------------------
 * Change-Log:
$* ------------------------------------------------------------------------*/
package cn.loveapple.client.android.shiba.database.impl;

import static cn.loveapple.client.android.Constant.*;
import static cn.loveapple.client.android.shiba.database.entity.UrlHistoryEntity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import cn.loveapple.client.android.LoveappleShibaDatabaseOpenHelper;
import cn.loveapple.client.android.shiba.database.BaseDao;
import cn.loveapple.client.android.shiba.database.UrlHistoryDao;
import cn.loveapple.client.android.shiba.database.entity.UrlHistoryEntity;
import cn.loveapple.client.android.util.StringUtils;

/**
 * 
 * @author hcl
 * @since 2011/08/22
 * @version $Revision$
 */
public class UrlHistoryDaoImpl extends BaseDao implements UrlHistoryDao {

	public UrlHistoryDaoImpl(LoveappleShibaDatabaseOpenHelper helper) {
		super(helper);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UrlHistoryEntity> getUrlHistory(String url, String title,
			int limit) {
		Cursor cursor = null;
		List<UrlHistoryEntity> result = null;
		StringBuilder condition = new StringBuilder();
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotEmpty(url)){
			condition.append(COLUMN_URL);
			condition.append(" LIKE '?%' ");
			params.add(url);
		}
		if(StringUtils.isNotEmpty(title)){
			if(StringUtils.isNotEmpty(url)){
				condition.append(" AND ");
			}
			condition.append(COLUMN_TITLE);
			condition.append(" LIKE '?%' ");
			params.add(title);
		}
		try{
			writableDb = getWritableDb();
			cursor = writableDb.query(
					TABLE_NAME, 
					null, 
					condition.toString() + " ORDER BY " + COLUMN_TIMESTAMP + " DESC" ,
					params.toArray(new String[]{}), null, null, null);
			result = new ArrayList<UrlHistoryEntity>();
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				result.add(getUrlHistoryEntity(cursor));
				cursor.moveToNext();
			}
		}finally{
			if(cursor != null){
				cursor.close();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public UrlHistoryEntity getUrlHistory(String url, String title) {
		List<UrlHistoryEntity> result = getUrlHistory(url, title, 1);
		if(result != null && 0 < result.size()){
			return result.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UrlHistoryEntity save(UrlHistoryEntity entity) {
		if(entity == null){
			throw new IllegalArgumentException("history entity is null.");
		}
		if(StringUtils.isEmpty(entity.getUrl())){
			throw new IllegalArgumentException("history url is null.");
		}

		ContentValues values = new ContentValues();
		values.put(COLUMN_URL, entity.getUrl());
		if(StringUtils.isNotEmpty(entity.getTitle())){
			values.put(COLUMN_TITLE, entity.getTitle());
		}
		if(entity.getTimestamp() != null){
			values.put(COLUMN_TIMESTAMP, entity.getTimestamp().getTime());
		}
		
		UrlHistoryEntity result = null;
		
		try{
			writableDb = getWritableDb();
			
			int colNum = writableDb.update(TABLE_NAME, values, COLUMN_URL + "=?", new String[]{entity.getUrl()});
			if(colNum < 1) {
				writableDb.insert(TABLE_NAME, null, values);
				Log.i(LOG_TAG, "update : " + ToStringBuilder.reflectionToString(values));
			}
			result = getUrlHistory(entity.getUrl(), null);
		}finally{
			//writableDb.close();
		}
		return result;
	}
	
	/**
	 * {@linkplain Cursor カーソル}から{@linkplain UrlHistoryEntity URL履歴}に変換する
	 * 
	 * @param cursor
	 * @return
	 */
	private UrlHistoryEntity getUrlHistoryEntity(Cursor cursor) {
		if(cursor == null || cursor.getCount() < 1){
			return null;
		}
		UrlHistoryEntity entity = new UrlHistoryEntity();
		entity.setUrl(cursor.getString(0));
		entity.setTitle(cursor.getString(1));
		entity.setTimestamp(new Date(cursor.getLong(2)));
		
		return entity;
	}

}
