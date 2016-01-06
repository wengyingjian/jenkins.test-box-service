package com.store59.box.dao.factory;

import com.store59.box.dao.mapper.*;
import com.store59.box.model.Order;
import com.store59.kylin.datasource.factory.MasterDB;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BoxMasteFactory {
	@Autowired
	private MasterDB masterDB;

	@Bean
	public SqlSessionTemplate masterSqlSession() {
		return masterDB.getSqlSession();
	}

	@Bean
	public MapperFactoryBean<BoxMapper> masterBoxMapper()
			throws Exception {
		MapperFactoryBean<BoxMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<BoxItemMapper> masterBoxItemMapper() throws Exception {
		MapperFactoryBean<BoxItemMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxItemMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<BoxMessageMapper> masterBoxMessageMapper() throws Exception {
		MapperFactoryBean<BoxMessageMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxMessageMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<BoxApplyMapper> masterBoxApplyMapper() throws Exception {
		MapperFactoryBean<BoxApplyMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxApplyMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<DormStockMapper> masterDormStockMapper() throws Exception {
		MapperFactoryBean<DormStockMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(DormStockMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<PurchaseMapper> masterPurchaseMapper() throws Exception {
		MapperFactoryBean<PurchaseMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(PurchaseMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<OrderMapper> masterOrderMapper() throws Exception {
		MapperFactoryBean<OrderMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(OrderMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<OrderItemMapper> masterOrderItemMapper() throws Exception {
		MapperFactoryBean<OrderItemMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(OrderItemMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<DislikeRepoMapper> masterDislikeRepoMapper() throws Exception {
		MapperFactoryBean<DislikeRepoMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(DislikeRepoMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<DistributionRecordMapper> masterDistributionRecordMapper() throws Exception {
		MapperFactoryBean<DistributionRecordMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(DistributionRecordMapper.class);
		mapperFactory.setSqlSessionTemplate(masterSqlSession());
		return mapperFactory;
	}

}