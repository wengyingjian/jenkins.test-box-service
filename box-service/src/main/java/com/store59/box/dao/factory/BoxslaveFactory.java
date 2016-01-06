package com.store59.box.dao.factory;

import com.store59.box.dao.mapper.*;
import com.store59.box.model.OrderItem;
import com.store59.kylin.datasource.factory.SlaveDB;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BoxslaveFactory {
	@Autowired
	private SlaveDB slaveDB;

	@Bean
	public SqlSessionTemplate slaveSqlSession() {
		return slaveDB.getSqlSession();
	}

	@Bean
	public MapperFactoryBean<BoxMapper> slaveBoxMapper()
			throws Exception {
		MapperFactoryBean<BoxMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<BoxItemMapper> slaveBoxItemMapper() throws Exception {
		MapperFactoryBean<BoxItemMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxItemMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<BoxMessageMapper> slaveBoxMessageMapper() throws Exception {
		MapperFactoryBean<BoxMessageMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxMessageMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<BoxApplyMapper> slaveBoxApplyMapper() throws Exception {
		MapperFactoryBean<BoxApplyMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(BoxApplyMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<DormStockMapper> slaveDormStockMapper() throws Exception {
		MapperFactoryBean<DormStockMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(DormStockMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<PurchaseMapper> slavePurchaseMapper() throws Exception {
		MapperFactoryBean<PurchaseMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(PurchaseMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<OrderMapper> slaveOrderMapper() throws Exception {
		MapperFactoryBean<OrderMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(OrderMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<OrderItemMapper> slaveOrderItemMapper() throws Exception {
		MapperFactoryBean<OrderItemMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(OrderItemMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<DislikeRepoMapper> slaveDislikeRepoMapper() throws Exception {
		MapperFactoryBean<DislikeRepoMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(DislikeRepoMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}

	@Bean
	public MapperFactoryBean<DistributionRecordMapper> slaveDistributionRecordMapper() throws Exception {
		MapperFactoryBean<DistributionRecordMapper> mapperFactory = new MapperFactoryBean<>();
		mapperFactory.setMapperInterface(DistributionRecordMapper.class);
		mapperFactory.setSqlSessionTemplate(slaveSqlSession());
		return mapperFactory;
	}
}
