package cn.loveapple.service.cool.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slim3.datastore.Datastore;

import cn.loveapple.service.cool.meta.SampleModelMeta;
import cn.loveapple.service.cool.model.SampleModel;
import cn.loveapple.service.cool.service.SampleService;
import cn.loveapple.service.type.ServiceComp;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * ロジック処理を行うサンプルのサービスインターフェイスの実装クラス(coolクラス)
 * 
 * @author hao_shunri
 *
 */
@ServiceComp
public class SampleServiceImpl extends BaseServiceImpl implements SampleService {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(SampleServiceImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SampleModel newAndPut(String name,Float latitude, Float longitude, Double accuracy, String detail) {
		
		SampleModel model = queryByGeoPt(latitude.floatValue(), longitude.floatValue(), name);
		if(model != null){
			if(log.isDebugEnabled()){
				log.debug("location has ben added. ");
			}
			model.setUpdateDate(new Date());
			return putData(model);
		}

		if(log.isDebugEnabled()){
			log.debug("add location. ");
		}
		
		model = new SampleModel();
		
		GeoPt location = new GeoPt(latitude.floatValue(), longitude.floatValue());
		model.setName(name);
		model.setDetail(detail);
		model.setAccuracy(accuracy);
		model.setLocation(location);
		model.setInsertDate(new Date());
		
		return putData(model);
	}
	
	/**
	 * Bigtableにデータを送信する
	 * 
	 * @param model
	 * @return
	 */
	private SampleModel putData(SampleModel model){
		if(log.isDebugEnabled()){
			log.debug("put samplemodel: [" + model + "]");
		}
		Key key = Datastore.put(model);
		model.setKey(key);
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SampleModel> queryAll() {

		if(log.isDebugEnabled()){
			log.debug("queryAll. ");
		}
		
		return Datastore.query(SampleModelMeta.get()).asList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SampleModel> queryAllByName(String name){
		if(name == null){
			throw new IllegalArgumentException("name is empty.");
		}
		if(log.isDebugEnabled()){
			log.debug("queryAllByName. name:[" + name + "]");
		}
		SampleModelMeta meta = SampleModelMeta.get();
		return Datastore.query(meta).filter(meta.name.equal(name)).asList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SampleModel queryByGeoPt(float latitude, float longitude, String name){
		if(log.isDebugEnabled()){
			log.debug("queryByGeoPt. name:[" + name + "]");
		}
		SampleModelMeta meta = SampleModelMeta.get();
		return Datastore.query(SampleModel.class)
			.filter(meta.location.equal(new GeoPt(latitude, longitude)),
					meta.name.equal(name)).asSingle();
	}

	@Override
	public SampleModel queryByKey(long keyId) {
		Key key = KeyFactory.createKey("SampleModel", keyId);
		SampleModelMeta meta = SampleModelMeta.get();
		return Datastore.query(SampleModel.class)
			.filter(meta.key.equal(key)).asSingle();
	}

}
