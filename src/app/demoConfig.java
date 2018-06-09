package app;

import java.util.HashMap;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;

import app.controller.courseController;
import app.controller.loginController;
import app.controller.mainController;
import app.model._MappingKit;
import app.routes.adminRoutes;

public class demoConfig extends JFinalConfig {
	@Override
	public void configConstant(Constants me) // 配置常量
	{
		Db.use();
		PropKit.use("app/application.properties");

		me.setDevMode(PropKit.getBoolean("devMode"));
		// me.setBaseDownloadPath(PropKit.get("apkPath")); //设置文件下载基础路径
		// Beetl在Jfinal3.0的集成
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config(); // 需要antlr4支持
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate; // 获得核心模板
		Map<String, Object> shared = new HashMap<String, Object>();
		shared.put("name", "lzy");
		gt.setSharedVars(shared);

	}

	@Override
	public void configRoute(Routes me) {
		// 设置全局视图层根目录
		me.setBaseViewPath("/WEB-INF/view");
		// 静态路由
		me.add(new adminRoutes());
	}

	@Override
	public void configEngine(Engine me) {

	}

	@Override
	public void configHandler(Handlers me) {
		// me.add(new ResourceHandler());
	}

	@Override
	public void configInterceptor(Interceptors me) // 全局拦截器
	{
		// me.add(new commonInterceptor());

		// me.addGlobalActionInterceptor(new commonInterceptor());

		// me.addGlobalServiceInterceptor(new commonInterceptor());
	}

	public static DruidPlugin createDruidPlugin() // 连接池插件
	{
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		// 添加连接池插件配置
		me.add(druidPlugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有对象-关系（ORM）映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
		// 配置数据库方言，默认是mysql
		arp.setDialect(new MysqlDialect()); // 支持mysql
		// 添加缓存插件
		me.add(new EhCachePlugin());
	}
}
