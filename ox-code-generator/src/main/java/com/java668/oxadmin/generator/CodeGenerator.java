package com.java668.oxadmin.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 代码生成器
 *
 * @author cxg
 * @date 2023-05-18 15:16
 **/
public class CodeGenerator implements ConstVal {

	private static Logger log = Logger.getLogger(CodeGenerator.class.getName());

	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		generateCode(mpg);
	}

	/**
	 * 配置文件路径
	 */
	public static String PROP_FILE_PATH = "";
	/**
	 * codeGen.author
	 * */
	private static String AUTHOR = "";
	/**
	 * codeGen.db.drivers
	 * */
	private static String DB_DRIVERS = "";
	/**
	 * codeGen.db.username
	 * */
	private static String DB_USERNAME = "";
	/**
	 * codeGen.db.pwd
	 * */
	private static String DB_PWD = "";
	/**
	 * codeGen.db.uri
	 * */
	private static String DB_URI = "";
	/**
	 * codeGen.code.output.dir
	 * */
	private static String OUTPUT_DIR = "";
	/**
	 * codeGen.code.SuperEntityCls
	 * */
	private static String SuperEntityCls = "";
	/**
	 * codeGen.code.SuperEntityColumns
	 * */
	private static String SuperEntityColumns = "";
	/**
	 * codeGen.code.SuperMapperCls
	 * */
	private static String SuperMapperCls = "";
	/**
	 * codeGen.code.SuperServiceCls
	 * */
	private static String SuperServiceCls = "";
	/**
	 * codeGen.code.SuperServiceImplCls
	 * */
	private static String SuperServiceImplCls = "";
	/**
	 * codeGen.code.SuperControllerCls
	 * */
	private static String SuperControllerCls = "";
	/**
	 * codeGen.mvn.groupId
	 * */
	private static String GROUPID = "";
	/**
	 * codeGen.code.module.name
	 * */
	private static String MODULE_NAME = "";
	/**
	 * codeGen.code.table.name
	 * */
	private static String TABLE_NAME = "";
	/**
	 * codeGen.code.table.prefix
	 * */
	private static String TBLPREFIX = "";

	static {
		try {
			init();
		} catch (Exception ex) {
			log.warning("CodeGenerator init failed.. please check if codeGenConf.properties is exists");
		}
	}

	/**
	 * 初始化CodeGenerator配置项<br>
	 * 选择codeGenConf.properties文件配置方法<br>
	 */
	public static void init() throws Exception {
		// 查找是否存在codeGenConf.properties文件, 默认读取编译后的classpath:下面的配置
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = null;
		resource = resourceLoader.getResource(isEmpty(PROP_FILE_PATH)?"classpath:codeGenConf.properties":PROP_FILE_PATH);
		if (resource.exists()) {
			// 使用配置文件初始化
			Properties props = new Properties();
			try {
				props.load(resource.getInputStream());
			} catch (IOException e) {
				throw new Exception("read codeGenConf.properties error.");
			}

			// 数据库驱动
			AUTHOR = props.getProperty("codeGen.Author");
			DB_DRIVERS = props.getProperty("codeGen.db.drivers");
			DB_USERNAME = props.getProperty("codeGen.db.username");
			DB_PWD = props.getProperty("codeGen.db.pwd");
			DB_URI = props.getProperty("codeGen.db.uri");
			OUTPUT_DIR = props.getProperty("codeGen.code.output.dir");
			SuperEntityCls = props.getProperty("codeGen.code.SuperEntityCls");
			SuperEntityColumns = props.getProperty("codeGen.code.SuperEntityColumns");
			SuperMapperCls = props.getProperty("codeGen.code.SuperMapperCls");
			SuperServiceCls = props.getProperty("codeGen.code.SuperServiceCls");
			SuperServiceImplCls = props.getProperty("codeGen.code.SuperServiceImplCls");
			SuperControllerCls = props.getProperty("codeGen.code.SuperControllerCls");
			GROUPID = props.getProperty("codeGen.mvn.groupId");
			MODULE_NAME = props.getProperty("codeGen.code.module.name");
			TABLE_NAME = props.getProperty("codeGen.code.table.name");
			TBLPREFIX = props.getProperty("codeGen.code.table.prefix");
		} else {
			throw new Exception("cannot find the codeGenConf.properties of the classpath.using default setting");
		}
	}

	public static boolean isEmpty(CharSequence str) {
		return str == null || str.length() == 0;
	}

	public static void generateCode(AutoGenerator mpg){
		// 选择 freemarker 引擎，默认 Veloctiy
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(OUTPUT_DIR);

		gc.setSwagger2(false);
		gc.setFileOverride(true);
		// 不需要ActiveRecord特性的请改为false
		gc.setActiveRecord(true);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(false);
		// .setKotlin(true) 是否生成 kotlin 代码
		gc.setAuthor(AUTHOR);

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName(DB_DRIVERS);
		dsc.setUsername(DB_USERNAME);
		dsc.setPassword(DB_PWD);
		dsc.setUrl(DB_URI);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();

		strategy.setEntityLombokModel(true);
		// 此处可以修改为您的表前缀
        if (!isEmpty(TBLPREFIX)) {
            strategy.setTablePrefix(TBLPREFIX);
        }
		// 表名生成策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 需要生成的表,如果不配置，默认所有
		strategy.setInclude(TABLE_NAME.split(","));

		//是否启用TableField注解(true为不添加注解)
		strategy.entityTableFieldAnnotationEnable(true);

		strategy.setRestControllerStyle(true);
		// 排除生成的表
		// strategy.setExclude(new String[]{"test"});
		// 自定义实体父类
		strategy.setSuperEntityClass(SuperEntityCls);
		// 自定义实体，公共字段
		strategy.setSuperEntityColumns(SuperEntityColumns.split(","));
		// 自定义 mapper 父类
		strategy.setSuperMapperClass(SuperMapperCls);
		// 自定义 service 父类
		strategy.setSuperServiceClass(SuperServiceCls);
		// 自定义 service 实现类父类
		strategy.setSuperServiceImplClass(SuperServiceImplCls);
		// 自定义 controller 父类
		strategy.setSuperControllerClass(SuperControllerCls);
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuilderModel(true);
		mpg.setStrategy(strategy);

		// 包配置(包路径配置)
		PackageConfig pc = new PackageConfig();


		if (!isEmpty(MODULE_NAME)) {
			pc.setParent(GROUPID + StringPool.DOT + "modules" + StringPool.DOT + MODULE_NAME);
		}
		else{
			pc.setParent(GROUPID + StringPool.DOT + "modules");
		}
		//模块名称，单独生成模块时使用！
		//pc.setModuleName("log");
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		// 自定义模板配置
		TemplateConfig tc = new TemplateConfig();
		tc.setController("/templates/controller.java");
		tc.setEntity("/templates/entity.java");
		tc.setMapper("/templates/mapper.java");
		tc.setXml("/templates/mapper.xml");
		tc.setService("/templates/service.java");
		tc.setServiceImpl("/templates/serviceImpl.java");
		// 如上任何一个模块如果设置 空 OR Null 将不生成该模块。

		mpg.setTemplate(tc);

		ConfigBuilder config = mpg.getConfig();
		if (null == config) {
			config = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());
		}


		//设置自定义输出目录路径
		String sourceRootDir = OUTPUT_DIR + File.separator+"java"+ File.separator + pc.getParent().replaceAll("\\.", "\\" + File.separator);
		System.out.println("***********sourceRootDir:{} "+sourceRootDir);
		config.getPathInfo().put(ConstVal.ENTITY_PATH, sourceRootDir+File.separator+ConstVal.ENTITY.toLowerCase());
		config.getPathInfo().put(ConstVal.CONTROLLER_PATH, sourceRootDir+File.separator+ConstVal.CONTROLLER.toLowerCase());
		config.getPathInfo().put(ConstVal.SERVICE_PATH, sourceRootDir+File.separator+ConstVal.SERVICE.toLowerCase());
		config.getPathInfo().put(ConstVal.SERVICE_IMPL_PATH, sourceRootDir+File.separator+ConstVal.SERVICE.toLowerCase()+File.separator+"impl");
		config.getPathInfo().put(ConstVal.MAPPER_PATH, sourceRootDir+File.separator+ConstVal.MAPPER.toLowerCase());

		config.getPathInfo().put(ConstVal.XML_PATH, OUTPUT_DIR + File.separator+"resources"+ File.separator + "mapper" + File.separator + MODULE_NAME);
		mpg.setConfig(config);

		// 执行生成
		mpg.execute();
	}

}
