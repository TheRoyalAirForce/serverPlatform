package test;
 
import org.junit.After;
import org.junit.BeforeClass;
 
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

import app.model._MappingKit;
 
/**
 * JFinal的Model测试用例
 */
public class JFinalModelCase{
     
    protected static DruidPlugin dp;
    protected static ActiveRecordPlugin activeRecord;    //连接池插件
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	/**
    	 * Db和Propkit等引擎初始配置
    	 */
		Db.use();   
    	PropKit.use("app/application.properties");
        /**
         * 数据连接地址
         */
        final String URL = PropKit.get("jdbcUrl");
        /**
         * 数据库账号
         */
        final String USERNAME = PropKit.get("user"); 
        /**
         * 数据库密码
         */
        final String PASSWORD = PropKit.get("password").trim();      
        /**
         * 数据库驱动
         */
        final String DRIVER = "com.mysql.jdbc.Driver";  
        /**
         * 数据库类型（如mysql，oracle）
         */
        final String DATABASE_TYPE = "mysql";  
        /**
         * @throws java.lang.Exception
         */
        dp=new DruidPlugin(URL, USERNAME,PASSWORD,DRIVER);
         
        dp.addFilter(new StatFilter());
         
        dp.setInitialSize(3);
        dp.setMinIdle(2);
        dp.setMaxActive(5);
        dp.setMaxWait(60000);
        dp.setTimeBetweenEvictionRunsMillis(120000);
        dp.setMinEvictableIdleTimeMillis(120000);
         
        WallFilter wall = new WallFilter();
        wall.setDbType(DATABASE_TYPE);
        dp.addFilter(wall);
         
        dp.getDataSource();
        dp.start();
         
        activeRecord = new ActiveRecordPlugin(dp);
        activeRecord.setDialect(new MysqlDialect());
         
        //映射数据库的表和继承与model的实体
        //只有做完该映射后，才能进行junit测试
		// 所有对象-关系（ORM）映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(activeRecord);
        activeRecord.start();
    }
 
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        activeRecord.stop();
        dp.stop();
    }
 
}