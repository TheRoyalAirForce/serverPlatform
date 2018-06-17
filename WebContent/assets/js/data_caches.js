/**
 * server_url 服务器主地址配置
 * 桌面版使用远程地址 http://lzy25.s1.natapp.cc/attendentServer/
 * Web端使用本地服务器地址： http://localhost:8080/attendentServer/
 */
//var server_url = "http://localhost:8080/attendentServer/";
var server_url = "";   //相对地址
/**
 * 全局变量保存
 */
var Cache = {
	type : 'local',
	setType : function() {
		if (this.type == 'session') {
			return window.sessionStorage;
		}
		if (this.type == 'local') {
			return window.localStorage;
		}
		console.log('类型错误！');
	},
	set : function($key, $value) {
		this.setType().setItem($key, $value);
	},
	get : function($key) {
		return this.setType().getItem($key)
	}
}