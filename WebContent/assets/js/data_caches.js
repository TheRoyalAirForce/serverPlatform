/**
 * 全局变量保存
 */
var server_url = "testserver";
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